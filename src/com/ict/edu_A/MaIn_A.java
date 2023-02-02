package com.ict.edu_A;
		
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

import com.ict.edu_D.MembersVO;
import com.ict.edu_D.NotimainVO;
import com.ict.edu_D.Pay_VO;
import com.ict.edu_U.LogIn_B;
		
public class MaIn_A extends JFrame {
	JLabel jl1 ;
	JTabbedPane jtap ,jtap1 ,jtap2 ; 
	JPanel jp1 , jp2 , jp3 , pnlSouth ,btnNorth ; 
	JButton jb,jb2; 
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10, jtf11, jtf12, jtf13, jtf14;
	JButton button ;
	
	
	
	public B_NewNoti_A b_NewNoti_A;
	public Admin_A admin;
	public Bata_WorkPlan_A Work;
	public Annual_A annual;
	public Ex02_A ex02;
	public B_Infor_A infor;
	public B_Write_A write;
	public String idx;
	
	public LogIn_A login_A;
	public MaIn_A main;
		
	public MaIn_A(LogIn_A login) {
		super("관리자");
		login_A = login;
		
//		 이미지 30
//		JButton btnNorth = new JButton("North");
		JPanel pnlSouth = new JPanel(new BorderLayout());
		
		
		JButton btnSouthEast = new JButton(""); // 로그아웃
		btnSouthEast.setHorizontalAlignment(SwingConstants.CENTER);
		btnSouthEast.setIcon(new ImageIcon(asd_A.class.getResource("/images/logout.pn"
				+ "g.png")));	
		
		
		JLabel btnSouthWest = new JLabel("");   // 회사로고
		btnSouthWest.setHorizontalAlignment(SwingConstants.CENTER);
		btnSouthWest.setIcon(new ImageIcon(asd_A.class.getResource("/images/logo2.png")));
		
		
		
		
		
		// 텝 
		
		JPanel a = new JPanel();
		jtap = new JTabbedPane();
		a.setLayout(new BorderLayout());
		
		b_NewNoti_A = new B_NewNoti_A(login_A);
		jtap.addTab("공지사항", b_NewNoti_A);
		
		
		admin = new Admin_A(login_A, jtap);
		jtap.addTab("관리자", admin); 	// 
		
		Work = new Bata_WorkPlan_A(login_A, jtap) ; 
		jtap.addTab("근태관리", Work);
		
		annual = new Annual_A(login_A) ;   // 세연누나
		jtap.addTab("연차관리", annual);
		
		ex02 = new Ex02_A(login_A);
		jtap.addTab("급여관리", ex02);	 // 상인

		infor = new B_Infor_A(login_A);
		jtap.addTab("기본정보", infor);	 // 행님 
		
		
		clock_A clock = new clock_A();
		clock.setFont(new Font("고딕", Font.PLAIN, 100));
		
		jtap.addTab("시간", clock);
		
		
		// 로고 시간 로그아웃
		
		pnlSouth.add(clock, BorderLayout.CENTER);
		pnlSouth.add(btnSouthEast, BorderLayout.EAST);
		pnlSouth.add(btnSouthWest, BorderLayout.WEST);
		//pnlSouth.setBorder(new EmptyBorder(10, 0, 0, 3));
		btnSouthEast.setPreferredSize(new Dimension(100,30)); // 로그아웃 버튼 크기
		btnSouthEast.setContentAreaFilled(false);			  // 로그아웃 버튼 투명하게
		
		
		clock.setBorder(new EmptyBorder(0, 500, 0, 0));//  중앙
		btnSouthEast.setBorder(new EmptyBorder(5, 30, 10, 10));//  오른쪽
		btnSouthWest.setBorder(new EmptyBorder(10, 15, 10, 100));//  왼쪽
		
		
		a.add(jtap);
		a.setBorder(new EmptyBorder(0, 10, 10, 10));
		
		add(pnlSouth , BorderLayout.NORTH);
		add(a , BorderLayout.CENTER);
		
		
		// 이벤트 시작~
		btnSouthEast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(getParent(), "로그아웃하시겠습니까?", "취소",
						JOptionPane.OK_CANCEL_OPTION);
				if(res == 0) {
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

	public void notiwriter(MembersVO wrivo) {
		b_NewNoti_A.notiwriter(wrivo);
	}

	public void notimain(List<NotimainVO> list) {
		b_NewNoti_A.notimain(list);
	}

	
}		