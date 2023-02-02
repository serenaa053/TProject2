package com.ict.edu_D;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class D_Client extends Thread {
	Socket s;
	D_Server server;

	ObjectInputStream in;
	ObjectOutputStream out;
	String ip;

	String saveinfoname;

	public D_Client(Socket s, D_Server server) {
		this.s = s;

		System.out.println("D_C 시작");

		this.server = server;
		try {
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			ip = s.getInetAddress().getHostAddress();
		} catch (Exception e) {
		}
	}

	public synchronized void run() {

		while (true) {
			try {
				Object obj = in.readObject();
				if (obj != null) {
					D_Protocol p = (D_Protocol) obj;
					switch (p.getCmd()) {
					case 1: // 회원가입
						String infoname = p.getInfovo().getIdx();
						saveinfoname = infoname;
						int result = D_DAO.getIns(p.getVo()); // DAO.getIns 로 출발
						D_DAO.getinfo(p.getInfovo()); // 내정보
						D_Protocol p2 = new D_Protocol();
						p2.setCmd(1);
						p2.setMsg(p.getVo().getIDX());
						p2.setResult(result);
						out.writeObject(p2);
						out.flush();
						break;

					case 2: // 아이디 중복체크
						int result3 = D_DAO.getidchkoverlap(p.getVo().getIDX()); // p에 담겨진 VO에서 저장된 IDX값을 빼내고 D_DAO에 있는
																					// getidchkoverlap을 호출 후 리턴값을
																					// string에 저장
						D_Protocol p1 = new D_Protocol();
						p1.setCmd(2);
						p1.setResult(result3); // 결과값(이미 있으면-중복 1, 없으면-사용가능 0)을 Result에 세팅(저장)
						out.writeObject(p1);
						out.flush();
						break;

					case 3: // 사원번호 조회 및 출력 사원번호는 int형
						int result2 = D_DAO.getSelectOne(p.getMsg());
						D_Protocol p3 = new D_Protocol();
						p3.setCmd(3);
						p3.setResult(result2);
						out.writeObject(p3);
						out.flush();
						break;

					case 4: // 로그인시 아이디 유무 확인
						int result4 = D_DAO.login_ID(p.getVo().getIDX());
						D_Protocol P4 = new D_Protocol();
						P4.setCmd(4);
						P4.setMsg(p.getVo().getIDX());
						P4.setResult(result4);
						out.writeObject(P4);
						out.flush();
						break;

					case 5: // 아이디 유무 확인후 비밀번호 확인\
						String result5 = D_DAO.login_PW(p.getMsg());
						System.out.println("DC 5번 result5가 비번이니?" + result5);
						D_Protocol P5 = new D_Protocol();
						P5.setCmd(5);
						P5.setMsg(result5); // pw
						P5.setString(p.getMsg()); // IDX 로그인할때 아이디를 알고 있자!!(연차때문에 필요)
						out.writeObject(P5);
						out.flush();
						break;

					case 6: // 해당 사원의 부서와 직급 확인
						D_Protocol p6 = new D_Protocol();
						Pay_VO result6 = D_DAO.getPay_VO(p.getVo());
						MembersVO mvo = D_DAO.getinfoOne(p.getMemvo());
						List<NotimainVO> list = D_DAO.getNotiMainList();
						p6.setCmd(6);
						p6.setMemvo(mvo);
						p6.setNotimainlist(list);
						p6.setPayVo(result6);
						out.writeObject(p6);
						out.flush();
						break;

					case 10: // 넘겨받은 사번으로, 관리자에서 급여관리로 이동하기
						System.out.println(p.getPayVo().getcOMID()+"안오는거야??");
						

						try {
							D_Protocol p10 = new D_Protocol();
							Pay_VO adPay10 = D_DAO.getPay_VO_A(p.getPayVo().getcOMID());
							System.out.println("안오는거야?????"+adPay10);
							p10.setCmd(10);
							p10.setPayVo(adPay10);
							out.writeObject(p10);
							out.flush();
						} catch (Exception e) {
							System.out.println("너는 왜 안찍히는거야?" + e.toString());
						}
						
						break;

					case 201: // 출근 날짜 추가
						int goResult = D_DAO.getInsGo(p.getW_Vo());
						D_Protocol go_p = new D_Protocol();
						if (p.getW_Vo().getH_w_check().equals("home")) {
							go_p.setCmd(201);
						} else if (p.getW_Vo().getH_w_check().equals("work")) {
							go_p.setCmd(202);
						} else {
							System.out.println("오류발생 확인 필요");
						}
						out.writeObject(go_p); // run으로 보낸다.
						out.flush();
						break;

					case 203: // 퇴근 날짜 업데이트
						int byeResult = D_DAO.getUpdateBye(p.getW_Vo());
						D_Protocol bye_p = new D_Protocol();
						if (p.getW_Vo().getH_w_check().equals("home")) {
							bye_p.setCmd(203);
						} else if (p.getW_Vo().getH_w_check().equals("work")) {
							bye_p.setCmd(204);
						} else {
							System.out.println("오류발생 확인 필요");
						}
						out.writeObject(bye_p);
						out.flush();
						break;

					case 205: // 사원 한명의 출퇴근 조회
						D_Protocol one_p = new D_Protocol();
						List<WorkVO> W_D_List = D_DAO.getW_D_List(p.getW_Vo());
						one_p.setCmd(205);
						one_p.setW_List(W_D_List);
						out.writeObject(one_p); // run으로 보낸다.
						out.flush();
						break;

					case 206: // 관리자 사원 한명의 출퇴근 조회
						D_Protocol a_one_p = new D_Protocol();
						List<WorkVO> A_W_D_List = D_DAO.getW_D_List(p.getW_Vo());
						a_one_p.setCmd(206);
						a_one_p.setW_List(A_W_D_List);
						out.writeObject(a_one_p); // run으로 보낸다.
						out.flush();
						break;

					case 207: // 관리자 출근 날짜 추가
						int m_goResult = D_DAO.getInsGo(p.getW_Vo());
						D_Protocol m_go_p = new D_Protocol();
						m_go_p.setCmd(207);
						out.writeObject(m_go_p); // run으로 보낸다.
						out.flush();
						break;

					case 208: // 관리자 퇴근 날짜 업데이트
						// comid 가지고 selectOne 를 이용해서 마지막 레코드의 worknumber 구하기
						int m_bye_Result = D_DAO.WorkUpdateBye(p.getW_Vo());
						D_Protocol m_bye_p = new D_Protocol();
						m_bye_p.setCmd(208);
						out.writeObject(m_bye_p);
						out.flush();
						break;

					case 210: // 관리자 사번 이름 가져오기
						D_Protocol comidname_p = new D_Protocol();
						comidname_p.setString(p.getString());
						comidname_p.setMsg(p.getMsg());
						comidname_p.setCmd(210);
						out.writeObject(comidname_p);
						out.flush();
						break;

					case 301: // 사진 파일명 데이터 베이스 저장하기
						D_DAO.getimgUpDate(p.getInfovo().getImgupdate());
						break;

					case 302: // 사원정보 수정
						System.out.println("0000");
						D_DAO.getUpadate1(p.getMemvo());
						D_DAO.getUpadate2(p.getInfovo());
						break;

					case 310: // 공지등록
						System.out.println("310");
						D_DAO.getInsNoti(p.getNotivo());
						D_Protocol p310 = new D_Protocol();
						List<NotimainVO> list310 = D_DAO.getNotiMainList();
						p310.setCmd(311);
						p310.setNotimainlist(list310);
						out.writeObject(p310);
						out.flush();

						break;

					case 311: // 카운트 올리기
						D_DAO.getNcountUpadate(p.getNotivo());
						D_Protocol p311 = new D_Protocol();
						List<NotimainVO> list311 = D_DAO.getNotiMainList();
						p311.setCmd(311);
						p311.setNotimainlist(list311);
						out.writeObject(p311);
						out.flush();
						break;

					case 312: // 해당 사원의 부서와 직급 확인
						D_Protocol p312 = new D_Protocol();
						List<NotimainVO> list312 = D_DAO.getNotiMainList();
						p312.setCmd(312);
						p312.setNotimainlist(list312);
						out.writeObject(p312);
						out.flush();
						break;

					case 313: // 해당 파일 삭제(NDELETE 컬럼 0->99로 변경)
						D_DAO.getNdeleteUpadate(p.getNotivo());
						D_Protocol p313 = new D_Protocol();
						List<NotimainVO> list313 = D_DAO.getNotiMainList();
						p313.setCmd(311);
						p313.setNotimainlist(list313);
						out.writeObject(p313);
						out.flush();
						break;

					case 314: // 공지내용 수정
						D_DAO.getUpadate(p.getNotivo());
						D_Protocol p314 = new D_Protocol();
						List<NotimainVO> list314 = D_DAO.getNotiMainList();
						p314.setCmd(311);
						p314.setNotimainlist(list314);
						out.writeObject(p314);
						out.flush();
						break;

					case 320: // comid info table update
						D_DAO.getComidUpadate(p.getInfovo());
						break;

					case 321: // 관리자에서 기본정보 조회로 넘어가기

						MembersVO info_Member = D_DAO.getadmininfoOne(p.getMemvo());
						try {
							// 테이블에서 선택된 사원정보(사번)를 가지고 D_DAO사원정보 받아서 저장 후 보내기
							D_Protocol p321 = new D_Protocol();

							p321.setCmd(321); // 한사람 정보만 불러오기
							p321.setMemvo(info_Member);

							// 보내자
							out.writeObject(p321);
							out.flush();
						} catch (Exception e) {
							System.out.println("D_client 405 연차 관리 캐치: " + e.toString());
						}
						break;

					// 관리자 : 사원 전체 리스트
					// 전체보기에서 검색까지 할 수 있기 때문에 404 검색은 401로 통합
					case 401:
						try {
							String searchText = p.getString(); // 검색한 텍스트를 담은 정보를 searchtext로 받음

							// 디비로 가자
							List<AdVO> adlist = D_DAO.getAdList(searchText);
							// 디비에서 가져온 결과를 담는것 : List<AdVO> adlist = 디비로 가는거 : DAO.getAdList();

							D_Protocol p401 = new D_Protocol();
							p401.setCmd(401);
							p401.setAdlist(adlist); // 프로토콜에 정보 저장한다.
							// 실제 보내자 : 어디로? 클라이언트 (나는 Login_A로 보낸다) --> 보내면 Login_A의 run에서 받는다.
							out.writeObject(p401);
							out.flush();
						} catch (Exception e) {
						}
						break;

					/**
					 * 연차사용날짜, 사용연차를 삽입(추가)하는 곳 입니다.(변수는 모두 스트링으로 저장되어있음?)
					 */
					case 402: // 연차 사용날짜 삽입하기, 사번과 날짜를 받아서 넘겨야 한다.

						// 화면으로부터 전달 받은 값이 제대로 넘어왔는지 확인

						System.out.println("D_C 402 왔니?");

						String used_DateB = p.getAvo().getUsed_DateB(); // 문자형으로 받은 연차날짜를 여기에 넣었다.
						String bComid = p.getAvo().getComid();

						System.out.println("D_C402 used_DateB : " + used_DateB);
						System.out.println("D_CC402 bComid : " + bComid);

						// 디비를 호출하기 전에 자료형 생성하여 데이터 셋
						AnnVO annvo = new AnnVO();

						annvo.setUsed_DateB(used_DateB);
						annvo.setComid(bComid);

						AnnVO annData402 = null; // 연차정보 가져오기

						// 디비를 호출하여 내가 하고자 하는 행위를 한다.
						int insertCnt = D_DAO.insertUsedDate(annvo);
						System.out.println("DC insertCnt  : " + insertCnt);

						annData402 = D_DAO.getaAnnData(annvo.getComid());
						// 결과값에 따라 결과값을 처리하기 위해 Login_B 로 이동하여 다음처리
						D_Protocol p402 = new D_Protocol();

						p402.setCmd(402);
						p402.setResult(insertCnt); // 0이 아니면 성공,
						p402.setAvo(annData402);

						out.writeObject(p402);
						out.flush();
						break;

					// 연차 목록의 히스토리 데이터를 삭제하는 곳입니다.
					case 403:
						System.out.println("copyclient 403 삭제 : 받았니");
						AnnVO avo = p.getAvo(); // 여기서 연차 정보를 가져옵니다.

						// 화면업데이트를 위해 가져올 데이터에 대해서 선언
						AnnVO annData403 = null; // 여기서 연차 정보를 가져옵니다.
						List<AnnVO> annHistoryList403 = null; // 여기서 사용내역조회 정보를 가져옵니다.

						try {
							// 테이블에서 선택된 사원정보(사번)를 가지고 DAO사원정보 받자마자 보냈어 DAO
							int returnCnt = D_DAO.deleteAnnUsedDate(avo); // DAO에서 서버에서 실행한 결과값을 가져왔어
																			// 삭제가 성공했다면 1, 아니면 0일거야.
							// 화면 업데이트를 위해 재조회를 한다.
							annData403 = D_DAO.getaAnnData(avo.getComid()); // 연차조회/
							annHistoryList403 = D_DAO.getannHistoryList(p.getAvo().getComid()); // 사용내역조회

						} catch (Exception e) {
						}

						// 화면을 새로 로딩해야한다.
						D_Protocol p403 = new D_Protocol();
						p403.setCmd(403); // login의 run 403으로 보내자
						p403.setAvo(annData403); // 보낼때 avo를 담아 보내자
						p403.setAnnlist(annHistoryList403);

						out.writeObject(p403);
						out.flush();

						break;

					/**
					 * 연차 관리자 한사람에 대한 내용만 불러오기 (이 사람이 가지고 있는 상세 데이터를 DB에서 가져와서 데이터를 조회하여 출력 사원정보,
					 * 사원정보, 연차정보, 너는 연차 언제 썼냐(히스토리) 관리자는 모두, 유저는 사원정보, 연차정보만 가져온다.
					 */
					case 405:
						System.out.println("D_client 405 받기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

						MembersVO memberData = null; // 여기서 사원정보를 가져옵니다.
						AnnVO annData = null; // 여기서 연차 정보를 가져옵니다.
						List<AnnVO> annHistoryList = null; // 여기서 사용내역조회 정보를 가져옵니다.
						String gubun = p.getMsg();
						String idx_405 = ""; // 유저일때 아이디
						String comid_405 = ""; // 관리자 로그인시 사번
						System.out.println("D_C 405 받기2");

						try {
							// 관리자
							
							if ("A".equals(gubun)) {
								comid_405 = p.getString();
								System.out.println("자네가 실행이되고");
							} else { // 사용자
								idx_405 = p.getString(); // 연차B 화면단에서 보낸 아이디값
								int iComid = D_DAO.getSelectOne(idx_405);
								comid_405 = String.valueOf(iComid);
								System.out.println("gubun : " + p.getMsg()); // 관리자(A)인지 유저(B)인지 확인
								System.out.println("D_C comid_405 값이 잘 들어왔어?:" + comid_405);
							}

							// 테이블에서 선택된 사원정보(사번)를 가지고 D_DAO사원정보 받아서 저장 후 보내기
							memberData = D_DAO.getMemberData(comid_405);
							System.out.println("D_C ㄴ :" + memberData);
							annData = D_DAO.getaAnnData(comid_405); // 연차조회
							System.out.println("D_C annData :" + annData);
							annHistoryList = D_DAO.getannHistoryList(comid_405); // 사용내역조회

							D_Protocol p405 = new D_Protocol();

							p405.setCmd(405); // 한사람 정보만 불러오기
							p405.setMemvo(memberData);
							p405.setAvo(annData);
							p405.setAnnlist(annHistoryList);

							// 보내자
							out.writeObject(p405);
							out.flush();
						} catch (Exception e) {
							System.out.println("D_client 405 연차 관리 캐치: " + e.toString());
						}
						break;

					}
				}
			} catch (Exception e) {
			}
		}
	}
}
