package com.Winner.service;

import java.util.Map;

public interface AppService {

	public int postAppExist(Map<String, Object> commandMap) throws Exception;
	
	public int insPerApp(Map<String, Object> commandMap) throws Exception;
	
	public void insTeamApp(Map<String, Object> commandMap) throws Exception;

}
