package com.ict.edu_A;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.MembersVO;
import com.ict.edu_D.NotiVO;
import com.ict.edu_D.NotimainVO;

public class B_NewNoti_A extends JPanel {
	// 구성 및 공지 항목 배열화
	JLabel jlno, jltitle, jlwriter, jldate, jlcount;

	JLabel[][] jl = new JLabel[7][5];

	// nnumber
	int n1, n2, n3, n4, n5, n6, n7;

	// 각 row가 붙을 Panel
	JPanel jptitle;

	JPanel[] jp = new JPanel[7];

	// 패널 구분 선
	JPanel[] jp_line = new JPanel[7];

	// 메인Panel과 버튼Panel 나누기
	JPanel jp_m, jp_b, jp_b_1;

	// 화면단 아래에 쓰일 등록 & 수정 버튼
	JButton jb1; // jb2

	// Carrlayout 활용을 위한 화면단 구성
	JPanel card; // card = 전체 카드 화면

	// JPanel card ;
	CardLayout cl;

	JPanel jp_noti;

	// 각 창 주요공지 체크여부
	String wri_jcb_important, rev_jcb_important;

	String wri_jcb_important_save, rev_jcb_important_save; // db 저장을 위한 전역 변수

	// ==================================================
	// 공지 읽기창 구성

	JPanel jp_rea;

	Font myFont_rea_basic;

	JPanel jp_rea_a, jp_rea_name, jp_rea_title; // 작성자 & 제목 만들기

	JPanel jp_rea_b, jp_rea_content;

	JPanel rea_name_1, rea_title_1, rea_a_1, rea_b_1; // 여백 패널

	JPanel jp_rea_c, jp_rea_bt, jp_rea_bt_1, jp_rea_enter;

	JLabel jlb_rea_writer, jlb_rea_title, jlb_rea_content;

	JTextField jtf_rea_writer, jtf_rea_title;

	JTextArea rea_jta;
	JScrollPane rea_jsp;

	JButton rea_jb1, rea_jb2;

	// ==================================================
	// 공지 등록창 구성

	JPanel jp_wri;

	Font myFont_wri_basic;

	JPanel jp_wri_a, jp_wri_name, jp_wri_title; // 작성자 & 제목 만들기

	JPanel jp_wri_b, jp_wri_content;

	JPanel wri_name_1, wri_title_1, wri_a_1, wri_b_1; // 여백 패널

	JPanel jp_wri_c, jp_wri_bt, jp_wri_bt_1, jp_wri_enter;

	JLabel jlb_wri_writer, jlb_wri_title, jlb_wri_content;

	JTextField jtf_wri_writer, jtf_wri_title;

	JTextArea wri_jta;
	JScrollPane wri_jsp;
	JCheckBox wri_jcb;

	JButton wri_jb1, wri_jb2;

	String writername; // db저장을 위한 전역변수

	int writecomid;

	// ==================================================
	// 공지 수정창 구성

	JPanel jp_rev;

	Font myFont_rev_basic;

	JPanel jp_rev_a, jp_rev_name, jp_rev_title; // 작성자 & 제목 만들기

	JPanel jp_rev_b, jp_rev_content;

	JPanel rev_name_1, rev_title_1, rev_a_1, rev_b_1; // 여백 패널

	JPanel jp_rev_c, jp_rev_bt, jp_rev_bt_1, jp_rev_enter, rev_nnumber_1;

	JLabel jlb_rev_writer, jlb_rev_title, jlb_rev_content, jlb_rev_nnumber;

	JTextField jtf_rev_writer, jtf_rev_title, jtf_rev_nnumber;

	JTextArea rev_jta;
	JScrollPane rev_jsp;
	JCheckBox rev_jcb;

	JButton rev_jb1, rev_jb2, rev_jb3;

	// ==================================================

	Font myFont_menubar, myFont_important, myFont_basic, myFont_noti_click;

	LineBorder noti_lb, basic_lb;

	// MaIn_A b_Main;

	// 행 표기를 위한 int 값

	public LogIn_A login_A;

	public List<NotimainVO> notilist;

	public B_NewNoti_A(LogIn_A login) {
		login_A = login;

		int[] nnumber = { n1, n2, n3, n4, n5, n6, n7 };

		LocalDate now = LocalDate.now();

		int year = now.getYear();
		String year_1 = Integer.toString(year);

		int month = now.getMonthValue();
		String month_1 = Integer.toString(month);

		int day = now.getDayOfMonth();
		String day_1 = Integer.toString(day);

		String ndate_now = year_1 + "." + month_1 + "." + day_1;

		// ===============================================
		// Font 및 LineBorder 구성
		myFont_menubar = new Font("굴림", Font.BOLD, 15);
		myFont_important = new Font("굴림", Font.BOLD, 14);
		myFont_basic = new Font("굴림", Font.PLAIN, 15);
		myFont_noti_click = new Font("궁서", Font.BOLD, 20);

		noti_lb = new LineBorder(Color.BLACK, 2);

		// 메인 화면단 구성
		jp_noti = new JPanel(new BorderLayout());

		jp_m = new JPanel(new FlowLayout());
		jp_m.setPreferredSize(new Dimension(750, 600));

		jptitle = new JPanel();
		jptitle.setPreferredSize(new Dimension(750, 50));

		// 버튼 화면단 구성
		jp_b = new JPanel(new BorderLayout());
		jp_b.setPreferredSize(new Dimension(750, 50));
		jb1 = new JButton("등록");
		jp_b_1 = new JPanel();
		jp_b_1.add(jb1);
		jp_b.add(jp_b_1, BorderLayout.EAST);

		// 1. "순번", "제목", "작성자", "작성일", "조회수"
		// 제목단 분리
		jlno = new JLabel("", JLabel.CENTER);
		jlno.setPreferredSize(new Dimension(80, 45));
		jlno.setHorizontalAlignment(JLabel.CENTER);

		jltitle = new JLabel("제목", JLabel.CENTER);
		jltitle.setPreferredSize(new Dimension(380, 45));
		jltitle.setHorizontalAlignment(JLabel.CENTER);

		jlwriter = new JLabel("작성자", JLabel.CENTER);
		jlwriter.setPreferredSize(new Dimension(80, 45));
		jlwriter.setHorizontalAlignment(JLabel.CENTER);

		jldate = new JLabel("작성일", JLabel.CENTER);
		jldate.setPreferredSize(new Dimension(80, 45));
		jldate.setHorizontalAlignment(JLabel.CENTER);

		jlcount = new JLabel("조회수", JLabel.CENTER);
		jlcount.setPreferredSize(new Dimension(80, 45));
		jlcount.setHorizontalAlignment(JLabel.CENTER);

		// 항목 Line 폰트 및 테두리 설정하기
		jlno.setFont(myFont_menubar);
		jltitle.setFont(myFont_menubar);
		jlwriter.setFont(myFont_menubar);
		jldate.setFont(myFont_menubar);
		jlcount.setFont(myFont_menubar);

		jlno.setBorder(noti_lb);
		jltitle.setBorder(noti_lb);
		jlwriter.setBorder(noti_lb);
		jldate.setBorder(noti_lb);
		jlcount.setBorder(noti_lb);

		jptitle.add(jlno);
		jptitle.add(jltitle);
		jptitle.add(jlwriter);
		jptitle.add(jldate);
		jptitle.add(jlcount);

		jp_m.add(jptitle);

		// 2. 1~7번 공지창 나누자
		for (int i = 0; i < 7; i++) {
			// 데이터 화면단에 호출하기
			jp[i] = new JPanel();

			jp_line[i] = new JPanel();
			jp_line[i].setPreferredSize(new Dimension(720, 1));
			jp_line[i].setBackground(Color.BLACK);

			jl[i][0] = new JLabel("");
			jl[i][0].setPreferredSize(new Dimension(80, 50));
			jl[i][0].setHorizontalAlignment(JLabel.CENTER);

			jl[i][1] = new JLabel("");
			jl[i][1].setPreferredSize(new Dimension(380, 50));
			jl[i][1].setHorizontalAlignment(JLabel.CENTER);

			jl[i][2] = new JLabel("");
			jl[i][2].setPreferredSize(new Dimension(80, 50));
			jl[i][2].setHorizontalAlignment(JLabel.CENTER);

			jl[i][3] = new JLabel("");
			jl[i][3].setPreferredSize(new Dimension(80, 50));
			jl[i][3].setHorizontalAlignment(JLabel.CENTER);

			jl[i][4] = new JLabel("");
			jl[i][4].setPreferredSize(new Dimension(80, 50));
			jl[i][4].setHorizontalAlignment(JLabel.CENTER);

			// 기본 폰트 설정하기
			jl[i][0].setFont(myFont_basic);
			jl[i][1].setFont(myFont_basic);
			jl[i][2].setFont(myFont_basic);
			jl[i][3].setFont(myFont_basic);
			jl[i][4].setFont(myFont_basic);

			jp[i].add(jl[i][0]);
			jp[i].add(jl[i][1]);
			jp[i].add(jl[i][2]);
			jp[i].add(jl[i][3]);
			jp[i].add(jl[i][4]);

			jp_m.add(jp[i]);
			jp_m.add(jp_line[i]);

		}

		jp_noti.add(jp_m, BorderLayout.CENTER);
		jp_noti.add(jp_b, BorderLayout.SOUTH);
		// 메인 공지창 구성 완료
		// ===============================================

		// ===============================================
		// 2. 공지 읽기 창 구성

		// 읽기창 rea 메인화면
		jp_rea = new JPanel(new BorderLayout());
		jp_rea.setBorder(new EmptyBorder(30, 20, 20, 20));

		// Font 만들기
		myFont_rea_basic = new Font("굴림", Font.BOLD, 15);
		basic_lb = new LineBorder(Color.BLACK);

		// 작성자 & 제목 만들기
		jp_rea_a = new JPanel(new BorderLayout());

		// 작성자
		jp_rea_name = new JPanel(new BorderLayout());
		jp_rea_name.setPreferredSize(new Dimension(700, 30));

		jlb_rea_writer = new JLabel(" 작성자 ", JLabel.CENTER);
		jlb_rea_writer.setPreferredSize(new Dimension(100, 30));
		jlb_rea_writer.setFont(myFont_basic);
		jlb_rea_writer.setBorder(basic_lb);

		jtf_rea_writer = new JTextField(10);
		jtf_rea_writer.setPreferredSize(new Dimension(200, 30));

		rea_name_1 = new JPanel();
		rea_name_1.setPreferredSize(new Dimension(400, 30));

		jp_rea_name.add(jlb_rea_writer, BorderLayout.WEST);
		jp_rea_name.add(jtf_rea_writer, BorderLayout.CENTER);
		jp_rea_name.add(rea_name_1, BorderLayout.EAST);

		// 작성자 & 제목 사이, 여백
		rea_a_1 = new JPanel();
		rea_a_1.setPreferredSize(new Dimension(700, 30));

		// 제목
		jp_rea_title = new JPanel(new BorderLayout());
		jp_rea_title.setPreferredSize(new Dimension(700, 30));

		jlb_rea_title = new JLabel(" 제  목 ", JLabel.CENTER);
		jlb_rea_title.setPreferredSize(new Dimension(100, 30));
		jlb_rea_title.setFont(myFont_basic);
		jlb_rea_title.setBorder(basic_lb);

		jtf_rea_title = new JTextField(10);
		jtf_rea_title.setPreferredSize(new Dimension(500, 30));

		rea_title_1 = new JPanel();
		rea_title_1.setPreferredSize(new Dimension(100, 30));

		jp_rea_title.add(jlb_rea_title, BorderLayout.WEST);
		jp_rea_title.add(jtf_rea_title, BorderLayout.CENTER);
		jp_rea_title.add(rea_title_1, BorderLayout.EAST);

		// 작성자 , 작성자 & 제목 여백, 여백 합치기
		jp_rea_a.add(jp_rea_name, BorderLayout.NORTH);
		jp_rea_a.add(rea_a_1, BorderLayout.CENTER);
		jp_rea_a.add(jp_rea_title, BorderLayout.SOUTH);

		// 내용 & JTextArea 만들기
		jp_rea_b = new JPanel(new BorderLayout());

		// 내용
		jp_rea_content = new JPanel(new BorderLayout());
		jp_rea_content.setPreferredSize(new Dimension(700, 30));

		jlb_rea_content = new JLabel(" 내  용 ", JLabel.CENTER);
		jlb_rea_content.setPreferredSize(new Dimension(100, 30));
		jlb_rea_content.setFont(myFont_basic);
		jlb_rea_content.setBorder(basic_lb);

		jp_rea_content.add(jlb_rea_content, BorderLayout.WEST);

		// JTextArea
		rea_jta = new JTextArea(20, 30);
		rea_jsp = new JScrollPane(rea_jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rea_jta.setLineWrap(true);

		jp_rea_b.add(jp_rea_content, BorderLayout.NORTH);
		jp_rea_b.add(rea_jsp, BorderLayout.CENTER);

		// 주요공지 버튼 & 확인 버튼
		jp_rea_c = new JPanel(new BorderLayout());

		rea_b_1 = new JPanel();
		rea_b_1.setPreferredSize(new Dimension(700, 20));

		// 주요공지 체크 박스 만들기
		jp_rea_bt = new JPanel(new BorderLayout());
		jp_rea_bt.setPreferredSize(new Dimension(700, 30));

		// 확인 버튼

		jp_rea_enter = new JPanel(new BorderLayout());
		jp_rea_enter.setPreferredSize(new Dimension(700, 50));

		rea_jb1 = new JButton("수정&삭제");
		rea_jb1.setPreferredSize(new Dimension(120, 26));
		rea_jb2 = new JButton("뒤로");
		jp_rea_bt_1 = new JPanel();

		jp_rea_bt_1.add(rea_jb1);
		jp_rea_bt_1.add(rea_jb2);

		jp_rea_enter.add(jp_rea_bt_1, BorderLayout.EAST);

		//
		jp_rea_c.add(rea_b_1, BorderLayout.NORTH);
		jp_rea_c.add(jp_rea_bt, BorderLayout.CENTER);
		jp_rea_c.add(jp_rea_enter, BorderLayout.SOUTH);

		// ***
		jp_rea.add(jp_rea_a, BorderLayout.NORTH);
		jp_rea.add(jp_rea_b, BorderLayout.CENTER);
		jp_rea.add(jp_rea_c, BorderLayout.SOUTH);

		// ===============================================

		// ===============================================
		// 3. 공지 등록 창 구성

		// 등록창 wri 메인화면
		jp_wri = new JPanel(new BorderLayout());
		jp_wri.setBorder(new EmptyBorder(30, 20, 20, 20));

		// Font 만들기
		myFont_wri_basic = new Font("굴림", Font.BOLD, 15);
		basic_lb = new LineBorder(Color.BLACK);

		// 작성자 & 제목 만들기
		jp_wri_a = new JPanel(new BorderLayout());

		// 작성자
		jp_wri_name = new JPanel(new BorderLayout());
		jp_wri_name.setPreferredSize(new Dimension(700, 30));

		jlb_wri_writer = new JLabel(" 작성자 ", JLabel.CENTER);
		jlb_wri_writer.setPreferredSize(new Dimension(100, 30));
		jlb_wri_writer.setFont(myFont_basic);
		jlb_wri_writer.setBorder(basic_lb);

		jtf_wri_writer = new JTextField(10);
		jtf_wri_writer.setPreferredSize(new Dimension(200, 30));

		wri_name_1 = new JPanel();
		wri_name_1.setPreferredSize(new Dimension(400, 30));

		jp_wri_name.add(jlb_wri_writer, BorderLayout.WEST);
		jp_wri_name.add(jtf_wri_writer, BorderLayout.CENTER);
		jp_wri_name.add(wri_name_1, BorderLayout.EAST);

		// 작성자 & 제목 사이, 여백
		wri_a_1 = new JPanel();
		wri_a_1.setPreferredSize(new Dimension(700, 30));

		// 제목
		jp_wri_title = new JPanel(new BorderLayout());
		jp_wri_title.setPreferredSize(new Dimension(700, 30));

		jlb_wri_title = new JLabel(" 제  목 ", JLabel.CENTER);
		jlb_wri_title.setPreferredSize(new Dimension(100, 30));
		jlb_wri_title.setFont(myFont_basic);
		jlb_wri_title.setBorder(basic_lb);

		jtf_wri_title = new JTextField(10);
		jtf_wri_title.setPreferredSize(new Dimension(500, 30));

		wri_title_1 = new JPanel();
		wri_title_1.setPreferredSize(new Dimension(100, 30));

		jp_wri_title.add(jlb_wri_title, BorderLayout.WEST);
		jp_wri_title.add(jtf_wri_title, BorderLayout.CENTER);
		jp_wri_title.add(wri_title_1, BorderLayout.EAST);

		// 작성자 , 작성자 & 제목 여백, 여백 합치기
		jp_wri_a.add(jp_wri_name, BorderLayout.NORTH);
		jp_wri_a.add(wri_a_1, BorderLayout.CENTER);
		jp_wri_a.add(jp_wri_title, BorderLayout.SOUTH);

		// 내용 & JTextArea 만들기
		jp_wri_b = new JPanel(new BorderLayout());

		// 내용
		jp_wri_content = new JPanel(new BorderLayout());
		jp_wri_content.setPreferredSize(new Dimension(700, 30));

		jlb_wri_content = new JLabel(" 내  용 ", JLabel.CENTER);
		jlb_wri_content.setPreferredSize(new Dimension(100, 30));
		jlb_wri_content.setFont(myFont_basic);
		jlb_wri_content.setBorder(basic_lb);

		jp_wri_content.add(jlb_wri_content, BorderLayout.WEST);

		// JTextArea
		wri_jta = new JTextArea(20, 30);
		wri_jsp = new JScrollPane(wri_jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		wri_jta.setLineWrap(true);

		jp_wri_b.add(jp_wri_content, BorderLayout.NORTH);
		jp_wri_b.add(wri_jsp, BorderLayout.CENTER);

		// 주요공지 버튼 & 확인 버튼
		jp_wri_c = new JPanel(new BorderLayout());

		wri_b_1 = new JPanel();
		wri_b_1.setPreferredSize(new Dimension(700, 20));

		// 주요공지 체크 박스 만들기
		jp_wri_bt = new JPanel(new BorderLayout());
		jp_wri_bt.setPreferredSize(new Dimension(700, 30));

		wri_jcb = new JCheckBox("주요공지 등록 시 체크해주세요");

		jp_wri_bt.add(wri_jcb, BorderLayout.EAST);

		// 확인 버튼

		jp_wri_enter = new JPanel(new BorderLayout());
		jp_wri_enter.setPreferredSize(new Dimension(700, 50));

		wri_jb1 = new JButton("등록");
		wri_jb2 = new JButton("뒤로");
		jp_wri_bt_1 = new JPanel();
		jp_wri_bt_1.add(wri_jb1);
		jp_wri_bt_1.add(wri_jb2);

		jp_wri_enter.add(jp_wri_bt_1, BorderLayout.EAST);

		//
		jp_wri_c.add(wri_b_1, BorderLayout.NORTH);
		jp_wri_c.add(jp_wri_bt, BorderLayout.CENTER);
		jp_wri_c.add(jp_wri_enter, BorderLayout.SOUTH);

		// ***
		jp_wri.add(jp_wri_a, BorderLayout.NORTH);
		jp_wri.add(jp_wri_b, BorderLayout.CENTER);
		jp_wri.add(jp_wri_c, BorderLayout.SOUTH);
		// ===============================================

		// ===============================================
		// 4. 공지 수정 창 구성

		// 수정창 rev 메인화면
		jp_rev = new JPanel(new BorderLayout());
		jp_rev.setBorder(new EmptyBorder(30, 20, 20, 20));

		// Font 만들기
		myFont_rev_basic = new Font("굴림", Font.BOLD, 15);
		basic_lb = new LineBorder(Color.BLACK);

		// 작성자 & 제목 만들기
		jp_rev_a = new JPanel(new BorderLayout());

		// 작성자
		jp_rev_name = new JPanel(new BorderLayout());
		jp_rev_name.setPreferredSize(new Dimension(700, 30));

		jlb_rev_writer = new JLabel(" 작성자 ", JLabel.CENTER);
		jlb_rev_writer.setPreferredSize(new Dimension(100, 30));
		jlb_rev_writer.setFont(myFont_basic);
		jlb_rev_writer.setBorder(basic_lb);

		jtf_rev_writer = new JTextField(10);
		jtf_rev_writer.setPreferredSize(new Dimension(200, 30));

		rev_name_1 = new JPanel();
		rev_name_1.setPreferredSize(new Dimension(400, 30));

		jp_rev_name.add(jlb_rev_writer, BorderLayout.WEST);
		jp_rev_name.add(jtf_rev_writer, BorderLayout.CENTER);
		jp_rev_name.add(rev_name_1, BorderLayout.EAST);

		// 공지 고유 넘버 표기
		rev_a_1 = new JPanel(new BorderLayout());
		rev_a_1.setPreferredSize(new Dimension(700, 30));

		jlb_rev_nnumber = new JLabel(" 공지넘버 ", JLabel.CENTER);
		jlb_rev_nnumber.setPreferredSize(new Dimension(100, 30));
		jlb_rev_nnumber.setFont(myFont_basic);
		jlb_rev_nnumber.setBorder(basic_lb);

		jtf_rev_nnumber = new JTextField(10);
		jtf_rev_nnumber.setPreferredSize(new Dimension(200, 30));

		rev_nnumber_1 = new JPanel();
		rev_nnumber_1.setPreferredSize(new Dimension(400, 30));

		rev_a_1.add(jlb_rev_nnumber, BorderLayout.WEST);
		rev_a_1.add(jtf_rev_nnumber, BorderLayout.CENTER);
		rev_a_1.add(rev_nnumber_1, BorderLayout.EAST);

		// 제목
		jp_rev_title = new JPanel(new BorderLayout());
		jp_rev_title.setPreferredSize(new Dimension(700, 30));

		jlb_rev_title = new JLabel(" 제  목 ", JLabel.CENTER);
		jlb_rev_title.setPreferredSize(new Dimension(100, 30));
		jlb_rev_title.setFont(myFont_basic);
		jlb_rev_title.setBorder(basic_lb);

		jtf_rev_title = new JTextField(10);
		jtf_rev_title.setPreferredSize(new Dimension(500, 30));

		rev_title_1 = new JPanel();
		rev_title_1.setPreferredSize(new Dimension(100, 30));

		jp_rev_title.add(jlb_rev_title, BorderLayout.WEST);
		jp_rev_title.add(jtf_rev_title, BorderLayout.CENTER);
		jp_rev_title.add(rev_title_1, BorderLayout.EAST);

		// 작성자 , 작성자 & 제목 여백, 여백 합치기
		jp_rev_a.add(jp_rev_name, BorderLayout.NORTH);
		jp_rev_a.add(rev_a_1, BorderLayout.CENTER);
		jp_rev_a.add(jp_rev_title, BorderLayout.SOUTH);

		// 내용 & JTextArea 만들기
		jp_rev_b = new JPanel(new BorderLayout());

		// 내용
		jp_rev_content = new JPanel(new BorderLayout());
		jp_rev_content.setPreferredSize(new Dimension(700, 30));

		jlb_rev_content = new JLabel(" 내  용 ", JLabel.CENTER);
		jlb_rev_content.setPreferredSize(new Dimension(100, 30));
		jlb_rev_content.setFont(myFont_basic);
		jlb_rev_content.setBorder(basic_lb);

		jp_rev_content.add(jlb_rev_content, BorderLayout.WEST);

		// JTextArea
		rev_jta = new JTextArea(20, 30);
		rev_jsp = new JScrollPane(rev_jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rev_jta.setLineWrap(true);

		jp_rev_b.add(jp_rev_content, BorderLayout.NORTH);
		jp_rev_b.add(rev_jsp, BorderLayout.CENTER);

		// 주요공지 버튼 & 확인 버튼
		jp_rev_c = new JPanel(new BorderLayout());

		rev_b_1 = new JPanel();
		rev_b_1.setPreferredSize(new Dimension(700, 20));

		// 주요공지 체크 박스 만들기
		jp_rev_bt = new JPanel(new BorderLayout());
		jp_rev_bt.setPreferredSize(new Dimension(700, 30));

		rev_jcb = new JCheckBox("주요공지로 수정시 체크, 아닐시에는 해제해주세요");

		jp_rev_bt.add(rev_jcb, BorderLayout.EAST);

		// 확인 버튼

		jp_rev_enter = new JPanel(new BorderLayout());
		jp_rev_enter.setPreferredSize(new Dimension(700, 50));

		rev_jb1 = new JButton("삭제");
		rev_jb2 = new JButton("수정");
		rev_jb3 = new JButton("뒤로");
		jp_rev_bt_1 = new JPanel();
		jp_rev_bt_1.add(rev_jb1);
		jp_rev_bt_1.add(rev_jb2);
		jp_rev_bt_1.add(rev_jb3);

		jp_rev_enter.add(jp_rev_bt_1, BorderLayout.EAST);

		//
		jp_rev_c.add(rev_b_1, BorderLayout.NORTH);
		jp_rev_c.add(jp_rev_bt, BorderLayout.CENTER);
		jp_rev_c.add(jp_rev_enter, BorderLayout.SOUTH);

		// ***
		jp_rev.add(jp_rev_a, BorderLayout.NORTH);
		jp_rev.add(jp_rev_b, BorderLayout.CENTER);
		jp_rev.add(jp_rev_c, BorderLayout.SOUTH);

		// =========================

		// ===============================================
		card = new JPanel(); // 0108 전역변수로 수정
		cl = new CardLayout(); // 0108 전역변수로 수정
		card.setLayout(cl);

		card.add("main", jp_noti);
		card.add("rea", jp_rea);
		card.add("wri", jp_wri);
		card.add("rev", jp_rev);

		add(card);

		cl.show(card, "main");

		// 공지 제목 라벨 액션 기능
		// 첫번째줄 제목 라벨
		jl[0][1].addMouseListener(new MouseAdapter() {
			@Override

			public void mouseExited(MouseEvent e) {
				jl[0][1].setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				jl[0][1].setForeground(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (notilist.size() == 0) {
					JOptionPane.showMessageDialog(login_A, "공지를 등록해주세요");
					jtf_wri_title.setText("");
					wri_jta.setText("");
					cl.show(card, "wri");
				} else {

					NotiVO notivo = new NotiVO();

					if (notilist.get(0).getNcount() == null) {
						notivo.setNcount("1");
					} else {
						notivo.setNcount(Integer.toString((Integer.parseInt(notilist.get(0).getNcount()) + 1)));
					}
					notivo.setNtitle(notilist.get(0).getNtitle());

					D_Protocol p = new D_Protocol();
					p.setCmd(311);
					p.setNotivo(notivo);
					try {
						login_A.out.writeObject(p);
						login_A.out.flush();
					} catch (IOException e1) {
						System.out.println(e1);
					}

					jtf_rea_writer.setText(notilist.get(0).getNusername());
					jtf_rea_writer.setEditable(false);
					jtf_rea_title.setText(notilist.get(0).getNtitle());
					jtf_rea_title.setEditable(false);
					rea_jta.setText("");
					rea_jta.append(notilist.get(0).getNcontents());
					rea_jta.setEditable(false);
					jtf_rev_writer.setText(notilist.get(0).getNusername());
					jtf_rea_writer.setEditable(false);
					jtf_rev_title.setText(notilist.get(0).getNtitle());
					rev_jta.setText("");
					rev_jta.append(notilist.get(0).getNcontents());
					jtf_rev_nnumber.setText(Integer.toString(n1));
					jtf_rev_nnumber.setEditable(false);

					cl.show(card, "rea");
				}

			}
		});

		// 두번째줄 제목 라벨
		jl[1][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				jl[1][1].setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				jl[1][1].setForeground(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				NotiVO notivo = new NotiVO();
				if (notilist.get(1).getNcount() == null) {
					notivo.setNcount("1");
				} else {
					notivo.setNcount(Integer.toString((Integer.parseInt(notilist.get(1).getNcount()) + 1)));
				}
				notivo.setNtitle(notilist.get(1).getNtitle());

				D_Protocol p = new D_Protocol();
				p.setCmd(311);
				p.setNotivo(notivo);
				try {
					login_A.out.writeObject(p);
					login_A.out.flush();
				} catch (IOException e1) {
					System.out.println(e1);
				}

				jtf_rea_writer.setText(notilist.get(1).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rea_title.setText(notilist.get(1).getNtitle());
				jtf_rea_title.setEditable(false);
				rea_jta.setText("");
				rea_jta.append(notilist.get(1).getNcontents());
				rea_jta.setEditable(false);
				jtf_rev_writer.setText(notilist.get(1).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rev_title.setText(notilist.get(1).getNtitle());
				rev_jta.setText("");
				rev_jta.append(notilist.get(1).getNcontents());
				jtf_rev_nnumber.setText(Integer.toString(n2));
				jtf_rev_nnumber.setEditable(false);

				cl.show(card, "rea");
			}
		});

		// 세번째줄 제목 라벨
		jl[2][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				jl[2][1].setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				jl[2][1].setForeground(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				NotiVO notivo = new NotiVO();
				if (notilist.get(2).getNcount() == null) {
					notivo.setNcount("1");
				} else {
					notivo.setNcount(Integer.toString((Integer.parseInt(notilist.get(2).getNcount()) + 1)));
				}
				notivo.setNtitle(notilist.get(2).getNtitle());

				D_Protocol p = new D_Protocol();
				p.setCmd(311);
				p.setNotivo(notivo);
				try {
					login_A.out.writeObject(p);
					login_A.out.flush();
				} catch (IOException e1) {
					System.out.println(e1);
				}

				jtf_rea_writer.setText(notilist.get(2).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rea_title.setText(notilist.get(2).getNtitle());
				jtf_rea_title.setEditable(false);
				rea_jta.setText("");
				rea_jta.append(notilist.get(2).getNcontents());
				rea_jta.setEditable(false);
				jtf_rev_writer.setText(notilist.get(2).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rev_title.setText(notilist.get(2).getNtitle());
				rev_jta.setText("");
				rev_jta.append(notilist.get(2).getNcontents());
				jtf_rev_nnumber.setText(Integer.toString(n3));
				jtf_rev_nnumber.setEditable(false);

				cl.show(card, "rea");
			}
		});

		// 네번째줄 제목 라벨
		jl[3][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				jl[3][1].setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				jl[3][1].setForeground(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				NotiVO notivo = new NotiVO();
				if (notilist.get(3).getNcount() == null) {
					notivo.setNcount("1");
				} else {
					notivo.setNcount(Integer.toString((Integer.parseInt(notilist.get(3).getNcount()) + 1)));
				}
				notivo.setNtitle(notilist.get(3).getNtitle());

				D_Protocol p = new D_Protocol();
				p.setCmd(311);
				p.setNotivo(notivo);
				try {
					login_A.out.writeObject(p);
					login_A.out.flush();
				} catch (IOException e1) {
					System.out.println(e1);
				}

				jtf_rea_writer.setText(notilist.get(3).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rea_title.setText(notilist.get(3).getNtitle());
				jtf_rea_title.setEditable(false);
				rea_jta.setText("");
				rea_jta.append(notilist.get(3).getNcontents());
				rea_jta.setEditable(false);
				jtf_rev_writer.setText(notilist.get(3).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rev_title.setText(notilist.get(3).getNtitle());
				rev_jta.setText("");
				rev_jta.append(notilist.get(3).getNcontents());
				jtf_rev_nnumber.setText(Integer.toString(n4));
				jtf_rev_nnumber.setEditable(false);

				cl.show(card, "rea");
			}
		});

		// 다섯째줄 제목 라벨
		jl[4][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				jl[4][1].setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				jl[4][1].setForeground(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				NotiVO notivo = new NotiVO();
				if (notilist.get(4).getNcount() == null) {
					notivo.setNcount("1");
				} else {
					notivo.setNcount(Integer.toString((Integer.parseInt(notilist.get(4).getNcount()) + 1)));
				}
				notivo.setNtitle(notilist.get(4).getNtitle());

				D_Protocol p = new D_Protocol();
				p.setCmd(311);
				p.setNotivo(notivo);
				try {
					login_A.out.writeObject(p);
					login_A.out.flush();
				} catch (IOException e1) {
					System.out.println(e1);
				}

				jtf_rea_writer.setText(notilist.get(4).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rea_title.setText(notilist.get(4).getNtitle());
				jtf_rea_title.setEditable(false);
				rea_jta.setText("");
				rea_jta.append(notilist.get(4).getNcontents());
				rea_jta.setEditable(false);
				jtf_rev_writer.setText(notilist.get(4).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rev_title.setText(notilist.get(4).getNtitle());
				rev_jta.setText("");
				rev_jta.append(notilist.get(4).getNcontents());
				jtf_rev_nnumber.setText(Integer.toString(n5));
				jtf_rev_nnumber.setEditable(false);

				cl.show(card, "rea");
			}
		});

		// 여섯째줄 제목 라벨
		jl[5][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				jl[5][1].setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				jl[5][1].setForeground(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				NotiVO notivo = new NotiVO();
				if (notilist.get(5).getNcount() == null) {
					notivo.setNcount("1");
				} else {
					notivo.setNcount(Integer.toString((Integer.parseInt(notilist.get(5).getNcount()) + 1)));
				}
				notivo.setNtitle(notilist.get(5).getNtitle());

				D_Protocol p = new D_Protocol();
				p.setCmd(311);
				p.setNotivo(notivo);
				try {
					login_A.out.writeObject(p);
					login_A.out.flush();
				} catch (IOException e1) {
					System.out.println(e1);
				}

				jtf_rea_writer.setText(notilist.get(5).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rea_title.setText(notilist.get(5).getNtitle());
				jtf_rea_title.setEditable(false);
				rea_jta.setText("");
				rea_jta.append(notilist.get(5).getNcontents());
				rea_jta.setEditable(false);
				jtf_rev_writer.setText(notilist.get(5).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rev_title.setText(notilist.get(5).getNtitle());
				rev_jta.setText("");
				rev_jta.append(notilist.get(5).getNcontents());
				jtf_rev_nnumber.setText(Integer.toString(n6));
				jtf_rev_nnumber.setEditable(false);

				cl.show(card, "rea");
			}
		});

		// 일곱번째줄 제목 라벨
		jl[6][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				jl[6][1].setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				jl[6][1].setForeground(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				NotiVO notivo = new NotiVO();
				if (notilist.get(6).getNcount() == null) {
					notivo.setNcount("1");
				} else {
					notivo.setNcount(Integer.toString((Integer.parseInt(notilist.get(6).getNcount()) + 1)));
				}
				notivo.setNtitle(notilist.get(6).getNtitle());

				D_Protocol p = new D_Protocol();
				p.setCmd(311);
				p.setNotivo(notivo);
				try {
					login_A.out.writeObject(p);
					login_A.out.flush();
				} catch (IOException e1) {
					System.out.println(e1);
				}

				jtf_rea_writer.setText(notilist.get(6).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rea_title.setText(notilist.get(6).getNtitle());
				jtf_rea_title.setEditable(false);
				rea_jta.setText("");
				rea_jta.append(notilist.get(6).getNcontents());
				rea_jta.setEditable(false);
				jtf_rev_writer.setText(notilist.get(6).getNusername());
				jtf_rea_writer.setEditable(false);
				jtf_rev_title.setText(notilist.get(6).getNtitle());
				rev_jta.setText("");
				rev_jta.append(notilist.get(6).getNcontents());
				jtf_rev_nnumber.setText(Integer.toString(n7));
				jtf_rev_nnumber.setEditable(false);

				cl.show(card, "rea");

			}
		});

		// '공지사항'
		// jb1 = 메인창 등록 버튼
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (notilist.size() >= 7) {
					System.out.println(notilist.size() + "몇개니");
					cl.show(card, "main");
					JOptionPane.showMessageDialog(login_A, "공지는 7개까지만 등록 가능합니다. 추가 등록을 원할시 삭제 바랍니다.");
				} else {
					int answer = JOptionPane.showConfirmDialog(login_A, "공지글을 등록하시겠습니까?", "공지등록",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						jtf_wri_title.setText("");
						wri_jta.setText("");
						cl.show(card, "wri");
					}
				}
			}
		});

		// '공지글 읽기'
		// rea_jb1 = 수정&삭제 버튼, rea_jb2 = 뒤로 버튼
		// 수정 삭제 창으로 들어가기 위한 버튼
		rea_jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(login_A, "공지글을 수정&삭제하시겠습니까?", "공지수정&삭제",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					cl.show(card, "rev");
				} else {
					cl.show(card, "rea");
				}
			}
		});

		// rea_jb2 = 뒤로 버튼
		rea_jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(card, "main");
				notimain(login_A.list_new_new);
			}
		});

		// ====================================================================
		// '공지등록' - 등록 & 취소 버튼
		// wri_jb1 = 등록
		wri_jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int answer = JOptionPane.showConfirmDialog(login_A, "공지사항을 등록겠습니까?", "공지등록", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					NotiVO notivo = new NotiVO();
					notivo.setNtitle(jtf_wri_title.getText());
					notivo.setNcontents(wri_jta.getText());
					notivo.setComid(writecomid);
					notivo.setNdate(ndate_now);
					if (wri_jcb_important_save == null) {
						notivo.setNimportant("0");
					} else {
						notivo.setNimportant(wri_jcb_important_save);
					}
					notivo.setNusername(writername);

					D_Protocol p = new D_Protocol();
					p.setCmd(310);
					p.setNotivo(notivo);
					try {
						login_A.out.writeObject(p);
						login_A.out.flush();
					} catch (IOException e1) {
						System.out.println(e1);
					}
					cl.show(card, "wri");
					JOptionPane.showMessageDialog(login_A, "등록이 되었습니다. 뒤로 버튼을 눌러주세요");
				} else {
					cl.show(card, "wri");
					JOptionPane.showMessageDialog(login_A, "등록이 되지 않았습니다. 다시 확인해 주세요.");
				}
			}
		});

		// wri_jb2 = 뒤로
		wri_jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notimain(login_A.list_new_new);
				cl.show(card, "main");
			}
		});

		// wri_jcb = 중요 공지 체크 박스
		wri_jcb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					wri_jcb_important = "1";
				} else {
					wri_jcb_important = "0";
				}
				wri_jcb_important_save = wri_jcb_important;
			}
		});

		// ================================================================
		// '공지수정' - 수정 & 삭제 & 취소 버튼
		// 삭제
		rev_jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(login_A, "공지사항을 삭제하시겠습니까?", "공지삭제",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					NotiVO notivo = new NotiVO();
					notivo.setNdelete("99");
					notivo.setNnumber(Integer.parseInt(jtf_rev_nnumber.getText()));

					D_Protocol p = new D_Protocol();
					p.setCmd(313);
					p.setNotivo(notivo);
					try {
						login_A.out.writeObject(p);
						login_A.out.flush();
					} catch (IOException e1) {
						System.out.println(e1);
					}
					cl.show(card, "rev");
					JOptionPane.showMessageDialog(login_A, "삭제가 완료 되었습니다. 뒤로 버튼으로 메인창으로 돌아가 주십시오");
				} else {
					JOptionPane.showMessageDialog(login_A, "삭제가 제대로 되지 않았습니다.");
					cl.show(card, "rev");
				}
			}
		});

		// 수정
		rev_jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(login_A, "공지사항을 수정하시겠습니까?", "공지수정",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					NotiVO notivo = new NotiVO();
					notivo.setNtitle(jtf_rev_title.getText());
					notivo.setNcontents(rev_jta.getText());
					if (rev_jcb_important_save == null) {
						notivo.setNimportant("0");
					} else {
						notivo.setNimportant(rev_jcb_important_save);
					}
					notivo.setNnumber(Integer.parseInt(jtf_rev_nnumber.getText()));

					D_Protocol p = new D_Protocol();
					p.setCmd(314);
					p.setNotivo(notivo);
					try {
						login_A.out.writeObject(p);
						login_A.out.flush();
					} catch (IOException e1) {
						System.out.println(e1);
					}
					JOptionPane.showMessageDialog(login_A, "수정이 완료 되었습니다. 뒤로 버튼으로 메인창으로 돌아가 주십시오");
					cl.show(card, "rev");
				} else {
					JOptionPane.showMessageDialog(login_A, "수정이 제대로 되지 않았습니다.");
					cl.show(card, "rev");
				}
			}
		});

		// 뒤로
		rev_jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notimain(login_A.list_new_new);
				cl.show(card, "main");
			}
		});

		// 주요 공지 여부
		rev_jcb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					rev_jcb_important = "1";
				} else {
					rev_jcb_important = "0";
				}
				rev_jcb_important_save = rev_jcb_important;
				System.out.println(rev_jcb_important);
			}
		});

	}

	public void notiwriter(MembersVO wrivo) { //
		jtf_wri_writer.setText(wrivo.getUSERNAME());
		jtf_wri_writer.setEditable(false);
		jtf_rev_writer.setText(wrivo.getUSERNAME());
		jtf_rev_writer.setEditable(false);

		// 로그인 이름 & comid 전역변수로 빼기
		writername = wrivo.getUSERNAME();
		writecomid = Integer.parseInt(wrivo.getCOMID());

	}

	public void notimain(List<NotimainVO> list) {
		// 가져온 list를 전역변수화 시켜 전체에서 쓰자
		notilist = list;

		// null 값은 빈값으로 뜬다.
		if (notilist.size() == 0) {
			jl[0][0].setText("");
			jl[0][1].setText("공지를 등록해주세요");
			jl[0][2].setText("");
			jl[0][3].setText("");
			jl[0][4].setText("");
		}

		// 1. 공지사항이 1개일때
		if (notilist.size() == 1) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반공지");
				} else {
					jl[i][0].setText("주요공지");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
				jl[i][2].setText(notilist.get(i).getNusername());
				jl[i][3].setText(notilist.get(i).getNdate());
				if (notilist.get(i).getNcount() == null) {
					jl[i][4].setText("0");
				} else {
					jl[i][4].setText(notilist.get(i).getNcount());
				}
			}

			n1 = notilist.get(0).getNnumber();

			jl[0][1].setVisible(true);
			jl[1][0].setText("");
			jl[1][1].setVisible(false);
			jl[1][2].setText("");
			jl[1][3].setText("");
			jl[1][4].setText("");
			jl[2][1].setVisible(false);
			jl[3][1].setVisible(false);
			jl[4][1].setVisible(false);
			jl[5][1].setVisible(false);
			jl[6][1].setVisible(false);
		}

		// 2. 공지사항이 2개일때
		if (notilist.size() == 2) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반공지");
				} else {
					jl[i][0].setText("주요공지");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
				jl[i][2].setText(notilist.get(i).getNusername());
				jl[i][3].setText(notilist.get(i).getNdate());
				if (notilist.get(i).getNcount() == null) {
					jl[i][4].setText("0");
				} else {
					jl[i][4].setText(notilist.get(i).getNcount());
				}
			}

			n1 = notilist.get(0).getNnumber();
			n2 = notilist.get(1).getNnumber();

			jl[0][1].setVisible(true);
			jl[1][1].setVisible(true);
			jl[2][0].setText("");
			jl[2][1].setVisible(false);
			jl[2][2].setText("");
			jl[2][3].setText("");
			jl[2][4].setText("");
			jl[3][1].setVisible(false);
			jl[4][1].setVisible(false);
			jl[5][1].setVisible(false);
			jl[6][1].setVisible(false);
		}

		// 3. 공지사항이 3개일때
		if (notilist.size() == 3) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반공지");
				} else {
					jl[i][0].setText("주요공지");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
				jl[i][2].setText(notilist.get(i).getNusername());
				jl[i][3].setText(notilist.get(i).getNdate());

				if (notilist.get(i).getNcount() == null) {
					jl[i][4].setText("0");
				} else {
					jl[i][4].setText(notilist.get(i).getNcount());
				}
			}

			n1 = notilist.get(0).getNnumber();
			n2 = notilist.get(1).getNnumber();
			n3 = notilist.get(2).getNnumber();

			jl[0][1].setVisible(true);
			jl[1][1].setVisible(true);
			jl[2][1].setVisible(true);
			jl[3][0].setText("");
			jl[3][1].setVisible(false);
			jl[3][2].setText("");
			jl[3][3].setText("");
			jl[3][4].setText("");
			jl[4][1].setVisible(false);
			jl[5][1].setVisible(false);
			jl[6][1].setVisible(false);
		}

		// 4. 공지사항이 4개일때
		if (notilist.size() == 4) {

			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반공지");
				} else {
					jl[i][0].setText("주요공지");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
				jl[i][2].setText(notilist.get(i).getNusername());
				jl[i][3].setText(notilist.get(i).getNdate());
				if (notilist.get(i).getNcount() == null) {
					jl[i][4].setText("0");
				} else {
					jl[i][4].setText(notilist.get(i).getNcount());
				}
			}

			n1 = notilist.get(0).getNnumber();
			n2 = notilist.get(1).getNnumber();
			n3 = notilist.get(2).getNnumber();
			n4 = notilist.get(3).getNnumber();

			jl[0][1].setVisible(true);
			jl[1][1].setVisible(true);
			jl[2][1].setVisible(true);
			jl[3][1].setVisible(true);
			jl[4][0].setText("");
			jl[4][1].setVisible(false);
			jl[4][2].setText("");
			jl[4][3].setText("");
			jl[4][4].setText("");
			jl[5][1].setVisible(false);
			jl[6][1].setVisible(false);
		}
		// 5. 공지사항이 5개일때
		if (notilist.size() == 5) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반공지");

				} else {
					jl[i][0].setText("주요공지");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
				jl[i][2].setText(notilist.get(i).getNusername());
				jl[i][3].setText(notilist.get(i).getNdate());
				if (notilist.get(i).getNcount() == null) {
					jl[i][4].setText("0");
				} else {
					jl[i][4].setText(notilist.get(i).getNcount());
				}
			}

			n1 = notilist.get(0).getNnumber();
			n2 = notilist.get(1).getNnumber();
			n3 = notilist.get(2).getNnumber();
			n4 = notilist.get(3).getNnumber();
			n5 = notilist.get(4).getNnumber();

			jl[0][1].setVisible(true);
			jl[1][1].setVisible(true);
			jl[2][1].setVisible(true);
			jl[3][1].setVisible(true);
			jl[4][1].setVisible(true);
			jl[5][0].setText("");
			jl[5][1].setVisible(false);
			jl[5][2].setText("");
			jl[5][3].setText("");
			jl[5][4].setText("");
			jl[6][1].setVisible(false);
		}

		// 6. 공지사항이 6개일때
		if (notilist.size() == 6) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반공지");
				} else {
					jl[i][0].setText("주요공지");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
				jl[i][2].setText(notilist.get(i).getNusername());
				jl[i][3].setText(notilist.get(i).getNdate());
				if (notilist.get(i).getNcount() == null) {
					jl[i][4].setText("0");
				} else {
					jl[i][4].setText(notilist.get(i).getNcount());
				}
			}

			n1 = notilist.get(0).getNnumber();
			n2 = notilist.get(1).getNnumber();
			n3 = notilist.get(2).getNnumber();
			n4 = notilist.get(3).getNnumber();
			n5 = notilist.get(4).getNnumber();
			n6 = notilist.get(5).getNnumber();

			jl[0][1].setVisible(true);
			jl[1][1].setVisible(true);
			jl[2][1].setVisible(true);
			jl[3][1].setVisible(true);
			jl[4][1].setVisible(true);
			jl[5][1].setVisible(true);
			jl[6][0].setText("");
			jl[6][1].setVisible(false);
			jl[6][2].setText("");
			jl[6][3].setText("");
			jl[6][4].setText("");

		}
		// 7. 공지사항이 7개일때
		if (notilist.size() == 7) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반공지");
				} else {
					jl[i][0].setText("주요공지");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
				jl[i][2].setText(notilist.get(i).getNusername());
				jl[i][3].setText(notilist.get(i).getNdate());
				if (notilist.get(i).getNcount() == null) {
					jl[i][4].setText("0");
				} else {
					jl[i][4].setText(notilist.get(i).getNcount());
				}
			}

			n1 = notilist.get(0).getNnumber();
			n2 = notilist.get(1).getNnumber();
			n3 = notilist.get(2).getNnumber();
			n4 = notilist.get(3).getNnumber();
			n5 = notilist.get(4).getNnumber();
			n6 = notilist.get(5).getNnumber();
			n7 = notilist.get(6).getNnumber();

			jl[0][1].setVisible(true);
			jl[1][1].setVisible(true);
			jl[2][1].setVisible(true);
			jl[3][1].setVisible(true);
			jl[4][1].setVisible(true);
			jl[5][1].setVisible(true);
			jl[6][1].setVisible(true);
		}
	}
}