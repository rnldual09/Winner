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
	public List<HashMap<String,Object>> selPostList(Map<String,Object> map) {
		List<HashMap<String,Object>> retPostList = new ArrayList<HashMap<String,Object>>(); //반환할 리스트 객체
		
		List<HashMap<String,Object>> selPostList = postMapper.selPostList(map);
		
		for(HashMap<String,Object> postMap : selPostList) {
			int postSeq = Integer.parseInt(postMap.get("postSeq").toString());
			List<CustomMap> selPostImgList = postMapper.selPostImgList(postSeq);
			int goodCnt = postMapper.postGoodCnt(postSeq);
			postMap.put("imgList", selPostImgList);
			postMap.put("goodCnt", goodCnt);
			retPostList.add(postMap);
		}
		
		return retPostList;
	}
	
}
