package com.Winner.web;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Winner.LoginVO;
import com.Winner.service.CommonService;
import com.Winner.service.LoginService;
import com.Winner.service.UserService;
import com.Winner.utils.CheckParamUtil;
import com.Winner.utils.Sha256Util;



@Controller("userController")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(CommonController.class.getName());
	
	@Autowired
	private UserService userService;
	
	/** 
	 * @Date 2023.05.11
	 * @author 금길영
	 * @deprecated 유저찾기
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/user/searchUserInfo.do")
	@ResponseBody
	public List<Map<String,Object>> userList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		List<Map<String,Object>> userList = userService.getUserList(commandMap);		
		return userList;
	}
	
}