
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 가입</title>
    <style>
        /* CSS 스타일링은 선택사항입니다. */
        .register-container {
          max-width: 400px;
          margin: 0 auto;
          padding: 20px;
          border: 1px solid #ccc;
          border-radius: 5px;
          text-align: center;
        }
        .register-input {
          width: calc(100% - 20px);
          padding: 10px;
          margin-bottom: 10px;
          border: 1px solid #ccc;
          border-radius: 5px;
        }
        .register-btn {
          background-color: #007bff;
          color: white;
          padding: 10px 20px;
          border: none;
          border-radius: 5px;
          cursor: pointer;
          margin-bottom: 20px;
        }
        .error-message {
          color: red;
          margin-top: 5px; /* 오류 메시지 위쪽에 여백 추가 */
          text-align: left; /* 오류 메시지 텍스트를 왼쪽 정렬 */
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>회원 가입</h2>
    <form action="/member/signin" method="post">
        <input type="text" id="name" name="mbrNm" class="register-input" placeholder="이름" required>
        <br>
        <input type="text" id="username" name="mbrId" class="register-input" placeholder="사용자 아이디(6글자 이상으로 된 영어)" required>
        <br>
        <c:if test="${mbrIdErrorMessage eq 'mbrIdError'}">
            <a> 사용자 이름이 중복되었습니다.</a>
        </c:if>
        <c:if test="${not empty errors and errors.hasFieldErrors('mbrId')}">
            <div class="error-message">${errors.getFieldError('mbrId').defaultMessage}</div>
        </c:if>
        <br>
        <input type="password" id="password" name="mbrPwd" class="register-input" placeholder="비밀번호(8글자 이상이고 숫자와 특수 기호를 포함한 영어)" required>
        <br>
        <c:if test="${not empty errors and errors.hasFieldErrors('mbrPwd')}">
            <div class="error-message">${errors.getFieldError('mbrPwd').defaultMessage}</div>
        </c:if>
        <br>
        <input type="password" id="confirm_password" name="mbrPwdConfirm" class="register-input" placeholder="비밀번호 확인" required>
        <br>
        <c:if test="${not empty errors and errors.hasFieldErrors('mbrPwdConfirm')}">
            <div class="error-message">${errors.getFieldError('mbrPwdConfirm').defaultMessage}</div>
        </c:if>
        <br>
        <input type="email" id="email" name="email" class="register-input" placeholder="이메일" required>
        <br>
        <a href="/chooseNeighborhood"><input type="button" value="동네 입력" class="register-input" style="width: calc(100% - 20px);"></a>
        <br>
        <button type="submit" class="register-btn" style="width: calc(100% - 20px);">가입하기</button>
    </form>
</div>
</body>
</html>