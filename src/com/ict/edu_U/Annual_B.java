package com.ict.edu_U;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.ict.edu_D.AnnVO;
import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.MembersVO;
import com.toedter.calendar.JCalendar;

// 사이즈 조절하기 

public class Annual_B extends JPanel {
	
	// 패널 선언
	JPanel jp1_tb1, jp2_tb2, jp3_dc, jp_north, jp_bt, jp_last;
	DefaultTableModel model1, model2;
	JTable info_tb, annu_tb;
	JButton admin_edit, edit, confirm, cal;
	JScrollPane i_jsp1, a_jsp2;
	JComboBox<String> com_year;
	ArrayList<String> year_arr;
	
	// 달력 이벤트 변수
	String datemsg = null; // 다이얼로그용 
	String used_DateB = null; // 디비사용시 가지고 있어야할 선택된 날짜 변수.
	String bComid; // 사원번호를 전역변수로 갖고 있자
	String idx; // 아이디를 전역변수로 갖고 있자. 
	
	//화면 관련된 전역변수 선언
	public MaIn_B main;
	public LogIn_B login_B;
	public Annual_B annual;
	
	
	public Annual_B(LogIn_B loginB) {
		
		login_B = loginB;
		
		// 1. 사원정보 테이블 만들기
				String[] Header = { "사 번", "이 름", "부 서", "직 급", "입사일" };

				// DefaultTableModel을 생성
				model1 = new DefaultTableModel(Header, 0);
				// JTable 생성 --> 사원 정보 불러오는 테이블
				info_tb = new JTable(model1);

				// 행크기 조절
				info_tb.setRowHeight(30);
				// 셀간격 조정을 위한 객체 생성(가운데 정렬)
				DefaultTableCellRenderer Center = new DefaultTableCellRenderer();
				Center.setHorizontalAlignment(JLabel.CENTER);

				// 셀 간격 조정 및 가운데 정렬하기
				info_tb.getColumn("사 번").setCellRenderer(Center);
				info_tb.getColumn("이 름").setCellRenderer(Center);
				info_tb.getColumn("부 서").setCellRenderer(Center);
				info_tb.getColumn("직 급").setCellRenderer(Center);
				info_tb.getColumn("입사일").setCellRenderer(Center);

				info_tb.setPreferredScrollableViewportSize(new Dimension(30, 30));// setsize()처럼 사이즈조절
				info_tb.setFillsViewportHeight(true); // setvisible = true 보인다.
				info_tb.getTableHeader().setReorderingAllowed(false); // 해더이동 금지
				info_tb.getTableHeader().setResizingAllowed(false); // 테이블 크기 조절 불가
				info_tb.setEnabled(false); // 테이블 수정금지
				info_tb.setShowVerticalLines(false); // 테이블 선 안보이게(수직)
				info_tb.setShowHorizontalLines(false); // 가로선 안보이게

				// 스크롤페인 만들기 (그래야 헤더가 보임)
				i_jsp1 = new JScrollPane(info_tb);
				i_jsp1.setPreferredSize(new Dimension(750, 55)); // 사원정보 테이블 끝

				jp1_tb1 = new JPanel();
				jp1_tb1.setBorder(new EmptyBorder(0, 5, 0, 5));
				jp1_tb1.add(i_jsp1);

				// 2. 연차 조회 테이블 만들기
				String[] Header2 = { "발생연도", "발생연차", "사용연차", "잔여연차" };
				// 디폴트테이블모델2 선언 ( 두번째 테이블 )
				model2 = new DefaultTableModel(Header2, 0);

				// JTable 생성 --> 사원 정보 불러오는 테이블
				annu_tb = new JTable(model2);

				// 행크기 조절
				annu_tb.setRowHeight(30);
				// 셀간격 조정을 위한 객체 생성(가운데 정렬)
				DefaultTableCellRenderer Center2 = new DefaultTableCellRenderer();
				Center2.setHorizontalAlignment(JLabel.CENTER);

				// 셀 간격 조정 및 가운데 정렬하기
				annu_tb.getColumn("발생연도").setCellRenderer(Center2);
				annu_tb.getColumn("발생연차").setCellRenderer(Center2);
				annu_tb.getColumn("사용연차").setCellRenderer(Center2);
				annu_tb.getColumn("잔여연차").setCellRenderer(Center2);

				annu_tb.setPreferredScrollableViewportSize(new Dimension(30, 30));// setsize()처럼 사이즈조절
				annu_tb.setFillsViewportHeight(true); // setvisible = true 보인다.
				annu_tb.getTableHeader().setReorderingAllowed(false); // 해더이동 금지
				annu_tb.getTableHeader().setResizingAllowed(false); // 테이블 크기 조절 불가
				annu_tb.setEnabled(false); // 테이블 수정금지
				annu_tb.setShowVerticalLines(false); // 테이블 선 안보이게(수직)
				annu_tb.setShowHorizontalLines(false); // 가로선 안보이게

				// 스크롤페인 만들기 (그래야 헤더가 보임)
				a_jsp2 = new JScrollPane(annu_tb);
				a_jsp2.setPreferredSize(new Dimension(750, 55)); // 사원정보 테이블 끝

				jp2_tb2 = new JPanel();
				jp2_tb2.setBorder(new EmptyBorder(0, 5, 0, 5));
				jp2_tb2.add(a_jsp2);

				jp_north = new JPanel(new GridLayout(5, 1)); // 사원정보, 연차조회, 연차사용 모두 합치기
				jp_north.add(new JLabel("  ○ 사원정보 ", JLabel.LEFT));
				jp_north.add(jp1_tb1);
				jp_north.add(new JLabel("  ○ 연차조회 ", JLabel.LEFT));
				jp_north.add(jp2_tb2);
				jp_north.add(new JLabel("  ○ 연차사용", JLabel.LEFT));

				jp_last = new JPanel(new BorderLayout()); // 모든 패널 붙이기--> 탭에 붙이기 위함.

				// 달력 생성
				JCalendar cal2 = new JCalendar();

				SimpleDateFormat dateform = new SimpleDateFormat("yyyy년 MM월 dd일"); // 날짜관련 데이터를 심플하게 변환하기 위해 선언
				String date = dateform.format(cal2.getDate());
				
				// 디비 비교용 변수 선언
				SimpleDateFormat dateform2 = new SimpleDateFormat("yy/MM/dd"); // 날짜관련 데이터를 심플하게 변환하기 위해 선언
//				SimpleDateFormat dateform2 = new SimpleDateFormat("yyyy/MM/dd"); // 디비 비교시 오류 날 수 있음. 
				String BselectDate = dateform2.format(cal2.getDate());

				cal2.setWeekOfYearVisible(false); // 달력 맨 왼쪽 주 보이지 않게 하기//이러면 정렬상태 살짝 이상해짐.
				cal2.setBorder(new EmptyBorder(0, 10, 10, 10));
				cal2.setPreferredSize(new Dimension(750, 290));

				// 연차사용버튼
				confirm = new JButton("연차사용");
				confirm.setPreferredSize(new Dimension(100, 35)); // 버튼 사이즈 조절
				jp_bt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				jp_bt.add(confirm);
				jp_bt.setBorder(new EmptyBorder(-5, 0, 0, 5));

				// 패널에 붙이기
				jp_last.add(jp_north, BorderLayout.NORTH); // 사원정보/연차조회
				// 달력 센터
				jp_last.add(cal2, BorderLayout.CENTER);
				jp_last.add(jp_bt, BorderLayout.SOUTH);

				jp_last.setBorder(new EmptyBorder(-10, 0, 0, 0));

				add(jp_last);
				
				System.out.println("무한루프 테스트");
				searchAnnB(login_B.idx); // 로그인B에 선언된 아이디

				
				
				// 달력 버튼 이벤트
				cal2.addPropertyChangeListener(new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						String date = dateform.format(cal2.getDate());
						datemsg = date; // date = dateform.format(cal2.getDate());
						System.out.println("연차B datemsg :"+ datemsg);
						
						
						String datedb = dateform2.format(cal2.getDate());
						used_DateB = datedb; // 디비에서 사용할 날짜 
						System.out.println("연차B used_DateB :"+ used_DateB);
						System.out.println("연차B bComid: "+ bComid);
						
					}
				});
				

				confirm.addActionListener(new ActionListener() {
					int ch;

					@Override
					public void actionPerformed(ActionEvent e) {
						ch = JOptionPane.showConfirmDialog(getParent(), datemsg + "에\n연차 사용 하시겠습니까?", "연차사용확인",
								JOptionPane.YES_NO_OPTION);
						
						if (ch == JOptionPane.YES_OPTION) {
							// 선택한 달력의 날짜를 알고있다. used_DateB
							MembersVO memvo = new MembersVO();
							memvo.setIDX(loginB.idx);
							System.out.println("연차B loginB.idx : " + loginB.idx); 
							
							AnnVO avo = new AnnVO();
							
								System.out.println("로그인 B avo.getComid()" + bComid);
							avo.setComid(bComid); // 사번을 넣자
							avo.setUsed_DateB(used_DateB); // 달력에서 선택한 날짜를 넣자 
								System.out.println("B 연차 405 used_DateB : " + used_DateB);
							
							D_Protocol pDay = new D_Protocol();
							
							pDay.setCmd(402); // 연차사용날짜를 삽입하기 위해 402번으로 보내자 
							try {
								pDay.setAvo(avo);
								login_B.out.writeObject(pDay);
								login_B.out.flush();
								
							} catch (Exception e2) {
								System.out.println("B연차사용버튼이벤트 캐치 : " + e2.toString());
							}
							
							JOptionPane.showMessageDialog(getParent(), "연차사용완료");
						}
					}
				});

			}// 생성자
			
			
			// comid는 전역변수로 선언해서 가지고 있자 !!!
				public void memberData(MembersVO mvo) { // 매퍼, 프로토콜 생성후 보내고, 다시 여기로 오게
					// Table Modal 초기화
					model1.setRowCount(0);
					
					System.out.println("연차 memberData 시작");
					String[] Header = { "사 번", "이 름", "부 서", "직 급", "입사일" };
					String[] record = new String[Header.length];
					record[0] = mvo.getCOMID();
					record[1] = mvo.getUSERNAME();
					record[2] = mvo.getDivision();
					record[3] = mvo.getJposition();
					record[4] = mvo.getJOINDATE();
					
					bComid = mvo.getCOMID();

					model1.addRow(record); // 테이블에 행을 추가하세요.
				}

				public void annData(AnnVO avo) {
					// Table Modal 초기화
					model2.setRowCount(0);
				
					System.out.println("연차조회 annData : 시작");
					String[] Header2 = { "발생연도", "발생연차", "사용연차", "잔여연차" };
					String[] record = new String[Header2.length];
					record[0] = avo.getYearann();
					record[1] = avo.getNewann();
					record[2] = avo.getUsedann();
					record[3] = avo.getRemain();
					
					bComid = avo.getComid();

					model2.addRow(record); // 테이블에 행을 추가하세요.
				}

				public void searchAnnB(String idx) {
					
					D_Protocol p = new D_Protocol();
					p.setCmd(405);
					
					p.setString(idx); // idx를 보내자 
						System.out.println("연차B searchAnnB() idx : "+idx);
					p.setMsg("B");

					try {
						login_B.out.writeObject(p);
						login_B.out.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}

}