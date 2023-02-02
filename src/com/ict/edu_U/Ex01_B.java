package com.ict.edu_U;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.apache.ibatis.annotations.Case;

import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.InfoVO;
import com.ict.edu_D.MembersVO;
import com.ict.edu_D.signup_in_VO;

public class Ex01_B extends JFrame {
	JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp12, jp13, jp14, jp15, jp16, jp17, jp18, jp19,
			jp20, jp21, jp22, jp23, jp24, jp25, jp26, jp27, jp28, jp29, input;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10, jl11, jl12, jl13, jl14, jl15, jl16, jl17, jl18, jl19,
			jl20, jl21, jl22, jl23, jl24, jl25, jl26, jl27, jl28, jl29, jl30, jl31, jl32, jl33, jl34, jl35;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10, jtf11, jtf12, jtf13, jtf14;
	JButton jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8;
	JTextArea jta, jta1, jta2, jta3, jta4;
	JScrollPane jsp;
	JCheckBox jbc1, jbc2, jbc3;
	CardLayout cl1;
	TextField tf2, tf3;
	JPasswordField jpass;
	public boolean show = true;

	LogIn_B logIn_B;

	public Ex01_B(LogIn_B login) {

		logIn_B = login;

		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

		jp1 = new JPanel();
		jl1 = new JLabel(" 아이디    ");
		jtf1 = new JTextField(21);
		jb4 = new JButton("중복확인");
		jl20 = new JLabel("*");
		jl20.setForeground(Color.RED);
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jb4);
		jp1.add(jl20);
		jp1.setBorder(new EmptyBorder(0, 10, 0, 20));
		jp.add(jp1);

		jp2 = new JPanel();
		jl2 = new JLabel(" 비밀번호  ");
		tf2 = new TextField(43);
		tf2.selectAll();
		tf2.setEchoChar('*');
		jl21 = new JLabel("*");
		jl21.setForeground(Color.RED);
		jp2.add(jl2);
		jp2.add(tf2);
		jp2.add(jl21);

		jp2.setBorder(new EmptyBorder(0, 0, 0, 18));
		jp.add(jp2);

		jp3 = new JPanel();
		jl3 = new JLabel(" 비번확인  ");
		tf3 = new TextField(43);
		tf3.selectAll();
		tf3.setEchoChar('*');
		jl22 = new JLabel("*");
		jl22.setForeground(Color.RED);
		jp3.add(jl3);
		jp3.add(tf3);
		jp3.add(jl22);
		jp3.setBorder(new EmptyBorder(0, 0, 0, 18));
		jp.add(jp3);
		add(jp1, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		add(jp3, BorderLayout.SOUTH);
		// 1 그룹

		jp4 = new JPanel();
		jl4 = new JLabel("   이름      ");
		jtf4 = new JTextField(10);
		jl23 = new JLabel("*");
		jl23.setForeground(Color.RED);
		jp4.add(jl4);
		jp4.add(jtf4);
		jp4.add(jl23);
		jp4.setBorder(new EmptyBorder(0, 0, 0, 220));
		jp.add(jp4);

		jp5 = new JPanel();
		jl5 = new JLabel(" 생년월일 ");
		String[] items = { "          년도          ", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002",
				"2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015",
				"2016", "2017", "2018", "2019", "2020", "2021", "2022" };
		JComboBox<String> jcom = new JComboBox<>(items);
		jl6 = new JLabel("년");
		String[] items1 = { "      월     ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		JComboBox<String> jcom1 = new JComboBox<>(items1);
		jl7 = new JLabel("월");
		String[] items2 = { "      일     ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31" };
		JComboBox<String> jcom2 = new JComboBox<>(items2);
		jl8 = new JLabel("일");

		jl24 = new JLabel("*");
		jl24.setForeground(Color.RED);
		jp5.add(jl5);
		jcom.setSelectedIndex(0);
		jp5.add(jcom);
		jp5.add(jl6);
		jcom.setSelectedIndex(0);
		jp5.add(jcom1);
		jp5.add(jl7);
		jcom.setSelectedIndex(0);
		jp5.add(jcom2);
		jp5.add(jl8);
		jp5.add(jl24);
		jp5.setBorder(new EmptyBorder(0, 0, 0, 19));
		jp.add(jp5);

		jp6 = new JPanel();
		jl5 = new JLabel("      주소     ");
		jtf5 = new JTextField(5);
		jb1 = new JButton("우편번호 찾기");
		jb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jl25 = new JLabel("*");
		jl25.setForeground(Color.RED);
		jp6.add(jl5);
		jp6.add(jtf5);
		jp6.add(jb1);
		jp6.add(jl25);
		jp6.setBorder(new EmptyBorder(0, 0, 0, 165));
		jp.add(jp6);
		add(jp4, BorderLayout.NORTH);
		add(jp5, BorderLayout.CENTER);
		add(jp6, BorderLayout.SOUTH);
		// 2 그룹

		jp7 = new JPanel();
		jtf6 = new JTextField("기본주소", 30);
		jp7.add(jtf6);
		jp7.setBorder(new EmptyBorder(0, 48, 0, 0));
		jp.add(jp7);

		jp8 = new JPanel();
		jtf7 = new JTextField("상세주소", 30);
		jp8.add(jtf7);
		jp8.setBorder(new EmptyBorder(0, 48, 0, 0));
		jp.add(jp8);

		jp9 = new JPanel();
		jl9 = new JLabel("일반전화 ");
		String[] items3 = { "02", "031", "032", "033", "043", "044", "042", "041", "055", "054", "053", "052", "051",
				"063", "062", "061" };
		JComboBox<String> jcom3 = new JComboBox<>(items3);
		jl10 = new JLabel("");
		jtf8 = new JTextField(10);
		jl11 = new JLabel("-");
		jtf14 = new JTextField(10);
		jp9.add(jl9);
		jp9.add(jcom3);
		jp9.add(jl10);
		jp9.add(jtf8);
		jp9.add(jl11);
		jp9.add(jtf14);
		jp9.setBorder(new EmptyBorder(0, 0, 0, 45));
		jp.add(jp9);
		add(jp7, BorderLayout.NORTH);
		add(jp8, BorderLayout.CENTER);
		add(jp9, BorderLayout.SOUTH);
		// 3 그룹

		jp10 = new JPanel();
		jl9 = new JLabel("    H  P     ");
		String[] items4 = { "010", "011", "019", "017", "018" };
		JComboBox<String> jcom4 = new JComboBox<>(items4);
		jl12 = new JLabel("");
		jtf9 = new JTextField(10);
		jl13 = new JLabel("-");
		jtf10 = new JTextField(10);

		jl26 = new JLabel("*");
		jl26.setForeground(Color.RED);
		jp10.add(jl9);
		jp10.add(jcom4);
		jp10.add(jl12);
		jp10.add(jtf9);
		jp10.add(jl13);
		jp10.add(jtf10);
		jp10.add(jl26);
		jp10.setBorder(new EmptyBorder(0, 0, 0, 30));
		jp.add(jp10);

		jp11 = new JPanel();
		jl14 = new JLabel("   이메일   ");
		jtf11 = new JTextField("이메일", 9);
		jl15 = new JLabel("@");
		jtf12 = new JTextField("직접입력", 10);
		String[] items5 = { "naver.com", "daum.net", "gmail.com" };
		JComboBox<String> jcom5 = new JComboBox<>(items5);
		jl27 = new JLabel("*");
		jl27.setForeground(Color.RED);
		jp11.add(jl14);
		jp11.add(jtf11);
		jp11.add(jl15);
		jp11.add(jtf12);
		jp11.add(jcom5);
		jp11.add(jl27);
		jp11.setBorder(new EmptyBorder(0, 10, 0, 15));
		jp.add(jp11);

		jp12 = new JPanel();
		jl16 = new JLabel(" 입사일자 ");
		String[] items6 = { "            　             ", "1995", "1996", "1997", "1998", "1999", "2000", "2001",
				"2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014",
				"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022" };
		JComboBox<String> jcom6 = new JComboBox<>(items6);
		jl17 = new JLabel("년");
		String[] items7 = { "      　      ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		JComboBox<String> jcom7 = new JComboBox<>(items7);
		jl18 = new JLabel("월");
		String[] items8 = { "      　      ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31" };
		JComboBox<String> jcom8 = new JComboBox<>(items8);
		jl19 = new JLabel("일");

		jl28 = new JLabel("*");
		jl28.setForeground(Color.RED);
		jp12.add(jl16);
		jcom.setSelectedIndex(0);
		jp12.add(jcom6);
		jp12.add(jl17);
		jcom.setSelectedIndex(0);
		jp12.add(jcom7);
		jp12.add(jl18);
		jcom.setSelectedIndex(0);
		jp12.add(jcom8);
		jp12.add(jl19);
		jp12.add(jl28);
		jp12.setBorder(new EmptyBorder(0, 30, 0, 40));
		jp.add(jp12);
		add(jp10, BorderLayout.NORTH);
		add(jp11, BorderLayout.CENTER);
		add(jp12, BorderLayout.SOUTH);
		// 4 그룹

		jp13 = new JPanel();
		jta = new JTextArea("제 1 장 총 칙\r\n" + "\r\n" + "제 1 조 (목적)\r\n" + "이 약관은 4조 마음대로 정한 것이며 불만 또한 받지 않습니다\r\n"
				+ "\r\n" + "제 2 조 (약관의 효력 및 변경)\r\n" + "① 이 약관은 동의 시 즉각 법적 효력이 발생합니다\r\n"
				+ "② 회사를 향한 불만 또한 받지 않습니다.\r\n" + "\r\n" + "제 3 조 (용어의 정의)\r\n" + "이 약관에서 사용하는 용어의 정의는 다음과 같습니다.\r\n"
				+ "① 회원 : 사이트와 서비스 이용계약을 체결하거나 이용자 아이디(ID)를 부여받은 개인 또는 단체를 말합니다.\r\n"
				+ "② 신청자 : 회원가입을 신청하는 개인 또는 단체를 말합니다.\r\n"
				+ "③ 아이디(ID) : 회원의 식별과 서비스 이용을 위하여 회원이 정하고 사이트가 승인하는 문자와 숫자의 조합을 말합니다.\r\n"
				+ "④ 비밀번호 : 회원이 부여 받은 아이디(ID)와 일치된 회원임을 확인하고, 회원 자신의 비밀을 보호하기 위하여 회원이 정한 문자와 숫자의 조합을 말합니다.\r\n"
				+ "⑤ 해지 : 사이트 또는 회원이 서비스 이용계약을 취소하는 것을 말합니다.\r\n" + "\r\n" + "제 4 조 (모상인 잘생김)\r\n"
				+ "① 비록 지금은 살이 쪄서 비수기지만 원래는 잘생긴 걸 인정한다.\r\n" + "\r\n" + "제 5 조 (급여)\r\n" + "① 연봉협상없음.\r\n"
				+ "② 휴가없음.\r\n", 10, 40);
		jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setLineWrap(true);
		jta.setEditable(false);
		jp13.add(jsp);
		jp13.setBorder(new EmptyBorder(10, 20, 0, 0));
		jp.add(jp13);

		jp14 = new JPanel();
		jbc1 = new JCheckBox("약관동의여부");
		jl29 = new JLabel("*");
		jl29.setForeground(Color.RED);
		jp14.add(jbc1);
		jp14.add(jl29);
		jp14.setBorder(new EmptyBorder(0, 350, 0, 0));
		jp.add(jp14);

		jp15 = new JPanel();
		jb3 = new JButton("닫기");
		jb3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jb2 = new JButton("회원가입");
		jb2.setEnabled(false);
		jb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp15.add(jb3);
		jp15.add(jb2);
		jp15.setBorder(new EmptyBorder(0, 360, 0, 0));
		jp.add(jp15);
		add(jp13, BorderLayout.NORTH);
		add(jp14, BorderLayout.CENTER);
		add(jp15, BorderLayout.SOUTH);
		// 5 그룹

		jp22 = new JPanel();
		cl1 = new CardLayout();

		JPanel jpp = new JPanel();
		jpp.setLayout(new BoxLayout(jpp, BoxLayout.Y_AXIS));

		jpp.add(jp1);
		jpp.add(jp2);
		jpp.add(jp3);
		jpp.add(jp4);
		jpp.add(jp5);
		jpp.add(jp6);
		jpp.add(jp7);
		jpp.add(jp8);
		jpp.add(jp9);
		jpp.add(jp10);
		jpp.add(jp11);
		jpp.add(jp12);
		jpp.add(jp13);
		jpp.add(jp14);
		jpp.add(jp15);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		MatteBorder bb = new MatteBorder(1, 1, 1, 1, Color.black);

		jpp.setBorder(bb);
		jp22.add(jpp);
		jp22.setBorder(new EmptyBorder(40, 0, 60, 0));
		add(jp22);

		setLocation(800, 100);
		setSize(800, 800);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 우편번호
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// 회원가입누르면 DB에 정보저장
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 아이디
				String a1 = jtf1.getText().trim();
				System.out.println(a1);
				// 비번
				String a2 = tf2.getText().trim();
				System.out.println(a2);
				// 이름
				String a3 = jtf4.getText().trim();
				System.out.println(a3);
				// 생년월일
				String birth1 = items[jcom.getSelectedIndex()];
				String birth2 = items1[jcom1.getSelectedIndex()];
				String birth3 = items2[jcom2.getSelectedIndex()];
				String birth = birth1 + "-" + birth2 + "-" + birth3;
				System.out.println(birth);
				// 우편번호
				String mailbox1 = jtf5.getText().trim();
				String mailbox2 = jtf6.getText().trim();
				String mailbox3 = jtf7.getText().trim();
				String mailbox = mailbox1 + "-" + mailbox2 + "-" + mailbox3;
				System.out.println(mailbox);
				// 집전화
				String tempHOME = items3[jcom3.getSelectedIndex()];
				String HOME1 = jtf8.getText().trim();
				String HOME2 = jtf14.getText().trim();
				String HOME = tempHOME + "-" + HOME1 + "-" + HOME2;
				System.out.println(HOME);
				// 핸드폰
				String temp = items4[jcom4.getSelectedIndex()];// 핸드폰
				String aa = jtf9.getText().trim();
				String ab = jtf10.getText().trim();
				String phone = temp + "-" + aa + "-" + ab;
				System.out.println(phone);
				// email
				String a8 = jtf11.getText().trim(); // email id
				String a9 = jtf12.getText().trim(); // email 주소
				String email = a8 + "@" + a9;
				System.out.println(email);
				// 입사일
				String temp1 = items6[jcom6.getSelectedIndex()]; // 입사일 년
				String temp2 = items7[jcom7.getSelectedIndex()];// 입사일 월
				String temp3 = items8[jcom8.getSelectedIndex()];// 입사일 일
				String com = temp1 + "-" + temp2 + "-" + temp3;
				System.out.println(com);

				// || 하나만 true면 true && 하나라도 false 면 false
				if (a1.length() == 0 || a2.length() == 0 || a3.length() == 0 || birth.length() == 0
						|| mailbox.length() == 0 || HOME.length() == 0 || phone.length() == 0 || email.length() == 0
						|| com.length() == 0) {
					JOptionPane.showMessageDialog(null, "필수입력창을 다시 확인하세요", "경고", JOptionPane.WARNING_MESSAGE);
				} else if (rootPaneCheckingEnabled) {
					int res = JOptionPane.showConfirmDialog(getParent(), "회원가입을하시겠습니까?", "회원가입 결정",
							JOptionPane.OK_CANCEL_OPTION);

					if (tf2.getText().trim().equals(tf3.getText().trim())) {
						if (res == 0) {

							setVisible(false);
							logIn_B = new LogIn_B();
							signup_in_VO vo = new signup_in_VO();

							vo.setIDX(a1);
							vo.setPW(a2);
							vo.setUSERNAME(a3);
							vo.setBIRTHDATE(birth);// 생년월일
							vo.setABODE(mailbox); // 우편
							vo.setTELEPHONE(HOME);// 집전화
							vo.setPHONE(phone); // 핸드폰
							vo.setEMAIL(email); // 이메일
							vo.setJOINDATE(com); // 입사

							InfoVO infoVO = new InfoVO();
							infoVO.setIdx(a1);
							infoVO.setUsername(a3);
							MembersVO membersVO = new MembersVO();
							membersVO.setIDX(a1);

							D_Protocol p = new D_Protocol();
							p.setCmd(1);
							p.setVo(vo);
							p.setInfovo(infoVO);
							p.setMemvo(membersVO);

							try {
								login.out.writeObject(p);
								login.out.flush();
							} catch (IOException e1) {
								e1.printStackTrace();
							}

						}
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 서로 다릅니다 재확인 해주세요", "경고",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});
		// ID 중복검사
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				signup_in_VO vo = new signup_in_VO(); // 아이디를 세팅해서 보내려고 vo 생성
				String a1 = jtf1.getText().trim(); // 아이디 입력창에서 입력된 정보를 가져옴, a1에 저장

				vo.setIDX(a1); // (a1)아이디를 IDX에 저장(세팅)

				D_Protocol p = new D_Protocol(); // Protocol로 주고 받으려고 p 생성
				p.setCmd(2); // cmd 2번이 회원가입할 때 아이디 중복검사
				p.setVo(vo); // vo에는 현재 IDX(아이디값)이 들어있음
				try {
					login.out.writeObject(p); // p 전송 (클라이언트에서 전송하면 서버에서 받음)
					login.out.flush(); // 버퍼에 남아있는 데이터 전송
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		jtf6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtf6.setText("");
			}
		});

		jtf6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jtf6.setText("");
			}
		});

		jtf7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jtf7.setText("");
			}
		});

		jtf7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtf7.setText("");
			}
		});

		jtf11.addMouseListener(new MouseAdapter() { // 이메일
			@Override
			public void mouseClicked(MouseEvent e) {
				jtf11.setText("");

			}
		});

		jtf11.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jtf11.setText("");
			}
		});

		jtf12.addMouseListener(new MouseAdapter() { // 직접입력
			@Override
			public void mouseClicked(MouseEvent e) {
				jtf12.setText("");
				jtf12.setEnabled(true);
			}

		});

		jtf12.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jtf12.setText("");

			}
		});

		jbc1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					jb2.setEnabled(true);
				} else
					jb2.setEnabled(false);
			}
		});

		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(getParent(), "입력한 정보가 없어집니다 정말 나가시겠습니까?", "취소",
						JOptionPane.OK_CANCEL_OPTION);
				if (res == 0) {
					setVisible(false);
					new LogIn_B();
				}
			}
		});

		jcom5.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				jtf12.setText(String.valueOf(a)); // 오브젝
				jtf12.setEnabled(false);// if you press the 'yes' button, the textfield(about id) is setEnabled(f
			}// U got it? 그니깐 YES 를 누르면 텍스트 필드가 꺼지게 하라는 거
		});

	}

	public void cc() {
		JOptionPane.showMessageDialog(null, "사용 가능한 ID입니다");
		int r = JOptionPane.showConfirmDialog(getParent(), "아이디를 사용하시겠습니까?", "아이디 결정", JOptionPane.OK_CANCEL_OPTION);
		jtf1.setEnabled(false);
		jb4.setEnabled(false);
	}

	public void dd() {
		JOptionPane.showMessageDialog(null, "중복 되는 ID 입니다");
		jtf1.setText("");
	}

}
