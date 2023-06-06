package com.Winner.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
		String[] postArea2Arr = ((String) map.get("postArea2")).split("/");
		map.put("postArea2", postArea2Arr);		
		
		List<Map<String,Object>> selPostList = postMapper.selPostList(map);
		
		for(Map<String,Object> postMap : selPostList) {
			int postSeq = Integer.parseInt(postMap.get("postSeq").toString());
			List<?> selPostImgList = postMapper.selPostImgList(postSeq);
			postMap.put("imglist", selPostImgList);
			retPostList.add(postMap);
		}
		
		return retPostList;
	}
	
	@Override
	public Map<String,Object> getPostInfo(Map<String,Object> map) {
		
		Map<String,Object> postInfoMap = new HashMap<>();
		
		Map<String,Object> postInfo = postMapper.getPostInfo(map);
		List<Map<String,Object>> grdList = postMapper.selGrdList(map);
		List<?> selPostImgList = postMapper.selPostImgList((int) postInfo.get("postSeq"));
		
		postInfoMap.put("postInfo", postInfo);
		postInfoMap.put("grdList", grdList);
		postInfoMap.put("selPostImgList", selPostImgList);
		
		return postInfoMap;
	}

	@Override
	public String getPostSeq() throws Exception {
		return postMapper.getPostSeq();
	}
	
	// @Transactional(rollbackFor = Exception.class, timeout=15)
	@Override
	public void insertPostMst(Map<String, Object> commandMap) throws Exception {		
		postMapper.insertPostMst(commandMap);
		insertPostImg(commandMap);
		insertGrdMst(commandMap);
	}

	@Override
	public void insertPostImg(Map<String, Object> commandMap) throws Exception {
		
		String postSeq = (String) commandMap.get("postSeq");
		
		// 게시글 이미지는 필수선택이 아니므로 빈 값이 넘어올 수 있음
		String oriFile = (String) commandMap.get("oriFile");
		String svrFile = (String) commandMap.get("svrFile");
		
		if(!"".equals(oriFile) && !"".equals(svrFile)) {
			
			String[] oriFileArr = oriFile.split("`");
			String[] svrFileArr = svrFile.split("`");
		
			for(int i=0; i<oriFileArr.length; i++) {
				
				Map<String, Object> fileMap = new HashMap<>();
				
				fileMap.put("oriFile", oriFileArr[i]);
				fileMap.put("svrFile", svrFileArr[i]);
				fileMap.put("postSeq", postSeq);
				fileMap.put("imgSeq", i+1);
				
				postMapper.insertPostImg(fileMap);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void insertGrdMst(Map<String, Object> commandMap) throws Exception {
		
		String postSeq = (String) commandMap.get("postSeq");
		
		// {'grdNm':등급명, 'grdCnt':등급인원수} 와 같은형태로 데이터 넘어온다
		List<Map<String ,String>> grdList = (List<Map<String, String>>) commandMap.get("grdArr");
		
		if(grdList.size() != 0) {
			
			for(int i=0; i<grdList.size(); i++) {

				Map<String, Object> grdMap = new HashMap<>();
				
				grdMap.put("grdNm", grdList.get(i).get("grdNm"));
				grdMap.put("grdCnt", grdList.get(i).get("grdCnt"));
				grdMap.put("postSeq", postSeq);
				grdMap.put("grdSeq", i+1);
				
				postMapper.insertGrdMst(grdMap);
			}
		}
	}

	@Override
	public void updatePostMst(Map<String, Object> commandMap) throws Exception {
		postMapper.updatePostMst(commandMap);
		
		deletePostImg(commandMap);
		deleteGrdMst(commandMap);
		
		insertPostImg(commandMap);
		insertGrdMst(commandMap);
	}

	private void deleteGrdMst(Map<String, Object> commandMap) {
		postMapper.deleteGrdMst(commandMap);
	}

	private void deletePostImg(Map<String, Object> commandMap) {
		postMapper.deletePostImg(commandMap);
	}

}
