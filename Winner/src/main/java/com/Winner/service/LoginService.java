package com.Winner.service;

import com.Winner.LoginVO;

public interface LoginService {
	
	public LoginVO userLogin(LoginVO loginVO) throws Exception;

	public int checkDupId(LoginVO loginVO);

	public int insertMember(LoginVO loginVO);
	
}
