package com.ict.edu_U;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.WorkVO;
import com.toedter.calendar.JDateChooser;

public class WorkPlan_Infor extends JPanel{
	JPanel super_jp1; // 가장 큰 패널
	
	JPanel air_jp1, air_jp2, air_jp3, air_jp4; //공백패널
	
	// 출퇴근 패널, 버튼, 시간
	JPanel big_jp1, // 큰 패널
		   grid_jp1,
		   g_jp1, g_jp2, g_jp3, b_jp1, b_jp2, b_jp3; // 작은 패널
	
	// 메인화면(홈) 버튼과 시간을 연동하기 위해 static을 준다.
	// static을 안주게 된다면 MainScrean이 null이라서?(찾을 수 없어서?) g_time, b_time을 읽을 수 없다고 한다.
	static JButton go, bye;
	static JLabel g_time, b_time; 
	
	// 출퇴근 현황, 관리자
	JPanel big_jp2, // 큰 패널
		   m_jp1, grid_jp2, // 중간패널
		   n_jp1, n_jp2, n_jp3, n_jp4; // 작은 패널
	
	// 테이블
	JPanel t_jp1, t_jp2; // 테이블 패널
	JLabel gb_time;
	JButton s_bt;
	JTable jtb;
	JScrollPane jsp;
	JDateChooser first_dateChooser, last_dateChooser;
	JCheckBox T_chk1, T_chk2;
	DefaultTableCellRenderer dtcr1, dtcr2;
	
	Font MyFont;
	
	JCheckBox CB;
	
	MainScrean_Infor home;
	
	MaIn_B Main;
	public LogIn_B Login_B;
	
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
	
	public WorkPlan_Infor(LogIn_B login) {
		
		Login_B = login;
		
		// 출퇴근
		super_jp1 = new JPanel();
		super_jp1.setLayout(new BoxLayout(super_jp1, BoxLayout.PAGE_AXIS));
		
		// 공백을 만들기 위한 패널 추가
		air_jp1 = new JPanel();
		air_jp2 = new JPanel();
		air_jp3 = new JPanel();
		air_jp4 = new JPanel();
		
		// 공백패널 크기 조절
		air_jp1.setPreferredSize(new Dimension(10, 0)); // 출근 버튼 앞 간격 조정
		air_jp2.setPreferredSize(new Dimension(78, 0)); // 출 퇴근 버튼 사이 간격 조정
		air_jp3.setPreferredSize(new Dimension(10, 0)); // 조회 버튼 간격 조정
		air_jp4.setPreferredSize(new Dimension(0, 50)); // 출 퇴근 시간, 출퇴근 현황 사이 간격 조정
		
		// 폰트 설정
		MyFont = new Font("굴림", Font.BOLD, 15);
		
		// 출근 패널, 버튼, 시간 만들기
		big_jp1 = new JPanel();

		g_jp1 = new JPanel();
		g_jp2 = new JPanel();
		g_jp3 = new JPanel();
		g_jp3.setLayout(new BoxLayout(g_jp3, BoxLayout.PAGE_AXIS));

		ImageIcon img = new ImageIcon("src\\images\\go_work2.PNG"); // 버튼 이미지 넣기
		go = new JButton();
		go.setBackground(Color.WHITE); // 버튼 배경색
		go.setIcon(img);
		go.setPreferredSize(new Dimension(300,130)); // 버튼 크기조절
		g_time = new JLabel("hh:mm:ss");
		g_time.setFont(MyFont);
		
		g_jp1.add(go);
		g_jp2.add(g_time);
		g_jp3.add(g_jp1);
		g_jp3.add(g_jp2);
		
		// 퇴근 패널, 버튼, 시간 만들기
		b_jp1 = new JPanel();
		b_jp2 = new JPanel();
		b_jp3 = new JPanel();
		b_jp3.setLayout(new BoxLayout(b_jp3, BoxLayout.PAGE_AXIS));
		
		ImageIcon img2 = new ImageIcon("src\\images\\off_work2.PNG"); // 버튼 이미지 넣기
		bye = new JButton();
		bye.setBackground(Color.WHITE); // 버튼 배경색
		bye.setIcon(img2);
		bye.setPreferredSize(new Dimension(300,130)); // 버튼 크기조절
		bye.setEnabled(false);
		b_time = new JLabel("hh:mm:ss");
		b_time.setFont(MyFont);
		
		b_jp1.add(bye);
		b_jp2.add(b_time);
		
		b_jp3.add(b_jp1);
		b_jp3.add(b_jp2);
		
		// 출퇴근 버튼과 시간을 넣을 텍스트 필드를 하나로 합치기
		big_jp1.add(air_jp1);
		big_jp1.add(g_jp3);
		big_jp1.add(air_jp2);
		big_jp1.add(b_jp3);
		big_jp1.setBorder(new EmptyBorder(0, 0, 0, 16)); // 출근버튼, 퇴근버튼 위치 변경
		
		// 출퇴근 현황
		big_jp2 = new JPanel();
		
		n_jp1 = new JPanel();
		n_jp2 = new JPanel();
		n_jp3 = new JPanel();
		n_jp4 = new JPanel();
		
		m_jp1 = new JPanel();
		m_jp1.setLayout(new BoxLayout(m_jp1, BoxLayout.PAGE_AXIS));
		
		// 날짜 캘린더 만들기
		first_dateChooser = new JDateChooser();
		first_dateChooser.setDateFormatString("yyyy/MM/dd");
		first_dateChooser.setBounds(58, 394, 155, 21); // 캘린더 크기 조정인것 같지만 변화가 없음 확인필요
		first_dateChooser.setPreferredSize(new Dimension(110,20)); // 캘린더 텍스트 길이 조정
		
		last_dateChooser = new JDateChooser();
		last_dateChooser.setDateFormatString("yyyy/MM/dd");
		last_dateChooser.setBounds(58, 394, 155, 21); // 캘린더 크기 조정인것 같지만 변화가 없음 확인필요
		last_dateChooser.setPreferredSize(new Dimension(110,20)); // 캘린더 텍스트 길이 조정
		
		s_bt = new JButton("조회");
		
		// 테이블을 만들기
		t_jp1 = new JPanel();
		t_jp2 = new JPanel();
		
		String[] columName = {"일 자", "사 번", "이 름", "출근시간", "퇴근시간"};
		Object[][] WP_data = {
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""}
		};
		DefaultTableModel model = new DefaultTableModel(WP_data, columName);
		jtb = new JTable(model);
		// 테이블 내용, 정렬기능
		// 중앙 정렬변수를 만들자
		DefaultTableCellRenderer Center = new DefaultTableCellRenderer();
		Center.setHorizontalAlignment(JLabel.CENTER);
				
		// 셀의 넓이를 지정하고 정렬하자
		jtb.getColumn("일 자").setCellRenderer(Center);
		jtb.getColumn("사 번").setCellRenderer(Center);
		jtb.getColumn("이 름").setCellRenderer(Center);
		jtb.getColumn("출근시간").setCellRenderer(Center);
		jtb.getColumn("퇴근시간").setCellRenderer(Center);
		
//		 // 테이블 내 체크박스를 만들어보자
		
		
		jtb.setAutoCreateRowSorter(true); // 정렬기능
		jtb.getTableHeader().setReorderingAllowed(false); // 테이블 해더이동 금지
		jtb.getTableHeader().setResizingAllowed(true); // 테이블 크기 조절 금지
		jtb.setEnabled(false); // 테이블 셀 수정 금지
		
		jsp = new JScrollPane(jtb); // JScrollPanel을 사용하지 않으면 헤더가 보이지 않는다.
		jsp.setPreferredSize(new Dimension(700,247)); // 테이블 크기조절
		t_jp1.add(jsp);
		t_jp2.add(t_jp1);
		
		// 출퇴근 현황 라벨
		gb_time = new JLabel("출퇴근 현황  ");
		gb_time.setFont(MyFont);
		n_jp1.add(gb_time);
		n_jp1.setBorder(new EmptyBorder(0, 0, 0, 608)); // 출퇴근 현황 라벨 위치 변경
		
		n_jp2.add(first_dateChooser);
		n_jp2.add(new JLabel("          ~          "));
        n_jp2.add(last_dateChooser);
        n_jp2.add(air_jp3);
		n_jp2.add(s_bt);
		n_jp2.setBorder(new EmptyBorder(0, 0, 0, 323)); // 출퇴근 현황 위치 변경
		n_jp3.add(t_jp2);
		
		// 관리자 버튼 합치기
		grid_jp2 = new JPanel(new GridLayout(0, 1));
		n_jp4.add(grid_jp2);
		n_jp4.setBorder(new EmptyBorder(0, 486, 0, 0)); // 관리자 버튼 위치 변경
		
		m_jp1.add(n_jp1);
		m_jp1.add(n_jp2);
		m_jp1.add(n_jp3);
		m_jp1.add(n_jp4);

		// 출퇴근 현황 패널을 하나로 합치기
		big_jp2.add(m_jp1);
		
		// 완성한 출퇴근버튼 패널, 시간과 현황 패널을 합치기
		super_jp1.add(big_jp1);
		super_jp1.add(air_jp4);
		super_jp1.add(big_jp2);
		super_jp1.setBorder(new EmptyBorder(50, 30 ,50, 30)); // 최종 패널 위치 지정
		
		add(super_jp1);
		
		// 출근 버튼액션
		go.addActionListener(new ActionListener() {
			@Override
			// 현재 날짜구하기
			public void actionPerformed(ActionEvent e) {
				// 출근 및 퇴근 시간을 구하자
				dayCheck();
				
				// VO에 출근시간 정보를 넣자
				WorkVO wvo = new WorkVO();
				wvo.setUsername(username);
				wvo.setComid(comid);
				wvo.setDates(D_T_now);
				wvo.setGotime(D_T_now);
				wvo.setH_w_check("work");
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
				// 출근 및 퇴근 시간을 구하자
				dayCheck();
				
				WorkVO wvo = new WorkVO();
				wvo.setUsername(username);
				wvo.setComid(comid);
				wvo.setByetime(D_T_now);
				wvo.setH_w_check("work");
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
		
		s_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comid);
				try {
				// try catch를 if문처럼 사용하여 시간값이 null일때 오류 메세지 출력
				// 날짜 값을 "YYYY/MM/DD"로 출력되게 변환
				String f_ymd = fom1.format(first_dateChooser.getDate());
				String l_ymd = fom1.format(last_dateChooser.getDate());
				
				int compare = f_ymd.compareTo(l_ymd);
				
				if(compare > 0) {
					// 시작시간이 끝 시간보다 클 경우
					JOptionPane.showMessageDialog(getParent(), "시작 시간이 끝 시간보다 큽니다." + "\n" +
														       "다시 확인해 주세요.");
				} else if(compare < 0 || compare == 0) {
					// 시작시간과 끝 시간을 조회
					WorkVO wvo = new WorkVO();
					wvo.setComid(comid);
					wvo.setSerch1(f_ymd);
					wvo.setSerch2(l_ymd);
					
					D_Protocol p = new D_Protocol();
					p.setCmd(205); // 카피 클라이언트로 보낸다.
					p.setW_Vo(wvo);
					try {
						login.out.writeObject(p);
						login.out.flush();
					} catch (Exception e2) {
						System.out.println(e2);
					}
				} else {
					// 예상치 못한 오류를 위해 추가
					JOptionPane.showMessageDialog(getParent(), "다시 확인해 주세요.");
				}
				
				} catch (Exception e2) {
					// 시작 시간 또는 끝 시간을 안넣었을때 출력
					JOptionPane.showMessageDialog(getParent(),"시작 시간 또는 끝 시간을 확인해 주세요");
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
	public void w_gowork() {
		JOptionPane.showMessageDialog(getParent(), "출근체크가 완료되었습니다."); // 팝업창
		g_time.setText(T_now); // 현재 시간을 라벨에 넣기
		go.setEnabled(false);
		bye.setEnabled(true);
		// 메인화면(홈) 버튼과 연동
		home.g_time.setText(T_now);
		home.go.setEnabled(false);
		home.bye.setEnabled(true);
	}
	
	// 퇴근 버튼 누를 시
	public void w_byework() {
		JOptionPane.showMessageDialog(getParent(), "퇴근체크가 완료되었습니다.");
		b_time.setText(T_now);
		go.setEnabled(true);
		bye.setEnabled(false);
		// 메인화면(홈) 버튼과 연동
		home.b_time.setText(T_now);
		home.go.setEnabled(true);
		home.bye.setEnabled(false);
	}
	
	// 사원 한명 조회
	public void W_Serch(List<WorkVO> W_D_List) {
		int count = W_D_List.size();
		for (int i = 0; i < count + 1; i++) {
			// .substring(0, 10) 0부터 시작해서 10번째까지만 뽑아내겠다.
			jtb.setValueAt(W_D_List.get(i).getDates().substring(0, 10), i, 0); 
			jtb.setValueAt(W_D_List.get(i).getComid(), i, 1);
			jtb.setValueAt(W_D_List.get(i).getUsername(), i, 2); 
			jtb.setValueAt(W_D_List.get(i).getGotime().substring(11, 19), i, 3);
			jtb.setValueAt(W_D_List.get(i).getByetime().substring(11, 19), i, 4);
		}
	}
	
	//테이블 내용 초기화
	public void table_reset() {
		jtb.setValueAt("", 0, 0); jtb.setValueAt("", 0, 1);	jtb.setValueAt("", 0, 2); jtb.setValueAt("", 0, 3);	jtb.setValueAt("", 0, 4);
		jtb.setValueAt("", 1, 0); jtb.setValueAt("", 1, 1);	jtb.setValueAt("", 1, 2); jtb.setValueAt("", 1, 3);	jtb.setValueAt("", 1, 4);
		jtb.setValueAt("", 2, 0); jtb.setValueAt("", 2, 1);	jtb.setValueAt("", 2, 2); jtb.setValueAt("", 2, 3);	jtb.setValueAt("", 2, 4);
		jtb.setValueAt("", 3, 0); jtb.setValueAt("", 3, 1);	jtb.setValueAt("", 3, 2); jtb.setValueAt("", 3, 3);	jtb.setValueAt("", 3, 4);
		jtb.setValueAt("", 4, 0); jtb.setValueAt("", 4, 1);	jtb.setValueAt("", 4, 2); jtb.setValueAt("", 4, 3);	jtb.setValueAt("", 4, 4);
		jtb.setValueAt("", 5, 0); jtb.setValueAt("", 5, 1);	jtb.setValueAt("", 5, 2); jtb.setValueAt("", 5, 3);	jtb.setValueAt("", 5, 4);
		jtb.setValueAt("", 6, 0); jtb.setValueAt("", 6, 1);	jtb.setValueAt("", 6, 2); jtb.setValueAt("", 6, 3);	jtb.setValueAt("", 6, 4);
		jtb.setValueAt("", 7, 0); jtb.setValueAt("", 7, 1);	jtb.setValueAt("", 7, 2); jtb.setValueAt("", 7, 3);	jtb.setValueAt("", 7, 4);
		jtb.setValueAt("", 8, 0); jtb.setValueAt("", 8, 1);	jtb.setValueAt("", 8, 2); jtb.setValueAt("", 8, 3);	jtb.setValueAt("", 8, 4);
		jtb.setValueAt("", 9, 0); jtb.setValueAt("", 9, 1);	jtb.setValueAt("", 9, 2); jtb.setValueAt("", 9, 3);	jtb.setValueAt("", 9, 4);
		jtb.setValueAt("", 10, 0); jtb.setValueAt("", 10, 1); jtb.setValueAt("", 10, 2); jtb.setValueAt("", 10, 3);	jtb.setValueAt("", 10, 4);
		jtb.setValueAt("", 11, 0); jtb.setValueAt("", 11, 1); jtb.setValueAt("", 11, 2); jtb.setValueAt("", 11, 3);	jtb.setValueAt("", 11, 4);
		jtb.setValueAt("", 12, 0); jtb.setValueAt("", 12, 1); jtb.setValueAt("", 12, 2); jtb.setValueAt("", 12, 3);	jtb.setValueAt("", 12, 4);
		jtb.setValueAt("", 13, 0); jtb.setValueAt("", 13, 1); jtb.setValueAt("", 13, 2); jtb.setValueAt("", 13, 3);	jtb.setValueAt("", 13, 4);
	}
}