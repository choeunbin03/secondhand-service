package com.secondhand.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.secondhand.dao.MemberDAOImpl;
import com.secondhand.domain.MemberDTO;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SigninService implements Validator { // 회원가입 확인용 필터

    private final MemberDAOImpl store;

    public Set<String> isValidate(MemberDTO member, String mbrPwdConfirm) {

        Set<String> errorMsg = new HashSet<>(); // 에러 메시지 저장

        // 1) mbrId 제약 조건 ==> 6글자 이상 & 한글 X & 중복 X
        String mbrId = member.getMbrId();

        if (mbrId.length() < 6) { // 6글자 미만인 경우
            errorMsg.add("mbrIdError");
        } else if (isHangulContain(mbrId)) { // 한글 O인 경우
            errorMsg.add("mbrIdError");
        } else if (store.findByMbrId(mbrId) != null) { // 중복된 mbrId가 있을 경우
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
}