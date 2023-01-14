package com.Winner.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Winner.config.CustomMap;

public interface PostService {
	
	public List<HashMap<String,Object>> selPostList(Map<String,Object> map) throws Exception;
	
}
