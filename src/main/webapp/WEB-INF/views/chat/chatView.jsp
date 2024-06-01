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
	<!-- <script src="${path}/resources/js/chat.js" type="text/javascript" defer="defer"></script>	-->

	
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
					<input type="hidden" id="partnerId" name="partnerId" value="${partnerId}" class="partnerId">
					<!-- 채팅 상대 프로필 -->
					<div class="partner-profile">
						<div>${partnerId}</div>
					</div>
					
					<!-- 게시글 정보 -->
					<div class="bbs-content">
						<a class="product-item" href="/board/bbsView?bbsId=${bbsContent.bbsId}">
							<div class="product-item">
								<div class="product-title">${bbsContent.bbsTtl}</div>
								<div class="price-wrapper">
									<c:choose>
										<c:when test='${bbsContent.sleCmptnYn eq 1}'> <!-- 판매 완료(sleCmptnYn==1) -->
											<div class="sleCmptnYn-btn">판매완료</div>
										</c:when>
									</c:choose>
									<div class="product-price">&#8361;${bbsContent.slePrc}원</div>
								</div>	
							</div>
						</a>
						<!-- 거래완료 및 후기 작성 버튼 -->
						<c:choose>
							<c:when test='${bbsContent.sleCmptnYn eq 1}'> <!-- 판매 완료(sleCmptnYn==1) -->
								<c:choose>
									<c:when test='${bbsContent.prchId eq mbrId}'>
										<c:choose>
											<c:when test="${not empty bbsContent.fdbk}">
												<button id="serviceBtn" onclick="fn_adiService(${bbsContent.bbsId}, '${partnerId}')">후기작성완료</button>
											</c:when>
											<c:otherwise>
												<div style="display:flex;">
													<button id="serviceBtn" onclick="fn_adiService(${bbsContent.bbsId}, '${partnerId}')">거래완료취소</button>
													<button id="serviceBtn" onclick="fn_adiService(${bbsContent.bbsId}, '${partnerId}')">후기작성</button>
												</div>												
											</c:otherwise>
										</c:choose>	
									</c:when>								
								</c:choose>															
							</c:when>
							<c:otherwise>
								<button id="serviceBtn" onclick="fn_adiService(${bbsContent.bbsId}, '${partnerId}')">거래완료</button>
							</c:otherwise>
						</c:choose>
						
						<!-- 위 버튼 onclik하면 html text 체크. -> html text 변경 -> 체크한 text에 따라 뒤의 쿼리 처리 -->
					</div><!-- bbs-content -->
					
					<!-- 채팅 정보 -->
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
						<!-- <form id="chatFrm">	-->
						<div id="chatFrm">
							<input type="hidden" id="chatSpceId" name="chatSpceId" value="${chatContent[0].chatSpceId}">
							<input type="hidden" id="bbsId" name="bbsId" value="${bbsId}">
							<div class="chat_input_area">
				                <input type="text" id="chatCn" name="chatCn" placeholder="메세지를 입력하세요." class="chat-text" value="">
				            </div>	
				            <div class="chat_button_area">				                
				                <button id="chatBtn">전송</button>
				            </div>
				            
				            
						<!-- </form>	-->		
						</div>            
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
	var mbrId = '${mbrId}';
	var chatting = document.querySelector('#chatBtn');
	
	
	//채팅방 클릭 했을 때
	//chatView 화면 뿌려주고
	//웹소켓 생성 -> 커넥션 맺기.
	//대신 메세지가 들어오면 pageLoad 되기 때문에 조건 다시 걸기.
	
	$(function() {
		debugger;
		fn_check();
	});
	
	function fn_check(){
		//이전 url 가져오기.
		var referrer = document.referrer;
		//현재 url 가져오기.
		var curr = document.location.href;
		
		//이전 url과 현재 url의 path가 같은지(둘다 /chat/가 있는지).
		var arrRefTmp = (referrer||"").split("=");
		var arrCurrTmp = (curr||"").split("=");
		
		var arrRefPath = arrRefTmp[0].split("/");
		var arrCurrPath = arrCurrTmp[0].split("/");
		//만약 이전 url이 chat 페이지가 아닌 경우 return.
		if(arrRefPath[3] != arrCurrPath[3])	return;
		
		//이전 chatSpceId와 현재 chatSpceId 비교.
		var refChatSpceId = arrRefTmp[1];
		var currChatSpceId = arrCurrTmp[1];	
		
		//혹시라도 chatSpceId 외의 다른 param이 있을 경우 수행하여 채팅방 번호 가져오기.
		if((arrRefTmp[1].split("?")).length != 1){
			var arrRef = arrRefTmp[1].split("?");
			refChatSpceId = arrRef[0];
		}
		if((arrCurrTmp[1].split("?")).length != 1){
			var arrCurr = arrCurrTmp[1].split("?");
			currChatSpceId = arrCurr[0];
		}	
		
		//채팅방 번호가 바뀌지 않은 경우 return.
		if(refChatSpceId == currChatSpceId)	return;
		else{ //다른 채팅방에 입장했을 경우 소켓 재연결
			
			ws = new WebSocket('ws://localhost:8181/chatHandle?chatSpceId='+currChatSpceId);
			
			ws.onopen = function() {
				console.log('연결 생성');
			};	
			
			ws.onmessage = function(e) {
				console.log('메시지 받음');
				var data = e.data;
				addMsg(data);
			};
			
			ws.onclose = function(e) {
				console.log('연결 끊김');
				
			};
			
			ws.onerror = function (evt) {
				console.log(evt.data);
			};
			
		}
		
		
	}

	
	function addMsg(object) { //원래 채팅 메시지에 방금 받은 메시지 더해서 설정하기
		
		var jsonObj = JSON.parse(object);
		
		var rcvId = jsonObj.rcvId
		
		if(rcvId != mbrId)	return;

		var chat = $('.chatting-wrapper').html();
		
		var chatCn = jsonObj.chatCn;
		var sendDt = jsonObj.sendDt;
		
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
		
	}

	function sendMsg(sendDt) {
		
		var chatSpceId = document.getElementById("chatSpceId").value;
		var partnerId = document.getElementById("partnerId").value;
		var chatCn = document.getElementById("chatCn").value;
		
		var object = {
			type : "chat", //메시지 구분하는 구분자 - 상대방 아이디와 메시지 포함해서 보냄
			chatSpceId : chatSpceId,
			rcvId : partnerId,
			sendId : mbrId,
			chatCn : chatCn,
			sendDt: sendDt//이 부분 추가함.(날짜정보)
		};
		ws.send(JSON.stringify(object));
	};
		
	chatting.addEventListener('click', function(){
		debugger;
		let now = new Date();
		sendDt = dateFormat(now);
		
		var chatCn = document.getElementById("chatCn").value;
		
		var appendHtml = '<div class="loginMember-chat chat-part">'
						+'<div class="additional-info">'											
							+'<div class="idntyYn">1</div>'									
							+'<div class="sendDt">'+sendDt+'</div>'
						+'</div>'										
						+'<div class="chatBox">'
							+'<div class="chatCn">'+chatCn+'</div>'
						+'</div>'										
					+'</div>'

		
		$('.chatting-wrapper').append(appendHtml);
		sendMsg(sendDt);
		document.getElementById("chatCn").val("");
	});
	
	function dateFormat(date) {
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();
        let second = date.getSeconds();

        month = month >= 10 ? month : '0' + month;
        day = day >= 10 ? day : '0' + day;
        hour = hour >= 10 ? hour : '0' + hour;
        minute = minute >= 10 ? minute : '0' + minute;
        second = second >= 10 ? second : '0' + second;

        return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
	}
	
	function fn_adiService(bbsId, chatPartnerId){
		
		var service = document.getElementById("serviceBtn")
		var serviceType = service.innerText;
		
		if(serviceType == '거래완료'){
			$.ajax({
				type: "POST",
				url: "/board/sleCmptn",
				data: {bbsId: bbsId, chatPartnerId:chatPartnerId},
				success:function(data){
					service.innerText = "후기작성";
					$(".bbs-content").load(location.href + " .bbs-content");
				},
				error: function(data){
					alert("실패");
					console.log(data);
				}
			});			
		}
		else if(serviceType == '후기작성'){
			location.href = "/myPage/writeReview?bbsId="+bbsId;
		}
		else if(serviceType == '거래완료취소'){
			$.ajax({
				type: "POST",
				url: "/board/sleCmptnCancel",
				data: {bbsId: bbsId},
				success:function(data){
					$(".bbs-content").load(location.href + " .bbs-content");	//부분 업데데이트
				},
				error: function(data){
					alert("실패");
					console.log(data);
				}
			});
		}
		else{
			alert("이미 후기를 작성하셨습니다.");
		}
		
	}
	


</script>

</body>
</html>