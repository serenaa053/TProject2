package com.ict.edu_D;

import java.io.Serializable;

public class MembersVO implements Serializable {
	// xxxVO는 원하는 테이블의 컬럼명과 일치
	// MEMBERS TABLE : USERNAME(이름), COMID(사번), JOINDATE(입사일), PHONE(핸드폰),
	// EMAIL(이메일), ID
	// INFO TABLE : DIVISION(부서), position(직급)
	
	private String USERNAME, COMID, PHONE, EMAIL, JOINDATE, IDX;
	private String division, jposition, imgupdate;
	
	
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getCOMID() {
		return COMID;
	}
	public void setCOMID(String cOMID) {
		COMID = cOMID;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getJOINDATE() {
		return JOINDATE;
	}
	public void setJOINDATE(String jOINDATE) {
		JOINDATE = jOINDATE;
	}
	public String getIDX() {
		return IDX;
	}
	public void setIDX(String iDX) {
		IDX = iDX;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getJposition() {
		return jposition;
	}
	public void setJposition(String jposition) {
		this.jposition = jposition;
	}
	public String getImgupdate() {
		return imgupdate;
	}
	public void setImgupdate(String imgupdate) {
		this.imgupdate = imgupdate;
	}

}