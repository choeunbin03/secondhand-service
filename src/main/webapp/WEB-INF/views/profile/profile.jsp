<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>프로필페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/profileStyle.css">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
        .container { width: 80%; margin: 20px auto; background: white; padding: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .profile-section { padding: 20px; border: 1px solid #ddd; border-radius: 5px; margin-bottom: 20px; background-color: #fff; }
        .profile-header { margin-top: 0; margin-bottom: 20px; font-size: 24px; }
        .profile-content { display: flex; align-items: center; }
        .profile-photo { width: 150px; height: 150px; border-radius: 50%; background-color: #ddd; display: inline-block; margin-right: 20px; }
        .profile-photo img { width: 100%; height: 100%; border-radius: 50%; object-fit: cover; }
        .profile-description { font-size: 18px; color: #333; font-weight: bold; margin-top: 10px; }
        .tab-links { margin-bottom: 20px; }
        .tab-button { display: inline-block; padding: 10px 20px; margin-right: 10px; background-color: #ffc0cb; color: white; border: none; border-radius: 5px; cursor: pointer; }
        .tab-button:hover { background-color: #ff99a5; }
        .tab-button.active { background-color: #ff99a5; }
        .tab-content { display: none; }
        .tab-content.active { display: block; }
        .header { color: #333; padding: 10px; background-color: #efefef; border-radius: 5px; }
        .table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        .table th, .table td { padding: 10px; border: 1px solid #ddd; text-align: left; }
        .table th { background-color: #f9f9f9; }
        .table-hover tbody tr:hover { background-color: #f1f1f1; }
    </style>
    <script>
        function openTab(evt, tabName) {
            var i, tabcontent, tabbuttons;
            tabcontent = document.getElementsByClassName("tab-content");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tabbuttons = document.getElementsByClassName("tab-button");
            for (i = 0; i < tabbuttons.length; i++) {
                tabbuttons[i].className = tabbuttons[i].className.replace(" active", "");
            }
            document.getElementById(tabName).style.display = "block";
            evt.currentTarget.className += " active";
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>프로필페이지</h1>
        <div class="profile-section">
            <h3 class="profile-header">상점 프로필</h3>
            <div class="profile-content">
                <div class="profile-photo">
                    <img src="${profile.profilePhotoUrl != null ? profile.profilePhotoUrl : '/resources/images/default.jpg'}" alt="Profile Photo">
                </div>
                <div>
                    <p class="profile-description">상점 소개글: ${profile.storeDescription}</p>
                </div>
            </div>
        </div>
        
        <div class="tab-links">
            <button class="tab-button active" onclick="openTab(event, 'sales')">판매 내역</button>
            <button class="tab-button" onclick="openTab(event, 'purchases')">후기</button>
        </div>

        <div id="sales" class="tab-content active">
            <h2 class="header">판매 내역</h2>
            <table class="table table-hover">
                <tr>
                    <th>상품명</th>
                    <th>가격</th>
                    <th>판매 여부</th>
                    <th>날짜</th>
                </tr>
                <c:forEach var="item" items="${sleReviewList}">
                    <tr onclick="location.href='/board/bbsView?bbsId=${item.bbsId}'">
                        <td>${item.bbsTtl}</td>
                        <td>${item.slePrc}</td>
                        <td>${item.sleCmptnYn == 1 ? '거래 완료' : '판매중' }</td>
                        <td><fmt:formatDate value="${item.rgtrDt}" pattern="yyyy-MM-dd" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div id="purchases" class="tab-content">
            <h2 class="header">작성된 후기</h2>
            <table class="table table-hover">
                <tr>
                    <th>상품명</th>
                    <th>작성자</th>
                    <th>후기</th>
                    <th>작성일</th>
                </tr>
                <c:forEach var="item" items="${sleReviewList}">
                    <c:if test="${item.fdbk != null}">
                        <tr onclick="location.href='/board/bbsView?bbsId=${item.bbsId}'">
                            <td>${item.bbsTtl}</td>
                            <td>${item.prchId}</td>
                            <td>${item.fdbk}</td>
                            <td><fmt:formatDate value="${item.fdbkDt}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
