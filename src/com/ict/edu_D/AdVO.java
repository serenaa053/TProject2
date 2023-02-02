package com.ict.edu_D;

import java.io.Serializable;

public class AdVO implements Serializable{

	// 사번, 이름, 부서, 직급, 텍스트검색
	private String comid, username, division, jposition, searchText; 

	public String getComid() {
		return comid;
	}

	public void setComid(String comid) {
		this.comid = comid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	@Override
	public String toString() {
		return "AdVO [comid=" + comid + ", username=" + username + ", division=" + division + ", jposition=" + jposition
				+ ", searchText=" + searchText + "]";
	}
	
}
