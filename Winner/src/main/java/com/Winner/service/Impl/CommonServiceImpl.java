package com.Winner.service.Impl;

import java.util.HashMap;
import java.util.List;
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
	public List<Map<String, Object>> getCodeList(Map<String, Object> commandMap) throws Exception {		
		return commonMapper.getCodeList(commandMap);
	}
	
}
