package com.Winner.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	// 유저찾기
	List<Map<String, Object>> getUserList(Map<String, Object> commandMap);
	
	// 친구리스트 가져오기
	List<Map<String, Object>> getMateList(Map<String, Object> commandMap);

	// 친구요청 수락 직전 상대방이 요청 삭제할 수 있으므로 받은요청 한번 더 확인 후 수락로직 실행
	int checkRequestCnt(Map<String, Object> map);
	
	// 친구요청수락하기 
	void updateAcceptYn(Map<String, Object> commandMap);
	
	// 친구테이블 데이터 인서트(요청, 수락)
	int insertMate(Map<String, Object> commandMap);

	// 받은 친구요청리스트조회 
	List<Map<String, Object>> getReceiveMateRequestList(Map<String, Object> commandMap);
	
	// acceptYn N시 보낸친구요청 리스트조회 acceptYn Y시 친구 리스트조회 
	List<Map<String, Object>> selectMateList(Map<String, Object> commandMap);
	
	// 친구요청알림삭제여부 업데이트 delYn : Y 요청삭제, N 재요청
	int updateDelYn(Map<String, Object> commandMap);

	// 친구요청 혹은 친구 삭제
	int deleteMate(Map<String, Object> map);
}