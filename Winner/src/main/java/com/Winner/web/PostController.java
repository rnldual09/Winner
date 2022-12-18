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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Winner.config.CustomMap;
import com.Winner.service.PostService;



@Controller("postController")
public class PostController {
	
	private final Logger logger = LoggerFactory.getLogger(PostController.class.getName());
	
	@Autowired
	private PostService postService;
	
	@Value("#{system['post.imgFilePath']}")
	private String postImgFilePath;
	
	/** 
	 * @Date 2022.12.10
	 * @author 박윤진
	 * @deprecated 게시글 리스트 정보 가져오기
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@RequestMapping(value = "/post/postList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<CustomMap> postList(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		List<CustomMap> selPostList = postService.selPostList(commandMap);
		
		System.out.println(selPostList);
		
		return selPostList;
	}
	
	/**
	 * 2022.12.18 박윤진
	 * 게시글 이미지 파일 미리보기(file대상)
	 * @param commandMap
	 * @throws Exception
	 */
	@RequestMapping(value = "/post/postImageView.do")
	public void selectImagePreviewByFile(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> commandMap) throws Exception{
		try {
			final String PATH = postImgFilePath;
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
