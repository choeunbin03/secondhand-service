<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성하기</title>

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
    .bbsViewWrap { margin-top:30px; width: 900px;}
    .bbsViewImgWrap{width: 450px; height: 315px;}
    .img-wrapper{margin-right: 50px;}
    .slick-prev:before, .slick-next:before{color: #c0c0c0;}
    .review-form {
        display: flex;
        flex-direction: column;
        width: 900px;
        margin-top: 30px;
    }
    .review-form h1 {
        font-size: 24px;
        margin-bottom: 10px;
    }
    .review-form .price {
        font-size: 20px;
        color: #ff0000;
        margin-bottom: 10px;
    }
    .review-form textarea {
        width: 80%;
        height: 120px;
        margin-bottom: 10px;
    }
    .review-form .submit-btn {
        padding: 10px 20px;
        background-color: #fafafa;
        color: black;
        text-decoration: none;
        border-radius: 5px;
        text-align: center;
        cursor: pointer;
    }
</style>

</head>
<body>

<%@ include file="../module/header.jsp" %>
<div class="content">
    <div class="bbsContent">
        <div class="bbsViewWrap">
            <!-- 첨부파일 -->
            <div class="bbsViewImgWrap">
                <div class="img-wrapper">
                    <c:choose>
                        <c:when test='${reviewBbs.atchFileNo ne 0}'>        
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
            <div class="bbsViewContentWrap">
                <div class="review-form">
                    <h1>${reviewBbs.bbsTtl}</h1>
                    <div class="price">
                        ${reviewBbs.slePrc} 원
                    </div>
                    <form id="reviewForm" action="/myPage/writeReview" method="post">
                   <textarea name="fdbk" placeholder="리뷰를 작성해주세요..."></textarea>
                   <input type="hidden" name="bbsId" value="${reviewBbs.bbsId}"/>
                   <div>
                   	<button type="submit" class="submit-btn">리뷰 제출</button>
                   </div>                   
               </form>
                </div>
            </div>
        </div><!-- bbsViewWrap -->
    </div><!-- //bbsContent -->
</div>

<script>
    $(document).ready(function(){
        $('.img-wrapper').slick();
    });

</script>

</body>
</html>