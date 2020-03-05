package com.example.spring02.service.shop;

import java.util.List;

import com.example.spring02.model.shop.dto.CartDTO;

public interface CartService {
	public List<CartDTO> cartMoney();
	public void insert(CartDTO dto);//장바구니에 상품저장
	public List<CartDTO> listCart(String userid);//장바구니 상품 리스트보기
	public void delete(int cart_id);
	public void deleteAll(String userid);
	public void update(int cart_id);
	public int sumMoney(String userid);
	public int countCart(String userid, int product_id);
	public void updateCart(CartDTO dto);
	public void modifyCart(CartDTO dto);

}
