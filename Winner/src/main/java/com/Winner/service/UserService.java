package com.Winner.service;

import java.util.List;
import java.util.Map;

public interface UserService {
	
	// 사용자 리스트
	List<Map<String, Object>> getUserList(Map<String, Object> commandMap) throws Exception;
	
	// 친구 리스트
	List<Map<String, Object>> getMateList(Map<String, Object> commandMap) throws Exception;

	// 친구테이블 데이터 인서트(요청, 수락)
	int insertMate(Map<String, Object> commandMap) throws Exception;

	// 받은 친구요청리스트조회 
	List<Map<String, Object>> getReceiveMateRequestList(Map<String, Object> commandMap) throws Exception;

	// 보낸 친구요청리스트조회 
	List<Map<String, Object>> selectSendMateRequestList(Map<String, Object> commandMap) throws Exception;
	
	// 친구요청알림삭제여부 업데이트 delYn : Y 요청삭제, N 재요청
	int updateDelYn(Map<String, Object> commandMap) throws Exception;

	// 보낸친구요청삭제
	int deleteMateRequest(Map<String, Object> commandMap) throws Exception;

	// 친구리스트
	List<Map<String, Object>> selectMateList(Map<String, Object> commandMap) throws Exception;

	// 친구삭제
	int deleteMate(Map<String, Object> commandMap) throws Exception;

}
