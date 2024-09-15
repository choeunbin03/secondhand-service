<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>FarmFarm</title>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

   <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<<<<<<< HEAD
   <link rel="stylesheet" href="${path}/resources/css/moduleStyle.css">
   <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">

   <style>
       body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
       .container { width: 80%; margin: 20px auto; background: white; padding: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
       .section { margin-bottom: 20px; }
       .section h2 { color: #333; padding: 10px; background-color: #efefef; border-radius: 5px; }
       .button { display: block; width: 20%; padding: 10px; margin-top: 5px; background-color: #ffb6c1; color: white; text-decoration: none; border: none; border-radius: 5px; cursor: pointer; }
       .button:hover { background-color: #ff8ca1; }
       .button + .button { margin-top: 10px; }
       a { text-decoration: none; }
       .profile-section { padding: 20px; border: 1px solid #ddd; border-radius: 5px; margin-bottom: 20px; background-color: #fff; }
       .profile-section h3 { margin-top: 0; }
       .profile-photo { width: 150px; height: 150px; border-radius: 50%; background-color: #ddd; display: inline-block; }
       .profile-photo img { width: 100%; height: 100%; border-radius: 50%; object-fit: cover; }
       .slide-menu .submenu{
         margin-top:7px;
         display:none;
      }
   </style>
</head>
<body>
   <%@ include file="../module/header.jsp" %>
      <div class="content">
         <div class="container">
             <h1>마이페이지</h1>
             <div class="profile-section">
                 <h3>상점 프로필 설정</h3>
                 <div class="profile-photo">
                     <img src="${member.profilePhotoUrl != null ? member.profilePhotoUrl : '/resources/images/default.jpg'}" alt="Profile Photo">
                 </div>
                 <form action="/myPage/updateProfile" method="post" enctype="multipart/form-data">
                     <input type="file" name="profilePhoto" accept="image/*"><br><br>
                     <textarea name="storeDescription" rows="4" cols="50" placeholder="상점 소개글을 입력하세요...">${member.storeDescription}</textarea><br><br>
                     <button type="submit" class="button">저장</button>
                 </form>
             </div>
             <div class="section">
                 <h2>내 정보 관리</h2>
=======
	<style>
	    body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
	    .container { width: 80%; margin: 20px auto; background: white; padding: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
	    .section { margin-bottom: 20px; }
	    .section h2 { color: #333; padding: 10px; background-color: #efefef; border-radius: 5px; }
	    .button { display: block; width: 20%; padding: 10px; margin-top: 5px; background-color: #ffb6c1; color: white; text-decoration: none; border: none; border-radius: 5px; cursor: pointer; }
	    .button:hover { background-color: #ff8ca1; }
	    .button + .button { margin-top: 10px; }
	    a { text-decoration: none; }
	    .profile-section { padding: 20px; border: 1px solid #ddd; border-radius: 5px; margin-bottom: 20px; background-color: #fff; }
	    .profile-section h3 { margin-top: 0; }
	    .profile-photo { width: 150px; height: 150px; border-radius: 50%; background-color: #ddd; display: inline-block; }
	    .profile-photo img { width: 100%; height: 100%; border-radius: 50%; object-fit: cover; }
	    .slide-menu .submenu{
			margin-top:7px;
			display:none;
		}
	</style>
</head>
<body>
	<%@ include file="../module/header.jsp" %>
		<div class="content">
			<div class="container">
			    <h1>마이페이지</h1>
			    <div class="profile-section">
			        <h3>상점 프로필 설정</h3>
			        <div class="profile-photo">
			            <img src="${member.profilePhotoUrl != null ? member.profilePhotoUrl : '/resources/images/default.jpg'}" alt="Profile Photo">
			        </div>
			        <form action="/myPage/updateProfile" method="post" enctype="multipart/form-data">
			            <input type="file" name="profilePhoto" accept="image/*"><br><br>
			            <textarea name="storeDescription" rows="4" cols="50" placeholder="상점 소개글을 입력하세요...">${member.storeDescription}</textarea><br><br>
			            <button type="submit" class="button">저장</button>
			        </form>
			    </div>
			    <div class="section">
			        <h2>내 정보 관리</h2>
>>>>>>> 67c7351913d3d7b685702f20c8a57c2edc988ea0
                 <ul>
                    <li><a href="/member/editMember" class="button">내 정보 수정</a></li>                     
                    <li><a href="/member/deleteMember" class="button">회원탈퇴</a></li>
                 </ul>
<<<<<<< HEAD
             </div>
             <div class="section">
                 <h2>활동</h2>
                 <ul>
                    <li><a href="/board/recentViewed" class="button">최근 본 상품</a></li>
                    <li><a href="/myPage/myStore" class="button">내 상점 관리</a></li>
                    <li><a href="/board/bmk" class="button">찜</a><a href="/myPage/myReviews" class="button">후기</a></li>
                    <li></li>
                 </ul>              
             </div>
         </div>
      </div>
      
<script>
   $(function(){
      //menu v1
      $(".slide-menu").mouseover(function(){
         $(this).children(".submenu").stop().slideDown();
      });   
      
      
      $(".slide-menu").mouseleave(function(){
         $(this).children(".submenu").stop().slideUp();
      });
   });   
</script>
=======
			    </div>
			    <div class="section">
			        <h2>활동</h2>
			        <ul>
			        	<li><a href="/board/recentViewed" class="button">최근 본 상품</a></li>
			        	<li><a href="/myPage/myStore" class="button">내 상점 관리</a></li>
			        	<li><a href="/board/bmk" class="button">찜</a><a href="/myPage/myReviews" class="button">후기</a></li>
			        	<li></li>
			        </ul>		        
			    </div>
			</div>
		</div>
		
<script>

</script>
</body>
>>>>>>> 67c7351913d3d7b685702f20c8a57c2edc988ea0
</html>