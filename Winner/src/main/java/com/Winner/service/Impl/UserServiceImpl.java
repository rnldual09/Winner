package com.Winner.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Winner.mapper.UserMapper;
import com.Winner.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<Map<String, Object>> getUserList(Map<String, Object> commandMap) throws Exception {
		return userMapper.getUserList(commandMap);
	}
	
	
}
