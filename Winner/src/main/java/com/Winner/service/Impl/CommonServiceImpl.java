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
	
	@Override
	public List<Map<String, Object>> getAreaList(Map<String, Object> commandMap) throws Exception {		
		return commonMapper.getAreaList(commandMap);
	}

	@Override
	public String getAreaNm(String usrArea) throws Exception {

		String usrNm = "";
		String[] usrAreaArr = usrArea.split("/");
		
		for(int i=0; i<usrAreaArr.length; i++) {
			usrNm = usrNm + commonMapper.getAreaNm(usrAreaArr[i]) + "/";
		}

		return usrNm.substring(0, usrNm.length()-1);
	}
	
}
