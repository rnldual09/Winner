package com.Winner.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.Winner.config.CustomMap;

@Mapper
public interface PostMapper {
	
	//게시글 리스트 가져오기
	List<Map<String,Object>> selPostList(Map<String,Object> map);
	
	//해당 게시글 이미지 리스트 가져오기
	List<Map<String,Object>> selPostImgList(int postSeq);
	
	//좋아요 갯수 가져오기
	int postGoodCnt(int postSeq);
	
	//한 개 게시글 정보
	Map<String,Object> getPostInfo(Map<String,Object> map);

	// 개사굴 번호 채번
	String getPostSeq();
	
	// 게시글 마스터 등록
	void insertPostMst(Map<String, Object> commandMap);

	// 게시글 사진등록
	void insertPostImg(Map<String, Object> commandMap);

	// 포지션 및 등급 등록
	void insertGrdMst(Map<String, Object> commandMap);

	// 등급리스트 가져오기
	List<Map<String, Object>> selGrdList(Map<String, Object> map);

	// 내 게시글 수정
	void updatePostMst(Map<String, Object> commandMap);

	// 포지션 및 등급 삭제
	void deleteGrdMst(Map<String, Object> commandMap);

	// 게시글 사진삭제
	void deletePostImg(Map<String, Object> commandMap);

	
}