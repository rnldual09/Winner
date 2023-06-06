package com.Winner.service;


import java.util.List;
import java.util.Map;

public interface CommonService {

	public List<Map<String, Object>> getCodeList(Map<String, Object> commandMap) throws Exception;

	public List<Map<String, Object>> getAreaList(Map<String, Object> commandMap) throws Exception;
	
	public String getAreaNm(String usrArea) throws Exception;
}
