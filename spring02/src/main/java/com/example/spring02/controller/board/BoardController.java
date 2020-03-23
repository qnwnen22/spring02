package com.example.spring02.controller.board;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.board.dto.BoardDTO;
import com.example.spring02.service.board.BoardService;
import com.example.spring02.service.board.Pager;

@Controller //controller bean
@RequestMapping("board/*") //공통적인 url pattern
public class BoardController {
	@Inject
	BoardService boardService;
	
	@RequestMapping("list.do") //세부적인 url pattern
	public ModelAndView list(
			@RequestParam(defaultValue = "name") String search_option,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue="1") int curPage) 
			throws Exception {
				
		//레코드 갯수 계산
		int count=boardService.countArticle(search_option,keyword);
		//페이지 관련 설정
		Pager pager=new Pager(count, curPage);
		int start=pager.getPageBegin();
		int end=pager.getPageEnd();
		
		List<BoardDTO> list=boardService.listAll(search_option,keyword, start,end); //게시물 목록
		ModelAndView mav=new ModelAndView();
		HashMap<String, Object> map=new HashMap<>();
		map.put("list", list); //map에 자료 저장
		map.put("count", count);
		map.put("pager", pager); //페이지 네비게이션을 위한 변수
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		mav.setViewName("board/list"); //포워딩할 뷰의 이름
		mav.addObject("map", map); //ModelAndView에 map을 저장
		return mav; //board/list.jsp로 이동
	}//list()
	
	@RequestMapping("write.do")
	public String write() {
		//글쓰기 폼 페이지로 이동
		return "board/write";
	}//write()
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute BoardDTO dto, 
			HttpSession session) throws Exception {
		//이름이 없기때문에 대신 세션에서 사용자의 아이디를 가져옴
		String writer=(String)session.getAttribute("userid");
		//<input name="userid" id="userid">
		dto.setWriter(writer);
		//레코드 저장
		boardService.create(dto);
		//게시물 목록으로 이동
		return "redirect:/board/list.do";
	}//insert()
	
	@RequestMapping("view.do")
	public ModelAndView view(int bno, HttpSession session) 
			throws Exception {
		//조회수 증가 처리
		boardService.increateViewcnt(bno, session);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("board/view");//포워딩할 뷰의 이름
		mav.addObject("dto", boardService.read(bno)); //자료 저장
		return mav; //view/board/view.jsp로 넘어가서 출력됨
	}
	
	//첨부파일 목록을 리턴
	//ArrayList를 json 배열로 변환하여 리턴
	@RequestMapping("getAttach/{bno}")
	@ResponseBody //view가 아닌 List<String>데이터 자체를 리턴
	public List<String> getAttach(@PathVariable int bno){
		return boardService.getAttach(bno);
	}
	
	//게시물 내용 수정
	@RequestMapping("update.do")
	public String update(BoardDTO dto) throws Exception {
		System.out.println("dto:"+dto);
		if(dto != null) {
			boardService.update(dto);
		}
		//수정 후 상세 화면으로 되돌아감
		return "redirect:/board/view.do?bno="+dto.getBno();
	}
	
	@RequestMapping("delete.do")
	public String delete(int bno) throws Exception{
		boardService.delete(bno);
		return "redirect:/board/list.do";//목록으로 이동
	}
	
	

}
