package com.example.spring02.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02.model.board.dao.BoardDAO;
import com.example.spring02.model.board.dto.BoardDTO;


@Service //service bean
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO boardDao;

	@Override
	public void deleteFile(String fullName) {
		boardDao.deleteFile(fullName);
	}

	@Override
	public List<String> getAttach(int bno) {
		return boardDao.getAttach(bno);
	}

	//1.글쓰기 - 게시물 번호 생성
	//2.첨부파일 등록 - 해당 게시물 번호 사용
	//위 두개가 동시에 처리 (두개가 하나의 트랜잭션 단위가 됨)
	@Transactional
	@Override
	public void create(BoardDTO dto) throws Exception {
		boardDao.create(dto);//board테이블에 레코드 추가
		//attach 테이블에 레코드 추가
		String[] files=dto.getFiles(); //첨부파일 이름 배열
		if(files==null) return; //첨부파일이 없으면 skip
		for(String name : files) {
			boardDao.addAttach(name);//attach 테이블에 insert
		}
		
	}

	@Transactional //트랜잭션 처리
	@Override
	public void update(BoardDTO dto) throws Exception {
		boardDao.update(dto);
		//별도로 만들었던 파일처리 attach 테이블도 함께 수정해야함.
		//board와 attach 테이블을 동시에 처리(transaction)해야 함.
		//service는 transaction 처리하기에 조금 더 유리하다.
		String[] files=dto.getFiles();
		if(files==null) return;
		for(String name : files) {
			System.out.println("첨부파일 이름:"+name);
			boardDao.updateAttach(name, dto.getBno());
		}
	}

	@Transactional
	@Override
	public void delete(int bno) throws Exception {
		//reply 레코드 삭제
		//attach 레코드 삭제
		//board 레코드 삭제
		boardDao.delete(bno);
	}

	@Override
	public List<BoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception {
		return boardDao.listAll(search_option, keyword, start,end);
	}

	//조회수 증가 처리
	@Override
	public void increateViewcnt(int bno, HttpSession session) throws Exception {
		long update_time=0;
		if(session.getAttribute("update_time_"+bno) != null) {
			//최근에 조회수를 올린 시간
			update_time=(long)session.getAttribute("update_time_"+bno);
		}
		long current_time=System.currentTimeMillis();
		//일정 시간(5초)이 경과후 조회수 증가 처리
		if(current_time - update_time > 5*1000) {//24*60*60*1000(하루)
			//조회수 증가 처리
			boardDao.increateViewcnt(bno);
			//조회수를 올린 시간 저장
			session.setAttribute("update_time_"+bno, current_time);
		}
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		return boardDao.countArticle(search_option, keyword);
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

}
