package com.example.spring02.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.service.admin.AdminService;


@Controller
@RequestMapping("admin/*") //공통적인 url mapping

public class AdminController {
	@Inject
	AdminService adminService;
	
	@RequestMapping("login.do") //세부적인 url mapping
	public String login() {
		return "admin/login"; //login.jsp로 이동
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto, 
			HttpSession session, ModelAndView mav) {
		String name=adminService.loginCheck(dto);//로그인 체크
		if(name != null) {//로그인 성공=>세션변수 저장
			//관리자용 세션변수
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			//일반 사용자용 세션변수
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("admin/admin");//admin.jsp로 이동
		}else {//로그인 실패
			mav.setViewName("admin/login");//login.jsp로 이동
			mav.addObject("message", "error");
		}
		return mav;
	}//login_check()
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();//세션 초기화
		//관리자 로그인 페이지로 이동
		return "redirect:/admin/login.do";
	}

}
