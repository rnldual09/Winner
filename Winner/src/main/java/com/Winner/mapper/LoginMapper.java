package com.Winner.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	
	int userLogin(HashMap<String, Object> map);
}