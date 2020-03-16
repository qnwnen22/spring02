package com.example.spring02.controller.message;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring02.model.message.dto.MessageDTO;
import com.example.spring02.service.message.MessageService;

@RestController //스피링 4.0이상부터 사용가능 (@Controller + @ResponseBody)
@RequestMapping("messages/*") //공통적인 url mapping
public class MessageController {
	@Inject
	MessageService messageService;
	@RequestMapping(value="/", method=RequestMethod.POST) //에러 추가
	public ResponseEntity<String> addMessage(
			@RequestBody MessageDTO dto){
		//@RequestBody : 클라이언트=>서버(json 데이터 입력될 때) 리턴값이 json
		//json String => dto로 변환
		ResponseEntity<String> entity=null;
		//ResponseEntity => 리턴값(json+에러메시지)
		try {
			messageService.addMessage(dto);
			//ResponseEntity=> 에러메시지+에러코드
			entity=new ResponseEntity<>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			//400 에러 : 상호간 변수등이 안맞을 때
		}
		return entity;
	}

}
