package com.Winner.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Winner.service.CommonService;



@Controller("commonController")
public class CommonController {
	
	private final Logger logger = LoggerFactory.getLogger(CommonController.class.getName());
	
	@Autowired
	private CommonService commonService;
	
	@Value("#{system['common.usrImgFilePath']}")
	private String usrImgFilePath;
	
	/** 
	 * @Date 2023.01.14
	 * @author 박윤진
	 * @deprecated 사용자 프로필
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@RequestMapping(value = "/common/usrProfile.do")
	public void usrProfile(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> commandMap) throws Exception{
		try {
			final String PATH = usrImgFilePath;
			String imgFileName = (String)commandMap.get("imgFileName");
			
			File imgFile = new File(PATH+imgFileName);
			
			InputStream in = new BufferedInputStream(new FileInputStream(imgFile));
			BufferedOutputStream out = new
			BufferedOutputStream(response.getOutputStream());
			
			int read = 0;
			
			byte[] b = new byte[8192];
			
			while((read = in.read(b)) != -1) { out.write(b,0,read); }
			
			out.close(); in.close();
			
		}catch (FileNotFoundException e) {
			logger.info(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @Date 2023.04.23
	 * @author 금길영
	 * @deprecated 코드테이블 리스트
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/common/getCodeList.do")
	@ResponseBody
	public List<Map<String,Object>> getCodeList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		List<Map<String,Object>> getCodeList = commonService.getCodeList(commandMap);
		return getCodeList;
	}
	
	/** 
	 * @Date 2023.05.17
	 * @author 금길영
	 * @deprecated 지역리스트 가져오기
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/common/getAreaList.do")
	@ResponseBody
	public List<Map<String,Object>> getAreaList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		List<Map<String,Object>> getAreaList = commonService.getAreaList(commandMap);
		return getAreaList;
	}
}
