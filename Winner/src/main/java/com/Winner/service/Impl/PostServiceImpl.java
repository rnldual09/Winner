package com.Winner.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Winner.config.CustomMap;
import com.Winner.mapper.CommonMapper;
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
	
	@Transactional(rollbackFor = Exception.class, timeout=15)
	@Override
	public boolean insertPostMst(Map<String, Object> commandMap) throws Exception {		
		
		boolean result = true;
		
		try {
			
			String postSeq = postMapper.getPostSeq();
			commandMap.put("postSeq", postSeq);
			
			// 게시글 정보입력
			postMapper.insertPostMst(commandMap);
			
			// 게시글 이미지 입력						
			insertPostImg(commandMap);
			
			// 게시글 등급 및 포지션 입력
			insertGrdMst(commandMap);
			
		} catch (Exception e) {
			return false;
		}
		
		return result;
	}
	
	public void insertPostImg(Map<String, Object> commandMap) throws Exception {
		
		List<Map<String, String>> fileDataList = (List<Map<String, String>>) commandMap.get("fileData");
		
		if(fileDataList.size() != 0) {
			
			for(int i=0; i<fileDataList.size(); i++) {
				
				Map<String, Object> fileMap = new HashMap<>();
				
				fileMap.put("postSeq", (String) commandMap.get("postSeq"));
				fileMap.put("imgSeq", i+1);
				fileMap.put("oriFile", fileDataList.get(i).get("oriFile"));
				fileMap.put("svrFile", fileDataList.get(i).get("svrFile"));
				
				postMapper.insertPostImg(fileMap);
			}
		}
	}
	
	public void insertGrdMst(Map<String, Object> commandMap) throws Exception {
		
		List<Map<String ,String>> grdArr = (List<Map<String, String>>) commandMap.get("grdArr");
		
		if(grdArr.size() != 0) {
			
			for(int i=0; i<grdArr.size(); i++) {

				Map<String, Object> grdMap = new HashMap<>();
				
				grdMap.put("postSeq", (String) commandMap.get("postSeq"));
				grdMap.put("grdSeq", i+1);
				grdMap.put("grdNm", grdArr.get(i).get("grdNm"));
				grdMap.put("grdCnt", grdArr.get(i).get("grdCnt"));
				
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

	@Override
	public List<Map<String,Object>> getCommentList(Map<String,Object> commandMap) {
		
		List<Map<String,Object>> resultList = new ArrayList<>();
		List<Map<String,Object>> commentList = postMapper.getCommentList(commandMap);
		
		if(commentList != null) {
			
			for(int i=0; i<commentList.size(); i++) {
				
				int cmntSeq = (int) commentList.get(i).get("cmntSeq");
				commandMap.put("cmntSeq", cmntSeq);
				
				List<Map<String,Object>> commentDetailList = postMapper.getCommentDetailList(commandMap);
				
				Map<String,Object> resultMap = new HashMap<>();
				resultMap.put("commentList", commentList.get(i));
				resultMap.put("commentDetailList", commentDetailList);
				
				resultList.add(resultMap);
			}
		}
		
		return resultList;
	}

	@Override
	public int insertComment(Map<String, Object> commandMap) throws Exception {
		
		int result = 0;
		
		/*
		 *  cmntSeq : 댓글번호
		 *  댓글번호가 있을시에는 답글 / 없을시에는 댓글 
		 */
		String cmntSeq = String.valueOf(commandMap.get("cmntSeq"));
		
		if(cmntSeq != null && !"".equals(cmntSeq)) {
			result = postMapper.insertDetailComment(commandMap);
		} else {
			result = postMapper.insertComment(commandMap);
		}
		
		return result;
	}

}
