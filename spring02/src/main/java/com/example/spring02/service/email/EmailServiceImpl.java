package com.example.spring02.service.email;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.spring02.model.email.EmailDTO;

@Service //서비스 빈으로 등록
public class EmailServiceImpl implements EmailService {
	
	@Inject
	JavaMailSender mailSender; 
	//root-context.xml에 설정한 bean, id도 같아야함

	@Override
	public void sendMail(EmailDTO dto) {
		try {
			//MimeMessage : 이메일 객체
			MimeMessage msg=mailSender.createMimeMessage();
			//받는 사람 이메일 주소 (getReceiveMail())
			msg.addRecipient(RecipientType.TO, 
					new InternetAddress(dto.getReceiveMail()));
			//보내는 사람(이메일주소+이름)
			msg.addFrom(new InternetAddress[] {
					new InternetAddress(
							dto.getSenderMail(),dto.getSenderName())});
			//이메일 제목
			msg.setSubject(dto.getSubject(), "utf-8");
			//이메일 본문
			msg.setText(dto.getMessage(), "utf-8");
			//이메일 보내기
			mailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
