package com.secondhand.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.secondhand.domain.ChatDTO;
import com.secondhand.domain.ChatRoomDTO;
import com.secondhand.domain.MemberDTO;
import com.secondhand.service.ChatService;
import com.secondhand.service.LoginService;
import com.secondhand.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
	
	@Inject
	private ChatService chatService;
	

	@RequestMapping(value="/chatView")
    public String goChatView(HttpSession session, Model model,  HttpServletRequest request){
		//interceptor??
		List<ChatRoomDTO> chatRoomList = new ArrayList<>();
		List<ChatDTO> chatContent = new ArrayList<>();
		
		MemberDTO member = (MemberDTO) session.getAttribute("LoginMember"); // 세션에서 로그인 멤버 가져오기
		String mbrId = member.getMbrId();// 로그인한 멤버 id가져오기
		int chatSpceId = Integer.parseInt(request.getParameter("chatSpceId"));//
		
		//파라미터 값 생성
		Map<String, Object> params = new HashMap<>();
		params.put("mbrId", mbrId);
		params.put("chatSpceId", chatSpceId);
		
		if (chatSpceId != 0) {//채팅 메인 페이지는 chatSpceId값이 0
			//chatRoomList 그 읽음 표시 그거 업데이트~~
			chatService.updateIdntyYn(params);
			
			//채팅 상대방 프로필? 정보 가져오기
			
			//선택된 채팅방의 채팅 내용 가져오기
			chatContent = chatService.getChat(params);
		}		
		
		//채팅방 리스트 가져오기
		chatRoomList = chatService.getchatRoomList(params);		
		
		model.addAttribute("mbrId", mbrId);
		model.addAttribute("chatSpceId", chatSpceId);
		model.addAttribute("chatRoomList",chatRoomList);
		model.addAttribute("chatContent",chatContent);
		
        return "/chat/chatView";
    }
	
	@PostMapping("/chatRegi")
	@ResponseBody
	public void chatRegi(HttpSession session, Model model,  HttpServletRequest request, ChatDTO chatDto) {
		
		//로그인한 사용자 아이디 가져오기
		MemberDTO member = (MemberDTO) session.getAttribute("LoginMember");
		String mbrId = member.getMbrId();
		
		Map<String, Object> params = new HashMap<>();
		params.put("mbrId", mbrId);
		params.put("chatSpceId", chatDto.getChatSpceId());
		
		//채팅 max 아이디 가져오기
		int maxChatId = chatService.getMaxChatId();
		
		//채팅 상대방 id 가져오기
		String rcvId = chatService.getChatPartnerId(params);
		
				
		//chatDto 객체에 남은 정보 insert
		chatDto.setSendId(mbrId);
		chatDto.setRcvId(rcvId);
		chatDto.setChatId(maxChatId + 1);
		
		//등록하기~
		chatService.regiChat(chatDto);
	}
	
	@GetMapping("/goChat")
	public String goChat(HttpSession session, Model model,  HttpServletRequest request) throws Exception{
		
		//로그인한 사용자 아이디 가져오기
		MemberDTO member = (MemberDTO) session.getAttribute("LoginMember");
		String mbrId = member.getMbrId();
		
		//bbsId 가져오기
		int bbsId = Integer.parseInt(request.getParameter("bbsId"));
		
		//map 형태의 파라미터 생성
		Map<String, Object> param = new HashMap<>();
		param.put("mbrId", mbrId);
		param.put("bbsId", bbsId);
		
		//채팅방이 이미 존재하면 chatSpceId > 0, 존재하지 않으면 0
		//int chatSpceId = chatService.getChatSpceId(param);
		
		//bbsId과 mbrId에 해당하는 채팅방이 있을 경우, 해당 채팅방으로 redirect
/*		if(chatSpceId > 0) {
			//해당 채팅방으로 redirect
		}else {
			//maxChatSpceId 가져오기
			
			//채팅방 
		}*/
		//없을 경우, 새로운 채팅방을 생성하고 해당 채팅방으로 redirect
		
		return "";
	}

}
