package com.secondhand.service;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.secondhand.dao.MemberDAO;
import com.secondhand.domain.LoginDTO;
import com.secondhand.domain.MemberDTO;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService implements Validator { // 로그인 확인용 필터

    private final MemberDAO memberDAO;


    public MemberDTO login(String loginId, String password) {
//        return memberDAO.findById(loginId);
        // ==> MemberDAO의 public Optional<MemberDTO> findByLoginId(String LoginId) 개발하면, 아래로 대체

//        return memberDAO.findById(loginId)
//                .filter(m -> m.getMbrPwd().equals(password))
//                .orElse(null);l
        MemberDTO loginMember = memberDAO.findByMbrId(loginId);
        if(loginMember == null){
            return null;
        }else{
            if(loginMember.getMbrPwd().equals(password)){
                return loginMember;
            }else{
                log.info("XXX");
                return null;
            }
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginDTO loginMem = (LoginDTO) target;
        if (!StringUtils.hasText(loginMem.getLoginId())) {
            log.info("111");
            errors.rejectValue("loginId", null, "아이디를 입력해주세요.");
        }

        // 비밀번호 입력 확인
        if (!StringUtils.hasText(loginMem.getPassword())) {
            log.info("222");
            errors.rejectValue("password", null, "비밀번호를 입력해주세요.");
        }
    }
}