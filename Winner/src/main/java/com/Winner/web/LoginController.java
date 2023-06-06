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
import com.Winner.utils.CheckParamUtil;
import com.Winner.utils.Sha256Util;



@Controller("loginController")
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(CommonController.class.getName());
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CommonService commonService;
	
	/** 
	 * 2022.11.19 박윤진
	 * 
	 * 로그인 로직 처리
	 * 
	 * */
	@PostMapping(value = "/login.do")
	@ResponseBody
	public HashMap<String, Object> login(@RequestBody LoginVO loginVO, HttpServletRequest request) throws Exception {
		
		LoginVO resultVO = loginService.userLogin(loginVO);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		// 유저정보 미존재
		if(resultVO == null) {			
			map.put("status", "undefinedUser");						
		}
		// 비밀번호 변경일 3개월 지난 유저
		else if("Y".equals(resultVO.getChgPwYn())) {
			map.put("status", "changePw");			
		}
		// 잠금된 유
		else if("Y".equals(resultVO.getLockYn())) {
			map.put("status", "lockUser");			
		}
		else {
			
			// jwt token 구현하기
			String token = "ddd"; 
			
			String ip = request.getHeader("X-Forwarded-For");
			logger.info("> X-FORWARDED-FOR : " + ip);

		    if (ip == null) {
		        ip = request.getHeader("Proxy-Client-IP");
		        logger.info("> Proxy-Client-IP : " + ip);
		    }
		    if (ip == null) {
		        ip = request.getHeader("WL-Proxy-Client-IP");
		        logger.info(">  WL-Proxy-Client-IP : " + ip);
		    }
		    if (ip == null) {
		        ip = request.getHeader("HTTP_CLIENT_IP");
		        logger.info("> HTTP_CLIENT_IP : " + ip);
		    }
		    if (ip == null) {
		        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		        logger.info("> HTTP_X_FORWARDED_FOR : " + ip);
		    }
		    if (ip == null) {
		        ip = request.getRemoteAddr();
		        logger.info("> getRemoteAddr : "+ip);
		    }
		    
		    String usrAreaNm1 = commonService.getAreaNm(resultVO.getUsrArea1());
			String usrAreaNm2 = commonService.getAreaNm(resultVO.getUsrArea2());
			
			resultVO.setUsrAreaNm1(usrAreaNm1);
			resultVO.setUsrAreaNm2(usrAreaNm2);
		    
		    resultVO.setIp(ip);
		    
		    map.put("status", "success");		
			map.put("usrInfo", resultVO);
			map.put("token", token);
		}
				
		return map;
	}
	
	@RequestMapping(value = "/common/testPyj.do", method = RequestMethod.GET) 
	public void testPyj(HttpServletRequest request, HttpServletResponse response, @RequestParam Map commandMap) throws Exception, SQLException, IOException {
		List<Map> list = new ArrayList();
		
		int cnt = 13; //토너먼트 할 총 인원(개인 or 팀) 수
		int postSeq = 1; //게시글 순번
		int touSeq = 1; // 토너먼트 순번
		
		Random rd = SecureRandom.getInstance("SHA1PRNG"); //랜덤 객체 생성
		int randomNum = 0; //부전승
		int befRandomNum = 0; //전 부선증 현재 자리
		boolean befRandomBool = false; //전 부선승 여부
		do {
			
			//부전승이 있는 경우 부전승할 순번 구하기
			if(cnt % 2 != 0) {
				while(true) {
					randomNum = (int)(rd.nextInt(cnt-1))+1;
					
					if(randomNum % 2 == 0 || (befRandomBool && befRandomNum/2+1 == randomNum)) {
						continue;
					}else {
						break;
					}
				}
				befRandomNum = randomNum;
				befRandomBool = true;
			}else {
				befRandomBool = false;
			}
			
			System.out.println("==========================================================================");
			System.out.print("<"+cnt+" 강>   ");
			if(cnt % 2 != 0) {
				System.out.print("부전승: "+randomNum);
			}
			System.out.print("\n");
			
			int nextCnt = cnt % 2 == 0 ? cnt / 2 : cnt / 2 + 1; //다음 강
			int groupCnt = cnt;
			for(int i=nextCnt; i>0; i--) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("postSeq", postSeq); // 게시글 순번 자리
				map.put("touSeq", touSeq); // 토너먼트 순번 자리
				
				map.put("touGang", cnt);
				map.put("nxtOrd", i);
				System.out.print(i+" 팀:   ");
				if(cnt % 2 != 0 && (randomNum/2+1) == i) {
					System.out.println(randomNum);
					map.put("touMap1", randomNum);
					map.put("touMap2", null);
					map.put("bujeon", 'Y');
					list.add(map);
					groupCnt--;
					continue;
				}
				map.put("touMap1", groupCnt-1);
				map.put("touMap2", groupCnt);
				map.put("bujeon", 'N');
				System.out.print((groupCnt-1)+"  "+groupCnt);
				System.out.print("\n");
				
				groupCnt = groupCnt-2;
				list.add(map);
			}
			System.out.print("\n");
			
			cnt = nextCnt;
		} while(cnt > 1);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("postSeq", postSeq); // 게시글 순번 자리
		map.put("touSeq", touSeq); // 토너먼트 순번 자리
		map.put("touGang", 1);
		map.put("nxtOrd", 1);
		map.put("touMap1", 1);
		map.put("touMap2", null);
		map.put("bujeon", 'N');
		list.add(map);
		System.out.println("==========================================================================");
		System.out.println(cnt+" 강");
		System.out.print(cnt);
		System.out.print("\n");
		
		for(Map<String,Object> listMap : list) {
			listMap.put("query", "eCusAcctDAO.insert");
			//commonService.commonInsert(listMap);
		}
		
		
	}

	/** 
	 * @Date 2023.04.29
	 * @author 금길영
	 * @deprecated 아이디중복체크
	 * @Param LoginVO loginVO
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/checkDupId.do")
	@ResponseBody
	public HashMap<String, Object> checkDupId(@RequestBody LoginVO loginVO, HttpServletRequest request) throws Exception {
		
		int cnt = loginService.checkDupId(loginVO);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String result = cnt == 0 ? "available" : "unavailable";
		map.put("result", result);
		
		return map;
	}

	/** 
	 * @Date 2023.04.29
	 * @author 금길영
	 * @deprecated 회원가입
	 * @Param LoginVO loginVO
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/insertMember.do")
	@ResponseBody
	public HashMap<String, Object> insertMember(@RequestBody LoginVO loginVO, HttpServletRequest request) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String result = "fail";

		// 중복된 아이디인지 한번 더 검사		
		int cnt = loginService.checkDupId(loginVO);
		
		if(cnt == 0) {			
			result = loginService.insertMember(loginVO) == 1 ? "success" : "fail";			
		} 
		
		map.put("result", result);
		return map;
	}
}



	
	
	


