package com.Winner.service;

import com.Winner.LoginVO;

public interface LoginService {
	
	public LoginVO userLogin(LoginVO loginVO) throws Exception;

	public int checkDupId(LoginVO loginVO) throws Exception;

	public int insertMember(LoginVO loginVO) throws Exception;

	public String findMyId(LoginVO loginVO) throws Exception;

	public int changePassWord(LoginVO loginVO) throws Exception;
	
}
