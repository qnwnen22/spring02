package com.example.spring02.service.message;

import com.example.spring02.model.message.dto.MessageDTO;

public interface MessageService {
	public void addMessage(MessageDTO dto);
	public MessageDTO readMessage(String userid, int mid);
}
