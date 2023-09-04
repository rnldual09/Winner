package com.Winner.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Winner.mapper.AppMapper;
import com.Winner.service.AppService;



@Service("appService")
public class AppServiceImpl implements AppService {
	
	@Autowired
	private AppMapper appMapper;
	
	@Override
	public int postAppExist(Map<String, Object> commandMap) throws Exception {
		return appMapper.selectPostAppExist(commandMap);
	}
	
	@Override
	public int insPerApp(Map<String, Object> commandMap) throws Exception {
		return appMapper.insertPersonApplication(commandMap);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class, timeout=15)
	public void insTeamApp(Map<String, Object> commandMap) throws Exception {
		int teamSeq = appMapper.selectMaxTeamSeq(commandMap); //팀 시퀀스 Max 값 구하기
		commandMap.put("teamSeq", teamSeq);
		
		appMapper.insertTeamApplication(commandMap);//팀 insert
		
		/** 팀원들 insert */
		List<Map<String,Object>> teamMembers = (List<Map<String, Object>>) commandMap.get("teamMembers");
		for(Map<String,Object> map : teamMembers) {
			commandMap.put("usrId", map.get("memId"));
			commandMap.put("grdSeq", map.get("memGrd"));
			appMapper.insertPersonApplication(commandMap);
		}
	}

}
