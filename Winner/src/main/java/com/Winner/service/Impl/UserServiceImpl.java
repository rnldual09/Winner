package com.Winner.service.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
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
	
	@Override
	public List<Map<String, Object>> getMateList(Map<String, Object> commandMap) throws Exception {
		return userMapper.getMateList(commandMap);
	}

	@Override
	public int insertMate(Map<String, Object> commandMap) throws Exception {
		
		// 수락여부 (요청시에는 N 수락시에는 Y) 로 넘어옴
		String acceptYn = (String) commandMap.get("acceptYn");	
		
		Map<String, Object> map = new HashMap<>();
		map.put("usrId", (String) commandMap.get("mateId"));
		map.put("mateId", (String) commandMap.get("usrId"));
		
		/*
		 * 친구 요청 시 	cnt 0 : 정상인상태 				/ 1 : 동시요청한상태
		 * 친구 요청 수락시 cnt 0 : 수락직전 상대방이 삭제한상태 	/ 1 : 정상인상태
		 */
		int cnt = userMapper.checkRequestCnt(map);
		
		// 친구요청 수락 시
		if("Y".equals(acceptYn)) {
			if(cnt == 1) {
				userMapper.updateAcceptYn(map);				
			} else {
				return -1; // 친구요청 수락직전 상대방이 요청 삭제한 상태
			}
		// 친구요청시	
		} else {  
			if(cnt == 1) {
				userMapper.updateAcceptYn(map);
				commandMap.put("acceptYn", "Y");	// 동시요청이라면 요청상태를 Y로 수정한다
			}
		}
		
		return userMapper.insertMate(commandMap);
	}

	@Override
	public List<Map<String, Object>> getReceiveMateRequestList(Map<String, Object> commandMap) throws Exception {
		return userMapper.getReceiveMateRequestList(commandMap);
	}
	
	@Override
	public List<Map<String, Object>> selectSendMateRequestList(Map<String, Object> commandMap) throws Exception {
		// N시 요청리스트 조회 Y시 친구리스트 조회 
		commandMap.put("acceptYn", "N");
		return userMapper.selectMateList(commandMap);
	}

	@Override
	public int updateDelYn(Map<String, Object> commandMap) {
		return userMapper.updateDelYn(commandMap);
	}

	@Override
	public int deleteMateRequest(Map<String, Object> commandMap) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("usrId", (String) commandMap.get("mateId"));
		map.put("mateId", (String) commandMap.get("usrId"));
		
		int cnt = userMapper.checkRequestCnt(map);
		
		// 보낸 친구요청 삭제 직전 상대방이 요청 수락한상태
		if(cnt == 1) {
			return -1;
		} 
		
		return userMapper.deleteMate(commandMap);
	}

	@Override
	public List<Map<String, Object>> selectMateList(Map<String, Object> commandMap) {
		// N시 요청리스트 조회 Y시 친구리스트 조회 
		commandMap.put("acceptYn", "Y");
		return userMapper.selectMateList(commandMap);
	}

	@Override
	public int deleteMate(Map<String, Object> commandMap) throws SQLException {
		
		int result = 0;
		
		DataSource dataSource = new DriverManagerDataSource();
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(false);
		
		try {
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("usrId", (String) commandMap.get("mateId"));
			map.put("mateId", (String) commandMap.get("usrId"));
			
			userMapper.deleteMate(commandMap);
			userMapper.deleteMate(map);
			
			result = 1;
			connection.commit();
			
		} catch (Exception e) {						
			connection.rollback();
			result = -1;
		} 
		
		return result;
	}
	
}
