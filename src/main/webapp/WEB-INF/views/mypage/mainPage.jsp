<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
    .container { width: 80%; margin: 20px auto; background: white; padding: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    .section { margin-bottom: 20px; }
    .section h2 { color: #333; padding: 10px; background-color: #efefef; border-radius: 5px; }
    .button { display: block; width: 20%; padding: 10px; margin-top: 5px; background-color: #007bff; color: white; text-decoration: none; border: none; border-radius: 5px; cursor: pointer; }
    .button:hover { background-color: #0056b3; }
    .button + .button { margin-top: 10px; }
    a { text-decoration: none; } /* Remove underline from links */
</style>
</head>
<body>
<div class="container">
    <h1>마이페이지</h1>
    <div class="section">
        <h2>내 정보 관리</h2>
        <a href="/myPage/editProfile" class="button">내정보수정</a>
        <a href="/member/deleteMember" class="button">회원탈퇴</a>
    </div>
    <div class="section">
        <h2>활동</h2>
        <a href="/myPage/recentViewed" class="button">최근 본 상품</a>
        <a href="/myPage/myStore" class="button">내 상점 관리</a>
        <a href="/myPage/reviews" class="button">후기</a>
        <a href="/myPage/favorites" class="button">찜</a>
    </div>
</div>
<script>
    // Here you can add any JavaScript needed for your page, like confirmations for account deletion.
</script>
</body>
</html>

