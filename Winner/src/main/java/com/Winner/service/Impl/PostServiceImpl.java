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
	public List<CustomMap> selPostList(Map<String,Object> map) {
		List<CustomMap> retPostList = new ArrayList<CustomMap>(); //반환할 리스트 객체
		
		List<CustomMap> selPostList = postMapper.selPostList(map);
		
		for(CustomMap postMap : selPostList) {
			int postSeq = Integer.parseInt(postMap.get("postSeq").toString());
			List<CustomMap> selPostImgList = postMapper.selPostImgList(postSeq);
			int goodCnt = postMapper.postGoodCnt(postSeq);
			postMap.put("imglist", selPostImgList);
			postMap.put("goodcnt", goodCnt);
			retPostList.add(postMap);
		}
		
		return retPostList;
	}
	
}
