package com.example.spring02.service.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.admin.AdminDAO;
import com.example.spring02.model.member.dto.MemberDTO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Inject
	AdminDAO adminDao;

	@Override
	public String loginCheck(MemberDTO dto) {
		return adminDao.loginCheck(dto);
	}

}
