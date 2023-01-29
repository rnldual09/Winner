package com.Winner.utils;

import java.util.HashMap;
import java.util.Map;

public class CheckParamUtil {
	
	private final static HashMap<String, String> urlMap = new HashMap<String, String>(){
		private static final long serialVersionUID = 188504916617318433L;

		{
			put("loginList","usrId/usrPw");
		}
	};
	
	public static boolean CheckParam (Map<String, Object> param, String urlType) {
		
		boolean flag = true;
		
		// 우리가 urlType name 잘못 넘겼을 때 
		if(!urlMap.containsKey(urlType+"List")) {
			flag = false;
		}else {
			// 화면에서 필요 데이터 잘 넘어왔는지 검증
			String[] chkParamArr = urlMap.get(urlType+"List").toString().split("\\/");
			
			for(String chkParam : chkParamArr) {
				if(param.containsKey(chkParam)) {
					if(param.get(chkParam) == null || param.get(chkParam).equals("")) {
						flag = false;
						break;
					}
				}else {
					flag = false;
					break;
				}
			}
		}
		
		return flag;
	}
	
}
