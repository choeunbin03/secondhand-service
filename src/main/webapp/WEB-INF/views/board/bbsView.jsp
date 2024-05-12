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
	<link rel="stylesheet" href="${path}/resources/css/bbsViewStyle.css">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
		
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="${path}/resources/js/board.js" type="text/javascript" defer="defer"></script>

	
</head>
<body>
 
	
	<%@ include file="../module/header.jsp" %>
	<div class="content">
		<%@ include file="../module/ctgryBar.jsp" %>
<<<<<<< Updated upstream
 	
		<div class="bbsContent">
			<br>		
			<br>	
			<div class="bbsViewWrap">
				<div class="bbsViewImgWrap">
					<img class="bbsViewImg" alt="" src="${path}/resources/img/test.jpg">
				</div>
				<div class="bbsViewContentWrap">
					<h1>[${bbsView.sleCmptnYn == 1 ? '판매완료' : '판매중' }] ${bbsView.bbsTtl}</h1>
					<div class="price">
						${bbsView.slePrc} 원
					</div>
					<div class="bbsViewInfo">
						<div class="profile">
							${bbsView.sleId}
						</div>
						<div class="dateWrap">
							<div class="date">
								작성일시 | <fmt:formatDate value="${bbsView.rgtrDt}" pattern="yyyy-MM-dd" />
							</div class="date">
=======
 		<div>
			<div class="bbsContent">
				<br>		
				<br>	
				
				<div class="bbsViewWrap">
					<!-- 찜 및 채팅 구현 -->
					<div class="bbsViewBtnWrap">	
						<form action="/board/bbsView" method="post">
							<input type="hidden" name="bbsId" value="${bbsView.bbsId}"/>
						    <input type="submit" name="bmk" value="찜하기" class="small_button" style=  "cursor: pointer; border:none; color:rgba(255,255,255,0); background: transparent; background-size : cover; background-image : url('/resources/images/heart_${isBMK ? 1 : 0}.png');"/>
						</form>
						<button type="submit" name="chat" class="small_button" value="chat" style= "cursor: pointer;  border:none; color:rgba(255,255,255,0); background: transparent; background-size : cover; background-image : url('/resources/images/chat.png')"></button>
					
					</div>
					<!-- 첨부파일 -->
					<div class="bbsViewImgWrap">
						<div class="img-wrapper">
							<c:choose>
								<c:when test='${bbsView.atchFileNo ne 0}'>		
									<c:forEach items="${files}" var="files"	>
										<div class="bbs-img-files">
											<img id="img-file" src="${files.atchFilePath}">
										</div>	
									</c:forEach>																	
								</c:when>
								<c:otherwise>
									<div class="bbs-img-files">
										<img id="img-file" src="/resources/images/default.jpg">
									</div>
								</c:otherwise>
							</c:choose>		
						</div>	
					</div>
					<div class="bbsViewContentWrap">
						<h1>[${bbsView.sleCmptnYn == 1 ? '판매완료' : '판매중' }] ${bbsView.bbsTtl}</h1>
											
						<!-- 판매 가격 -->
						<div class="price">
							${bbsView.slePrc} 원
						</div>					
						
						<!-- 게시글 상세정보(작성자, 작성 날짜) -->
						<div class="bbsViewInfo">
							<div class="profile">
								${bbsView.rgtrId}
							</div>
							<div class="dateWrap">
								<div class="date">
									작성일시 | <fmt:formatDate value="${bbsView.rgtrDt}" pattern="yyyy-MM-dd" />
								</div>
								<div class="date">
									수정일시 | <fmt:formatDate value="${bbsView.mdfrDt}" pattern="yyyy-MM-dd" />
								</div>
							</div>
						</div>					
						
						<!-- 게시글 내용 -->
						<div class="bbsViewContent">
>>>>>>> Stashed changes
							<div>
								<p>${bbsView.bbsCn}</p>
							</div>
						</div>
<<<<<<< Updated upstream
					</div>
					<div class="bbsViewContent">
						<div>
							<p>${bbsView.bbsCn}</p>
						</div>
					</div>
				</div>
			</div>
=======
						
					</div>
				</div>
			</div><!-- bbsViewWrap -->
>>>>>>> Stashed changes
		
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