<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>내 상점 관리</title>

	<link rel="stylesheet" href="${path}/resources/css/moduleStyle.css">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	<style>	
	    body { font-family: Arial, sans-serif; background-color: #white; }
	    .container { width: 60%; margin: 50px auto; background: white; padding: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
	    .tab-links { display: flex; justify-content: space-between; margin-bottom: 20px; }
	    .tab-button { background: none; border: none; color: #000; font-size: 16px; cursor: pointer; padding: 10px 20px; }
	    .tab-button.active, .tab-button:hover { border-bottom: 2px solid #ffc5d0; }
	    .tab-content { display: none; }
	    .tab-content.active { display: block; }
	    .header { color: #333; margin: 20px 0; }
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
		        <p>상품 판매 DB에서 가져와서 보여주게 해야함</p>
		    </div>
		    <div id="purchases" class="tab-content">
		        <h2 class="header">구매 내역</h2>
		        <p>상품 구매 DB에서 가져와서 보여주게 해야함</p>
		    </div>
		</div><!-- container -->
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