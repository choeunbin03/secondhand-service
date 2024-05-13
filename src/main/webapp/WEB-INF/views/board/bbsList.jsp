<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시물 목록</title>
    <style type="text/css">
        .btn {border: 0; background-color: transparent;}
        .add-button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
        }
    </style>
    
    <link rel="stylesheet" href="${path}/resources/css/moduleStyle.css">
    <link rel="stylesheet" href="${path}/resources/css/bbsStyle.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
        
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
     
    <%@ include file="../module/header.jsp" %>
    <div class="content">
        <%@ include file="../module/ctgryBar.jsp" %>

        <!-- 게시글 등록 버튼 -->
        <div class="header-element">
            <a href="/board/add" class="add-button">게시글 등록하기</a>
        </div>
        
        <div class="bbsContent">
           <c:forEach items="${bbsList}" var="item" varStatus="status">
    <div class="product-wrapper">
        <a class="product-item" href="/board/bbsView?bbsId=${item.bbsId}">
            <div class="product-item">
                <div class="product-image">
                    <div class="img-wrapper">
                        <c:choose>
                            <c:when test="${item.atchFileNo ne 0}">
                                <img id="img-file" src="${fileList[status.index].atchFilePath}">
                            </c:when>
                            <c:otherwise>
                                <img id="img-file" src="/resources/images/defaultImage.png">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="product-content">
                    <div class="product-title">${item.bbsTtl}</div>
                    <div class="product-detail">
                        <div class="price-wrapper">
                            <c:choose>
                                <c:when test="${item.sleCmptnYn eq 1}">
                                    <div class="sleCmptnYn-btn">판매완료</div>
                                </c:when>
                            </c:choose>
                            <div class="product-price">&#8361;${item.slePrc}원</div>
                        </div>
                        <div class="register-date"><fmt:formatDate pattern="yyyy/MM/dd" value="${item.rgtrDt}" /></div>
                    </div>
                </div>
            </div>
        </a>
    </div>
</c:forEach>

        
        </div><!-- //bbs_content -->       
    </div>

</body>
</html>
