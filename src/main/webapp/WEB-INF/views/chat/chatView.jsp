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
		.content{
			display: flex;
			height: 100%;
		}
		.chatContent {	
			padding-top: 10px;	
			justify-content: center;
			align-items: flex-start;
			display: flex;
			width: calc(100% - 200px);
			flex-wrap: wrap;
		}
	</style>
	
	<link rel="stylesheet" href="${path}/resources/css/moduleStyle.css">
	<link rel="stylesheet" href="${path}/resources/css/chatStyle.css">
	
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
		
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="${path}/resources/js/chat.js" type="text/javascript" defer="defer"></script>

	
</head>
<body>
 
	<!-- 만약 chatDTO가 null인 경우> 빈 화면에 채팅방을 선택해주세요 글 띄우기. -->
	<%@ include file="../module/header.jsp" %>
	<div class="content"> 
		<%@ include file="../module/chatRoomBar.jsp" %>
		<div class="chatContent">
			<br>		
			<br>	
			<c:choose>
				<c:when test="${chatSpceId ne 0}"><!-- 이건 업데이트해도 null이라서 업데이트 안됨. 이부분 수정 -->
					<div class="chatting-wrapper">
						<c:forEach items="${chatContent}" var="chatContent" varStatus="status">							
							<c:choose>								
								<c:when test="${chatContent.sendId eq mbrId}">
									<div class="loginMember-chat chat-part">
										<div class="additional-info">
											<c:choose>
												<c:when test="${chatContent.idntyYn eq 1}">
													<div class="idntyYn">${chatContent.idntyYn}</div>
												</c:when>
											</c:choose>
											<div class="sendDt"><fmt:formatDate pattern="yyyy/MM/dd" value="${chatContent.sendDt}" /></div>
											
										</div>										
										<div class="chatBox">
											<div class="chatCn">${chatContent.chatCn}</div>
										</div>										
									</div>
								</c:when>
								<c:otherwise>
									<div class="chatPartner-chat chat-part">
										<div class="additional-info">
											<c:choose>
												<c:when test="${chatContent.idntyYn eq 1}">
													<div class="idntyYn">${chatContent.idntyYn}</div>
												</c:when>
											</c:choose>
											<div class="sendDt"><fmt:formatDate pattern="yyyy/MM/dd" value="${chatContent.sendDt}" /></div>
										</div>										
										<div class="chatBox">
											<div class="chatCn">${chatContent.chatCn}</div>
										</div>										
									</div>
								</c:otherwise>	
							</c:choose>
						</c:forEach>
					</div><!-- chatRoom-wrapper -->
					<!-- 채팅 작성 -->
					<div class="chat_input">
						<form id="chatFrm">
							<input type="hidden" name="chatSpceId" value="${chatContent[0].chatSpceId}">
							<input type="hidden" name="bbsId" value="${chatContent[0].bbsId}">
							<div class="chat_input_area">
				                <input type="text" name="chatCn" placeholder="메세지를 입력하세요." class="chat-text" value="">
				            </div>	
				            <div class="chat_button_area">
				                <button onclick="fn_regiChat()">전송</button>
				            </div>
						</form>			            
			        </div>
				</c:when>
				
				<c:otherwise>
					<div>채팅방을 선택해주세요.</div>
				</c:otherwise>
				
			</c:choose>
		</div>			
			
	</div><!-- content -->


</body>
</html>