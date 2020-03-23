package com.example.spring02.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.service.member.MemberService;

@Controller
@RequestMapping("member/*") //공통 url
public class MemberController {
	//로깅처리
	private static final Logger logger=
			LoggerFactory.getLogger(MemberController.class);
	@Inject
	MemberService memberService;
	
	@RequestMapping("login.do") //세부 url
	public String login() {
		return "member/login"; //login.jsp로 이동
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto, 
			HttpSession session) {
		//로그인 성공 true, 실패 false
		boolean result=memberService.loginCheck(dto, session);
		ModelAndView mav=new ModelAndView();
		if(result) {//로그인 성공
			mav.setViewName("home");
		}else {//로그인 실패
			mav.setViewName("member/login");
			//뷰에 전달할 값
			mav.addObject("message", "error");
		}
		return mav;
	}//login_check()
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		//세션 초기화
		memberService.logout(session);
		//login.jsp로 이동
		mav.setViewName("member/login");
		mav.addObject("message", "logout");
		return mav;
	}
	


}
