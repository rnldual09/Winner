package com.Winner.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppMapper {

	//이미 신청한 게시글인지 확인
	int selectPostAppExist(Map<String, Object> commandMap);
	
	//개인단위 신청
	int insertPersonApplication(Map<String, Object> commandMap);
	
	//해당 게시글 팀 Max 시퀀스 구하기
	int selectMaxTeamSeq(Map<String, Object> commandMap);
	
	//팀 신청
	int insertTeamApplication(Map<String, Object> commandMap);
}