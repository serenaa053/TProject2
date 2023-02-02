package com.ict.edu_D;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class D_DAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화 처리) : 프로그램이 종료 될때 까지 한번 만들어진 객체를
	// 재 사용 한다.
	private static SqlSession getSession() {
		if (ss == null) {
			ss = DBService1.getFactory().openSession();
		}
		return ss;
	}

	public static List<signup_in_VO> getList() {
		List<signup_in_VO> list = null;
		list = getSession().selectList("TProject.MEMBERS1");
		return list;
	}

	// 회원가입 result 값은 주어진 행에따라 변경 ex)0,1
	public static int getIns(signup_in_VO vo) {
		int result = 0;
		try {
			result = getSession().insert("TProject.MEMBERS", vo); // 매퍼로 출발
			ss.commit();
		} catch (Exception e) {
			System.out.println("D_DAO" + e.toString());
		}
		return result;
	}

	// 회원가입시 info에 인설트
	public static int getinfo(InfoVO ivo) {
		int result = 0;
		try {
			result = getSession().insert("TProject.MEMBERS_addinfo", ivo); // 매퍼로 출발
			ss.commit();
		} catch (Exception e) {
			System.out.println("D_DAO info 확인" + e.toString());
		}
		return result;
	}


	// 직급/ 직책 빼오기
	public static Pay_VO getPay_VO(signup_in_VO svo) {
		System.out.println("1");
		Pay_VO one = null;
		one = getSession().selectOne("TProject.fact", svo);
		System.out.println(one+"2");
		return one;
	}
	
	// 관리자 선택에 맞는 정보 가져오기
	public static Pay_VO getPay_VO_A(String string) {
		Pay_VO one = null;
		one = getSession().selectOne("TProject.fact_A", string);
		return one;
	}
	
	
	// 업데이트
	public static int getUppay(Pay_VO pay_VO) {
		System.out.println("1");
		System.out.println(pay_VO.getUSERNAME()+"넌 뭐니??????????");
		int result = 0;
		try {
			result = getSession().update("TProject.pay_getUppay", pay_VO);
			System.out.println(result + "     2");
			ss.commit();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	

	// 아이디 중복체크
	public static int getidchkoverlap(String ID) {
		// *** 반환 데이터형이 int인 이유 : sql문에서 count(집계함수)를 사용했으니 결과값은 int형
		// overlap mapper에서 결과값이 int니까 받아서 리턴해주려면 리턴형(반환할 데이터형)도 String이 아니라 int겠죠
		int result = 0;
		result = getSession().selectOne("TProject.overlap", ID);
		return result;
	}

	// 사원번호
	public static int getSelectOne(String ID) {
		int result = 0;
		result = getSession().selectOne("TProject.COMID", ID);
		return result;
	}



	public static int getcomidins(InfoVO infovo) {
		int result = 0;
		result = getSession().insert("TProject.COMID_ins", infovo); // 매퍼로 출발
		ss.commit();
		return result;
	}

	// 로그인 id 중복체크
	public static int login_ID(String ID) {
		int result = 0;
		result = getSession().selectOne("TProject.login_ID", ID);
		return result;
	}

	// 로그인 PW 중복체크
	public static String login_PW(String PW) {
		String string = null;
		string = getSession().selectOne("TProject.login_PW", PW);
		return string;
	}

	// =========================================================
	// 기본정보 불러오기
	public static MembersVO getinfoOne(MembersVO mvo) {
		MembersVO one = null;
		one = getSession().selectOne("TProject.Info_memList", mvo);
		return one;
	}

	// 사진수정
	public static int getimgUpDate(String imgupdate) {
		int result = 0;
		try {
			result = getSession().update("TProject.Info_ImgUpdate", imgupdate);
			ss.commit();
		} catch (Exception e) {
		}
		return result;
	}

	// 정보 업데이트 하기(Member Table)
	public static int getUpadate1(MembersVO mvo) {
		int res = 0;
		try {
			res = getSession().update("TProject.Info_Update_MemberVO", mvo);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		ss.commit();
		return res;
	}

	// 정보 업데이트 하기(Info Table)
	public static int getUpadate2(InfoVO ivo) {
		int res = 0;
		res = getSession().update("TProject.Info_Update_InfoVO", ivo);
		ss.commit();
		return res;
	}

	// Comid 회원가입과 동시에 업데이트하기
	public static int getComidUpadate(InfoVO ivo) {
		int res = 0;
		res = getSession().update("TProject.Info_ComidUpdate", ivo);
		ss.commit();
		return res;
	}

	// 기본정보 끝 =====================================================

	// 공지창=========================================================
    // 공지 메인 가져오기
    public static List<NotimainVO> getNotiMainList() {
        List<NotimainVO> getNotiMainList = null;
        try {
            getNotiMainList = getSession().selectList("TProject.Noti_notimainList");
        } catch (Exception e) {
        	System.out.println("DAO" + e);
        }
        return getNotiMainList;
    }

    // 공지사항 등록하기
    public static int getInsNoti(NotiVO notivo) {
        int result = 0;
        try {
            result = getSession().insert("TProject.Noti_wri", notivo); // 매퍼로 출발
            ss.commit();
        } catch (Exception e) {
        }
        return result;
    }

    // 공지카운트 올리기
    public static int getNcountUpadate(NotiVO notivo) {
        int res = 0;
        try {
            res = getSession().update("TProject.Noti_ncount", notivo);
            ss.commit();
        } catch (Exception e) {
        }
        return res;
    }

    // 공지사항 삭제
    public static int getNdeleteUpadate(NotiVO notivo) {
        int res = 0;
        try {
            res = getSession().update("TProject.Noti_delete", notivo);
            ss.commit();
        } catch (Exception e) {
        }
        return res;
    }

    // 공지사항 수정
    public static int getUpadate(NotiVO notivo) {
        int res = 0;
        try {
            res = getSession().update("TProject.Noti_update", notivo);
            ss.commit();
        } catch (Exception e) {
        }
        return res;
    }

    // 공지등록 끝=========================================================

	
	// 관리자창 기본정보 불러오기
	public static MembersVO getadmininfoOne(MembersVO mvo) {
		MembersVO one = null;
		one = getSession().selectOne("TProject.Admin_InfomemList", mvo);
		return one;
	}

	
	
	/* 관리자 테이블 ================================================= */
	// 관리자 ad테이블에 사원번호, 부서, 직급 정보 (전체내용) 보기
	public static List<AdVO> getAdList(String searchText) {
		System.out.println("DAO_ getAdList searchText : " + searchText);
		List<AdVO> adlist = getSession().selectList("TProject.adList", searchText);
		return adlist;
	}

	
	
	// select 문 결과는 하나, 파라미터 있음.
	// 관리자 ad 테이블에서 하나의 아이디(직원) 검색하기
	public static AdVO getserchOne(String comid) {
		AdVO advo = null;
		advo = getSession().selectOne("TProject.serchOne", comid);
		return advo;
	}

	// annual 테이블의 사원정보 보기 , 인자를 넣었으니 아래 문장에도 있어야 함.
	public static MembersVO getMemberData(String comid) {
		System.out.println("자네군??");
		System.out.println(comid);
		MembersVO memberData = getSession().selectOne("TProject.InfoData", comid);
		try {
			MembersVO ii = memberData;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return memberData;
	}

	// annual 테이블의 연차조회 정보 보기 (발생연도, 발생연차, 사용연차, 잔여연차)
	public static AnnVO getaAnnData(String comid) {
		System.out.println("DAO에 있는 getaAnnData comid :" + comid);
		AnnVO annData = getSession().selectOne("TProject.annData", comid);
		return annData;
	}

	// annual_A 테이블의 연차 사용내역조회 정보 보기
	public static List<AnnVO> getannHistoryList(String comid) {
		List<AnnVO> annHistoryList = getSession().selectList("TProject.annHistoryList", comid);
		return annHistoryList;
	}

	// 연차테이블 삽입하기 -- 연차 유저
	// 파라미터 필요
	public static int insertUsedDate(AnnVO avo) {
		try {
			System.out.println("DAO 연차 삽입 왔나?" + avo);
			int insertUsedDate = getSession().insert("TProject.insertUsedDate", avo);
			System.out.println("DAO - insertUsedDate : " + insertUsedDate);
			ss.commit(); // 반드시 commit하기
			return insertUsedDate;

		} catch (Exception e) {
			System.out.println("DAO 연차삽입 오류 : db");
			System.out.println(e);
		}
		return 0;
	}

	// 연차테이블 삭제하기
	public static int deleteAnnUsedDate(AnnVO avo) {

		int result = 0;
		result = getSession().delete("TProject.deleteAnnUsedDate", avo);
		ss.commit(); // 반드시 commit하기
		return result;
	}

	/* 관리자, 연차 끝 ======================================= */
	
// 근퇴
    
    // 사원 출퇴근 조회
	public static List<WorkVO> getW_D_List(WorkVO wvo){
		List<WorkVO> W_D_List = null;
		try {
			W_D_List = getSession().selectList("TProject.WorkDayList", wvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return W_D_List;
	}
	
	// 출근 삽입
		public static int getInsGo(WorkVO wvo2) {
			int go_Result = 0;
			try {
				go_Result = getSession().insert("TProject.WorkInsGo", wvo2);
				ss.commit();
			} catch (Exception e) {
				System.out.println(e);
			}
			return go_Result;
		}
		
	// 퇴근 업데이트(삽입)
	public static int getUpdateBye(WorkVO wvo3) {
		int bye_Result = 0 ;
		try {
			bye_Result = getSession().update("TProject.WorkUpdateBye", wvo3);
			ss.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		return bye_Result;
	}
		
	// 관리자 출근 삽입
	public static int m_getInsGo(WorkVO wvo4) {
		int m_go_Result = 0 ;
		try {
			m_go_Result = getSession().insert("TProject.WorkInsGo", wvo4);
			ss.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		return m_go_Result;
	}
	
	// 관리자 퇴근 추가
	public static int WorkUpdateBye(WorkVO wvo5) {
		int m_bye_Result = 0 ;
		try {
			m_bye_Result = getSession().update("TProject.WorkUpdateBye", wvo5);
			ss.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		return m_bye_Result;
	}
	// 근퇴 끝

}