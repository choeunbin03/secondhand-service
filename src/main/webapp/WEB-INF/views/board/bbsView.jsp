<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세보기</title>
    <link rel="stylesheet" href="${path}/resources/css/moduleStyle.css">
    <link rel="stylesheet" href="${path}/resources/css/bbsViewStyle.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
    <%@ include file="../module/header.jsp" %>
    <div class="content">
        <%@ include file="../module/ctgryBar.jsp" %>
        <div class="bbsContent">
            <div class="bbsViewWrap">
                <div class="bbsViewImgWrap">
                    <img class="bbsViewImg" alt="게시글 이미지" src="${path}/resources/img/test.jpg">
                </div>
                <div class="bbsViewContentWrap">
                    <h1>[${bbsView.sleCmptnYn == 1 ? '판매완료' : '판매중'}] ${bbsView.bbsTtl}</h1>
                    <div class="price">${bbsView.slePrc} 원</div>
                    <div class="bbsViewInfo">
                        <div class="profile">${bbsView.rgtrId}</div>
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
                        <p>${bbsView.bbsCn}</p>
                    </div>
                    <c:if test="${bbsView.rgtrId == sessionScope.LoginMember.mbrId}">
                        <div style="text-align: right;">
                            <a href="/board/edit?bbsId=${bbsView.bbsId}" class="action-button">수정</a>
                            <a href="/board/delete?bbsId=${bbsView.bbsId}" class="action-button" onclick="return confirm('이 게시글을 삭제하시겠습니까?');">삭제</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
