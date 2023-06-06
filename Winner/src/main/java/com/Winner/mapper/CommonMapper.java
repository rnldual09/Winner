package com.Winner.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
	// 코드리스트조회
	List<Map<String, Object>> getCodeList(Map<String, Object> commandMap);
	// 지역리스트조회
	List<Map<String, Object>> getAreaList(Map<String, Object> commandMap);
	// 지역명가져오기
	String getAreaNm(String codCd);
}