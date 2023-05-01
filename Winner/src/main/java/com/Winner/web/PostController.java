package com.Winner.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
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
	@PostMapping(value = "/post/postList.do")
	@ResponseBody
	public List<Map<String,Object>> postList(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		List<Map<String,Object>> selPostList = postService.selPostList(commandMap);
		
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
	
	/** 
	 * @Date 2023.03.12
	 * @author 박윤진
	 * @deprecated 한 개 게시글 정보 가져오기
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/post/getPostInfo.do")
	@ResponseBody
	public Map<String,Object> getPostInfo(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		Map<String,Object> postInfoMap = postService.getPostInfo(commandMap);		
		return postInfoMap;
	}

	/** 
	 * @Date 2023.04.27
	 * @author 금길영
	 * @deprecated 게시글 등록하기
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/post/insPostMst.do")
	@ResponseBody
	public Map<String,Object> insPostMst(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		Map<String,Object> resultMap = new HashMap<>();
						
		// 게시글 번호 채번
		String postSeq = postService.getPostSeq();
		commandMap.put("postSeq", postSeq);
		
		postService.insertPostMst(commandMap);
		
		return resultMap;
	}

	/** 
	 * @Date 2023.04.30
	 * @author 금길영
	 * @deprecated 내 게시글 수정하기
	 * @Param Map<String,Object> commandMap
	 * @throws Exception, SQLException, IOException
	 * */
	@PostMapping(value = "/post/uptPostMst.do")
	@ResponseBody
	public Map<String,Object> uptPostMst(@RequestBody Map<String,Object> commandMap) throws Exception, SQLException, IOException {
		
		Map<String,Object> resultMap = new HashMap<>();
		
		postService.updatePostMst(commandMap);		
		
		return resultMap;
	}
}
