package com.Winner.web;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("id", "rnldual09");
		map.put("password", "12345");
		
		int cnt = loginService.userLogin(map);
		
		System.out.println("cnt : " + cnt);		
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

	/*
	 * commit test
	 * 
	 * 두번째 커밋 테스트
	 * 
	 * 세번째 커밋 테스트
	 * 
	 * 네번째 커밋 테스트
	 * 
	 * 다섯번째 커
	 * 
	 * 여섯번째 커밋
	 * 
	 * */
	
}
