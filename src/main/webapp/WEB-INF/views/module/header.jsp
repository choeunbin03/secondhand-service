<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!-- Begin Wrapper -->
	<div class="header-content">

		<!-- Begin Header -->
		<div class="header-wrapper">
		
			<div class="member-wrapper">
				<a href="/member/login">로그인/회원가입</a>
			</div>
			
			<div class="header-menu-block">
				<!-- 홈화면(로고) -->
				<div class="flex-center logo-wrapper">
					<a href="/board/bbsList"><span class="logo-text">중고게시판</span></a>
				</div>
				
				<!-- 검색 -->
				<div class="sc-eNQAEJ voMyM">
					<div class="sc-eNQAEJ voMyM">
						<input type="text" name="keyWd" placeholder="상품명, 지역명, @상점명 입력" maxlength="40" class="sc-hMqMXs cLfdog" value="">
						<button type="button" class="search-btn btn" onclick="fn_search()">
							<i class="xi-search xi-2x"></i>
						</button>
					</div>
				</div>
				
				<!-- 찜 목록 -->
				<div class="show-heart-list">
					<a href="/myPage/heartList">찜 목록</a>
				</div>
				
				<!-- 채팅 목록 -->
				<div class="show-chatSpce-list">
					<a href="/chat/chatSpceList">채팅</a>
				</div>
				
				<!-- 마이페이지 -->
				<div class="show-myPage">
					<a href="/myPage/mainPage">마이페이지</a>
				</div>
			</div> <!-- header-menu-block -->
			

		 </div> <!-- header-wrapper -->
	 
	</div> <!-- header-content -->
	 <!-- End Header -->