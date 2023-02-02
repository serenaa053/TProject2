package com.ict.edu_U;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
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
import com.ict.edu_D.NotiVO;
import com.ict.edu_D.NotimainVO;

public class B_NewNoti_B extends JPanel {
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

	JButton rea_jb2;

	// ==================================================

	Font myFont_menubar, myFont_important, myFont_basic, myFont_noti_click;

	LineBorder noti_lb, basic_lb;

	public LogIn_B login_B;

	public List<NotimainVO> notilist;

	public B_NewNoti_B(LogIn_B login) {
		login_B = login;

		int[] nnumber = { n1, n2, n3, n4, n5, n6, n7 };

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
		jp_b_1 = new JPanel();
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

		rea_jb2 = new JButton("뒤로");
		jp_rea_bt_1 = new JPanel();
		jp_rea_bt_1.add(rea_jb2);

		jp_rea_enter.add(jp_rea_bt_1, BorderLayout.EAST);

		//
		jp_rea_c.add(jp_rea_bt, BorderLayout.CENTER);
		jp_rea_c.add(jp_rea_enter, BorderLayout.SOUTH);

		// ***
		jp_rea.add(jp_rea_a, BorderLayout.NORTH);
		jp_rea.add(jp_rea_b, BorderLayout.CENTER);
		jp_rea.add(jp_rea_c, BorderLayout.SOUTH);

		// ===============================================

		// ===============================================
		card = new JPanel(); // 0108 전역변수로 수정
		cl = new CardLayout(); // 0108 전역변수로 수정
		card.setLayout(cl);

		card.add("main", jp_noti);
		card.add("rea", jp_rea);

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
					JOptionPane.showMessageDialog(login_B, "공지를 등록해주세요");
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
						login_B.out.writeObject(p);
						login_B.out.flush();
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
					jtf_rea_writer.setEditable(false);

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
					login_B.out.writeObject(p);
					login_B.out.flush();
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
				jtf_rea_writer.setEditable(false);

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
					login_B.out.writeObject(p);
					login_B.out.flush();
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
				jtf_rea_writer.setEditable(false);

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
					login_B.out.writeObject(p);
					login_B.out.flush();
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
				jtf_rea_writer.setEditable(false);

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
					login_B.out.writeObject(p);
					login_B.out.flush();
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
				jtf_rea_writer.setEditable(false);

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
					login_B.out.writeObject(p);
					login_B.out.flush();
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
				jtf_rea_writer.setEditable(false);

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
					login_B.out.writeObject(p);
					login_B.out.flush();
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
				jtf_rea_writer.setEditable(false);

				cl.show(card, "rea");

			}
		});

		// rea_jb2 = 뒤로 버튼
		rea_jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(card, "main");
				notimain(login_B.list_new_new);
			}
		});
	}
	// ====================================================================

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