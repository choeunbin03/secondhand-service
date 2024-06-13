<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FarmFarm</title>
</head>
<body>
    <div>
        <h2>회원 탈퇴 하시겠습니까?</h2>
        <p style="font-size: smaller;">회원 탈퇴시 작성한 게시글 및 개인 정보가 삭제됩니다.</p>
		<form action="/member/deleteMember" method="POST" style="display: inline;">
        	<button type="submit">예</button>
    	</form>        
    	<a href="/" style="display: inline-block;"><button>아니오</button></a>
    </div>
</body>
</html>