package com.secondhand.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
		
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
//		String result = "";
//		String mbrId = (String)session.getAttribute("sessionId");
//		if(mbrId!=null) {
//			result = "redirect:/board/bbsList";
//		}else {
//			result = "/member/login";
//		}
		return "home";
	}
	
}
