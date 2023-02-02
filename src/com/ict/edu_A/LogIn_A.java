package com.ict.edu_A;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.MembersVO;
import com.ict.edu_D.NotimainVO;
import com.ict.edu_D.Pay_VO;
import com.ict.edu_D.WorkVO;
import com.ict.edu_D.signup_in_VO;

public class LogIn_A extends JFrame implements Runnable {
	JPanel jp, jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp22, jp23, jp2_1;
	JLabel img;
	JTextField jtf1, jtf2, jtf4;
	JButton jb1;

	Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;

	MaIn_A main;
	Admin_A admin;

	String mid; // 아이디
	String mpw; // 비번
	public String idx; // 연차테이블에서 필요

	static LogIn_A login;

	List<NotimainVO> list_new_new;
	Pay_VO save_payvo;

	String infocomididx;
	
	MembersVO admin_m321;

	public LogIn_A() {
		super("로그인 ");
		// 이미지 320
		jp22 = new JPanel(new GridLayout(1, 4));
		jp2_1 = new JPanel(new BorderLayout());
		img = new JLabel("");
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setIcon(new ImageIcon(asd_A.class.getResource("/images/logo.png")));
		jp2_1.add(img, BorderLayout.CENTER);
		jp22.add(jp2_1, BorderLayout.WEST);

		jp1 = new JPanel();
		jp1.setLayout(new BorderLayout());
		jtf1 = new JTextField("", 0);
		jtf1.setFont(new Font("Serif", Font.BOLD, 20));
		jtf1.setForeground(Color.black);
		jp1.setBorder(new EmptyBorder(310, 70, 5, 50));
		jp1.add(jtf1);

		jp2 = new JPanel();
		jp2.setLayout(new BorderLayout());
		jtf2 = new JPasswordField("", 0);
		jtf2.setFont(new Font("Serif", Font.BOLD, 20));
		jtf2.setForeground(Color.black);
		jp2.setBorder(new EmptyBorder(5, 70, 5, 50));
		jp2.add(jtf2);

		jp4 = new JPanel();
		jb1 = new JButton("Log In");
		jb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp4.setLayout(new BorderLayout());
		jp4.setBorder(new EmptyBorder(0, 200, 330, 50));
		jp4.add(jb1);

		jp6 = new JPanel();
		jp6.setLayout(new BorderLayout());

		jp6.add(jp1, BorderLayout.NORTH);
		jp6.add(jp2, BorderLayout.CENTER);

		jp7 = new JPanel();
		jp7.setLayout(new BorderLayout());
		jp7.add(jp4, BorderLayout.CENTER);

		jp8 = new JPanel();
		jp8.setLayout(new BorderLayout());
		jp8.add(jp6, BorderLayout.NORTH);
		jp8.add(jp7, BorderLayout.SOUTH);
		jp22.add(jp8);
		jp22.setBorder(new EmptyBorder(0, 50, 0, 50));

		add(jp22);

		setLocation(800, 100);
		setSize(800, 800);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		connected(); // 이미 시작된 서버에 연결요청
		new Thread(this).start();

		jtf2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jtf2.setText("");
			}
		});

		jtf1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtf1.setText("");
			}
		});

		jtf2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtf2.setText("");
			}
		});

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				signup_in_VO vo = new signup_in_VO();
				String id = jtf1.getText().trim(); // 아이디
				System.out.println(id);
				String pw = jtf2.getText().trim(); // 비밀번호
				System.out.println(pw);
				vo.setIDX(id);
				vo.setPW(pw);

				D_Protocol p = new D_Protocol();
				p.setCmd(4);
				p.setVo(vo);

				try {
					out.writeObject(p);
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void connected() {
		try {
			s = new Socket("192.168.0.21", 7010);
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
		} catch (Exception e) {
		}
	}

	private void closed() {
		try {
			in.close();
			out.close();
			s.close();
			System.out.println("프로그램 종료");
			System.exit(0);
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		bk: while (true) {
			try {
				Object obj = in.readObject();
				if (obj != null) {
					D_Protocol p = (D_Protocol) obj;
					switch (p.getCmd()) {
					case 0:
						break bk;

					case 4: // 로그인시 아이디 중복검삼
						if (p.getResult() == 1) { // 성공시
							D_Protocol P2 = new D_Protocol();
							P2.setCmd(5);
							P2.setMsg(p.getMsg());
							out.writeObject(P2);
							out.flush();
							break;
						} else {
							JOptionPane.showConfirmDialog(getParent(), "로그인하시겠습니까?", "취소",
									JOptionPane.OK_CANCEL_OPTION);
							JOptionPane.showConfirmDialog(getParent(), "아이디가 다릅니다 다시 입력해주세요", "취소",
									JOptionPane.OK_CANCEL_OPTION);
							break;
						}
					case 5:
						if (p.getMsg().equals(jtf2.getText().trim())) { // => 스트링형인 이퀄스를 써야한다() p.getMsg =>
																		// jtf2.getText().trim() 비교
							JOptionPane.showConfirmDialog(getParent(), "로그인하시겠습니까?", "취소",
									JOptionPane.OK_CANCEL_OPTION);
							mid = jtf1.getText().trim(); // 아이디
							mpw = jtf2.getText().trim();
							D_Protocol p5 = new D_Protocol();
							signup_in_VO vo5 = new signup_in_VO();
							MembersVO vo6 = new MembersVO();
							vo5.setIDX(mid);
							vo5.setPW(mpw);
							vo6.setIDX(mid);
							p5.setMemvo(vo6);
							p5.setVo(vo5);
							p5.setCmd(6);

							out.writeObject(p5);
							out.flush();
						} else {
							JOptionPane.showConfirmDialog(getParent(), "비밀번호가 다릅니다 다시 한번 확인해 주세요", "취소",
									JOptionPane.OK_CANCEL_OPTION);
						}
						break;

					case 6:
						Pay_VO result6 = p.getPayVo();
						main = new MaIn_A(login);
						main.ex02.ssdd(result6);
						main.exec(p.getMemvo());
						main.notiwriter(p.getMemvo());
						main.notimain(p.getNotimainlist());
						setVisible(false);
						break;

					case 7:
						Pay_VO payvo = p.getPayVo();
						save_payvo = payvo;
						break;
					
					case 10 : 
//	                      Pay_VO adminPayvo = p.getPayVo();
//	                      System.out.println("2" + adminPayvo);
	                      main.ex02.ssdd2(p.getPayVo());
	                      System.out.println(p.getPayVo()+"넌 뭔데??");
	                      break;
						
				
					case 206: // 관리자 사원 한명 근퇴 조회 보내기
						List<WorkVO> A_W_D_List = p.getW_List();
						main.Work.table_reset();
						main.Work.M_W_Serch(A_W_D_List);
						break;
						
					case 207: // 관리자 출근 체크시 하는 행동
						main.Work.m_w_gowork();
						break;
						
					case 208: // 관리자 퇴근 체크시 하는 행동
						main.Work.m_w_byework();
						break;
						
					case 210: // 관리자 사번 이름 가져오기
						main.Work.getw_username(p.getMsg());
						main.Work.getw_comid(p.getString());
						break;

					case 311: // 공지창 뒤로가기 버튼
						List<NotimainVO> list_new = p.getNotimainlist();
						list_new_new = list_new;
						break;
						
					case 321: // 관리자 기본정보 조회로 넘어가기
						try {
							main.infor.exec2(p.getMemvo());
							// main.infor.exe3(p.getMemvo());
							break;
							
						} catch (Exception e) {
							System.out.println(e.toString());
						}

					
					case 401: // 관리자 - 사원정보 전체보기
						try {
							main.admin.search_List(p.getAdlist()); // 받고 보낸다.
							break;
						} catch (Exception e) {
							break;
						}

					case 403: // 삭제한 내용을 화면에 보내자
						try {
							main.annual.annData(p.getAvo()); // 받을 거면 보내는게 있어야 한다.
							main.annual.annHistoryList(p.getAnnlist()); // 보내는개 cc에 있는지 확인하자
							// 받고 보낸다.
							break;
						} catch (Exception e) {
						}
						break;

					case 405: // 관리자 - 검색 정보(상세) 불러오기
						try {
							main.annual.memberData(p.getMemvo()); // cc에서 받고 보낸다.
							main.annual.annData(p.getAvo());
							main.annual.annHistoryList(p.getAnnlist());

						} catch (Exception e) {
						}
						break;

					}
				}
			} catch (Exception e) {
			}
		}
		closed();
	}

	public static void main(String[] args) {
		login = new LogIn_A();
	}

}
