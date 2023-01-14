package com.Winner.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Winner.service.CommonService;



@Controller("commonController")
public class CommonController {
	
	private final Logger logger = LoggerFactory.getLogger(CommonController.class.getName());
	
	@Autowired
	private CommonService commonService;
	
	/** 
	 * @Date 2023.01.14
	 * @author 박윤진
	 * @deprecated 사용자 프로필
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/common/usrProfile.do")
	@ResponseBody
	public HashMap<String,Object> usrProfile(@RequestParam Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		HashMap<String,Object> usrProfile = commonService.usrProfile(commandMap);
		
		return usrProfile;
	}
	
}
