<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="com.secondhand.domain.MemberDTO" %>
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
			width: calc(100% - 300px);
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
					<input type="hidden" name="partnerId" value="${partnerId}" class="partnerId">
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
							<input type="hidden" name="bbsId" value="${bbsId}">
							<div class="chat_input_area">
				                <input type="text" name="chatCn" placeholder="메세지를 입력하세요." class="chat-text" value="">
				            </div>	
				            <div class="chat_button_area">
				                <!-- <button id=chatBtn" onclick="fn_regiChat()">전송</button> -->
				                <button id=chatBtn">전송</button>
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

<script>

	var ws;
	var sendId = '${mbrId}';
	
	function connect(){
		debugger;
		//localhost:8181은 개인적으로 수정하셔야해요!
		//ws = new WebSocket('ws://localhost:8181/chatHandle');
		ws = new WebSocket('ws://192.168.45.212:8181/chatHandle');
		
		ws.onopen = function() {
			console.log('연결 생성');
			register();
		};
		
		ws.onmessage = function(e) {
			console.log('메시지 받음');
			var data = e.data;
			//alert("받은 메시지 : " + data);
			addMsg(data);
		};
		
		ws.onclose = function() {
			console.log('연결 끊김');
		};
		
	}	
	
//	function addMsg(msg) { //원래 채팅 메시지에 방금 받은 메시지 더해서 설정하기
//		var chat = $('.chatting-wrapper').val();
//		//이 부분 수정하기. 저 위에 화면 구조대로 추가하는 코드 써줘야함.
//		chat = chat + "\n상대방 : " + msg;
//		$('#msgArea').val(chat);
//	}

	function addMsg(object) { //원래 채팅 메시지에 방금 받은 메시지 더해서 설정하기

		var chat = $('.chatting-wrapper').html();
		
		var chatCn = object.msg;
		var sendDt = object.sendDt;
		//Date.parse(sendDt);
		
		var appendHtml = '<div class="chatPartner-chat chat-part">'
							+'<div class="additional-info">'											
								+'<div class="idntyYn">1</div>'									
								+'<div class="sendDt">'+sendDt+'</div>'
							+'</div>'										
							+'<div class="chatBox">'
								+'<div class="chatCn">'+chatCn+'</div>'
							+'</div>'										
						+'</div>'
		
		
		$('.chatting-wrapper').append(appendHtml);
		//chat = chat + "\n상대방 : " + msg;
		//$('#msgArea').html(chat);
	}
	
	function register() { //메시지 수신을 위한 서버에 id 등록하기
		var msg = {
			type : "register", //메시지 구분하는 구분자 - 상대방 아이디와 메시지 포함해서 보냄
			sendId : sendId
		};
		ws.send(JSON.stringify(msg));
	}
	
	function sendMsg(sendDt) {
		//var msg = $("#chatMsg").val();
		//ws.send(userid + " : " + msg);
		
		var object = {
			type : "chat", //메시지 구분하는 구분자 - 상대방 아이디와 메시지 포함해서 보냄
			rcvId : $(".partnerId").val(),
			message : $(".chatCn").val(),
			sendDt: sendDt//이 부분 추가함.(날짜정보)
		};
		ws.send(JSON.stringify(object));
	};
	
//	$(function() {
//		connect();
//		$('#chatBtn').on("click", function() {
//			let now = new Date();
//			
//			var chat =  $(".chatCn").val();
//			//이 부분 수정(밑부분)
//			chat = chat + "\n나 : " + $(".chatCn").val();
//			$(".chatCn").val(chat);
//			sendMsg(now);
//			$(".chatCn").val("");
//		})
//	});

	$(function() {
		debugger;
		connect();
		$('#chatBtn').on("click", function() {
			debugger;
			let sendDt = new Date();			
			var chatCn =  $(".chatCn").val();
			
			var appendHtml = '<div class="loginMember-chat chat-part">'
							+'<div class="additional-info">'											
								+'<div class="idntyYn">1</div>'									
								+'<div class="sendDt">'+sendDt+'</div>'
							+'</div>'										
							+'<div class="chatBox">'
								+'<div class="chatCn">'+chatCn+'</div>'
							+'</div>'										
						+'</div>'
			
			//이 부분 수정(밑부분)
			
			$('.chatting-wrapper').append(appendHtml);
			sendMsg(sendDt);
			$(".chatCn").val("");
		})
	});


</script>

</body>
</html>