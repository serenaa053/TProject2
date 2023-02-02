package com.ict.edu_D;

import java.io.Serializable;

public class WorkVO implements Serializable{
	private String worknumber, comid, dates, username, gotime, byetime, serch1, serch2, h_w_check;

	public String getWorknumber() {
		return worknumber;
	}
	public void setWorknumber(String worknumber) {
		this.worknumber = worknumber;
	}
	
	public String getComid() {
		return comid;
	}
	
	public void setComid(String comid) {
		this.comid = comid;
	}

	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getGotime() {
		return gotime;
	}
	public void setGotime(String gotime) {
		this.gotime = gotime;
	}

	public String getByetime() {
		return byetime;
	}
	public void setByetime(String byetime) {
		this.byetime = byetime;
	}
	
	// 데이터 베이스에 없는 컬럼
	public String getSerch1() {
		return serch1;
	}
	public void setSerch1(String serch1) {
		this.serch1 = serch1;
	}
	
	public String getSerch2() {
		return serch2;
	}
	public void setSerch2(String serch2) {
		this.serch2 = serch2;
	}
	
	public String getH_w_check() {
		return h_w_check;
	}
	public void setH_w_check(String h_w_check) {
		this.h_w_check = h_w_check;
	}
}
