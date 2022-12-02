package com.Winner.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.Winner.service.LoginService;



@Controller("loginController")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	/** 
	 * 2022.11.19 박윤진
	 * 
	 * 로그인 로직 처리
	 * 
	 * */
	@GetMapping(value = "/login.do")
	public  void login()  throws Exception {
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("id", "rnldual09");
		map.put("password", "12345");
		
		int cnt = loginService.userLogin(map);
		
		System.out.println("cnt : " + cnt);		
	}

	/*
	 * commit test
	 * 
	 * */
	
}
