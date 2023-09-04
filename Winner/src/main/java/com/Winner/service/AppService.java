package com.Winner.service;

import java.util.List;
import java.util.Map;

public interface AppService {

	public int postAppExist(Map<String, Object> commandMap) throws Exception;
	
	public int insPerApp(Map<String, Object> commandMap) throws Exception;
	
	public void insTeamApp(Map<String, Object> commandMap) throws Exception;

	public Map<String, Object> selMyPostInfo(Map<String, Object> commandMap) throws Exception;

	public Map<String, Object> selApplyTeamInfo(Map<String, Object> commandMap) throws Exception;

	public boolean saveConfirmYn(Map<String, Object> commandMap) throws Exception;

	public List<Map<String, Object>> getPostGradeList(Map<String, Object> commandMap) throws Exception;
}
