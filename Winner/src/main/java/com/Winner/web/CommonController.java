package com.Winner.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	
}
