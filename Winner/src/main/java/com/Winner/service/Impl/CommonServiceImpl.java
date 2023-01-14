package com.Winner.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Winner.mapper.CommonMapper;
import com.Winner.service.CommonService;



@Service("commonService")
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	private CommonMapper commonMapper;
	
	
	@Override
	public HashMap<String,Object> usrProfile(Map<String,Object> map) {
		
		HashMap<String,Object> usrProfile = commonMapper.usrProfile(map);
		
		return usrProfile;
	}
	
}
