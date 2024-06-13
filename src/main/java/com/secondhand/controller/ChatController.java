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

import com.secondhand.domain.BoardDTO;
import com.secondhand.domain.ChatDTO;
import com.secondhand.domain.ChatRoomDTO;
import com.secondhand.domain.MemberDTO;
import com.secondhand.service.BoardService;
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
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value="/chatView")
    public ModelAndView goChatView(HttpSession session, Model model,  HttpServletRequest request){
		//interceptor??
		List<ChatRoomDTO> chatRoomList = new ArrayList<>();
		List<ChatDTO> chatContent = new ArrayList<>();
		
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember"); // 세션에서 로그인 멤버 가져오기
		String mbrId = member.getMbrId();// 로그인한 멤버 id가져오기
		int chatSpceId = Integer.parseInt(request.getParameter("chatSpceId"));//
		String partnerId = null;
		int bbsId = 0;
		BoardDTO bbsContent = new BoardDTO();
		
		//파라미터 값 생성
		Map<String, Object> params = new HashMap<>();
		params.put("mbrId", mbrId);
		params.put("chatSpceId", chatSpceId);
		
		if (chatSpceId != 0) {//채팅 메인 페이지는 chatSpceId값이 0
			//채팅 상대 id 가져오기
			partnerId = chatService.getChatPartnerId(params);	
			
			//채팅방 게시글 id 가져오기
			bbsId = chatService.getBbsId(params);
			params.put("bbsId", bbsId);
			
			//채팅방 게시글 정보 가져오기
			if(bbsId != 0) {
				bbsContent = boardService.getBbsView(params);
			}			
			
			//chatRoomList 그 읽음 표시 그거 업데이트~~
			chatService.updateIdntyYn(params);		
			
			//선택된 채팅방의 채팅 내용 가져오기
			chatContent = chatService.getChat(params);
		}		
		
		//채팅방 리스트 가져오기
		chatRoomList = chatService.getchatRoomList(params);		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("mbrId", mbrId);
		mav.addObject("partnerId", partnerId);
		mav.addObject("chatSpceId", chatSpceId);
		mav.addObject("bbsId", bbsId);
		mav.addObject("bbsContent", bbsContent);
		mav.addObject("chatRoomList", chatRoomList);
		mav.addObject("chatContent", chatContent);
		mav.setViewName("/chat/chatView");
		return mav;

    }
/*	
	@PostMapping("/chatRegi")
	@ResponseBody
	public void chatRegi(HttpSession session, Model model,  HttpServletRequest request, ChatDTO chatDto) {
		
		//로그인한 사용자 아이디 가져오기
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		String mbrId = member.getMbrId();
		
		Map<String, Object> params = new HashMap<>();
		params.put("mbrId", mbrId);
		params.put("chatSpceId", chatDto.getChatSpceId());
		
		//채팅 max 아이디 가져오기
		int maxChatId = chatService.getMaxChatId();
		
		//채팅 상대방 id 가져오기
		String rcvId = chatService.getChatPartnerId(params);
		
				
		//chatDto 객체에 남은 정보 insert
		//chatDto.setSendId(mbrId);
		//chatDto.setRcvId(rcvId);
		//chatDto.setChatId(maxChatId + 1);
		
		//등록하기~
		chatService.regiChat(chatDto);
	}
*/	
	@GetMapping("/goChat")
	public String goChat(HttpSession session, Model model,  HttpServletRequest request) throws Exception{
		
		//로그인한 사용자 아이디 가져오기
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		String mbrId = member.getMbrId();
		
		//bbsId 가져오기
		int bbsId = Integer.parseInt(request.getParameter("bbsId"));
		
		//map 형태의 파라미터 생성
		Map<String, Object> param = new HashMap<>();
		param.put("mbrId", mbrId);
		param.put("bbsId", bbsId);
		
		//채팅방 존재 여부 가져오기. 채팅방 존재하면 1, 존재하지 않으면 0.
	//	int chatRoomExists = chatService.chatRoomExists(param);
		
		//채팅방이 이미 존재하면 chatSpceId > 0, 존재하지 않으면 0
		int chatSpceId = chatService.getChatSpceId(param);
		
		//채팅방이 존재하지 않을 경우 새로 생성
		if(chatSpceId == 0) {
			//maxChatSpceId 가져오기
			chatSpceId = chatService.getMaxChatSpceId() + 1;			
			param.put("chatSpceId", chatSpceId);
			//채팅 상대 id가져오기.(게시글 작성자)
			String partnerId = chatService.getPartnerId(bbsId);
			param.put("partnerId", partnerId);
			//채팅방 생성
			chatService.createChatRoom(param);
		}
		//없을 경우, 새로운 채팅방을 생성하고 해당 채팅방으로 redirect
		
		return "redirect:/chat/chatView?chatSpceId="+chatSpceId;
	}

}
