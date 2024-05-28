<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!-- Begin Wrapper -->
	<div class="header-content">

		<!-- Begin Header -->
		<div class="header-wrapper">
		
			<div class="member-wrapper">
				<%
					if(session.getAttribute("mbrId")==null){
						out.println("<a href='/member/login'>로그인/회원가입</a>");
					}else{
						out.println("<a href='/member/logout'>로그아웃</a>");
					}
				%>
				
			</div>
			
			<div class="header-menu-block">
				<!-- 홈화면(로고) -->
				<div class="flex-center logo-wrapper header-element">
					<a href="/board/bbsList"><span class="logo-text">중고게시판</span></a>
				</div>
				
				<!-- 검색 -->
				<div class="search-wrapper">
					<div class="search-content">
						<input type="text" name="keyWd" placeholder="상품명, 지역명, @상점명 입력" maxlength="40" class="search input" value="">
						<button type="button" class="search-btn btn" onclick="fn_search()">
							<i class="xi-search xi-2x"></i>
						</button>
					</div>
				</div>
				
				<!-- 찜 목록 -->
				<div class="show-heart-list header-element">
					<a href="/board/bmk">찜 목록</a>
				</div>
				
				<!-- 채팅 목록 -->
				<div class="show-chatSpce-list header-element">
					<a href="/chat/chatSpceList">채팅</a>
				</div>
				
				<!-- 마이페이지 -->
				<div class="show-myPage header-element">
					<a href="/myPage/mainPage">마이페이지</a>
				</div>
			</div> <!-- header-menu-block -->
			

		 </div> <!-- header-wrapper -->
	 
	</div> <!-- header-content -->
	 <!-- End Header -->