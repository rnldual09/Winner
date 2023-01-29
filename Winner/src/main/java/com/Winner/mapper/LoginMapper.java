package com.Winner.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.Winner.LoginVO;

@Mapper
public interface LoginMapper {
	
	LoginVO userLogin(LoginVO loginVO);
}