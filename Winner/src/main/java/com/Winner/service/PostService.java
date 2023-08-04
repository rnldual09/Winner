package com.Winner.service;


import java.util.List;
import java.util.Map;

import com.Winner.config.CustomMap;

public interface PostService {
	
	public List<Map<String,Object>> selPostList(Map<String,Object> map) throws Exception;
	
	public Map<String,Object> getPostInfo(Map<String,Object> map) throws Exception;

	public String getPostSeq() throws Exception;
	
	public void insertPostMst(Map<String, Object> commandMap) throws Exception;

	public void insertPostImg(Map<String, Object> commandMap) throws Exception;

	public void insertGrdMst(Map<String, Object> commandMap) throws Exception;

	public void updatePostMst(Map<String, Object> commandMap) throws Exception;

	public List<Map<String,Object>> getCommentList(Map<String,Object> commandMap) throws Exception;

	public int insertComment(Map<String, Object> commandMap) throws Exception;

}
