package com.example.spring02.service.memo;

import java.util.List;

import com.example.spring02.model.memo.dto.MemoDTO;

public interface MemoService {
	
	public List<MemoDTO> list();
	public void insert(MemoDTO dto);
	public MemoDTO memo_view(int idx);
	public void update(MemoDTO dto);
	public void delete(int idx);

}
