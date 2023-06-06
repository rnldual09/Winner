package com.Winner.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	// 유저찾기
	List<Map<String, Object>> getUserList(Map<String, Object> commandMap);
	
}