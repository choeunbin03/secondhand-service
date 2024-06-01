<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
	<link rel="stylesheet" href="${path}/resources/css/moduleStyle.css">
	<link rel="stylesheet" href="${path}/resources/css/bbsStyle.css">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
		
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	<script src="${path}/resources/js/board.js" type="text/javascript" defer="defer"></script>

	<style type="text/css">
		.btn {border: 0; background-color: transparent;}
		.bbs-img-files{width: 350px; height: 315px;}
		#img-file{width: 100%; height: 100%;}
		.bbsViewWrap {display:flex; margin-top:30px; width: 900px;}
		.bbsViewImgWrap{width: 450px; height: 315px;}
		.img-wrapper{margin-right: 50px;}
		.slick-prev:before, .slick-next:before{color: #c0c0c0;}
		.action-button {
		    padding: 10px 20px;
		    background-color: #fafafa; /* 핑크색 */
		    color: black;
		    text-decoration: none;
		    border-radius: 5px;
		    margin-top: 10px; /* 간격 조절 */
		}
				
	</style>
	
</head>
<body>
 
	
	<%@ include file="../module/header.jsp" %>
	<div class="content">
		<%@ include file="../module/ctgryBar.jsp" %>
 	
		<div class="bbsContent">
			<br>		
			<br>	
			<div class="bbsViewWrap">
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
							</div class="date">
							<div>
								수정일시 | <fmt:formatDate value="${bbsView.mdfrDt}" pattern="yyyy-MM-dd" />
							</div>
						</div>
					</div>					
					
					<!-- 게시글 내용 -->
					<div class="bbsViewContent">
						<div>
							<p>${bbsView.bbsCn}</p>
						</div>
					</div>
					
				</div>
				
				<c:if test="${bbsView.rgtrId == sessionScope.loginMember.mbrId}">
					<div style="text-align: right; margin:20px;">
						<div class="action-button">
							<a href="/board/edit?bbsId=${bbsView.bbsId}">수정</a>
						</div>
						<div class="action-button">
							<a href="/board/delete?bbsId=${bbsView.bbsId}" onclick="return confirm('이 게시글을 삭제하시겠습니까?');">삭제</a>
						</div>                        
                    </div>
                </c:if>
				
				<!-- 찜 및 채팅 구현 -->
				<div class="bbsViewBtnWrap">	
					<form action="/board/addBmk" method="post">
						<input type="hidden" name="bbsId" value="${bbsView.bbsId}"/>
					    <input type="submit" name="bmk" value="찜하기" class="small_button" style=  "cursor: pointer; border:none; color:rgba(255,255,255,0); background: transparent; background-size : cover; background-image : url('/resources/images/heart_${isBMK ? 1 : 0}.png');"/>
					</form>
					<div class="btn chat-btn">
						<button type="button" onclick="location.href='/chat/goChat?bbsId=${bbsView.bbsId}'">채팅하기</button>
					</div>
				
				</div>
			</div><!-- bbsViewWrap -->
		
		</div><!-- //bbs_content -->		
	</div>


<script>
	$(document).ready(function(){
		$('.img-wrapper').slick();
	});
</script>


</body>
</html>