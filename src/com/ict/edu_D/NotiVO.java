package com.ict.edu_D;

import java.io.Serializable;

public class NotiVO implements Serializable {

	private String ntitle, ncontents, nimportant, ncount, nusername, ndelete, ndate;
	private int nnumber, comid;

	public String getNdate() {
		return ndate;
	}

	public void setNdate(String ndate) {
		this.ndate = ndate;
	}

	public String getNusername() {
		return nusername;
	}

	public int getNnumber() {
		return nnumber;
	}

	public void setNnumber(int nnumber) {
		this.nnumber = nnumber;
	}

	public int getComid() {
		return comid;
	}

	public void setComid(int comid) {
		this.comid = comid;
	}

	public void setNusername(String nusername) {
		this.nusername = nusername;
	}

	public String getNtitle() {
		return ntitle;
	}

	public String getNdelete() {
		return ndelete;
	}

	public void setNdelete(String ndelete) {
		this.ndelete = ndelete;
	}

	public String getNimportant() {
		return nimportant;
	}

	public void setNimportant(String nimportant) {
		this.nimportant = nimportant;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNcontents() {
		return ncontents;
	}

	public void setNcontents(String ncontents) {
		this.ncontents = ncontents;
	}

	public String getNcount() {
		return ncount;
	}

	public void setNcount(String ncount) {
		this.ncount = ncount;
	}
}