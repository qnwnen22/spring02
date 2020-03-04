package com.example.spring02.model.shop.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.shop.dto.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject // 의존관계 주입(DI)
	SqlSession sqlsession;
	
	@Override
	public List<ProductDTO> listProduct() {
		return sqlsession.selectList("product.product_list");
	}

	@Override
	public ProductDTO detailProduct(int product_id) {
		return sqlsession.selectOne("product.detail_product",product_id);
	}

	@Override
	public void updateProduct(ProductDTO dto) {
		sqlsession.update("product.update_product", dto);
	}

	@Override
	public void deleteProduct(int product_id) {
		sqlsession.delete("product.delete_product",product_id);
	}

	@Override
	public void insertProduct(ProductDTO dto) {
		sqlsession.insert("product.insert",dto);
	}

	@Override
	public String fileInfo(int product_id) {
		return sqlsession.selectOne("product.fileInfo",product_id);
	}

}
