<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>FarmFarm</title>
	
	<link rel="stylesheet" href="${path}/resources/css/moduleStyle.css">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	
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
	<%@ include file="../module/header.jsp" %>
	<div class="content">
		<div class="container">
		    <div class="tab-links">
		        <button class="tab-button active" onclick="openTab(event, 'sales')">판매 내역</button>
		        <button class="tab-button" onclick="openTab(event, 'purchases')">구매 내역</button>
		    </div>
		    <div id="sales" class="tab-content active">
		        <h2 class="header">판매 내역</h2>
		           <table class = "table table-hover">
		              <tr>
		                 <th>상품명</th>
		                 <th>가격</th>
		                 <th>판매 여부</th>
		                 <th>날짜</th>
		              </tr>
		              <c:forEach var = "item" items="${sleBbsList}">
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
		        <h2 class="header">구매 내역</h2>
		           <table class = "table table-hover">
		              <tr>
		                 <th>상품명</th>
		                 <th>가격</th>
		                 <th>판매 여부</th>
		                 <th>날짜</th>
		              </tr>
		              <c:forEach var = "item" items="${prchBbsList}">
		                 <tr onclick="location.href='/board/bbsView?bbsId=${item.bbsId}'">
		                    <td>${item.bbsTtl}</td>
		                    <td>${item.slePrc}</td>
		                    <td>${item.sleCmptnYn == 1 ? '거래 완료' : '판매중' }</td>
		                    <td><fmt:formatDate value="${item.rgtrDt}" pattern="yyyy-MM-dd" /></td>
		                 </tr>
		              </c:forEach>
		           </table>
		    </div>
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