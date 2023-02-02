package com.ict.edu_D;

import java.io.Serializable;
import java.util.List;

public class D_Protocol implements Serializable {
	
	// 통신규약(규칙)
	// cmd => 0 : 종료(접속해제)
	// cmd => 1 : 회원가입 (삽입)
	// cmd => 2 : 회원가입 아이디 중복검사
	// cmd => 3 : 사원번호 조회후 출력
	// cmd => 4 : 로그인 id 중복검사
	// cmd => 5 : 로그인 pw 중복검사
	// cmd => 6 : 급여명세서
	
	//관리자, 연차 cmd
	// cmd => 401 : 전체보기 => 결과가 list 이므로 list 필요
	// cmd => 402 : 삽입 => int result
	// cmd => 403 : 삭제 => int result
	// cmd => 405 : 불러오기 => AnnVO avo;
	// cmd => 406 : 수정 => int result

	
	// 근퇴 cmd
	// cmd => 201 : 출근 날짜 추가(삽입)
	// cmd => 203 : 퇴근 날짜 업데이트
	// cmd => 205 : 사원한명 출퇴근 조회(유저)
	// cmd => 206 : 관리자 - 사원한명 출퇴근조회
	// cmd => 207 : 관리자 - 출근날짜 추가 
	// cmd => 208 : 관리자 - 퇴근날짜 업데이트

	// 회원가입, 로그인, 급여, 기본정보, 공지사항
	int cmd;
	String msg;
	List<signup_in_VO> list;
	Pay_VO payVo;
	signup_in_VO vo;
	InfoVO infovo;
	int result;
	String string;
	MembersVO Memvo;
	NotiVO notivo;
	NotimainVO notimainvo;
	List<NotimainVO> notimainlist;
	
	// 근태
	List<WorkVO> w_list;
	WorkVO wvo;
	
	
	// 관리자, 연차 
	List<AnnVO> annlist;
	AnnVO avo; 
	List<AdVO> adlist; 
	AdVO dvo;
	
	
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<signup_in_VO> getList() {
		return list;
	}
	public void setList(List<signup_in_VO> list) {
		this.list = list;
	}
	public Pay_VO getPayVo() {
		return payVo;
	}
	public void setPayVo(Pay_VO payVo) {
		this.payVo = payVo;
	}
	public signup_in_VO getVo() {
		return vo;
	}
	public void setVo(signup_in_VO vo) {
		this.vo = vo;
	}
	public InfoVO getInfovo() {
		return infovo;
	}
	public void setInfovo(InfoVO infovo) {
		this.infovo = infovo;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public MembersVO getMemvo() {
		return Memvo;
	}
	public void setMemvo(MembersVO memvo) {
		Memvo = memvo;
	}
	public NotiVO getNotivo() {
		return notivo;
	}
	public void setNotivo(NotiVO notivo) {
		this.notivo = notivo;
	}
	public NotimainVO getNotimainvo() {
		return notimainvo;
	}
	public void setNotimainvo(NotimainVO notimainvo) {
		this.notimainvo = notimainvo;
	}
	public List<NotimainVO> getNotimainlist() {
		return notimainlist;
	}
	public void setNotimainlist(List<NotimainVO> notimainlist) {
		this.notimainlist = notimainlist;
	}
	public List<WorkVO> getW_List() {
		return w_list;
	}
	public void setW_List(List<WorkVO> list) {
		this.w_list = list;
	}
	
	public WorkVO getW_Vo() {
		return wvo;
	}
	public void setW_Vo(WorkVO wvo) {
		this.wvo = wvo;
	}
	public List<AnnVO> getAnnlist() {
		return annlist;
	}
	public void setAnnlist(List<AnnVO> annlist) {
		this.annlist = annlist;
	}
	public AnnVO getAvo() {
		return avo;
	}
	public void setAvo(AnnVO avo) {
		this.avo = avo;
	}
	public List<AdVO> getAdlist() {
		return adlist;
	}
	public void setAdlist(List<AdVO> adlist) {
		this.adlist = adlist;
	}
	public AdVO getDvo() {
		return dvo;
	}
	public void setDvo(AdVO dvo) {
		this.dvo = dvo;
	}

}
