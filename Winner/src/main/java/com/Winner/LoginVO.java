package com.Winner;

import java.io.Serializable;

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information
 * @
 * @  수정일			수정자					수정내용
 * @ ---------------------------------------------------
 * @ 2023.01.29			박윤진					최초 생성
 *
 *  @since 2023.01.29
 *  @version 1.0
 */
public class LoginVO implements Serializable{
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	/** 아이디 */
	private String usrId;
	/** 비밀번호 */
	private String usrPw;
	/** 성명 */
	private String usrNm;
	/** 연락처 */
	private String usrPh;
	/** 생년월일 */
	private String birthDt;
	/** 성별 */
	private String usrSex;
	/** 접속 ip */
	private String ip;
	/** 락 상태 */
	private String lockYn;
	/** 위치1 */
	private String usrArea1;
	/** 위치2 */
	private String usrArea2;
	/** 프로필 */
	private String profile;
	/** 마지막 비번 변경일 */
	private String chgPwYn;
	
	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrPw() {
		return usrPw;
	}

	public void setUsrPw(String usrPw) {
		this.usrPw = usrPw;
	}

	public String getUsrNm() {
		return usrNm;
	}

	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}

	public String getUsrPh() {
		return usrPh;
	}

	public void setUsrPh(String usrPh) {
		this.usrPh = usrPh;
	}

	public String getBirthDt() {
		return birthDt;
	}

	public void setBirthDt(String birthDt) {
		this.birthDt = birthDt;
	}

	public String getUsrSex() {
		return usrSex;
	}

	public void setUsrSex(String usrSex) {
		this.usrSex = usrSex;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLockYn() {
		return lockYn;
	}

	public void setLockYn(String lockYn) {
		this.lockYn = lockYn;
	}

	public String getUsrArea1() {
		return usrArea1;
	}

	public void setUsrArea1(String usrArea1) {
		this.usrArea1 = usrArea1;
	}

	public String getUsrArea2() {
		return usrArea2;
	}

	public void setUsrArea2(String usrArea2) {
		this.usrArea2 = usrArea2;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getChgPwYn() {
		return chgPwYn;
	}

	public void setChgPwYn(String chgPwYn) {
		this.chgPwYn = chgPwYn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
