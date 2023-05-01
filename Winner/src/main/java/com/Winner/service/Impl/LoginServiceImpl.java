package com.Winner.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Winner.LoginVO;
import com.Winner.mapper.LoginMapper;
import com.Winner.service.LoginService;



@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public LoginVO userLogin(LoginVO loginVO) throws Exception {
		//암호화로 저장했을 때 주석 풀어주면 됌
		/*
		String encryPassword = Sha256Util.encrypt(loginVO.getUsrId()+loginVO.getUsrPw()); //sha256 단방향 암호화
		
		String Base64Pw = Base64Utils.encodeToString(encryPassword.getBytes()); //base64 인코딩
		
		loginVO.setUsrPw(Base64Pw);
		*/
		return loginMapper.userLogin(loginVO);

	}

	@Override
	public int checkDupId(LoginVO loginVO) {
		return loginMapper.checkDupId(loginVO);
	}

	@Override
	public int insertMember(LoginVO loginVO) {
		return loginMapper.insertMember(loginVO);
	}
}
