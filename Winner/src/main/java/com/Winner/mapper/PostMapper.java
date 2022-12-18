package com.Winner.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.Winner.config.CustomMap;

@Mapper
public interface PostMapper {
	
	//게시글 리스트 가져오기
	List<CustomMap> selPostList(Map<String,Object> map);
	
	//해당 게시글 이미지 리스트 가져오기
	List<CustomMap> selPostImgList(int postSeq);
	
	//좋아요 갯수 가져오기
	int postGoodCnt(int postSeq);
}