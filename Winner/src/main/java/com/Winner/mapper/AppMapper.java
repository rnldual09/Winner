package com.Winner.mapper;


import java.util.List;
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
	
	// 내 게시글 가져오기
	List<Map<String, Object>> selMyPostList(Map<String, Object> commandMap);

	// 내 게시글 전체 갯수
	int selMyPostListCnt(Map<String, Object> commandMap);

	// 신청 팀 리스트
	List<Map<String, Object>> selApplyTeamList(Map<String, Object> commandMap);
	
	// 신청 팀 전체 카운트
	int selApplyTeamCnt(Map<String, Object> commandMap);

	// 확정취소 및 확정
	boolean saveConfirmYn(Map<String, Object> commandMap);

	// 신청팀 등급리스트 가져오기
	List<Map<String, Object>> getPostGradeList(Map<String, Object> commandMap);
}