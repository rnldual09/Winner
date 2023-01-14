package com.Winner.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.Winner.config.CustomMap;

@Mapper
public interface CommonMapper {
	
	//사용자 프로필
	HashMap<String,Object> usrProfile(Map<String,Object> map);
}