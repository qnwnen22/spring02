package com.example.spring02.model.shop.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.shop.dto.CartDTO;

@Repository
public class CartDAOImpl implements CartDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<CartDTO> cartMoney() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(CartDTO dto) {
		sqlSession.insert("cart.insert",dto);
	}

	@Override
	public List<CartDTO> listCart(String userid) {
		return sqlSession.selectList("cart.listCart",userid);
	}

	@Override
	public void delete(int cart_id) {
		sqlSession.delete("cart.delete",cart_id);
	}

	@Override
	public void deleteAll(String userid) {
		sqlSession.delete("cart.deleteAll",userid);
	}

	@Override
	public void update(int cart_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public int sumMoney(String userid) {
		return sqlSession.selectOne("cart.sumMoney",userid);
	}

	@Override
	public int countCart(String userid, int product_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCart(CartDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyCart(CartDTO dto) {
		sqlSession.update("cart.modifyCart",dto);
	}

}
