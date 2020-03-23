package com.example.spring02.model.board.dao;

import java.util.List;

import com.example.spring02.model.board.dto.BoardDTO;

public interface BoardDAO {
	public void deleteFile(String fullName); //첨부파일 삭제
	public List<String> getAttach(int bno); //첨부파일 정보
	public void addAttach(String fullName); //첨부파일 저장
	public void updateAttach(String fullName, int bno);//첨부파일 수정
	public void create(BoardDTO dto) throws Exception; //글쓰기 
	public void update(BoardDTO dto) throws Exception; //글수정
	public void delete(int bno) throws Exception; //글삭제
	public List<BoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception;//목록
	public void increateViewcnt(int bno) throws Exception;//조회수 증가 처리
	public int countArticle(String search_option, String keyword) throws Exception;//레코드 갯수 계산
	public BoardDTO read(int bno) throws Exception;//레코드 조회
}
