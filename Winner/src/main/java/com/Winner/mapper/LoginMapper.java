package com.Winner.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.Winner.LoginVO;

@Mapper
public interface LoginMapper {
	
	LoginVO userLogin(LoginVO loginVO);

	int checkDupId(LoginVO loginVO);

	int insertMember(LoginVO loginVO);

	String findMyId(LoginVO loginVO);

	int changePassWord(LoginVO loginVO);
}