package com.example.spring02.model.admin.dao;

import com.example.spring02.model.member.dto.MemberDTO;

public interface AdminDAO {
	public String loginCheck(MemberDTO dto);
}
