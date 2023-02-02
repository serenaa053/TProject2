package com.ict.edu_A;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.ict.edu_D.AnnVO;
import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.MembersVO;

// 사이즈 조절하기 

public class Annual_A extends JPanel {

	// 패널 선언
	JPanel jp1_tb1, jp2_tb2, jp3_tb3, jp_north, jp_bt, jp_last;
	DefaultTableModel model1, model2, model3;
	JTable info_tb, annu_tb, look_tb;
	JButton del;
	JScrollPane i_jsp1, a_jsp2, l_jsp3;
	JCheckBox c_box;
	JTabbedPane jtap3;

	// 사번과 사용날짜를 알기위한 변수
	String comid_a;
	String ann_selectrow;
	String selused_date;

	// 화면 이동 및 정보전달을 위한 전역변수
	public MaIn_A main;
	public LogIn_A login_A;
	public Admin_A admin;

	public Annual_A(LogIn_A login) {

		login_A = login;

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

		// 사원정보, 연차조회, 사용내역조회 모두 합치기
		jp_north = new JPanel(new GridLayout(5, 1));
		jp_north.add(new JLabel("  ○ 사원정보 ", JLabel.LEFT));
		jp_north.add(jp1_tb1);
		jp_north.add(new JLabel("  ○ 연차조회 ", JLabel.LEFT));
		jp_north.add(jp2_tb2);
		jp_north.add(new JLabel("  ○ 사용내역조회", JLabel.LEFT));

		jp_last = new JPanel(new BorderLayout()); // 모든 패널 붙이기--> 탭에 붙이기 위함.

		// 3. 사용내역조회 테이블 만들기
		String[] Header3 = { "No.", "사용날짜", "차감갯수", "사번" };

		// DefaultTableModel을 생성
		model3 = new DefaultTableModel(Header3, 0);

		// JTable 생성 --> 사원 정보 불러오는 테이블
		look_tb = new JTable(model3);

		// 행크기 조절
		look_tb.setRowHeight(30);

		// 셀 간격 조정 및 가운데 정렬하기
		look_tb.getColumn("No.").setCellRenderer(Center);
		look_tb.getColumn("사용날짜").setCellRenderer(Center);
		look_tb.getColumn("차감갯수").setCellRenderer(Center);
		look_tb.getColumn("사번").setCellRenderer(Center);

		look_tb.setPreferredScrollableViewportSize(new Dimension(30, 30));// setsize()처럼 사이즈조절
		// look_tb.setFillsViewportHeight(true); // setvisible = true 보인다.
		look_tb.getTableHeader().setReorderingAllowed(false); // 해더이동 금지
		look_tb.getTableHeader().setResizingAllowed(false); // 테이블 크기 조절 불가
		look_tb.setEnabled(true); // 테이블 수정
		look_tb.getColumn("사번").setWidth(0);
		look_tb.getColumn("사번").setMinWidth(0);
		look_tb.getColumn("사번").setMaxWidth(0);

		// 스크롤페인 만들기
		l_jsp3 = new JScrollPane(look_tb);
		l_jsp3.setPreferredSize(new Dimension(750, 270)); // 사원정보 테이블 끝

		jp3_tb3 = new JPanel();
		jp3_tb3.setBorder(new EmptyBorder(0, 5, 0, 5));
		jp3_tb3.add(l_jsp3);

		// 확인, 수정, 삭제
		del = new JButton("삭제");

		jp_bt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp_bt.add(del);
		jp_bt.setBorder(new EmptyBorder(0, 0, 0, 5));

		// 패널에 붙이기
		jp_last.add(jp_north, BorderLayout.NORTH); // 사원정보/연차조회
		jp_last.add(jp3_tb3, BorderLayout.CENTER); // 조회테이블 센터
		jp_last.add(jp_bt, BorderLayout.SOUTH); // 버튼
		jp_last.setBorder(new EmptyBorder(-10, 0, 0, 0));

		add(jp_last);
		// 이벤트처리하기
		// 삭제 버튼을 누르면 사용날짜 삭제 하기
		del.addActionListener(new ActionListener() {
			int ch;

			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택한 행의 위치를 알고있자
				int selectrow = look_tb.getSelectedRow(); // 이 정보는 행정보
				TableModel selectmodel = look_tb.getModel(); // 이렇게 TableModel 객체 뽑으셔서
				selused_date = (String) selectmodel.getValueAt(selectrow, 1); // 선택한 행에 날짜

				System.out.println("연차탭 usedDate : " + selused_date); // (YY/MM/DD 형식입)

				ch = JOptionPane.showConfirmDialog(getParent(), "삭제 하시겠습니까?", "삭제여부 확인", JOptionPane.YES_NO_OPTION);
				if (ch == JOptionPane.YES_OPTION) {
					// 삭제를 하기 위해 사번과 선택된 날짜를 알고 있다 그것을 VO에 담자
					AnnVO avo = new AnnVO();
					avo.setComid(comid_a);
					avo.setSelused_date(selused_date);
					System.out.println("연차403 : avo" + avo.toString());

					D_Protocol pp = new D_Protocol();

					pp.setCmd(403); // cc 403 삭제하는 곳으로 보내자
					try {
						pp.setAvo(avo);

						login.out.writeObject(pp);
						login.out.flush();
						JOptionPane.showMessageDialog(getParent(), "삭제 되었습니다.");

					} catch (Exception e2) {
					}

				}
			}
		});

	}// 생성자

	// 아래에 세가지 메서드는 세트다!!!
	// comid는 전역변수로 선언해서 가지고 있자 !!!
	public void memberData(MembersVO mvo) { // 매퍼, 프로토콜 생성후 보내고, 다시 여기로 오게
		// Table Modal 초기화
		model1.setRowCount(0);

		System.out.println("연차 멤버 데이터 시작문");
		String[] Header = { "사 번", "이 름", "부 서", "직 급", "입사일" };
		String[] record = new String[Header.length];
		record[0] = mvo.getCOMID();// No. 표현하기 위해 사용
		record[1] = mvo.getUSERNAME();
		record[2] = mvo.getDivision();
		record[3] = mvo.getJposition();
		record[4] = mvo.getJOINDATE();

		comid_a = mvo.getCOMID();

		model1.addRow(record); // 테이블에 행을 추가하세요.
	}

	public void annData(AnnVO avo) {
		// Table Modal 초기화
		model2.setRowCount(0);

		String[] Header2 = { "발생연도", "발생연차", "사용연차", "잔여연차" };
		String[] record = new String[Header2.length];
		record[0] = avo.getYearann();
		record[1] = avo.getNewann();
		record[2] = avo.getUsedann();
		record[3] = avo.getRemain();

		model2.addRow(record); // 테이블에 행을 추가하세요.

	}

	public void annHistoryList(List<AnnVO> list) {
		// Table Modal 초기화
		model3.setRowCount(0);

		String[] Header3 = { "No.", "사용날짜", "차감갯수", "사번" };
		String[] record = new String[Header3.length];

		// mapper 에도 comid를 불러오기 ! 그래야 delete 할 수 있다.
		// comid를 보내고, 행 하나는 히든으로 바꾸기
		for (int i = 0; i < list.size(); i++) {
			record[0] = String.valueOf(i + 1); // No. 표현하기 위해 사용
			record[1] = list.get(i).getUseddate();
			record[2] = list.get(i).getUsedcnt();
			record[3] = list.get(i).getComid();

			System.out.println("연차탭 사용날짜 : " + list.get(i).getUseddate());
			System.out.println("연차탭 숨겨진 사번이 있나?? : " + list.get(i).getComid());

			model3.addRow(record); // 테이블에 행을 추가하세요.
			Annual_A a = new Annual_A(login_A);

		}
	}// history끝

}