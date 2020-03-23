package com.example.spring02.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.spring02.model.board.dto.BoardDTO;

public interface BoardService {
	public void deleteFile(String fullName); //첨부파일 삭제v
	public List<String> getAttach(int bno); //첨부파일 정보v
	public void create(BoardDTO dto) throws Exception; //글쓰기v 
	public void update(BoardDTO dto) throws Exception; //글수정v
	public void delete(int bno) throws Exception; //글삭제v
	public List<BoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception;//목록v
	public void increateViewcnt(int bno, HttpSession session) throws Exception;//조회수 증가 처리v
	public int countArticle(String search_option, String keyword) throws Exception;//레코드 갯수 계산v
	public BoardDTO read(int bno) throws Exception;//레코드 조회v

}
