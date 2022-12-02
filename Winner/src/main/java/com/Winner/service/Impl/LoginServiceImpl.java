package com.Winner.service.Impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Winner.mapper.LoginMapper;
import com.Winner.service.LoginService;



@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public int userLogin(HashMap<String, String> map) throws Exception {
		return loginMapper.userLogin(map);

	}
}
