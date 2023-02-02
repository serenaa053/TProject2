package com.ict.edu_U;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.WorkVO;

public class MainScrean_Infor extends JPanel{
	JPanel super_jp1; // 가장 큰 패널
	
	JPanel air_jp1, air_jp2, air_jp3, air_jp4, 
		   air_jp5, air_jp6, air_jp7, air_jp8,
		   air_jp9; // 공백패널
	
	// 출퇴근 패널, 버튼, 시간
	JPanel big_jp1, // 큰 패널
	       m_jp1, m_jp2,
		   date_jp, gb_jp1, gb_jp2, // 작은 패널
		   group_jp1, group_jp2, group_jp3, group_jp4, group_jp5, // 묶음 패널
		   changTF_jp1, changTF_jp2, changTF_jp3, changTF_jp4; // 텍스트 필드 크기로 인해 만든 패널
	
	// 출퇴근 버튼과 시간을 연동하기 위해 static을 준다.
	// static을 안주게 된다면 WorkPlan이 null이라서?(찾을 수 없어서?) g_time, b_time을 읽을 수 없다고 한다.
	static JButton go, bye;
	static JLabel g_time, b_time;
	
	JLabel ymd_day, d_day;
	
	Font MyFont;
	
	WorkPlan_Infor Work;
	
	H_NewNoti_B noti;
	
	// 현재 날짜
	LocalDateTime dates;
	DateTimeFormatter formatter;
	String D_T_now;
	String D_now;
		
	// 현재 시간
	LocalTime time;
	DateTimeFormatter formatter2;
	String T_now;
		
	// 간단 날짜 변환
	SimpleDateFormat fom1 = new SimpleDateFormat("YYYY/MM/DD");
	SimpleDateFormat fom2 = new SimpleDateFormat("HH:mm:ss");
	
	// 사원이름, 번호
	String comid;
	String username;
	
	public MainScrean_Infor(LogIn_B login) {
		// 출퇴근
		super_jp1 = new JPanel();
		
		// 공백을 만들기 위한 패널 추가
		air_jp1 = new JPanel();
		air_jp2 = new JPanel();
		air_jp3 = new JPanel();
		air_jp4 = new JPanel();
		air_jp5 = new JPanel();
		air_jp6 = new JPanel();
		air_jp7 = new JPanel();
		air_jp8 = new JPanel();
		air_jp9 = new JPanel();
		
		// 공백패널 크기 조절
		air_jp1.setPreferredSize(new Dimension(200, 75)); // 날짜 위치 변경
		air_jp2.setPreferredSize(new Dimension(160, 0)); // 출근 시간 위치 변경
		air_jp3.setPreferredSize(new Dimension(160, 30)); // 퇴근 시간 위치 변경
		air_jp4.setPreferredSize(new Dimension(0, 0));
		air_jp5.setPreferredSize(new Dimension(50, 10));
		air_jp6.setPreferredSize(new Dimension(0, 100));
		air_jp7.setPreferredSize(new Dimension(50, 0));
		air_jp8.setPreferredSize(new Dimension(50, 60));
		
		// 폰트 설정
		MyFont = new Font("굴림", Font.BOLD, 15);
		
		// 출퇴근패널, 버튼, 시간 만들기
		big_jp1 = new JPanel(); // 출퇴근 묶음
		
		m_jp1 = new JPanel(); // 출근 묶음
		m_jp1.setLayout(new BoxLayout(m_jp1, BoxLayout.PAGE_AXIS));
		
		m_jp2 = new JPanel(); // 퇴근 묶음
		
		gb_jp1 = new JPanel();
		gb_jp1.setLayout(new BoxLayout(gb_jp1, BoxLayout.PAGE_AXIS));
		
		// 값 넣기
		LocalDateTime Datetime = LocalDateTime.now(); // 시간 값 담기
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd"); // 시간값 형태변환
		String DT_Now = Datetime.format(formatter); // 시간 값을 형태변환한 상태로 만든다.
		
		date_jp = new JPanel();
		d_day = new JLabel("<   yyyy. MM. dd");
		d_day.setFont(MyFont);
		d_day.setText("<    " + String.valueOf(DT_Now));
		date_jp.add(d_day);
		
		group_jp1 = new JPanel();
		
		// 출근
		ImageIcon img = new ImageIcon("src\\images\\go_work1.PNG"); // 출근 이미지 넣기
		go = new JButton();
		go.setBackground(Color.WHITE);
		go.setIcon(img);
		go.setPreferredSize(new Dimension(240,130)); // 버튼 크기조절
		group_jp2 = new JPanel();
		
		g_time = new JLabel("hh:mm:ss");
		g_time.setFont(MyFont);
		group_jp3 = new JPanel();
		
		gb_jp2 = new JPanel();
		gb_jp2.setLayout(new BoxLayout(gb_jp2, BoxLayout.PAGE_AXIS));
				
		// 퇴근
		ImageIcon img2 = new ImageIcon("src\\images\\off_work1.PNG"); // 퇴근 이미지 넣기
		bye = new JButton();
		bye.setBackground(Color.WHITE);
		bye.setIcon(img2);
		bye.setPreferredSize(new Dimension(240,130)); // 버튼 크기조절
		bye.setEnabled(false);
		group_jp4 = new JPanel();
		
		b_time = new JLabel("hh:mm:ss");
		b_time.setFont(MyFont);
		group_jp5 = new JPanel();

		// 날짜
		group_jp1.add(air_jp1);
		group_jp1.add(date_jp);
		
		// 출근버튼
		group_jp2.add(go);
		
		// 출근체크 시간
		group_jp3.add(g_time);
		group_jp3.add(air_jp2);
				
		// 퇴근버튼
		group_jp4.add(bye);
		
		// 퇴근체크 시간
		group_jp5.add(air_jp3);
		group_jp5.add(b_time);
		
		// 날짜, 출근버튼, 체크
		gb_jp1.add(group_jp1);
		gb_jp1.add(air_jp4);
		gb_jp1.add(group_jp2);
		gb_jp1.add(air_jp5);
		gb_jp1.add(group_jp3);
		
		// 퇴근버튼, 체크
		gb_jp2.add(air_jp6);
		gb_jp2.add(group_jp4);
		gb_jp2.add(air_jp7);
		gb_jp2.add(group_jp5);
		gb_jp2.add(air_jp8);
		
		m_jp1.add(gb_jp1);
		m_jp1.add(gb_jp2);
		
		// 왼쪽 근퇴패널
		big_jp1.add(m_jp1);
		// 패널 테두리 설정
		big_jp1.setBorder(new LineBorder(Color.BLACK));
		
		// 공지사항 클래스를 상속받아보자.
		noti = new H_NewNoti_B(login);
		noti.setBorder(new LineBorder(Color.BLACK));
		
		// 근퇴, 공지사항
		try {
			super_jp1.add(big_jp1);
			super_jp1.add(noti);
		} catch (Exception e) {
			System.out.println(e);
			e.toString();
		}

		add(super_jp1);
		
		// 출근 버튼액션
		go.addActionListener(new ActionListener() {
			@Override
			// 현재 날짜구하기
			public void actionPerformed(ActionEvent e) {
				dayCheck();
				
				// VO에 출근시간 정보를 넣자
				WorkVO wvo = new WorkVO();
				wvo.setUsername(username);
				wvo.setComid(comid);
				wvo.setDates(D_T_now);
				wvo.setGotime(D_T_now);
				wvo.setH_w_check("home");
				D_Protocol p = new D_Protocol();
				p.setCmd(201);
				p.setW_Vo(wvo);
				try {
					login.out.writeObject(p);
					login.out.flush();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		
		// 퇴근 버튼액션
		bye.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dayCheck();
				
				WorkVO wvo = new WorkVO();
				wvo.setUsername(username);
				wvo.setComid(comid);
				wvo.setByetime(D_T_now);
				wvo.setH_w_check("home");
				D_Protocol p = new D_Protocol();
				p.setCmd(203);
				p.setW_Vo(wvo);
				try {
					login.out.writeObject(p);
					login.out.flush();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
	}
	
	// 사원이름, 사원번호를 가져오자
	public String getw_username(String w_username) {
		username = w_username;
		return username;
	}
	public String getw_comid(String w_comid) {
		comid = w_comid;
		return comid;
	}
	
	// 출근 및 퇴근 시간을 구하자
	public void dayCheck() {
		// 현재 날짜
		dates = LocalDateTime.now(); // 시간 값 담기
		formatter = DateTimeFormatter.ofPattern("YYYY/MM/DD HH:mm:ss"); // 시간값 형태변환
		D_T_now = dates.format(formatter); // 시간 값을 형태변환한 상태로 만든다.
		
		// 현재 시간
		time = LocalTime.now();
		formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		T_now = time.format(formatter2);
	}
	
	// 출근 버튼 누를 시
	public void h_gowork() {
		JOptionPane.showMessageDialog(getParent(), "출근체크가 완료되었습니다.");
		g_time.setText(String.valueOf(T_now));
		go.setEnabled(false);
		bye.setEnabled(true);
		// 출퇴근 버튼 연동
		Work.g_time.setText(String.valueOf(T_now));
		Work.go.setEnabled(false);
		Work.bye.setEnabled(true);
	}
		
	// 퇴근 버튼 누를 시
	public void h_byework() {
		JOptionPane.showMessageDialog(getParent(), "퇴근체크가 완료되었습니다.");
		b_time.setText(String.valueOf(T_now));
		go.setEnabled(true);
		bye.setEnabled(false);
		// 출퇴근 버튼 연동
		Work.b_time.setText(String.valueOf(T_now));
		Work.go.setEnabled(true);
		Work.bye.setEnabled(false);
	}
}