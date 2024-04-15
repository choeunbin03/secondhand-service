<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		.btn {border: 0; background-color: transparent;}
		
	</style>
	
	<link rel="stylesheet" href="${path}/resources/css/moduleStyle.css">
	<link rel="stylesheet" href="${path}/resources/css/bbsStyle.css">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
		
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="${path}/resources/js/board.js" type="text/javascript" defer="defer"></script>

	
</head>
<body>
 
	
	<%@ include file="../module/header.jsp" %>
	<div class="content">
		<%@ include file="../module/ctgryBar.jsp" %>
 	
		<div class="bbsContent">
			<div class="ctgry-wrapper">카테고리> ${ctgryFld}</div>
			<br>		
			<br>	
			
			<c:forEach items="${bbsList}" var="bbsList" varStatus="status">
				<div class="product-wrapper">
				<!-- 클릭 시 상세페이지 이동. -->
					<!-- <a class="product-item" href="/board/bbsView?bbsId=${bbsList.bbsId}"> -->
					<div class="product-item">
						<!-- 첨부파일 -->
						<div class="product-image">
							<img src="">	<!-- 여기에 이미지 경로 넣어야 함~!! -->
						</div>
						<div class="product-content">
							<!-- 게시글 제목 -->
							<div class="product-title">${bbsList.bbsTtl}</div>
							<!-- 게시글 상세 정보 -->
							<div class="product-detail">
								<!-- 판매 완료 여부 및 가격 -->
								<div class="price-wrapper">
									<c:choose>
										<c:when test='${bbsList.sleCmptnYn eq 1}'> <!-- 판매 완료(sleCmptnYn==1) -->
											<div class="sleCmptnYn-btn">판매완료</div>
										</c:when>
									</c:choose>
									<div class="product-price">&#8361;${bbsList.slePrc}원</div>
								</div>								
								<!-- 게시글 등록 날짜 -->
								<div class="register-date"><fmt:formatDate pattern="yyyy/MM/dd" value="${bbsList.rgtrDt}" /></div>
							</div>
						</div>
					<!-- </a> -->
					</div>
				</div>
			</c:forEach>		
		
		</div><!-- //bbs_content -->		
	</div>


</body>
</html>