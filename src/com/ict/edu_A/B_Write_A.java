package com.ict.edu_A;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class B_Write_A extends JPanel {

	JPanel jp_final, jp_a, jp_b, jp_c, jp_name, jp_name_lb, jp_name_text, jp_title, jp_title_lb, jp_title_text,
			jp_content, jp_content_lb, jp_bt, jp_enter, a_1, b_1, name_1, title_1; // 여백패널

	JLabel jlb_writer, jlb_title, jlb_content;
	JTextField jtf_writer, jtf_title;
	JTextArea jta;
	JScrollPane jsp;
	JButton jb1;
	Font myFont_basic;
	JCheckBox jcb;
	LineBorder lb;
	MaIn_A b_Main;
	String filePath;

	public B_Write_A() {
		// setBackground(Color.CYAN);

		jp_final = new JPanel();
		jp_final.setLayout(new BorderLayout());
		// jp_final.setBackground(Color.BLUE);
		jp_final.setBorder(new EmptyBorder(30, 10, 10, 10));

		// Font 만들기
		myFont_basic = new Font("굴림", Font.BOLD, 15);

		// lineBorder
		lb = new LineBorder(Color.BLACK);

		// 작성자 & 제목 만들기
		jp_a = new JPanel();
		jp_a.setLayout(new BorderLayout());

		// 작성자
		jp_name = new JPanel();
		jp_name.setLayout(new BorderLayout());
		jp_name.setPreferredSize(new Dimension(700, 30));
		// jp_name.setBackground(Color.YELLOW);

		jp_name_lb = new JPanel();
		jp_name_lb.setLayout(new BorderLayout());
		jp_name_lb.setPreferredSize(new Dimension(100, 30));
		jlb_writer = new JLabel(" 작성자 ", JLabel.CENTER);
		jlb_writer.setFont(myFont_basic);
		jp_name_lb.add(jlb_writer);

		jp_name_text = new JPanel();
		jp_name_text.setLayout(new BorderLayout());
		jp_name_text.setPreferredSize(new Dimension(200, 30));

		jtf_writer = new JTextField(10);
		jp_name_text.add(jtf_writer);

		name_1 = new JPanel();
		name_1.setPreferredSize(new Dimension(400, 30));

		jp_name_lb.setBorder(lb);
		jp_name.add(jp_name_lb, BorderLayout.WEST);
		jp_name.add(jp_name_text, BorderLayout.CENTER);
		jp_name.add(name_1, BorderLayout.EAST);

		// 작성자 & 제목 사이, 여백

		a_1 = new JPanel();
		a_1.setPreferredSize(new Dimension(700, 30));
		// a_1.setBackground(Color.BLACK);

		// 제목

		jp_title = new JPanel();
		jp_title.setLayout(new BorderLayout());
		jp_title.setPreferredSize(new Dimension(700, 30));
		// jp_title.setBackground(Color.ORANGE);

		jp_title_lb = new JPanel();
		jp_title_lb.setLayout(new BorderLayout());
		jp_title_lb.setPreferredSize(new Dimension(100, 30));
		jlb_title = new JLabel(" 제  목 ", JLabel.CENTER);
		jlb_title.setFont(myFont_basic);
		jp_title_lb.add(jlb_title);

		jp_title_text = new JPanel();
		jp_title_text.setLayout(new BorderLayout());
		jp_title_text.setPreferredSize(new Dimension(500, 30));

		jtf_title = new JTextField(10);
		jp_title_text.add(jtf_title);

		title_1 = new JPanel();
		title_1.setPreferredSize(new Dimension(100, 30));

		//
		jp_title_lb.setBorder(lb);
		jp_title.add(jp_title_lb, BorderLayout.WEST);
		jp_title.add(jp_title_text, BorderLayout.CENTER);
		jp_title.add(title_1, BorderLayout.EAST);

		// 작성자 , 작성자 & 제목 여백, 여백 합치기
		jp_a.add(jp_name, BorderLayout.NORTH);
		jp_a.add(a_1, BorderLayout.CENTER);
		jp_a.add(jp_title, BorderLayout.SOUTH);

		// 내용 & JTextArea 만들기
		jp_b = new JPanel();
		jp_b.setLayout(new BorderLayout());

		// 내용
		jp_content = new JPanel();
		jp_content.setLayout(new BorderLayout());
		jp_content.setPreferredSize(new Dimension(700, 30));
		// jp_content.setBackground(Color.PINK);

		jp_content_lb = new JPanel();
		jp_content_lb.setLayout(new BorderLayout());
		jp_content_lb.setPreferredSize(new Dimension(100, 30));
		jlb_content = new JLabel(" 내  용 ", JLabel.CENTER);
		jlb_content.setFont(myFont_basic);
		jp_content_lb.add(jlb_content);

		jp_content_lb.setBorder(lb);
		jp_content.add(jp_content_lb, BorderLayout.WEST);

		// JTextArea
		jta = new JTextArea(20, 30);
		jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setLineWrap(true);

		jp_b.add(jp_content, BorderLayout.NORTH);
		jp_b.add(jsp, BorderLayout.CENTER);

		// 주요공지 버튼 & 확인 버튼
		jp_c = new JPanel();
		jp_c.setLayout(new BorderLayout());

		b_1 = new JPanel();
		b_1.setPreferredSize(new Dimension(700, 50));
		// b_1.setBackground(Color.BLACK);

		// 주요공지 체크 박스 만들기
		jp_bt = new JPanel();
		jp_bt.setLayout(new BorderLayout());
		jp_bt.setPreferredSize(new Dimension(700, 30));
		// jp_bt.setBackground(Color.GREEN);

		jcb = new JCheckBox("주요공지");

		jp_bt.add(jcb, BorderLayout.EAST);

		// 확인 버튼

		jp_enter = new JPanel();
		jp_enter.setLayout(new BorderLayout());
		jp_enter.setPreferredSize(new Dimension(700, 30));
		// jp_enter.setBackground(Color.ORANGE);

		jb1 = new JButton("확인");

		jp_enter.add(jb1, BorderLayout.EAST);

		//
		jp_c.add(b_1, BorderLayout.NORTH);
		jp_c.add(jp_bt, BorderLayout.CENTER);
		jp_c.add(jp_enter, BorderLayout.SOUTH);

		// ***

		jp_final.add(jp_a, BorderLayout.NORTH);
		jp_final.add(jp_b, BorderLayout.CENTER);
		jp_final.add(jp_c, BorderLayout.SOUTH);

		// ****
		add(jp_final);

		jb1.addMouseListener(new MouseAdapter() {
			JFileChooser chooser;

			@Override
			public void mouseClicked(MouseEvent e) {
				chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt파일", "TXT", "txt");
				chooser.setFileFilter(filter);
				int ret = chooser.showSaveDialog(b_Main); // ??????
				if (ret != JFileChooser.APPROVE_OPTION) { // 사용자가 창을 강제로 닫았거나 취소 버튼을 누르면 다이어로그
					JOptionPane.showMessageDialog(b_Main, "파일 필요");
					return;
				}
				filePath = chooser.getSelectedFile().getPath().trim();
				file_save();

			}
		});

	}

	private void file_save() {
		File file = new File(filePath);
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);

			String msg = filePath.trim();
			bos.write(msg.getBytes());
			bos.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null)
					bos.close();
				if (fos != null)
					fos.close();
			} catch (Exception e2) {
			}
		}

	}

}