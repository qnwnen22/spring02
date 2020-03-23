package com.example.spring02.controller.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.memo.dto.MemoDTO;
import com.example.spring02.service.memo.MemoService;

//스프링에게 컨트롤러임을 명시
@Controller
@RequestMapping("memo/*") //공통적인 url pattern
public class MemoController {
	
	@Inject
	MemoService memoService;
	
	// memo/list.do
	@RequestMapping("list.do") //세부적인 url pattern
	public ModelAndView list(ModelAndView mav) {
		List<MemoDTO> items=memoService.list();
		//WEB-INF/views/memo/memo_list.jsp
		mav.setViewName("memo/memo_list");//포워딩할 뷰의 이름
		mav.addObject("list", items); //전달할 데이터(모델)
		return mav;
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemoDTO dto) {
		memoService.insert(dto);
		return "redirect:/memo/list.do";
	}//insert()

	//글번호가 @PathVariable로 url에 포함되어 전달됨
	@RequestMapping("view/{idx}")
	public ModelAndView view(@PathVariable int idx, ModelAndView mav) {
		//포워딩할 뷰의 이름
		mav.setViewName("memo/view");
		//전달할 데이터
		mav.addObject("dto", memoService.memo_view(idx));
		return mav;
	}
	
	@RequestMapping("update/{idx}")
	public String update(@PathVariable int idx, 
			@ModelAttribute MemoDTO dto) {
		//메모 수정
		memoService.update(dto);
		//수정 완료 후 목록으로 이동
		return "redirect:/memo/list.do";
	}
	
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx) {
		//레코드 삭제 처리
		memoService.delete(idx);
		return "redirect:/memo/list.do";
	}
	

}
