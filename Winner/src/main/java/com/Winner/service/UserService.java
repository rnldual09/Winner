package com.Winner.service;

import java.util.List;
import java.util.Map;

public interface UserService {
	
	//사용자 리스트
	List<Map<String, Object>> getUserList(Map<String, Object> commandMap) throws Exception;
	
	//친구 리스트
	List<Map<String, Object>> getMateList(Map<String, Object> commandMap) throws Exception;
		
}
