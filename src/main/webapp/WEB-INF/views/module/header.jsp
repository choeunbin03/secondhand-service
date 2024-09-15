<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
	.logo-wrapper {
		height: 100px;
		box-sizing: border-box;
	}
	
	.logo-text {
		font-family: fantasy;
		font-size: 40px;
		margin-top: 50px;
		color: black;
		padding: 10px;
	}
</style>

<!-- Begin Wrapper -->
	<div class="header-content">

		<!-- Begin Header -->
		<div class="header-wrapper">
		
			<div class="member-wrapper">				
				<%
					if(session.getAttribute("loginMember")==null){
						out.println("<a href='/member/login'>로그인/회원가입</a>");
					}else{
						out.println("<a href='/member/logout'>로그아웃</a>");
					}
				%>
				<!-- <c:choose>
         			<c:when test="${not empty sessionScope.loginMember.mbrId}">
         				<a href='/member/logout'>로그아웃</a>
         			</c:when>
         			<c:otherwise>
         				<a href='/member/login'>로그인/회원가입</a>
         			</c:otherwise>
         		</c:choose>
				-->
			</div>
			
			<div class="header-menu-block">
				<!-- 홈화면(로고) -->
				<div class="flex-center logo-wrapper header-element">
					<a href="/">
						<div class="flex-center logo-wrapper">
							<span class="logo-text">FarmFarm</span>
						</div>
					</a>
				</div>
				
				<!-- 검색 -->
				<div class="search-wrapper">
					<div class="search-content">
						<form action="/board/search" method="get">
							<input type="text" name="searchKeyword" placeholder="검색어 입력" maxlength="40" class="search input" value="">
							<button type="submit" class="search-btn btn">
								<i class="xi-search xi-2x"></i>
							</button>
						</form>
					</div>
				</div>
				
				<!-- 찜 목록 -->
				<div class="show-heart-list header-element">
					<a href="/board/bmk">찜 목록</a>
				</div>
				
				<!-- 채팅 목록 -->
				<div class="show-chatRoom-list header-element">
					<!-- <a href="#" onClick="fn_chatView(0)">채팅</a>	-->
					<a href="/chat/chatView?chatSpceId=0">채팅</a>
				</div>
				
				<!-- 마이페이지 -->
				<div class="show-myPage header-element">
					<a href="/myPage/mainPage">마이페이지</a>
				</div>
			</div> <!-- header-menu-block -->
			

		 </div> <!-- header-wrapper -->
	 
	</div> <!-- header-content -->
	 <!-- End Header -->
