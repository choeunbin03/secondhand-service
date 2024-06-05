package com.secondhand.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.secondhand.dao.MemberDAO;
import com.secondhand.domain.MemberDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, Validator { // 회원가입 확인용 필터

    private final MemberDAO memberDao;
    @Inject
    private S3Service s3Service;
    
//MemberService 메소드
    @Override
    public Set<String> isValidate(MemberDTO member, String mbrPwdConfirm) {

        Set<String> errorMsg = new HashSet<>(); // 에러 메시지 저장

        // 1) mbrId 제약 조건 ==> 6글자 이상 & 한글 X & 중복 X
        String mbrId = member.getMbrId();

        if (mbrId.length() < 6) { // 6글자 미만인 경우
            errorMsg.add("mbrIdError");
        } else if (isHangulContain(mbrId)) { // 한글 O인 경우
            errorMsg.add("mbrIdError");
        } else if (memberDao.findByMbrId(mbrId) != null) { // 중복된 mbrId가 있을 경우
            errorMsg.add("mbrIdError");
        }

        // 2) mbrPwd 제약 조건 ==> must 영어 + 숫자 + 특수기호 & 8글자 이상
        String mbrPwd = member.getMbrPwd();

        if (isHangulContain(mbrPwd)) {
            errorMsg.add("mbrPwdError");
        } else if (!isDigitContain(mbrPwd)) {
            errorMsg.add("mbrPwdError");
        } else if (!isSpecialSymbolContain(mbrPwd)) {
            errorMsg.add("mbrPwdError");
        } else if (mbrPwd.length() < 8) {
            errorMsg.add("mbrPwdError");
        }

        // 3) 비밀번호 확인 제약 조건 ==>
        if (!mbrPwdConfirm.equals(member.getMbrPwd())) { // 비밀번호 확인 제약 조건 통과 X
            errorMsg.add("mbrPwdConfirmError");
        }

        if (errorMsg.isEmpty()) {
            errorMsg.add("noError");
        }
        return errorMsg; // 모든 제약 조건을 통과
    }    

	@Override
	public void save(MemberDTO member) {		
		memberDao.save(member);
	}
	
	// 회원 탈퇴 메소드
	@Override
	public boolean delete(MemberDTO member) {
		return memberDao.delete(member);
	}

//Validator 메소드
    @Override
    public boolean supports(Class<?> clazz) {
        return MemberDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    private static boolean isHangulContain(String input) { // input에 한글이 있으면 true
        if (input != null && input.matches(".*[ㄱ-하-ㅣ가-힣]+.*")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isDigitContain(String input) { // input에 숫가가 있으면 true
        if (input != null && input.matches(".*\\d+.*")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSpecialSymbolContain(String input) { // input에 특수 문자가 있으면 true
        if (input != null && input.matches(".*[!@#$%^&*()-+=<>?/\\\\.,:;\"'{}\\[\\]~`|]+.*")) {
            return true;
        } else {
            return false;
        }
    }

  //최근본글 조회 관련 메소드
    @Override
    public List<String> getALLRecentView(String userId) {
    	 return memberDao.getALLRecentView(userId);
    }
//찜 관련 메소드
    
    @Override
    public List<String> getALLBMK(String userId) {
    	 return memberDao.getBMK(userId);
    }
    

    @Override
    public boolean isBMK(String userId, String bbsId) {
    	 return getALLBMK(userId).contains(bbsId);
    }
    
    @Override
    public void updateBMK(String userId, String bbsId) {
    	Map<String, Object> params = new HashMap<String, Object>();
		List<String> l =  new ArrayList<String>();
		l.addAll(getALLBMK(userId));
		
    	if(isBMK(userId,bbsId)){
    		l.remove(bbsId);
    	}
    	else {
       	 	l.add(bbsId);
    	}
    	params.put("NewBMK", String.join(" ",l).trim());
    	params.put("loginId", userId);
    	memberDao.updateBMK(params);
    }
    
//사용자 프로필 관련
    @Override
    public MemberDTO getUserProfile(String mbrId) throws Exception {
        return memberDao.getUserProfile(mbrId);
    }
    
    @Override
    public void updateProfile(MemberDTO member, MultipartFile profilePhoto) throws Exception {
        MemberDTO existingMember = memberDao.getUserProfile(member.getMbrId());
        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            // 기존 프로필 사진 삭제
            if (existingMember.getProfilePhotoUrl() != null && !existingMember.getProfilePhotoUrl().isEmpty()) {
                String existingFileName = existingMember.getProfilePhotoUrl().substring(existingMember.getProfilePhotoUrl().lastIndexOf("/") + 1);
                s3Service.delete(existingFileName);
            }

            // 새로운 프로필 사진 업로드
            String newFileName = member.getMbrId() + "_" + profilePhoto.getOriginalFilename();
            String newFileUrl = s3Service.upload(profilePhoto, newFileName);
            member.setProfilePhotoUrl(newFileUrl);
        }else {
            // 프로필 사진이 없는 경우 기존 프로필 사진 URL 유지
            member.setProfilePhotoUrl(existingMember.getProfilePhotoUrl());
        }

        memberDao.updateProfile(member);
    }
    
//최근 본 게시물 관련
    @Override
    public void updateRecentView(String userId, String bbsId) {
    	Map<String, Object> params = new HashMap<String, Object>();
		List<String> l =  new ArrayList<String>();
		l.addAll(memberDao.getRecentViewed(userId));
		l.remove(bbsId);
   	 	l.add(0,bbsId);
   	 	//10은 최근게시물 띄울 개수
   	 	while(l.size() > 10) {
   	 		l.remove(9);
   	 	}
    	params.put("NewRecentView", String.join(" ",l).trim());
    	params.put("loginId", userId);
		System.out.println(params);
    	
    	memberDao.updateRecentView(params);
    }



}