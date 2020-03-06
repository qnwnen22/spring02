package com.example.spring02.model.message.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public void updatePoint(String userid, int point) {
		Map<String,Object> map=new HashMap<>();
		map.put("userid", userid);
		map.put("point", point);
		//2개 이상의 값을 전달할 경우 - dto, map
		sqlSession.update("point.updatePoint", map);
	}

}
