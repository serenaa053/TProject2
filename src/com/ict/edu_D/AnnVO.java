package com.ict.edu_D;

import java.io.Serializable;

public class AnnVO implements Serializable {
	
		private String comid, yearann, newann, usedann, remain, useddate, division, jposition, 
		username, joindate, usedcnt, selused_date, used_DateB;

		public String getComid() {
			return comid;
		}

		public void setComid(String comid) {
			this.comid = comid;
		}

		public String getYearann() {
			return yearann;
		}

		public void setYearann(String yearann) {
			this.yearann = yearann;
		}

		public String getNewann() {
			return newann;
		}

		public void setNewann(String newann) {
			this.newann = newann;
		}

		public String getUsedann() {
			return usedann;
		}

		public void setUsedann(String usedann) {
			this.usedann = usedann;
		}

		public String getRemain() {
			return remain;
		}

		public void setRemain(String remain) {
			this.remain = remain;
		}

		public String getUseddate() {
			return useddate;
		}

		public void setUseddate(String useddate) {
			this.useddate = useddate;
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

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getJoindate() {
			return joindate;
		}

		public void setJoindate(String joindate) {
			this.joindate = joindate;
		}

		public String getUsedcnt() {
			return usedcnt;
		}

		public void setUsedcnt(String usedcnt) {
			this.usedcnt = usedcnt;
		}

		//관리자에서 선택된 날짜를 삭제하기위해 저장 및 변경하는 것.(연차 삭제)
		public String getSelused_date() {
			return selused_date;
		}

		public void setSelused_date(String selused_date) {
			this.selused_date = selused_date;
		}

		
		// 달력에서 선택된 날짜를 저장 및 변경하는 것. (연차 사용위해)
		public String getUsed_DateB() {
			return used_DateB;
		}

		public void setUsed_DateB(String used_DateB) { 
			this.used_DateB = used_DateB;
		}

		@Override
		public String toString() {
			return "AnnVO [comid=" + comid + ", yearann=" + yearann + ", newann=" + newann + ", usedann=" + usedann
					+ ", remain=" + remain + ", useddate=" + useddate + ", division=" + division + ", jposition="
					+ jposition + ", username=" + username + ", joindate=" + joindate + ", usedcnt=" + usedcnt
					+ ", selused_date=" + selused_date + ", used_DateB=" + used_DateB + "]";
		}

}
