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
	@PostMapping(value = "/user/getUserList.do")
	@ResponseBody
	public List<Map<String,Object>> userList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		List<Map<String,Object>> userList = userService.getUserList(commandMap);		
		return userList;
	}
	
	/** 
	 * @Date 2023.06.06
	 * @author 박윤진
	 * @deprecated 친구 리스트
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/user/getMateList.do")
	@ResponseBody
	public List<Map<String,Object>> getMateList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		List<Map<String,Object>> mateList = userService.getMateList(commandMap);		
		return mateList;
	}
	
	/** 
	 * @Date 2023.06.11
	 * @author 금길영
	 * @deprecated 친구테이블 데이터 인서트(요청, 수락)
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/user/insertMate.do")
	@ResponseBody
	public Map<String,Object> sendMateRequest(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		int result = userService.insertMate(commandMap);		
		resultMap.put("result", result);
		
		return resultMap;
	}
	
	/** 
	 * @Date 2023.06.11
	 * @author 금길영
	 * @deprecated 받은 친구요청리스트조회 
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/user/receiveMateRequestList.do")
	@ResponseBody
	public List<Map<String,Object>> receiveMateRequestList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		List<Map<String,Object>> receiveMateRequestList = userService.getReceiveMateRequestList(commandMap);		
		return receiveMateRequestList;
	}
	
	/** 
	 * @Date 2023.06.11
	 * @author 금길영
	 * @deprecated 보낸 친구요청리스트조회 
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/user/sendMateRequestList.do")
	@ResponseBody
	public List<Map<String,Object>> sendMateRequestList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		List<Map<String,Object>> sendMateRequestList = userService.selectSendMateRequestList(commandMap);		
		return sendMateRequestList;
	}

	/** 
	 * @Date 2023.06.11
	 * @author 금길영
	 * @deprecated 친구요청삭제여부 업데이트 
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/user/updateDelYn.do")
	@ResponseBody
	public Map<String,Object> updateDelYn(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		Map<String,Object> resultMap = new HashMap<>();
		
		// 친구요청삭제여부 업데이트 delYn : Y 요청삭제, N 재요청 
		int result = userService.updateDelYn(commandMap);		
		resultMap.put("result", result);
		
		return resultMap;
	}
	
	/** 
	 * @Date 2023.06.11
	 * @author 금길영
	 * @deprecated 보낸친구요청삭제 
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/user/deleteMateRequest.do")
	@ResponseBody
	public Map<String,Object> deleteMateRequest(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		Map<String,Object> resultMap = new HashMap<>();
		
		int result = userService.deleteMateRequest(commandMap);		
		resultMap.put("result", result);
		
		return resultMap;
	}
	
	/** 
	 * @Date 2023.06.11
	 * @author 금길영
	 * @deprecated 친구리스트
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/user/mateList.do")
	@ResponseBody
	public List<Map<String,Object>> mateList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		List<Map<String,Object>> mateList = userService.selectMateList(commandMap);		
		return mateList;
	}
	
	/** 
	 * @Date 2023.06.11
	 * @author 금길영
	 * @deprecated 친구삭제
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/user/deleteMate.do")
	@ResponseBody
	public Map<String,Object> deleteMate(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		Map<String,Object> resultMap = new HashMap<>();
		
		int result = userService.deleteMate(commandMap);
		resultMap.put("result", result);
		
		return resultMap;
	}
}