<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>후기</title>
<style>
    body { font-family: 'Noto Sans KR', sans-serif; background-color: #f8f9fa; }
    .container { max-width: 85%; margin: 40px auto; background: white; padding: 20px; box-shadow: 0 4px 8px rgba(0,0,0,0.15); }
    .tab-links { display: flex; justify-content: space-between; margin-bottom: 20px; }
    .tab-button { background: none; border: none; color: #495057; font-size: 18px; cursor: pointer; padding: 10px 20px; }
    .tab-button.active, .tab-button:hover { color: #007bff; border-bottom: 3px solid #007bff; }
    .tab-content { display: none; }
    .tab-content.active { display: block; }
    .header { color: #495057; margin: 20px 0; font-weight: 500; }
    table { width: 100%; border-collapse: collapse; border-spacing: 0; }
    th, td { text-align: center; padding: 15px 20px; border-bottom: 1px solid #ddd; }
    th { background-color: #f0f0f0; }
    tr:hover { background-color: #f5f5f5; }
</style>
</head>
<body>
<div class="container">
    <div class="tab-links">
        <button class="tab-button active" onclick="openTab(event, 'sales')">작성한 후기</button>
        <button class="tab-button" onclick="openTab(event, 'purchases')">작성된 후기</button>
    </div>
    <div id="sales" class="tab-content active">
        <h2 class="header">작성한 후기</h2>
        	<table class = "table table-hover">
        		<tr>
        			<th>상품명</th>
        			<th>작성자</th>
        			<th>후기</th>
        			<th>작성일</th>
        		</tr>
        		<c:forEach var = "item" items="${prchReviewList}">
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
    <div id="purchases" class="tab-content">
        <h2 class="header">작성된 후기</h2>
        	<table class = "table table-hover">
        		<tr>
        			<th>상품명</th>
        			<th>작성자</th>
        			<th>후기</th>
        			<th>작성일</th>
        		</tr>
        		<c:forEach var = "item" items="${sleReviewList}">
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

<script>
    function openTab(evt, tabName) {
        var i, tabcontent, tabbuttons;
        tabcontent = document.getElementsByClassName("tab-content");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
            tabcontent[i].classList.remove("active");
        }
        tabbuttons = document.getElementsByClassName("tab-button");
        for (i = 0; i < tabbuttons.length; i++) {
            tabbuttons[i].className = tabbuttons[i].className.replace(" active", "");
        }
        document.getElementById(tabName).style.display = "block";
        document.getElementById(tabName).classList.add("active");
        evt.currentTarget.className += " active";
    }
</script>
</body>
</html>
