package com.Winner.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Winner.config.CustomMap;
import com.Winner.mapper.PostMapper;
import com.Winner.service.PostService;



@Service("postService")
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostMapper postMapper;
	
	
	@Override
	public List<Map<String,Object>> selPostList(Map<String,Object> map) {
		List<Map<String,Object>> retPostList = new ArrayList<>(); //반환할 리스트 객체
		
		List<Map<String,Object>> selPostList = postMapper.selPostList(map);
		
		for(Map<String,Object> postMap : selPostList) {
			int postSeq = Integer.parseInt(postMap.get("postSeq").toString());
			List<?> selPostImgList = postMapper.selPostImgList(postSeq);
			int goodCnt = postMapper.postGoodCnt(postSeq);
			postMap.put("imglist", selPostImgList);
			postMap.put("goodcnt", goodCnt);
			retPostList.add(postMap);
		}
		
		return retPostList;
	}
	
	@Override
	public Map<String,Object> getPostInfo(Map<String,Object> map) {
		return postMapper.getPostInfo(map);
	}
	
}
