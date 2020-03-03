package com.example.spring02.service.shop;

import java.util.List;

import com.example.spring02.model.shop.dto.CartDTO;

public interface CartService {
	public List<CartDTO> cartMoney();
	public void insert(CartDTO dto);
	public List<CartDTO> listCart(String userid);
	public void delete(int cart_id);
	public void deleteAll(String userid);
	public void update(int cart_id);
	public int sumMoney(String userid);
	public int countCart(String userid, int product_id);
	public void updateCart(CartDTO dto);
	public void modifyCart(CartDTO dto);

}
