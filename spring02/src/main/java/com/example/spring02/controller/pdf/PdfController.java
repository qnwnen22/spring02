package com.example.spring02.controller.pdf;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.pdf.PdfService;

@Controller
@RequestMapping("pdf/*")
public class PdfController {
	
	@Inject
	PdfService pdfService;
	
	@RequestMapping("list.do")
	public ModelAndView list() throws Exception {
		//pdf 파일 생성
		String result=pdfService.createPdf();
		//화면 이동
		
		return new ModelAndView("pdf/result","message",result);
	}
	
}
