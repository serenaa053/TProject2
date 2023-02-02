package com.ict.edu_U;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ict.edu_D.AnnVO;
import com.ict.edu_D.MembersVO;
import com.ict.edu_D.NotimainVO;
import com.ict.edu_D.Pay_VO;

public class MaIn_B extends JFrame {
	JLabel jl1;
	JTabbedPane jtap, jtap1, jtap2;
	JPanel jp1, jp2, jp3, pnlSouth, btnNorth;
	JButton jb, jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10, jtf11, jtf12, jtf13, jtf14;
	JButton button;

	public LogIn_B logIn_B;
	MainScrean_Infor home;
	public WorkPlan_Infor Work;
	Annual_B annual;
	Ex02_B ex02;
	B_Infor_B infor;
	B_NewNoti_B b_NewNoti_B;
	public String idx = "";

	public MaIn_B(LogIn_B Login) {
		logIn_B = Login;

		JPanel pnlSouth = new JPanel(new BorderLayout());

		JButton btnSouthEast = new JButton(""); // 로그아웃
		btnSouthEast.setHorizontalAlignment(SwingConstants.CENTER);
		btnSouthEast.setIcon(new ImageIcon(asd_B.class.getResource("/images/logout.png.png")));

		JLabel btnSouthWest = new JLabel(""); // 회사로고
		btnSouthWest.setHorizontalAlignment(SwingConstants.CENTER);
		btnSouthWest.setIcon(new ImageIcon(asd_B.class.getResource("/images/logo (1).png")));

		JPanel a = new JPanel();
		jtap = new JTabbedPane();
		a.setLayout(new BorderLayout());

		home = new MainScrean_Infor(logIn_B);
		jtap.addTab("홈", home); // 상묵

		Work = new WorkPlan_Infor(logIn_B);
		jtap.addTab("근태관리", Work);

		annual = new Annual_B(logIn_B); // 세연누나
		jtap.addTab("연차관리", annual);

		ex02 = new Ex02_B(logIn_B);
		jtap.addTab("급여관리", ex02); // 상인

		b_NewNoti_B = new B_NewNoti_B(logIn_B);
		jtap.addTab("공지사항", b_NewNoti_B); // 행님

		infor = new B_Infor_B(logIn_B);
		jtap.addTab("기본정보", infor); // 행님

		clock_B clock = new clock_B();
		clock.setFont(new Font("고딕", Font.PLAIN, 100));

		jtap.addTab("시간", clock);

		// 로고 시간 로그아웃

		pnlSouth.add(clock, BorderLayout.CENTER);
		pnlSouth.add(btnSouthEast, BorderLayout.EAST);
		pnlSouth.add(btnSouthWest, BorderLayout.WEST);
		btnSouthEast.setPreferredSize(new Dimension(100, 30)); // 로그아웃 버튼 크기
		btnSouthEast.setContentAreaFilled(false); // 로그아웃 버튼 투명하게

		clock.setBorder(new EmptyBorder(0, 500, 0, 0));// 중앙
		btnSouthEast.setBorder(new EmptyBorder(5, 30, 10, 10));// 오른쪽
		btnSouthWest.setBorder(new EmptyBorder(10, 15, 10, 100));// 왼쪽

		a.add(jtap);
		a.setBorder(new EmptyBorder(0, 10, 10, 10));

		add(pnlSouth, BorderLayout.NORTH);
		add(a, BorderLayout.CENTER);

		// 이벤트 시작~
		btnSouthEast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(getParent(), "로그아웃하시겠습니까?", "취소", JOptionPane.OK_CANCEL_OPTION);
				if (res == 0) {
					dispose();
					new LogIn_B();
				}
			}
		});

		setLocation(800, 100);
		setSize(800, 800);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void ssdd(Pay_VO getOne) {
		ex02.ssdd(getOne);
	}

	public void exec(MembersVO infomvo) {
		infor.exec(infomvo);
	}

	public void notimain(List<NotimainVO> list) {
		b_NewNoti_B.notimain(list);
	}

	public void memberData(MembersVO mvo) {
		annual.memberData(mvo);
	}

	public void annData(AnnVO avo) {
		annual.annData(avo);
	}
}