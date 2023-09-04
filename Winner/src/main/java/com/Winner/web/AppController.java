package com.Winner.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Winner.service.AppService;



@Controller("appController")
public class AppController {
	
	private final Logger logger = LoggerFactory.getLogger(AppController.class.getName());
	
	@Autowired
	private AppService appService;
	
	/** 
	 * @Date 2023.08.30
	 * @author 박윤진
	 * @deprecated 해당 게시글이 이미 신청한 게시글인지 확인
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/app/postAppExist.do")
	@ResponseBody
	public int postAppExist(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		int insCnt = appService.postAppExist(commandMap);
		
		return insCnt;
	}
	
	/** 
	 * @Date 2023.08.30
	 * @author 박윤진
	 * @deprecated 개인단위 신청
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/app/insPerApp.do")
	@ResponseBody
	public int insPerApp(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		int insCnt = appService.insPerApp(commandMap);
		
		return insCnt;
	}
	
	/** 
	 * @Date 2023.08.30
	 * @author 박윤진
	 * @deprecated 팀단위 신청
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/app/insTeamApp.do")
	@ResponseBody
	public void insTeamApp(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		appService.insTeamApp(commandMap);
	}
	
	/** 
	 * @Date 2023.08.15
	 * @author 금길영
	 * @deprecated 내 게시글 가져오기
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/app/getMyPostList.do")
	@ResponseBody
	public Map<String,Object> getMyPostList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		Map<String,Object> selMyPostInfo = appService.selMyPostInfo(commandMap);
		return selMyPostInfo;
	}
	
	/** 
	 * @Date 2023.08.17
	 * @author 금길영
	 * @deprecated 신청 팀 가져오기
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/app/getApplyTeamList.do")
	@ResponseBody
	public Map<String,Object> getApplyTeamList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		Map<String,Object> selApplyTeamInfo = appService.selApplyTeamInfo(commandMap);
		return selApplyTeamInfo;
	}
	
	/** 
	 * @Date 2023.08.28
	 * @author 금길영
	 * @deprecated 확정취소 및 확정
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/app/saveConfirmYn.do")
	@ResponseBody
	public Map<String,Object> saveConfirmYn(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		Map<String,Object> resultMap = new HashMap<>(); 
		
		boolean result = appService.saveConfirmYn(commandMap);
		resultMap.put("result", result);
		
		return resultMap;
	}
	
	/** 
	 * @Date 2023.08.28
	 * @author 금길영
	 * @deprecated 신청팀 등급리스트 가져오기
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/app/getPostGradeList.do")
	@ResponseBody
	public List<Map<String,Object>> getPostGradeList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		List<Map<String,Object>> postGradeList = appService.getPostGradeList(commandMap);
		return postGradeList;
	}
}
