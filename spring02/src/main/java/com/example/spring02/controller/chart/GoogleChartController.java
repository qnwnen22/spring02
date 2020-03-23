package com.example.spring02.controller.chart;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.chart.GoogleChartService;

@RestController //ajax => json으로 리턴 (스프링4.0부터 지원)
//만약 일반 Controller로 쓰게 되면 메소드에 @ResponseBody를 써야
//json으로 리턴
@RequestMapping("chart/*")
public class GoogleChartController {
	
	@Inject
	GoogleChartService googleChartService;
	
	@RequestMapping("chart1.do")
	public ModelAndView chart1() {
		return new ModelAndView("chart/chart01");
	}
	
	@RequestMapping("chart2.do")
	public ModelAndView chart2() {
		return new ModelAndView("chart/chart02");
	}
	
	//view(jsp)로 넘어가지 않고 호출한 곳에 JSONObject를 리턴함
	@RequestMapping("cart_money_list.do")
	public JSONObject cart_money_list() {
		return googleChartService.getChartData();
	}

}
