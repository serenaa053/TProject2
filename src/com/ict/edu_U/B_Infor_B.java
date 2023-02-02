package com.ict.edu_U;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.InfoVO;
import com.ict.edu_D.MembersVO;

public class B_Infor_B extends JPanel {

	JPanel jp_a, jp_b, // 좌 & 우 큰 패널

			jp_a_big, // big = 왼쪽 정보 패널 통합 그리드
			jp1_1, jp1_2, jp1_3, jp1_4, jp1_5, jp1_6, jp1_7, // 좌, 정보 패널
			a_1, b_1, c_1, d_1, e_1, f_1, g_1, // 좌, 여백 패널
			bl_1, bl_2, // 좌, 기본정보창 상,하 여백 패널

			jp_b_big, // 우, 오른쪽 통합 그리드
			jp_ph1, jp_ph2, jp_em, jp2_1, jp2_2, jp2_3, // 우, 사진 패널

			h_1, // 아래, 여백 패널
			jp_final, // 프레임에 붙을 2칸짜리 패널
			jp_bottom, jp_bottom_r, // 아래, 관리자 6칸짜리 그리드
			jp_last; // final과 bottom을 합친 그리드

	JTextField jtf_name, jtf_comid, jtf_position, jtf_division, jtf_joindate, jtf_phone_middle, jtf_phone_last,
			jtf_email_id, jtf_email_addr;

	JButton jb_picture_modify, jb_picture_delete, // 우, 사진 (왼쪽부터 사진 수정, 사진 삭제)
			jb_modify, jb_complete; // 하단, 관리자 버튼

	JComboBox jcb_phone, jcb_email;

	JLabel img, jl_ph1, jl_ph2, jl_em, img2, jl_bottom;

	String url;

	public LogIn_B logIn_B; // Frame을 쓰기 위한 전역 변수 선언

	public B_Infor_B(LogIn_B login) {

		logIn_B = login;

		// JFrame 위의 큰 2패널 생성
		jp_a = new JPanel();
		jp_b = new JPanel();
		jp_b.setLayout(new BorderLayout());

		// JFrame 에 붙을 가장 큰 그리드 생성
		jp_final = new JPanel(new GridLayout(1, 2));

		// 1. 왼쪽 정보창 생성
		// 왼쪽 jp_a 의 큰 그리드드 생성
		jp_a_big = new JPanel(new GridLayout(16, 0));

		// 왼쪽 jp_a 그리드틀에 들어갈 여백 및 내용 생성
		// 그 외 중간 여백들은 영어소문자_1(ex : a_1)로 위부터 아래로 생성
		// 맨위 여백 : bl_1, 맨아래 여백 : bl_2

		bl_1 = new JPanel();
		bl_2 = new JPanel();

		jp1_1 = new JPanel();
		a_1 = new JPanel();

		jp1_2 = new JPanel();
		b_1 = new JPanel();

		jp1_3 = new JPanel();
		c_1 = new JPanel();

		jp1_4 = new JPanel();
		d_1 = new JPanel();

		jp1_5 = new JPanel();
		e_1 = new JPanel();

		jp1_6 = new JPanel();
		f_1 = new JPanel();

		jp1_7 = new JPanel();
		g_1 = new JPanel();

		// 각 정보들이 입력될 TextField 생성

		jtf_name = new JTextField(10);
		jtf_comid = new JTextField(10);
		jtf_position = new JTextField(10);
		jtf_division = new JTextField(10);
		jtf_joindate = new JTextField(10);
		jtf_phone_middle = new JTextField(10);
		jtf_phone_last = new JTextField(10);
		jtf_email_id = new JTextField(10);
		jtf_email_addr = new JTextField(10);

		// ===================================
		jp1_1.setLayout(new BorderLayout());
		JLabel name = new JLabel("이름 : ");
		name.setPreferredSize(new Dimension(60, 30));
		jp1_1.add(name, BorderLayout.WEST);

		JPanel jp1_1a = new JPanel();
		jp1_1a.setLayout(new BorderLayout());
		jp1_1a.setPreferredSize(new Dimension(130, 30));

		jp1_1a.add(jtf_name);
		jp1_1.add(jp1_1a, BorderLayout.CENTER);

		JPanel jp1_1b = new JPanel();
		jp1_1b.setPreferredSize(new Dimension(120, 30)); // 70 + 70 + 20 + 20
		jp1_1.add(jp1_1b, BorderLayout.EAST);

		// ===================================
		jp1_2.setLayout(new BorderLayout());
		JLabel number = new JLabel("사번 : ");
		number.setPreferredSize(new Dimension(60, 30));
		jp1_2.add(number, BorderLayout.WEST);

		JPanel jp1_2a = new JPanel();
		jp1_2a.setLayout(new BorderLayout());
		jp1_2a.setPreferredSize(new Dimension(130, 30));

		jp1_2a.add(jtf_comid);
		jp1_2.add(jp1_2a, BorderLayout.CENTER);

		JPanel jp1_2b = new JPanel();
		jp1_2b.setPreferredSize(new Dimension(120, 30));
		jp1_2.add(jp1_2b, BorderLayout.EAST);

		// =====================================
		jp1_3.setLayout(new BorderLayout());
		JLabel position = new JLabel("직급 : ");
		position.setPreferredSize(new Dimension(60, 30));
		jp1_3.add(position, BorderLayout.WEST);

		JPanel jp1_3a = new JPanel();
		jp1_3a.setLayout(new BorderLayout());
		jp1_3a.setPreferredSize(new Dimension(130, 30));

		jp1_3a.add(jtf_position);
		jp1_3.add(jp1_3a, BorderLayout.CENTER);

		JPanel jp1_3b = new JPanel();
		jp1_3b.setPreferredSize(new Dimension(120, 30));
		jp1_3.add(jp1_3b, BorderLayout.EAST);

		// =====================================
		jp1_4.setLayout(new BorderLayout());
		JLabel deapart = new JLabel("부서 : ");
		deapart.setPreferredSize(new Dimension(60, 30));
		jp1_4.add(deapart, BorderLayout.WEST);

		JPanel jp1_4a = new JPanel();
		jp1_4a.setLayout(new BorderLayout());
		jp1_4a.setPreferredSize(new Dimension(130, 30));

		jp1_4a.add(jtf_division);
		jp1_4.add(jp1_4a, BorderLayout.CENTER);

		JPanel jp1_4b = new JPanel();
		jp1_4b.setPreferredSize(new Dimension(120, 30));
		jp1_4.add(jp1_4b, BorderLayout.EAST);

		// =====================================
		jp1_5.setLayout(new BorderLayout());
		JLabel date = new JLabel("입사일 : ");
		date.setPreferredSize(new Dimension(60, 30));
		jp1_5.add(date, BorderLayout.WEST);

		JPanel jp1_5a = new JPanel();
		jp1_5a.setLayout(new BorderLayout());
		jp1_5a.setPreferredSize(new Dimension(130, 30));

		jp1_5a.add(jtf_joindate);
		jp1_5.add(jp1_5a, BorderLayout.CENTER);

		JPanel jp1_5b = new JPanel();
		jp1_5b.setPreferredSize(new Dimension(120, 30));
		jp1_5.add(jp1_5b, BorderLayout.EAST);

		// =====================================
		// Phone
		// 1. number_full & label

		jp1_6.setLayout(new BorderLayout());
		JLabel hp = new JLabel("H.P : ");
		hp.setPreferredSize(new Dimension(60, 30));

		jp1_6.add(hp, BorderLayout.WEST);

		// 2. number_front
		JPanel jp1_6_1 = new JPanel();
		jp1_6_1.setLayout(new BorderLayout());
		jp1_6_1.setPreferredSize(new Dimension(250, 30));

		String ph[] = { "010", "011", "017", "018", "019" };
		jcb_phone = new JComboBox<>(ph);

		JPanel jp1_6a = new JPanel();
		jp1_6a.setLayout(new BorderLayout());
		jp1_6a.setPreferredSize(new Dimension(70, 30));
		jp1_6a.add(jcb_phone); // jp1_6a를 jp1_6_1에 west

		// 3. number_middle
		JPanel jp1_6b = new JPanel();
		jp1_6b.setPreferredSize(new Dimension(90, 30));
		jp1_6b.setLayout(new BorderLayout());

		// "-" Panel
		JPanel jp1_6b1 = new JPanel();
		jp1_6b1.setPreferredSize(new Dimension(20, 30));
		JLabel jlb1_6b1 = new JLabel("-");
		jp1_6b1.add(jlb1_6b1);
		jp1_6b.add(jp1_6b1, BorderLayout.WEST);

		// number_middle text panel
		JPanel jp1_6b2 = new JPanel();
		jp1_6b2.setLayout(new BorderLayout());
		jp1_6b2.setPreferredSize(new Dimension(70, 30));
		jp1_6b2.add(jtf_phone_middle);
		jp1_6b.add(jp1_6b2, BorderLayout.CENTER);

		// 4. number_last
		JPanel jp1_6c = new JPanel();
		jp1_6c.setPreferredSize(new Dimension(90, 30));
		jp1_6c.setLayout(new BorderLayout());

		JPanel jp1_6c1 = new JPanel();
		jp1_6c1.setPreferredSize(new Dimension(20, 30));
		JLabel jlb1_6c1 = new JLabel("-");
		jp1_6c1.add(jlb1_6c1);
		jp1_6c.add(jp1_6c1, BorderLayout.WEST);

		JPanel jp1_6c2 = new JPanel();
		jp1_6c2.setLayout(new BorderLayout());
		jp1_6c2.setPreferredSize(new Dimension(70, 30));
		jp1_6c2.add(jtf_phone_last);
		jp1_6c.add(jp1_6c2, BorderLayout.CENTER);

		// **
		jp1_6_1.add(jp1_6a, BorderLayout.WEST);
		jp1_6_1.add(jp1_6b, BorderLayout.CENTER);
		jp1_6_1.add(jp1_6c, BorderLayout.EAST);

		// ***
		jp1_6.add(jp1_6_1, BorderLayout.CENTER);

		// =====================================
		// e-mail

		jp1_7.setLayout(new BorderLayout());
		JLabel em = new JLabel("E-mail : ");
		em.setPreferredSize(new Dimension(60, 30));
		// ***
		jp1_7.add(em, BorderLayout.WEST);

		// 1. email_full
		JPanel jp1_7_1 = new JPanel();
		jp1_7_1.setLayout(new BorderLayout());
		jp1_7_1.setPreferredSize(new Dimension(250, 30));

		// 2. email_front
		JPanel jp1_7a = new JPanel();
		jp1_7a.setPreferredSize(new Dimension(90, 30));
		jp1_7a.setLayout(new BorderLayout());

		JPanel jp1_7a1 = new JPanel();
		jp1_7a1.setLayout(new BorderLayout());
		jp1_7a1.setPreferredSize(new Dimension(70, 30));
		jp1_7a1.add(jtf_email_id);
		jp1_7a.add(jp1_7a1, BorderLayout.WEST);

		//
		JPanel jp1_7a2 = new JPanel();
		jp1_7a2.setPreferredSize(new Dimension(20, 30));
		JLabel jlb1_7a1 = new JLabel("@");
		jp1_7a2.add(jlb1_7a1);
		jp1_7a.add(jp1_7a2, BorderLayout.CENTER);

		// 3. email_middle
		JPanel jp1_7c = new JPanel();
		jp1_7c.setLayout(new BorderLayout());
		jp1_7c.setPreferredSize(new Dimension(90, 30));

		JPanel jp1_7c1 = new JPanel();
		jp1_7c1.setPreferredSize(new Dimension(20, 30));
		JLabel jlb1_7c1 = new JLabel(" ");
		jp1_7c1.add(jlb1_7c1);
		jp1_7c.add(jp1_7c1, BorderLayout.CENTER);

		JPanel jp1_7c2 = new JPanel();
		jp1_7c2.setLayout(new BorderLayout());
		jp1_7c2.setPreferredSize(new Dimension(70, 30));
		jp1_7c2.add(jtf_email_addr);
		jp1_7c.add(jp1_7c2, BorderLayout.WEST);

		// 4. email_last
		String email[] = { "naver.com", "daum.net", "gmail.com", "직접입력" };
		jcb_email = new JComboBox<>(email);

		JPanel jp1_7b = new JPanel();
		jp1_7b.setLayout(new BorderLayout());
		jp1_7b.setPreferredSize(new Dimension(70, 30));
		jp1_7b.add(jcb_email);

		// **
		jp1_7_1.add(jp1_7a, BorderLayout.WEST);
		jp1_7_1.add(jp1_7b, BorderLayout.EAST); // 회의 결과 순서 바꿈(1223)
		jp1_7_1.add(jp1_7c, BorderLayout.CENTER); // 회의 결과 순서 바꿈(1223)

		// ***
		jp1_7.add(jp1_7_1, BorderLayout.CENTER);
		// ===========================================================
		// 각 정보 panel 들 왼쪽 그리드에 추가
		jp_a_big.add(bl_1);
		jp_a_big.add(jp1_1);
		jp_a_big.add(a_1);
		jp_a_big.add(jp1_2);
		jp_a_big.add(b_1);
		jp_a_big.add(jp1_3);
		jp_a_big.add(c_1);
		jp_a_big.add(jp1_4);
		jp_a_big.add(d_1);
		jp_a_big.add(jp1_5);
		jp_a_big.add(e_1);
		jp_a_big.add(jp1_6);
		jp_a_big.add(f_1);
		jp_a_big.add(jp1_7);
		jp_a_big.add(g_1);
		jp_a_big.add(bl_2);

		// 빈 왼쪽 가장 큰 패널에 통합 그리드 합치기
		jp_a.add(jp_a_big);

		// ===========================================================
		// 2. 오른쪽 패널에 사진 정보 넣기
		// 사진 윗 여백 창
		jp2_1 = new JPanel();
		jp2_1.setPreferredSize(new Dimension(250, 35));

		// 사진 패널 과 그 아래 패널
		jp2_2 = new JPanel();
		jp2_2.setLayout(new BorderLayout());
		JPanel jp2_2_1 = new JPanel();
		JPanel jp2_2_2 = new JPanel();

		jp2_2_1.setPreferredSize(new Dimension(50, 250));
		jp2_2.add(jp2_2_1, BorderLayout.WEST);

		jp2_2_2.setPreferredSize(new Dimension(50, 250));
		jp2_2.add(jp2_2_2, BorderLayout.EAST);

		jp2_2.setPreferredSize(new Dimension(250, 250));

		// *******
		img = new JLabel("사진 없음");
		img.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon img2 = new ImageIcon("src/images/logo.png");
		img2 = imageSetSize(img2, 250, 250); // img2 size 자체를 여기서 다시 지정해준거구나
		img.setIcon(img2);

		jp2_2.add(img);

		jp2_3 = new JPanel();
		jp2_3.setPreferredSize(new Dimension(250, 180));
		jb_picture_modify = new JButton("사진 수정");
		jb_picture_delete = new JButton("사진 삭제");
		jp2_3.add(jb_picture_modify);
		jp2_3.add(jb_picture_delete);

// ===================================================================================================
		// 사진 & 버튼 패널 Border로 방향 주기
		jp_b_big = new JPanel();
		jp_b_big.setLayout(new BorderLayout());
		jp_b_big.add(jp2_1, BorderLayout.NORTH);
		jp_b_big.add(jp2_2, BorderLayout.CENTER);
		jp_b_big.add(jp2_3, BorderLayout.SOUTH);
		jp_b.add(jp_b_big);

		// 3. 하단 관리자 버튼 만들기
		jp_bottom = new JPanel();
		jp_bottom.setLayout(new BorderLayout());

		jp_bottom_r = new JPanel();

		jl_bottom = new JLabel("");
		jb_modify = new JButton("수정");
		jb_complete = new JButton("확인");
		h_1 = new JPanel();

		jp_bottom_r.add(jl_bottom);
		jp_bottom_r.add(jb_modify);
		jp_bottom_r.add(jb_complete);
		jp_bottom_r.add(h_1);

		jp_bottom.add(jp_bottom_r, BorderLayout.EAST);

		// 4. 왼쪽 오른쪽 큰 패널을 좌우 대칭을 위해 맨 마지막 그리드 창에 합치기
		jp_final.add(jp_a);
		jp_final.add(jp_b);

		// bottom 과 final을 합치기
		jp_last = new JPanel();
		jp_last.setLayout(new BorderLayout());
		jp_last.setPreferredSize(new Dimension(700, 600));
		jp_last.setBorder(new EmptyBorder(70, 10, 10, 10));

		jp_last.add(jp_final, BorderLayout.CENTER);
		jp_last.add(jp_bottom, BorderLayout.SOUTH);

		// 통합 그리드를 프레임에 합치기
		add(jp_last);

		// ********************************************
		jb_picture_modify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 1. 파일(이미지 서버에 저장)
				JFileChooser fc = new JFileChooser(); // 파일 선택기

				FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG파일", "png", "PNG");
				fc.setFileFilter(filter);
				int ret = fc.showOpenDialog(logIn_B);
				if (ret != JFileChooser.APPROVE_OPTION) { // 사용자가 창을 강제로 닫았거나 취소 버튼을 누르면 다이어로그
					JOptionPane.showMessageDialog(logIn_B, "파일 필요");
					return;
				}

				// 선택된 파일을 f에 저장
				File f = fc.getSelectedFile();

				// 이미지 f의 절대 경로 url에 초기화
				url = f.getPath();

				// 상대경로를 위한 문자 치환
				url = url.replaceAll("\\\\", "/");

				// 2. 이미지 저장하자
				// 이미지 경로
				String imagePath = url;

				// 이미지의 이름만 추출
				String imageName = url.substring(url.lastIndexOf('/') + 1);

				// 상대주소, 이미지를 저장할 위치
				String savePoint = "src/images/";

				// 파일 입출력 스트림
				FileOutputStream fos = null;

				try {
					// 파일 저장 형식 File(저장위치, 저장이름)
					File imgFile = new File(savePoint + imageName);

					// 바이트 스트림을 이용하니 바이트 배열 선언
					byte[] b;

					// 해당 경로의 이미지를 읽어와서 imgbf에 초기화
					BufferedImage imgbf = ImageIO.read(new File(imagePath));

					// 이미지를 byte[]로 변환하기 위해 ByteArrayOutputStream의
					// toByteArray를 사용하려고 ByteArrayOutputStream 선언
					ByteArrayOutputStream baos = new ByteArrayOutputStream();

					// Image.IO에 (경로, 확장자, 스트림)을 write
					ImageIO.write(imgbf, url.substring(url.lastIndexOf('.')), baos);

					// baos 스트림에 남아있는 데이b터 전송
					baos.flush();

					// baos에 저장된 이미지를 byte[]로 쪼갬
					b = baos.toByteArray();

					// 저장형식
					fos = new FileOutputStream(imgFile);

					// byte[]로 쪼개진 이미지를 fos스트림으로 전송
					fos.write(b);

					// fos스트림 버퍼에 남아있는 데이터 전송
					fos.flush();

				} catch (Exception e2) {
				}

				img.setHorizontalAlignment(SwingConstants.CENTER); // 22222
				ImageIcon img3 = new ImageIcon(url);
				ImageIcon img4 = imageSetSize(img3, 250, 250);
				img.setIcon(img4);
				jp2_2.add(img);

				InfoVO infovo = new InfoVO();
				String image = imageName;
				infovo.setIdx(logIn_B.mid);
				infovo.setImgupdate(image);

				D_Protocol p = new D_Protocol();
				p.setCmd(301);
				p.setInfovo(infovo);
				JOptionPane.showMessageDialog(logIn_B, "사진 수정이 완료되었습니다.");
				try {
					logIn_B.out.writeObject(p);
					logIn_B.out.flush();
				} catch (Exception e2) {

				}
			}
		});

		jb_picture_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(logIn_B, "사진이 삭제되었습니다.");
				ImageIcon img5 = new ImageIcon("src/images/delete.PNG");
				ImageIcon img6 = imageSetSize(img5, 250, 250);
				img.setIcon(img6);
				jp2_2.add(img);

				InfoVO infovo = new InfoVO();

				infovo.setIdx(logIn_B.mid);
				infovo.setImgupdate("delete.PNG");

				D_Protocol p = new D_Protocol();
				p.setCmd(301);
				p.setInfovo(infovo);

				try {
					logIn_B.out.writeObject(p);
					logIn_B.out.flush();
				} catch (Exception e2) {

				}

			}
		});

		// 수정 요청
		jb_modify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtf_name.setEditable(false);
				jtf_position.setEditable(false);
				jtf_division.setEditable(false);
				jtf_phone_middle.setEditable(true);
				jtf_phone_last.setEditable(true);
				jtf_email_id.setEditable(true);
				if (jtf_email_addr.getText().equals("naver.com")) {
					jtf_email_addr.setEditable(false);
				} else if (jtf_email_addr.getText().equals("daum.net")) {
					jtf_email_addr.setEditable(false);
				} else if (jtf_email_addr.getText().equals("gmail.com")) {
					jtf_email_addr.setEditable(false);
				} else {
					jtf_email_addr.setEditable(true);
				}

				JOptionPane.showMessageDialog(logIn_B, "수정 후 완료버튼을 눌러주세요.");
			}
		});

		// 수정 확인
		jb_complete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtf_phone_middle.getText().equals("") || jtf_phone_last.getText().equals("")
						|| jtf_email_id.getText().equals("") || jtf_email_addr.getText().equals("")) {
					JOptionPane.showMessageDialog(logIn_B, "빈칸없이 입력해주세요.");
				} else {
					MembersVO mvo = new MembersVO();
					InfoVO ivo = new InfoVO();
					String mname = jtf_name.getText().trim();
					String pos = jtf_position.getText().trim();
					String div = jtf_division.getText().trim();

					String phfirst = jcb_phone.getSelectedItem().toString();
					String phmiddle = jtf_phone_middle.getText().trim();
					String phlast = jtf_phone_last.getText().trim();
					String modify_phone = phfirst + "-" + phmiddle + "-" + phlast;

					// 메일 좀 더 생각 필요
					String email_id = jtf_email_id.getText().trim();
					String email_addr = jcb_email.getSelectedItem().toString();
					String modify_email = email_id + "@" + email_addr;
					mvo.setIDX(logIn_B.mid);
					mvo.setUSERNAME(mname);
					mvo.setPHONE(modify_phone);
					mvo.setEMAIL(modify_email);
					ivo.setIdx(logIn_B.mid);
					ivo.setJposition(pos);
					ivo.setDivision(div);

					D_Protocol p = new D_Protocol();
					p.setCmd(302);
					p.setMemvo(mvo);
					p.setInfovo(ivo);
					System.out.println("??왜 안가냐");
					
					JOptionPane.showMessageDialog(logIn_B, "수정이 완료 되었습니다.");

					jtf_name.setEditable(false);
					jtf_position.setEditable(false);
					jtf_division.setEditable(false);
					jtf_phone_middle.setEditable(false);
					jtf_phone_last.setEditable(false);
					jtf_email_id.setEditable(false);
					jtf_email_addr.setEditable(false);

					try {
						logIn_B.out.writeObject(p);
						logIn_B.out.flush();
					} catch (Exception e2) {
					}
				}
			}
		});

		jtf_phone_middle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtf_phone_middle.setText("");
			}
		});

		jtf_phone_middle.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jtf_phone_middle.setText("");
			}
		});

		jtf_phone_last.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtf_phone_last.setText("");
			}
		});

		jtf_phone_last.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jtf_phone_last.setText("");
			}
		});

		jtf_email_id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtf_email_id.setText("");
			}
		});

		jtf_email_id.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				jtf_email_id.setText("");
			}
		});

		jcb_email.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();

				if (String.valueOf(a).equals("직접입력")) {
					jtf_email_addr.setEditable(true);
				} else {
					jtf_email_addr.setText(String.valueOf(a));
					jtf_email_addr.setEditable(false);
				}
			}
		});
	}

	// image size
	ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}

	public void exec(MembersVO infomvo) {
		// 이름
		jtf_name.setText(infomvo.getUSERNAME());
		jtf_name.setEditable(false);

		// 사번
		if (infomvo.getCOMID() == null) {
			jtf_comid.setText("관리자문의");
		} else {
			jtf_comid.setText(infomvo.getCOMID());
		}
		jtf_comid.setEditable(false);

		// 직급
		if (infomvo.getJposition() == null) {
			jtf_position.setText("관리자문의");
		} else {
			jtf_position.setText(infomvo.getJposition());
		}
		jtf_position.setEditable(false);

		// 부서
		if (infomvo.getDivision() == null) {
			jtf_division.setText("관리자문의");
		} else {
			jtf_division.setText(infomvo.getDivision());
		}
		jtf_division.setEditable(false);

		// 입사일
		jtf_joindate.setText(infomvo.getJOINDATE().substring(0, 10));
		jtf_joindate.setEditable(false);

		// H.P
		String getphone = infomvo.getPHONE();
		String del1 = "-";
		String[] resultph = getphone.split(del1);
		for (int i = 0; i < resultph.length; i++) {
			if (resultph[0].equals("010")) {
				jcb_phone.setSelectedIndex(0);
			} else if (resultph[0].equals("011")) {
				jcb_phone.setSelectedIndex(1);
			} else if (resultph[0].equals("017")) {
				jcb_phone.setSelectedIndex(2);
			} else if (resultph[0].equals("018")) {
				jcb_phone.setSelectedIndex(3);
			} else if (resultph[0].equals("019")) {
				jcb_phone.setSelectedIndex(4);
			}
			if (i == 1) {
				jtf_phone_middle.setText(resultph[i]);
			}
			if (i == 2) {
				jtf_phone_last.setText(resultph[i]);
			}
		}
		jtf_phone_middle.setEditable(false);
		jtf_phone_last.setEditable(false);

		// EMAIL
		String email = infomvo.getEMAIL();
		String del2 = "@";
		String[] resultemial = email.split(del2);
		for (int i = 0; i < resultemial.length; i++) {
			if (i == 0) {
				jtf_email_id.setText(resultemial[i]);
			}
			if (i == 1) {
				jtf_email_addr.setText(resultemial[i]);
			}
			if (resultemial[1].equals("naver.com")) {
				jcb_email.setSelectedIndex(0);
			} else if (resultemial[1].equals("daum.net")) {
				jcb_email.setSelectedIndex(1);
			} else if (resultemial[1].equals("gmail.com")) {
				jcb_email.setSelectedIndex(2);
			} else {
				jcb_email.setSelectedIndex(3);
			}
		}
		jtf_email_id.setEditable(false);
		jtf_email_addr.setEditable(false);

		// 서버에 저장된 파일(이미지) 읽어오기
		// 사진
		if (infomvo.getImgupdate() == null) {
			ImageIcon img2a = new ImageIcon("src/images/delete.PNG");
			img2a = imageSetSize(img2a, 250, 250); // img2 size 자체를 여기서 다시 지정해준거구나
			img.setIcon(img2a);
		} else {
			ImageIcon img2a = new ImageIcon("src/images/" + infomvo.getImgupdate().trim());
			img2a = imageSetSize(img2a, 250, 250); // img2 size 자체를 여기서 다시 지정해준거구나
			img.setIcon(img2a);
		}
	}
}