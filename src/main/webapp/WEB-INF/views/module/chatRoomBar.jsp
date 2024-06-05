<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Begin chatRoomBarWrapper -->
	<div class="chatRoomBar-wrapper">
		
		<c:forEach items="${chatRoomList}" var="chatRoomList" varStatus="status">
			<div class="chatRoom-wrapper">
			<!-- 클릭 시 상세페이지 이동. -->
				<!-- <a href="/chat/chatRoom?chatSpceId=${chatRoomList.chatSpceId}">	-->
					<div class="chatRoomList">
						<div class="chatRoom-content" onclick='location.href="/chat/chatView?chatSpceId=${chatRoomList.chatSpceId}"'>
						<!-- <div class="chatRoom-content" onclick='fn_goChatView(${chatRoomList.chatSpceId})'>	-->
							<!-- 채팅 상대 프로필(이름) -->
							<div class="chatRoom-chatPartner">${chatRoomList.chatPartner}</div>
							<!-- 최근 채팅 정보 -->
							<div class="chatRoom-detail">
								<!-- 최근 채팅 내용 -->									
								<div class="chat-Content">${chatRoomList.chatCn}</div>																
								<!-- 최근 채팅 일시 -->
								<div class="chat-date"><fmt:formatDate pattern="yyyy/MM/dd" value="${chatRoomList.sendDt}" /></div>
								<div class="chat-idntyYn">
									<c:choose>
										<c:when test='${chatRoomList.chkYn eq 1}'> 
											<i class="xi-lightbulb xi-2x"></i>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</div>
									
							</div><!-- chatRoom-detail -->
						</div><!-- chatRoom-content -->
					
					</div>
				<!-- </a> -->
			</div>
		</c:forEach>		
	</div>	

