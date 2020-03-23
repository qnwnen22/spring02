package com.example.spring02.model.member.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.member.dto.MemberDTO;

@Repository //스프링에서 관리하는 dao bean으로 설정
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public boolean loginCheck(MemberDTO dto) {
		String name=sqlSession.selectOne("member.login_check", dto);
		//조건식 ? true일 때의 값 : false일 때의 값
		return (name==null) ? false : true;
	}

	@Override
	public MemberDTO viewMember(String userid) {
		return sqlSession.selectOne("member.viewMember", userid);
	}

}
