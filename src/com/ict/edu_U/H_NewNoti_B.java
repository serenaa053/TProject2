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

public class H_NewNoti_B extends JPanel {
	// 구성 및 공지 항목 배열화
	JLabel jlno, jltitle;

	JLabel[][] jl = new JLabel[7][2];

	// number
	int n1, n2, n3, n4, n5, n6, n7;

	// 각 row가 붙을 Panel
	JPanel jptitle;

	JPanel[] jp = new JPanel[7];

	// 패널 구분 선
	JPanel[] jp_line = new JPanel[7];

	// 메인Panel과 버튼Panel 나누기
	JPanel jp_m, jp_b, jp_b_1;

	JPanel jp_noti;

	// 각 창 주요공지 체크여부
	String wri_jcb_important, rev_jcb_important;

	String wri_jcb_important_save, rev_jcb_important_save; // db 저장을 위한 전역 변수

	Font myFont_menubar, myFont_important, myFont_basic, myFont_noti_click;

	LineBorder noti_lb, basic_lb;

	public LogIn_B login_B;

	public List<NotimainVO> notilist;

	public H_NewNoti_B(LogIn_B login) {
		login_B = login;

		int[] number = { n1, n2, n3, n4, n5, n6, n7 };

		// ===============================================
		// Font 및 LineBorder 구성
		myFont_menubar = new Font("굴림", Font.BOLD, 15);
		myFont_important = new Font("굴림", Font.BOLD, 14);
		myFont_basic = new Font("굴림", Font.PLAIN, 15);
		myFont_noti_click = new Font("궁서", Font.BOLD, 5);

		noti_lb = new LineBorder(Color.BLACK, 2);

		// 메인 화면단 구성
		jp_noti = new JPanel(new BorderLayout());

		jp_m = new JPanel(new FlowLayout());
		jp_m.setPreferredSize(new Dimension(375, 558));

		jptitle = new JPanel();
		jptitle.setPreferredSize(new Dimension(350, 50));

		// 버튼 화면단 구성
		jp_b = new JPanel(new BorderLayout());
		jp_b.setPreferredSize(new Dimension(200, 50));
		jp_b_1 = new JPanel();
		jp_b.add(jp_b_1, BorderLayout.EAST);

		// 1. "순번", "제목", "작성자", "작성일", "조회수"
		// 제목단 분리
		jlno = new JLabel("순번", JLabel.CENTER);
		jlno.setPreferredSize(new Dimension(50, 40));
		jlno.setHorizontalAlignment(JLabel.CENTER);

		jltitle = new JLabel("제목", JLabel.CENTER);
		jltitle.setPreferredSize(new Dimension(290, 40));
		jltitle.setHorizontalAlignment(JLabel.CENTER);

		// 항목 Line 폰트 및 테두리 설정하기
		jlno.setFont(myFont_menubar);
		jltitle.setFont(myFont_menubar);

		jlno.setBorder(noti_lb);
		jltitle.setBorder(noti_lb);

		jptitle.add(jlno);
		jptitle.add(jltitle);

		jp_m.add(jptitle);

		// 2. 1~7번 공지창 나누자
		for (int i = 0; i < 7; i++) {
			// 데이터 화면단에 호출하기
			jp[i] = new JPanel();

			jp_line[i] = new JPanel();
			jp_line[i].setPreferredSize(new Dimension(344, 1));
			jp_line[i].setBackground(Color.BLACK);

			jl[i][0] = new JLabel("");
			jl[i][0].setPreferredSize(new Dimension(50, 50));
			jl[i][0].setHorizontalAlignment(JLabel.CENTER);

			jl[i][1] = new JLabel("");
			jl[i][1].setPreferredSize(new Dimension(290, 50));
			jl[i][1].setHorizontalAlignment(JLabel.CENTER);

			// 기본 폰트 설정하기
			jl[i][0].setFont(myFont_basic);
			jl[i][1].setFont(myFont_basic);

			jp[i].add(jl[i][0]);
			jp[i].add(jl[i][1]);

			jp_m.add(jp[i]);
			jp_m.add(jp_line[i]);
		}

		jp_noti.add(jp_m, BorderLayout.CENTER);
		jp_noti.add(jp_b, BorderLayout.SOUTH);

		add(jp_noti);
		setPreferredSize(new Dimension(380, 615));
		
		// 공지 제목 라벨 액션 기능
		// 첫번째줄 제목 라벨
		jl[0][1].addMouseListener(new MouseAdapter() {
			@Override
			// 평상시
			public void mouseExited(MouseEvent e) {
				jl[0][1].setForeground(Color.BLACK);
			}

			// 마우스 포인터 두기
			@Override
			public void mouseEntered(MouseEvent e) {
				jl[0][1].setForeground(Color.RED);
			}

			// 클릭시
			@Override
			public void mouseClicked(MouseEvent e) {
				if (notilist.size() == 0) {
					JOptionPane.showMessageDialog(login_B, "등록된 공지가 없습니다");
				} else {
						login.main.jtap.setSelectedIndex(4);
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
				login.main.jtap.setSelectedIndex(4);
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
				login.main.jtap.setSelectedIndex(4);
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
				login.main.jtap.setSelectedIndex(4);
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
				login.main.jtap.setSelectedIndex(4);
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
				login.main.jtap.setSelectedIndex(4);
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
				login.main.jtap.setSelectedIndex(4);
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
			jl[0][1].setText("등록된 공지가 없습니다.");
		}

		// 1. 공지사항이 1개일때
		if (notilist.size() == 1) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반");
				} else {
					jl[i][0].setText("주요");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
			}

			n1 = notilist.get(0).getNnumber();

			jl[0][1].setVisible(true);
			jl[1][0].setText("");
			jl[1][1].setVisible(false);
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
					jl[i][0].setText("일반");
				} else {
					jl[i][0].setText("주요");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
			}

			n1 = notilist.get(0).getNnumber();
			n2 = notilist.get(1).getNnumber();

			jl[0][1].setVisible(true);
			jl[1][1].setVisible(true);
			jl[2][0].setText("");
			jl[2][1].setVisible(false);
			jl[3][1].setVisible(false);
			jl[4][1].setVisible(false);
			jl[5][1].setVisible(false);
			jl[6][1].setVisible(false);
		}

		// 3. 공지사항이 3개일때
		if (notilist.size() == 3) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반");
				} else {
					jl[i][0].setText("주요");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());

			}

			n1 = notilist.get(0).getNnumber();
			n2 = notilist.get(1).getNnumber();
			n3 = notilist.get(2).getNnumber();

			jl[0][1].setVisible(true);
			jl[1][1].setVisible(true);
			jl[2][1].setVisible(true);
			jl[3][0].setText("");
			jl[3][1].setVisible(false);
			jl[4][1].setVisible(false);
			jl[5][1].setVisible(false);
			jl[6][1].setVisible(false);
		}

		// 4. 공지사항이 4개일때
		if (notilist.size() == 4) {

			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반");
				} else {
					jl[i][0].setText("주요");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
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
			jl[5][1].setVisible(false);
			jl[6][1].setVisible(false);
		}
		// 5. 공지사항이 5개일때
		if (notilist.size() == 5) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반");

				} else {
					jl[i][0].setText("주요");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
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
			jl[6][1].setVisible(false);
		}

		// 6. 공지사항이 6개일때
		if (notilist.size() == 6) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반");
				} else {
					jl[i][0].setText("주요");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
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
		}
		
		// 7. 공지사항이 7개일때
		if (notilist.size() == 7) {
			for (int i = 0; i < notilist.size(); i++) {
				if (notilist.get(i).getNimportant().equals("0")) {
					jl[i][0].setText("일반");
				} else {
					jl[i][0].setText("주요");
				}
				jl[i][1].setText(notilist.get(i).getNtitle());
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