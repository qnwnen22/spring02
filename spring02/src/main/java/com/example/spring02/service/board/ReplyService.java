package com.example.spring02.service.board;

import java.util.List;

import com.example.spring02.model.board.dto.ReplyDTO;

public interface ReplyService {
	public List<ReplyDTO> list(int bno);
	public int count(int bno);
	public void create(ReplyDTO dto);
}
