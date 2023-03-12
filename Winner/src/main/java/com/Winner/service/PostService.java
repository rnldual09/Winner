package com.Winner.service;


import java.util.List;
import java.util.Map;

import com.Winner.config.CustomMap;

public interface PostService {
	
	public List<Map<String,Object>> selPostList(Map<String,Object> map) throws Exception;
	
	public Map<String,Object> getPostInfo(Map<String,Object> map) throws Exception;
	
}
