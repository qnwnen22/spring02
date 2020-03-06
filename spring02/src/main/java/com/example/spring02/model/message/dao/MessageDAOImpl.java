package com.example.spring02.model.message.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.message.dto.MessageDTO;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Inject //의존관계 주입시 2개이상이면 따로 처리
	SqlSession sqlSession;
	
	@Override
	public void create(MessageDTO dto) {
		sqlSession.insert("message.create",dto);
	}

	@Override
	public MessageDTO readMessage(int mid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(int mid) {
		// TODO Auto-generated method stub

	}

}
