
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<html lang="en" xmlns:c="http://java.sun.com/jsp/jstl/core">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>로그인</title>
  <style>
    .login-container {
      max-width: 300px;
      margin: 0 auto;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      text-align: center;
    }
    .login-input {
      width: calc(100% - 20px);
      padding: 10px;
      margin-bottom: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    .login-btn {
      background-color: #007bff;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      margin-bottom: 20px;
    }
    .register-link {
      color: #007bff;
      text-decoration: none;
    }
    .error-message {
      color: red;
      margin: 5px 0; /* Adjusted margin for better spacing */
    }
    .success-message {
      color: green;
      margin: 5px 0; /* Adjusted margin for better spacing */
    }
  </style>
</head>
<body>
<div class="login-container">
  <h2>로그인</h2>
  <c:if test="${errors.hasFieldErrors('loginFail')}">
    <div class="error-message">${errors.getFieldError('loginFail').defaultMessage}</div>
  </c:if>

  <form action="/member/login" method="post">
    <input type="text" id="loginId" name="loginId"
           class="login-input" placeholder="사용자 아이디"
           value="${loginId}" required> <!-- value 속성 추가 -->
    <c:if test="${not empty errors and errors.hasFieldErrors('loginId')}">
      <div class="error-message">${errors.getFieldError('loginId').defaultMessage}</div>
    </c:if>
    <br>
    <input type="password" id="password" name="password"
           class="login-input" placeholder="비밀번호"
           value="${password}" required> <!-- value 속성 추가 -->
    <c:if test="${not empty errors and errors.hasFieldErrors('password')}">
      <div class="error-message">${errors.getFieldError('password').defaultMessage}</div>
    </c:if>
    <br>
    <button type="submit" class="login-btn">로그인</button>
  </form>
  <c:if test="${not empty successMessage}">
    <div class="success-message">${successMessage}</div>
  </c:if>
  <p>계정이 없으신가요? <a href="/member/signin" class="register-link">가입하기</a></p>
</div>
</body>
</html>