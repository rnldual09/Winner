package com.Winner.service;


import java.util.List;
import java.util.Map;

import com.Winner.config.CustomMap;

public interface PostService {
	
	public List<CustomMap> selPostList(Map<String,Object> map) throws Exception;
	
}
