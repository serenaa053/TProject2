package com.ict.edu_D;

import java.io.Serializable;

public class InfoVO implements Serializable{
	private int comid ;
	private String division, jposition, idx, username;
	private String imgupdate, imgdelete;
	
	
	
	public int getComid() {
		return comid;
	}
	public void setComid(int comid) {
		this.comid = comid;
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
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
