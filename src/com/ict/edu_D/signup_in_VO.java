package com.ict.edu_D;

import java.io.Serializable;

public class signup_in_VO implements Serializable {

	
	private int COMID;
	private String IDX;
	private String PW;
	private String USERNAME;
	private String BIRTHDATE;
	private String ABODE;
	private String TELEPHONE;
	private String PHONE;
	private String JOINDATE;
	private String EMAIL;
	private String mid;
	private String mpw;
	private String jposition, division, imgupdate, imgdelete;
	
	
	public int getCOMID() {
		return COMID;
	}
	public void setCOMID(int cOMID) {
		COMID = cOMID;
	}
	public String getIDX() {
		return IDX;
	}
	public void setIDX(String iDX) {
		IDX = iDX;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getBIRTHDATE() {
		return BIRTHDATE;
	}
	public void setBIRTHDATE(String bIRTHDATE) {
		BIRTHDATE = bIRTHDATE;
	}
	public String getABODE() {
		return ABODE;
	}
	public void setABODE(String aBODE) {
		ABODE = aBODE;
	}
	public String getTELEPHONE() {
		return TELEPHONE;
	}
	public void setTELEPHONE(String tELEPHONE) {
		TELEPHONE = tELEPHONE;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getJOINDATE() {
		return JOINDATE;
	}
	public void setJOINDATE(String jOINDATE) {
		JOINDATE = jOINDATE;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getJposition() {
		return jposition;
	}
	public void setJposition(String jposition) {
		this.jposition = jposition;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getImgupdate() {
		return imgupdate;
	}
	public void setImgupdate(String imgupdate) {
		this.imgupdate = imgupdate;
	}
	public String getImgdelete() {
		return imgdelete;
	}
	public void setImgdelete(String imgdelete) {
		this.imgdelete = imgdelete;
	}

}
