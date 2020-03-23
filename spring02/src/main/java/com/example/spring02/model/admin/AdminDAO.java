package com.example.spring02.model.admin;

import com.example.spring02.model.member.dto.MemberDTO;

public interface AdminDAO {
	public String loginCheck(MemberDTO dto);

}
