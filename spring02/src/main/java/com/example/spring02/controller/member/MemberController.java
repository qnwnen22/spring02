package com.example.spring02.controller.member;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.model.memo.dto.MemoDTO;
import com.example.spring02.service.member.MemberService;

@Controller
@RequestMapping("member/*") //공통 url
public class MemberController {
	//로깅처리
	private static final Logger logger=
			LoggerFactory.getLogger(MemberController.class);
	@Inject
	MemberService memberService;
	
	@RequestMapping("address.do")
	public String address() {
		return "member/join";
	}
	
	//회원리스트
	@RequestMapping("list.do") //url mapping
	public String memberList(Model model) {
		List<MemberDTO> list=memberService.list();
		model.addAttribute("list", list);
		return "member/member_list";
	}
	
	//관리자용회원등록페이지
	@RequestMapping("write.do") //url mapping
	public String write() {
		//WEB-INF/views/member/write.jsp로 포워딩
		return "member/write";
	}
	
	//일반회원용회원등록페이지
	@RequestMapping("mywrite.do") //url mapping
	public String myWrite() {
		return "member/my_write";
	}
	
	//관리자용회원등록
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		memberService.insertMember(dto);//create method 눌러
		return "redirect:/member/list.do";
	}
	
	//일반회원용회원등록
	@RequestMapping("myinsert.do")
	public String myInsert(@ModelAttribute MemberDTO dto) {
		memberService.insertMember(dto);//create method 눌러
		return "redirect:/";//home.jsp 메인홈으로 이동
	}
	
	//회원수정관련
	@RequestMapping("view.do")
	public String view(@RequestParam String userid, Model model) {
		//모델에 자료 저장
		model.addAttribute("dto", memberService.viewMember(userid));
		return "member/view";//view.jsp로 포워딩
	}
	
	//나의회원수정관련
	@RequestMapping("myview.do")
	public String myView(@RequestParam String userid, Model model) {
		//모델에 자료 저장
		model.addAttribute("dto", memberService.viewMember(userid));
		return "member/myView";//view.jsp로 포워딩
	}
	
	//회원수정
	@RequestMapping("update.do")
	public String update(MemberDTO dto, Model model) {
		//비밀번호 체크
		boolean result=memberService.checkPw(dto.getUserid(), dto.getPasswd());//create method
		if(result) {//비밀번호가 맞으면
			//회원정보 수정
			memberService.updateMember(dto);//create method
			//수정 후 목록으로 이동
			return "redirect:/member/list.do";
		}else {//비밀번호가 틀리면
			model.addAttribute("dto", dto);
			model.addAttribute("join_date", 
					memberService.viewMember(dto.getUserid()).getJoin_date());
			model.addAttribute("message", "비밀번호를 확인하세요.");
			return "member/view";
		}
	}//update()
	
	
	//내정보수정
	@RequestMapping("myupdate.do")
	public String myupdate(MemberDTO dto, Model model) {
		//비밀번호 체크
		boolean result=memberService.checkPw(dto.getUserid(), dto.getPasswd());//create method
		if(result) {//비밀번호가 맞으면
			//회원정보 수정
			memberService.updateMember(dto);//create method
			//수정 후 목록으로 이동
			return "redirect:/";//home.jsp로 이동
		}else {//비밀번호가 틀리면
			model.addAttribute("dto", dto);
			model.addAttribute("join_date", 
					memberService.viewMember(dto.getUserid()).getJoin_date());
			model.addAttribute("message", "비밀번호를 확인하세요.");
			return "member/myView";
		}
	}//update()
	
	//회원삭제
	@RequestMapping("delete.do")
	public String delete(String userid, String passwd, Model model) {
		boolean result=memberService.checkPw(userid, passwd);
		if(result) {//비번이 맞으면 삭제 => 목록으로 이동
			memberService.deleteMember(userid);
			return "redirect:/member/list.do";
		}else {//비번이 틀리면 되돌아감
			model.addAttribute("message", "비밀번호를 확인하세요.");
			model.addAttribute("dto", memberService.viewMember(userid));
			return "member/view";
		}
	}
	
	
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
