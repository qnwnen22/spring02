package com.example.spring02.model.admin;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.member.dto.MemberDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public String loginCheck(MemberDTO dto) {
		return sqlSession.selectOne("admin.login_check", dto);
	}

}
