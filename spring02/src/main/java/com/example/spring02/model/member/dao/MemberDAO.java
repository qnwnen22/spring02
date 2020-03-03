package com.example.spring02.model.member.dao;

import com.example.spring02.model.member.dto.MemberDTO;

public interface MemberDAO {
	public boolean loginCheck(MemberDTO dto);
	public MemberDTO viewMember(String userid);

}
