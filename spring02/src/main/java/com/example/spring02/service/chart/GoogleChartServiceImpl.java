package com.example.spring02.service.chart;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;

@Service
public class GoogleChartServiceImpl implements GoogleChartService {

	@Inject
	CartService cartService;
	
	@Override
	public JSONObject getChartData() {
		List<CartDTO> items=cartService.cartMoney();
		//리턴할 최종 json객체
		JSONObject data=new JSONObject();
		//컬럼을 정의할 json객체
		JSONObject col1=new JSONObject();
		JSONObject col2=new JSONObject();
		JSONArray title=new JSONArray();
		//json의 cols 객체구성(헤더,제목구성)
		col1.put("label","상품명");
		col1.put("type","string");
		col2.put("label","금액");
		col2.put("type","number");
		//json 배열에 json 객체 cnrk
		title.add(col1);
		title.add(col2);
		data.put("cols", title);
		//json의 rows 객체구성(바디, 내용구성)
		JSONArray body=new JSONArray();
		for(CartDTO dto : items) {
			JSONObject name=new JSONObject();//JSONObject는 HashMap과 같음
			name.put("v", dto.getProduct_name());
			JSONObject money=new JSONObject();
			money.put("v", dto.getMoney());
			JSONArray row=new JSONArray();
			row.add(name);
			row.add(money);
			JSONObject cell=new JSONObject();
			cell.put("c", row);
			body.add(cell);
		}
		data.put("rows", body);
		return data;
	}
}
