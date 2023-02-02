package com.ict.edu_U;

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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.ict.edu_D.AnnVO;
import com.ict.edu_D.D_DAO;
import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.InfoVO;
import com.ict.edu_D.MembersVO;
import com.ict.edu_D.NotimainVO;
import com.ict.edu_D.Pay_VO;
import com.ict.edu_D.WorkVO;
import com.ict.edu_D.signup_in_VO;

public class LogIn_B extends JFrame implements Runnable {
	JPanel jp, jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp12, jp13, jp14, jp15, jp16, jp17, jp18, jp19,
			jp20, jp21, jp22, jp23, jp24, jp25, jp26, jp27, jp28, jp29, jp2_1;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10, jl11, jl12, jl13, jl14, jl15, jl16, jl17, jl18, jl19,
			jl20, jl21, jl22, jl23, jl24, jl25, jl26, jl27, jl28, jl29, jl30, jl31, jl32, jl33, jl34, jl35, img;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10, jtf11, jtf12, jtf13, jtf14;
	JButton jb1, jb2, jb3;
	JTextArea jta, jta1, jta2, jta3, jta4;
	JScrollPane jsp;
	JCheckBox jbc1;
	JTable jtb, jtb1, jtb2, jtb3;
	DefaultTableModel dtm;
	DefaultTableCellRenderer dtcr;

	Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;
	MaIn_B main;

	String mid; // 아이디
	String mpw;
	String infocomididx;

	// 연차관리와 연결하기 위한 변수
	String idx; // 연차에서 사용할 id
	Annual_B annual; // 연차관리

	Ex01_B b;
	static LogIn_B login;

	List<NotimainVO> list_new_new;

	public LogIn_B() {
		super("로그인 ");
		jp22 = new JPanel(new GridLayout(1, 4));
		jp2_1 = new JPanel(new BorderLayout());
		img = new JLabel("");
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setIcon(new ImageIcon(asd_B.class.getResource("/images/logo.png")));
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
		jp4.setBorder(new EmptyBorder(0, 130, 3, 50));
		jp4.add(jb1);

		jp5 = new JPanel();
		jb2 = new JButton("Sign up");
		jb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp5.setLayout(new BorderLayout());
		jp5.setBorder(new EmptyBorder(0, 130, 250, 50));
		jp5.add(jb2);

		jp6 = new JPanel();
		jp6.setLayout(new BorderLayout());

		jp6.add(jp1, BorderLayout.NORTH);
		jp6.add(jp2, BorderLayout.CENTER);

		jp7 = new JPanel();
		jp7.setLayout(new BorderLayout());
		jp7.add(jp4, BorderLayout.CENTER);
		jp7.add(jp5, BorderLayout.SOUTH);

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
		jb2.addActionListener(new ActionListener() { // 회원가입

			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(getParent(), "회원가입을하시겠습니까?", "취소",
						JOptionPane.OK_CANCEL_OPTION);
				if (res == 0) {
					setVisible(false);
					b = new Ex01_B(login);

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
					case 1: // 회원가입
						System.out.println(p.getResult() + "case1");
						if (p.getResult() == 1) {
							// 성공했을 때
							D_Protocol p1 = new D_Protocol();
							p1.setCmd(3);
							p1.setMsg(p.getMsg());
							out.writeObject(p1);
							out.flush();
							break;
						} else {
							// 실패했을 때
							JOptionPane.showMessageDialog(null, "회원가입 실패");
							break;
						}
					case 2: // 회원가입 아이디 중복검사
						// int result = p.getResult(); // p에 저장된 result값을 int형 변수 result에 저장
						if (p.getResult() == 0) { // 0이면 중복확인 결과값이 없음, 사용가능
							b.cc();
							break;
						} else { // 0이 아니면 중복있음, 사용 불가능
							b.dd();
							break;
						}

					case 3: // 사원번호 출력
						int agent = p.getResult();

						InfoVO infovo = new InfoVO();
						infovo.setComid(agent);
						infovo.setIdx(infocomididx);

						D_Protocol p3 = new D_Protocol();
						p3.setCmd(320);
						p3.setInfovo(infovo);
						out.writeObject(p3);
						out.flush();
						JOptionPane.showMessageDialog(getParent(), "축하합니다.\n당신의 사원번호는 " + agent + "입니다", "축하합니다",
								JOptionPane.INFORMATION_MESSAGE);
						break;
						
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

							// 연차 관련때문에 전역변수 갖고 있기
							idx = p.getString(); // 로그인한 아이디 갖고 있자
							System.out.println("idx : " + idx);

							out.writeObject(p5);
							out.flush();
						} else {
							JOptionPane.showConfirmDialog(getParent(), "비밀번호가 다릅니다 다시 한번 확인해 주세요", "취소",
									JOptionPane.OK_CANCEL_OPTION);
						}
						break;

					case 6:

						Pay_VO result6 = p.getPayVo();
						main = new MaIn_B(login);
						main.ex02.ssdd(result6);
						main.exec(p.getMemvo());
						main.notimain(p.getNotimainlist());
						main.Work.getw_username(p.getMemvo().getUSERNAME());
						main.Work.getw_comid(p.getMemvo().getCOMID());
						main.home.getw_username(p.getMemvo().getUSERNAME());
						main.home.getw_comid(p.getMemvo().getCOMID());
						try {
						main.home.noti.notimain((p.getNotimainlist()));
						} catch (Exception e) {
							System.out.println(e);
						}

						setVisible(false);
						// 내가 한거
						break;

					case 201: // Home 출근 체크시 하는 행동
						main.home.h_gowork();
						break;

					case 202: // Work 출근 체크시 하는 행동
						main.Work.w_gowork();
						break;

					case 203: // Home 퇴근 체크시 하는 행동
						main.home.h_byework();
						break;

					case 204: // Work 퇴근 체크시 하는 행동
						main.Work.w_byework();
						break;

					case 205: // 사원 한명 근퇴 조회 보내기
						List<WorkVO> W_D_List = p.getW_List();
						main.Work.table_reset();
						main.Work.W_Serch(W_D_List);
						break;

					case 311: // 공지창 뒤로가기 버튼
						List<NotimainVO> list_new = p.getNotimainlist();
						list_new_new = list_new;
						break;

					case 320: // comid info table update
						D_DAO.getComidUpadate(p.getInfovo());
						break;

					case 402: // 사용할 연차 디비에 삽입

						/**
						 * 402 번에서는 DAO 에서 호출한 결과값으로 화면을 다시 그려준다.
						 * 
						 * 1. D_Client 로 부터 받은 결과값이 1이상(삽입이 성공) 되었을때 2. 화면에 연차 조회테이블의 데이터를 다시 그린다.
						 * 
						 */

						// D_Client 로 부터 전달받은 매개인자의 값이 제대로 담겨있는지 출력한다.
						System.out.println("로그인 402 유저 왔나??? ");
						int insertCnt = p.getResult();
						
						System.out.println("402로그인 insertCnt : " +insertCnt);
						if (insertCnt != 0) {
							AnnVO avo = p.getAvo();
							System.out.println("402 로그인 avo : " + avo);
							main.annual.annData(avo);
						}

						// D_Client 로 부터 받은 결과값이 1이상(삽입이 성공) 되었을때
						// 화면에 연차 조회테이블의 데이터를 다시 그린다.
						// 그려주는 코드를 다시 호출하여 화면의 레아이웃을 리 랜더링
						// 호출된 결과가 화면으로 가야한다. 디비를 다녀오는 게 아니다!
						break;

					/**
					 * 연차 관리자 한사람에 대한 내용만 불러오기 (이 사람이 가지고 있는 상세 데이터를 DB에서 가져와서 데이터를 조회하여 출력 사원정보,
					 * 연차정보,
					 */
					case 405: // 유저 - 검색 정보(상세) 불러오기 (관리자 내용중 2개가 동일하기에 같이쓰기로 한다.)
						try {
							System.out.println("로그인_Bbbbbbbbbbbbbbbb_405 : 왔니?");
							main.memberData(p.getMemvo()); // D_C에서 받고 보낸다.
							main.annData(p.getAvo());
						} catch (Exception e) {
							System.out.println("405 캐치 : " + e.toString());
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
		login = new LogIn_B();
	}

}
