package com.Winner.service;

import java.util.List;
import java.util.Map;

public interface UserService {

	List<Map<String, Object>> getUserList(Map<String, Object> commandMap) throws Exception;
		
}
