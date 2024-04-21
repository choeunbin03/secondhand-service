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
			<br>		
			<br>	
			
			<c:forEach items="${bbsList}" var="bbslist" varStatus="status">
				<div class="product-wrapper">
				<!-- 클릭 시 상세페이지 이동. -->
					<!-- <a class="product-item" href="/board/bbsView?bbsId=${bbslist.bbsId}"> -->
					<div class="product-item">
						<!-- 첨부파일 -->
						<div class="product-image">
							<img src="">	<!-- 여기에 이미지 경로 넣어야 함~!! -->
						</div>
						<div class="product-content">
							<!-- 게시글 제목 -->
							<div class="product-title">${bbslist.bbsTtl}</div>
							<!-- @@@@@@@@	판매완료 시 어떻게 표시할것인지 상의	@@@@@@@@ -->
							<!-- 게시글 상세 정보 -->
							<div class="product-detail">
								<!-- 판매 가격 -->
								<div class="product-price">${bbslist.slePrc}</div>
								<!-- 판매 완료 여부 및 가격 -->
								<div class="price-wrapper">
									<c:choose>
										<c:when test='${bbslist.sleCmptnYn eq 1}'> <!-- 판매 완료(sleCmptnYn==1) -->
											<div class="sleCmptnYn-btn">판매완료</div>
										</c:when>
									</c:choose>
									<div class="product-price">&#8361;${bbslist.slePrc}원</div>
								</div>								
								<!-- 게시글 등록 날짜 -->
								<div class="register-date"><fmt:formatDate pattern="yyyy/MM/dd" value="${bbslist.rgtrDt}" /></div>
							</div>
						</div>
					<!-- </a> -->
					</div>
				</div>
			</c:forEach>		
		
		</div><!-- //bbs_content -->		
	</div>


<script>
	var ctgry = document.querySelector('.menu-v1');
	ctgry.addEventListener('click', function(){
		
		var ctgryVal = ctgry.attr("value");
		location.href = "/board/bbsList?ctgryFld="+ctgryVal;
		
	})
</script>

</body>
</html>