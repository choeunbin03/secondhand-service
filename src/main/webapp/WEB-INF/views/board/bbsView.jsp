<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>

<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
<link rel="stylesheet" href="${path}/resources/css/moduleStyle.css">
<link rel="stylesheet" href="${path}/resources/css/bbsStyle.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script src="${path}/resources/js/board.js" type="text/javascript" defer="defer"></script>

<style type="text/css">
    .bbsViewWrap {display:flex; flex-wrap: wrap; margin-top:30px; width: 100%; max-width: 1200px; margin: 0 auto;}
    .bbsViewImgWrap {flex: 1; max-width: 50%; padding: 20px; box-sizing: border-box;}
    .bbsViewContentWrap {flex: 1; max-width: 50%; padding: 20px; box-sizing: border-box;}
    .bbs-img-files {width: 100%; height: auto; margin-bottom: 10px;}
    #img-file {width: 100%; height: auto;}
    .price {font-size: 24px; color: #333; margin: 20px 0;}
    .bbsViewInfo {margin: 20px 0;}
    .profile a {color: #007bff; text-decoration: none;}
    .profile a:hover {text-decoration: underline;}
    .bbsViewContent {margin-top: 20px; font-size: 16px; line-height: 1.6;}
    .action-buttons {display: flex; justify-content: flex-end; gap: 10px; margin-top: 20px;}
    .action-button {padding: 10px 20px; background-color: #fafafa; color: black; text-decoration: none; border-radius: 5px; border: 1px solid #ccc; transition: background-color 0.3s;}
    .action-button:hover {background-color: #f0f0f0;}
    .bbsViewBtnWrap {display: flex; gap: 10px; margin-top: 20px;}
    .bbsViewBtnWrap .btn {flex: 1; padding: 15px; text-align: center; border-radius: 5px; cursor: pointer;}
    .bbsViewBtnWrap .btn-heart {background-color: #ffcccc; color: black;} /* 연한 빨강색 */
    .bbsViewBtnWrap .btn-heart:hover {background-color: #ff9999;}
    .bbsViewBtnWrap .btn-chat {background-color: #E6E6FA; color: black;} /* 연한 파랑색 */
    .bbsViewBtnWrap .btn-chat:hover {background-color: #D8BFD8;} /* 호버 시 더 진한 라벤더 색깔 */
</style>

</head>
<body>

<%@ include file="../module/header.jsp" %>
<div class="content">
    <%@ include file="../module/ctgryBar.jsp" %>

    <div class="bbsContent">
        <div class="bbsViewWrap">
            <!-- 첨부파일 -->
            <div class="bbsViewImgWrap">
                <div class="img-wrapper">
                    <c:choose>
                        <c:when test="${bbsView.atchFileNo ne 0}">
                            <c:forEach items="${files}" var="files">
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
            
            <!-- 게시글 내용 -->
            <div class="bbsViewContentWrap">
                <h1>[${bbsView.sleCmptnYn == 1 ? '판매완료' : '판매중' }] ${bbsView.bbsTtl}</h1>
                
                <!-- 판매 가격 -->
                <div class="price">
                    ${bbsView.slePrc} 원
                </div>
                
                <!-- 게시글 작성자 -->
                <div class="bbsViewInfo">
                    <div class="profile">
                        작성자: <a href="/myPage/profile?userId=${bbsView.rgtrId}">${bbsView.rgtrId}</a>
                    </div>
                    <div class="dateWrap">
                        <div class="date">
                            작성일시 | <fmt:formatDate value="${bbsView.rgtrDt}" pattern="yyyy-MM-dd" />
                        </div>
                        <div>
                            수정일시 | <fmt:formatDate value="${bbsView.mdfrDt}" pattern="yyyy-MM-dd" />
                        </div>
                    </div>
                </div>
                
                <div class="bbsViewContent">
                    <p>상품 정보: ${bbsView.bbsCn}</p>
                </div>
                         
                <!-- 수정 및 삭제 버튼 -->
               <c:if test="${bbsView.rgtrId == sessionScope.loginMember.mbrId}">
                   <div class="action-buttons">
                       <a href="/board/edit?bbsId=${bbsView.bbsId}" class="action-button">수정</a>
                       <a href="/board/delete?bbsId=${bbsView.bbsId}" class="action-button" onclick="return confirm('이 게시글을 삭제하시겠습니까?');">삭제</a>
                   </div>
               </c:if>
                   
                <!-- 찜 및 채팅 버튼 -->
            <!-- 찜 및 채팅 버튼 -->
            <div class="bbsViewBtnWrap">
                <form action="/board/addBmk" method="post">
                    <input type="hidden" name="bbsId" value="${bbsView.bbsId}" />
                    <input type="submit" name="bmk" value="찜하기" class="btn btn-heart action-button" />
                </form>
                <div class="btn btn-chat action-button" onclick="location.href='/chat/goChat?bbsId=${bbsView.bbsId}'">채팅하기</div>
            </div>

            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('.img-wrapper').slick();
    });
</script>

</body>
</html>