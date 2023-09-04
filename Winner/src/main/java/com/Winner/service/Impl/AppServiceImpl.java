package com.Winner.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Winner.mapper.AppMapper;
import com.Winner.service.AppService;



@Service("appService")
public class AppServiceImpl implements AppService {
	
	@Autowired
	private AppMapper appMapper;
	
	@Override
	public int postAppExist(Map<String, Object> commandMap) throws Exception {
		return appMapper.selectPostAppExist(commandMap);
	}
	
	@Override
	public int insPerApp(Map<String, Object> commandMap) throws Exception {
		return appMapper.insertPersonApplication(commandMap);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class, timeout=15)
	public void insTeamApp(Map<String, Object> commandMap) throws Exception {
		int teamSeq = appMapper.selectMaxTeamSeq(commandMap); //팀 시퀀스 Max 값 구하기
		commandMap.put("teamSeq", teamSeq);
		
		appMapper.insertTeamApplication(commandMap);//팀 insert
		
		/** 팀원들 insert */
		List<Map<String,Object>> teamMembers = (List<Map<String, Object>>) commandMap.get("teamMembers");
		for(Map<String,Object> map : teamMembers) {
			commandMap.put("usrId", map.get("memId"));
			commandMap.put("grdSeq", map.get("memGrd"));
			appMapper.insertPersonApplication(commandMap);
		}
	}

	@Override
	public Map<String, Object> selMyPostInfo(Map<String, Object> commandMap) throws Exception {
		
		Map<String, Object> myPostInfo = new HashMap<>();
		
		int myPostListCnt = appMapper.selMyPostListCnt(commandMap);
		List<Map<String,Object>> myPostList = appMapper.selMyPostList(commandMap);
		
		myPostInfo.put("myPostList", myPostList);
		myPostInfo.put("myPostListCnt", myPostListCnt);
		
		return myPostInfo;
	}

	@Override
	public Map<String, Object> selApplyTeamInfo(Map<String, Object> commandMap) throws Exception {
		
		Map<String, Object> applyTeamInfo = new HashMap<>();
		
		int applyTeamCnt = appMapper.selApplyTeamCnt(commandMap);
		List<Map<String,Object>> applyTeamList = appMapper.selApplyTeamList(commandMap);
		
		applyTeamInfo.put("applyTeamCnt", applyTeamCnt);
		applyTeamInfo.put("applyTeamList", applyTeamList);
		
		return applyTeamInfo;
	}

	@Override
	public boolean saveConfirmYn(Map<String, Object> commandMap) throws Exception {

		boolean result = true;
		
		try {
			
			List<String> teamSeqArr = (List<String>) commandMap.get("teamSeqArr");
			
			for(int i=0; i<teamSeqArr.size(); i++) {
				commandMap.put("teamSeq", teamSeqArr.get(i));
				result = appMapper.saveConfirmYn(commandMap);
				
				if(!result) {
					break;
				}
			}
			
		} catch(Exception e) {
			result = false;
			System.out.print(e);
		} finally {
			// rollback 처리
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> getPostGradeList(Map<String, Object> commandMap) throws Exception {
		List<Map<String, Object>> getPostGradeList = appMapper.getPostGradeList(commandMap);
		return getPostGradeList;
	}
}
