package com.ict.edu_A;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.ict.edu_D.AdVO;
import com.ict.edu_D.D_DAO;
import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.MembersVO;
import com.ict.edu_D.Pay_VO;

public class Admin_A extends JPanel {
	
	JPanel jp1/*tf, bt*/, jp2/*테이블,콤보박스*/, jp3/*jp1,2 붙이기*/;
	JTextField serch_tf;
	JButton serch_bt, confirm_bt;
	DefaultTableModel model;
	JTable table;
	JScrollPane jsp1/*테이블용*/, c_jsp2/*콤보박스용*/;
	JComboBox<String> com_bx;
	ImageIcon press_icon;
	
	
	// 화면이동을 위한 변수들 선언 
	public JTabbedPane jtap2; // 탭이동을 위한 전역변수 선언
	public MaIn_A main_a; // 프레임에서 실행하기 위해 프레임 상속한 메인 클래스 전역변수 선언 
	public LogIn_A login_a;
	public Admin_A admin;
	
	public String comid_a; // 이것은 콤보박스 선택시 얻어지는 사원번호입니다.
	public String username; // 이것은 콤보박스 선택시 얻어지는 이름입니다.
	String searchtext; // 검색을 하기 위한 변수
	
	 public Admin_A(LogIn_A login, JTabbedPane jtap) {
		 
		 login_a = login;
		this.jtap2 = jtap;
		
		// serch_tf & bt 을 생성하여 jp1에 붙이기
		jp1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		serch_tf = new JTextField(20);
		serch_tf.setText("직원검색"); // 텍스트필드 높이변경을 원한다면 폰트크로 결정할 수 있다. 
		jp1.add(serch_tf);
		
		serch_bt = new JButton(new ImageIcon("src/images/galss.png"));
		

		// 버튼에 이미지만 남기기
		serch_bt.setPreferredSize(new Dimension(20,20));
		serch_bt.setBorderPainted(false);
		serch_bt.setFocusPainted(false);
		serch_bt.setContentAreaFilled(false);
		serch_bt.setPressedIcon(new ImageIcon("src/images/galss_press.png"));
		
		
		
		
		jp1.add(serch_bt);
		
		confirm_bt = new JButton("확인");
		jp1.add(confirm_bt);
		
		
		// 테이블 넣을 패널
		jp2 = new JPanel();
		jp2.setLayout(new BorderLayout());
		
		// 1. 디폴트테이블모델 생성
		
		 String[] Header = {"No.", "사원번호", "이 름", "부 서","직 급","관리목록"};
	     model = new DefaultTableModel(Header, 0);
	     
	     // 2.  Jtable 생성후 디폴트 테이블 모델 인자로 넣기
	     table = new JTable(model);
	     
	     
	     List<AdVO> voList = D_DAO.getAdList(null);
			System.out.println("DAO.getAdList() : " + voList);
		    System.out.println(voList.get(0).getComid());
			// 관리자 테이블 컬럼에 내용 넣기 .
			String[] record = new String[Header.length];

			for (int i = 0; i < voList.size(); i++) {
				record[0] = String.valueOf(i + 1); // No. 표현하기 위해 사용
				record[1] = voList.get(i).getComid();
				record[2] = voList.get(i).getUsername();
				record[3] = voList.get(i).getDivision();
				record[4] = voList.get(i).getJposition();
				record[5] = "관리목록을 선택하세요";

				model.addRow(record); // 테이블에 행을 추가하세요.
			}
		     // 테이블 크기 조정
		     table.setRowHeight(20);// 행 크기조절
	     	
	     	// 셀간격 조정을 위한 객체 생성
	     	DefaultTableCellRenderer Left = new DefaultTableCellRenderer();
	        Left.setHorizontalAlignment(JLabel.LEFT);
	     	DefaultTableCellRenderer Center = new DefaultTableCellRenderer();
	 		Center.setHorizontalAlignment(JLabel.CENTER);
	 		DefaultTableCellRenderer Right = new DefaultTableCellRenderer();
	 		Right.setHorizontalAlignment(JLabel.RIGHT);
	 		
	 		//셀 간격 조정 및 가운데 정렬하기 
	 		table.getColumn("No.").setPreferredWidth(10);
	 		table.getColumn("No.").setCellRenderer(Center);
	 		table.getColumn("사원번호").setPreferredWidth(20);
	 		table.getColumn("사원번호").setCellRenderer(Center);
	 		table.getColumn("이 름").setPreferredWidth(20);
	 		table.getColumn("이 름").setCellRenderer(Center);
	 		table.getColumn("부 서").setPreferredWidth(70);
	 		table.getColumn("부 서").setCellRenderer(Center);
	 		table.getColumn("직 급").setPreferredWidth(10);
	 		table.getColumn("직 급").setCellRenderer(Center);
	 		table.getColumn("관리목록").setPreferredWidth(200);
	 		table.getColumn("관리목록").setCellRenderer(Center);		
	
		        
	 		table.setPreferredScrollableViewportSize(new Dimension(750,580));// setsize()처럼 사이즈조절 
	 		table.setFillsViewportHeight(true); // setvisible = true 보인다. 
	 		table.setAutoCreateRowSorter(true); // 정렬기능(오름차순, 내림차순)
	 		table.getTableHeader().setReorderingAllowed(false); // 해더이동 금지
	 		table.getTableHeader().setResizingAllowed(true); // 테이블 크기 조절 가능 
	    

	 		
	 		// combobox를 넣을 colum 지정(index(5)에 넣기)
	 		TableColumn col = table.getColumnModel().getColumn(5);
	 		
	 		// 테이블 안에 넣을 콤보박스 데이터 만들기
	 		String[] list = {"관리목록을 선택하세요","근태관리", "연차관리", "급여관리" , "기본정보관리"} ; 
	 		// 콤보박스 선언
	 		com_bx = new JComboBox<String>(list);
	 		com_bx.setSelectedItem(String.valueOf(list));
	 		col.setCellEditor(new DefaultCellEditor(com_bx));
	 		
	 		// 스크롤페인 만들기
	 		jsp1 = new JScrollPane(table,
	 				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	 				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	 		jsp1.setPreferredSize(new Dimension(750,609));
	 		setSize(150,150);
	 		jp2.add(jsp1);
	 		
	 		// 최종패널 jp3에 텍스트필드, 테이블&콤보박스 붙이고 왼쪽 정렬하기 
	 		jp3 = new JPanel();
	 		jp3.setLayout(new BorderLayout());
	 		jp3.add(jp1, BorderLayout.NORTH);
	 		jp3.add(jp2, BorderLayout.CENTER);
	 		
	 		
	 	// jp3이라는 최종패널을 만들어서 붙이자 
	 	add(jp3);
	 	
	 	
	 	// 이벤트 처리 
	 	// 검색 텍스트 필드 -> 필드 클릭시 초기화하기
	 	serch_tf.addMouseListener(new MouseAdapter() {
	 		@Override
	 		public void mouseClicked(MouseEvent e) {
	 			serch_tf.setText("");
	 		}
		});
	 	
	 	
		// 검색 버튼 (돋보기)을 누르면, 텍스트필드에 있는 내용을 검색 후, 해당 정보를 테이블에 넣자.
		serch_bt.addActionListener(new ActionListener() {
			/*
			 * 검색버튼을 누르면, 1. 기존 테이블 초기화 2. 검색된 내용 표시되기 3. 어떤걸로 검색될지 모름.이름, 사번, 등등
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				init(); // 테이블 초기화 먼저 하기
				searchtext = serch_tf.getText().trim();
				if (searchtext.length() > 0) {
					System.out.println("keyword :" + searchtext);
					D_Protocol p = new D_Protocol();
					p.setCmd(401); // 검색한 정보 보기 (상세정보)
					p.setString(searchtext); // 검색어
					try {
						// 보내야 함.
						System.out.println(2);

						login.out.writeObject(p);
						login.out.flush();
					} catch (Exception e2) {
						System.out.println("무엇이 잘못되었니? : " + e2);
					}
				} else {
					
					
				} // if문 끝

			}
		});

		// 확인 버튼 --> 콤보박스에 선택된 페이지로 이동한다. (해당 직원이어야 하고, 콤보박스에 선택된 페이지 여야 한다)
		// String[] list = {"목록을 선택하세요","근태관리", "연차관리", "급여관리" , "기본정보관리"} ;

	 	confirm_bt.addActionListener(new ActionListener() {
	 		
	 		int index = com_bx.getSelectedIndex();
	 		int ch;
	 		// 콤보박스에서 선택된 아이템 페이지로 이동해야 함. 
	 		// 선택된 해당 아이디에 저장된 모든 정보를 갖고 이동해야함.
			@Override
			public void actionPerformed(ActionEvent e) {
				// vo에 저장되어야 한다. = table.getSelectedRow(); // 선택된 행에 있는 정보를 ....??

				if (e.getSource() != null && com_bx.getSelectedIndex() == 1) {
					// 버튼 누르면 바로 서버로 간다.
					ch = JOptionPane.showConfirmDialog(main_a, "근태관리로 이동하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
					if (ch == JOptionPane.YES_OPTION) {

						// 목록에 해당되는 comid를 가지고, 근태관리탭으로 이동하자
						comid_a = getTableIdxInfo();
						username = getTableIdxname();
						System.out.println("comid_a 근태: " + comid_a);

						D_Protocol pWork = new D_Protocol();
						pWork.setString(comid_a);
						pWork.setMsg(username);
						pWork.setCmd(210); // CC 206으로 가도록 한다. 근태 관리 이동하는 cmd
					
						try {
							login.out.writeObject(pWork);
							login.out.flush();
						} catch (Exception e2) {
						}
						jtap.setSelectedIndex(2);

					}
				} else if (e.getSource() != null && com_bx.getSelectedIndex() == 2) {
					ch = JOptionPane.showConfirmDialog(main_a, "연차관리로 이동하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
					if (ch == JOptionPane.YES_OPTION) {

						// 선택된 행의 사원번호정보를 갖고 VO에 저장하자.
						comid_a = getTableIdxInfo();
						System.out.println("comid_a 연차 : " + comid_a);

						D_Protocol pAnuual = new D_Protocol();
						pAnuual.setCmd(405); // 405번으로 가도록 !
						pAnuual.setString(comid_a);
						pAnuual.setMsg("A"); // 유저창과 함께 쓰므로 구분자 필요함(연차에서만)
						// 담았으면 보내자
						try {
							login.out.writeObject(pAnuual);
							login.out.flush();
						} catch (IOException e1) {
						}
						jtap.setSelectedIndex(3); // 연차관리탭이동
					}
				} else if (e.getSource() != null && com_bx.getSelectedIndex() == 3) {
					ch = JOptionPane.showConfirmDialog(main_a, "급여관리로 이동하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
					if (ch == JOptionPane.YES_OPTION) {

						// 선택된 행의 사원번호정보를 갖고 VO에 저장하자.
						D_Protocol p10 = new D_Protocol();
						comid_a = getTableIdxInfo();
						System.out.println("comid_a 급여 : " + comid_a);
						Pay_VO pay= new Pay_VO();
						pay.setcOMID(comid_a);
						
						
						p10.setCmd(10); //급여관리 조회하는 cmd로 보내자 
						p10.setPayVo(pay);
						
//						 담았으면 보내자
						try {
							login.out.writeObject(p10);
							login.out.flush();
						} catch (IOException e1) {
						}

						jtap.setSelectedIndex(4); // 급여관리탭이동
						
					}

				} else if (e.getSource() != null && com_bx.getSelectedIndex() == 4) {
					ch = JOptionPane.showConfirmDialog(main_a, "기본정보관리로 이동하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
					if (ch == JOptionPane.YES_OPTION) {

						// 선택된 행의 사원번호정보를 갖고 VO에 저장하자.
						comid_a = getTableIdxInfo();
						System.out.println("comid_a 기본정보 : " + comid_a);
						MembersVO mvo = new MembersVO();
						mvo.setCOMID(comid_a);

						D_Protocol pMyInfo = new D_Protocol();
						pMyInfo.setCmd(321); // 기본정보 조회하는 cmd로 보내자
						pMyInfo.setMemvo(mvo); // 보낼때 사원번호도 같이 보내자
						//pMyInfo.setMsg("A"); // 유저창과 함께 쓰므로 구분자 필요함(연차에서만)
						// 담았으면 보내자
						try {
							login.out.writeObject(pMyInfo);
							login.out.flush();
						} catch (IOException e1) {
						}
						jtap.setSelectedIndex(5); // 기본정보관리 탭
					}
				}
			}

			// 선택된 행이 갖고 있는 사원번호를 찾아내는 메서드
			private String getTableIdxInfo() {
				int h = table.getSelectedRow(); // 이 정보는 행정보
				TableModel model = table.getModel(); // 이렇게 TableModel 객체 뽑으셔서
				comid_a = (String) model.getValueAt(h, 1); // 선택된행에 1번째에 있는 값(comid)를 저장하자
			
				return comid_a;
			}
			
			private String getTableIdxname() {
		 		int h = table.getSelectedRow(); // 이 정보는 행정보
		 		TableModel model = table.getModel(); // 이렇게 TableModel 객체 뽑으셔서
		 		username = (String) model.getValueAt(h, 2); // 선택된행에 1번째에 있는 값(comid)를 저장하자
		 		
		 		return username;
		 	}
		});

	} // 생성자

	// 테이블 초기화 하는 메서드 (헤더를 제외한 행을 모두 초기화)
	public void init() {
		model.setRowCount(0);
	}

	// 검색된 내용 불러오는 메서드
	public void search_List(List<AdVO> list) {
		// 관리자 테이블 컬럼에 내용 넣기 .
		String[] Header = { "No.", "사원번호", "이 름", "부 서", "직 급", "관리목록" };
		String[] record = new String[Header.length];

		for (int i = 0; i < list.size(); i++) {
			record[0] = String.valueOf(i + 1); // No. 표현하기 위해 사용
			record[1] = list.get(i).getComid();
			record[2] = list.get(i).getUsername();
			record[3] = list.get(i).getDivision();
			record[4] = list.get(i).getJposition();
			record[5] = "관리목록을 선택하세요";

			model.addRow(record); // 테이블에 행을 추가하세요.
			Admin_A a = new Admin_A(login_a, jtap2); // 화면이 새로 고쳐지면서 테이블이 보여진다.
		}
	}

} // 패널 끝