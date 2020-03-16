package com.example.spring02.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter{//1. HandlerInterceptorAdapater 상속 받기

//	2. 로깅 처리
	private static final Logger logger = LoggerFactory.getLogger(SampleInterceptor.class);
	
//	3. ctrl + spacebar를 눌러서 preHandle를 오버라이딩
	//선처리
	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			logger.info("pre handle..."); //로깅처리
			return true; //true이면 계속 진행, false이면 멈춤
		}
//	4. ctrl + spacebar를 눌러서 postHandle를 오버라이딩
	//후처리
	@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			logger.info("post handel...");
		
		}
//	5. servlet-context.xml 파일 열기
//	56번째 줄에 코드 추가 
	/*
	 * <!-- 인터셉터 빈을 등록 --> <beans:bean id="sampleIntercepter"
	 * class="com.example.spring02.interceptor.SampleInterceptor"> </beans:bean>
	 * 
	 * <!-- 인터셉터 호출을 위한 url mapping --> <interceptors> <interceptor> <mapping
	 * path="/shop/**"/> <beans:ref bean="SampleInterceptor"></beans:ref>
	 * </interceptor> </interceptors>
	 */
}
