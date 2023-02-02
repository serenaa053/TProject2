package com.ict.edu_A;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.ict.edu_D.D_Protocol;
import com.ict.edu_D.Pay_VO;

public class Ex02_A extends JPanel {

	JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp12, jp13, jp14, jp15, jp16, jp17, jp18, jp19,
			jp20, jp21, jp22, jp23, jp24, jp25, jp26, jp27, jp28, jp29;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, pay1, pay2, pay3, pay4, pay5, pay6, pay7, pay8, pay9, pay10,
			pay11, pay12, pay13, pay14, pay15, pay16, pay17, pay18, pay19, pay20, pay21, pay22, pay23, pay24;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10, jtf11, jtf12, jtf13, jtf14;
	JButton jb1, jb2, jb3;
	JTextArea jta, jta1, jta2, jta3, jta4;
	JScrollPane jsp;
	JCheckBox jbc1;
	JTable jtb, jtb1, jtb2, jtb3;
	DefaultTableModel dtm;
	DefaultTableCellRenderer dtcr;
	CardLayout cl;
	public LogIn_A login_A;

	Object[][] data = { { "기본급", pay1, "소득세", pay13 }, { "직책수당", pay2, "주민세", pay14 }, { "연장수당", pay3, "고용보험", pay15 },
			{ "휴일수당", pay4, "국민연금", pay16 }, { "상여금", pay5, "장기요양", pay17 }, { "기타", pay6, "건강보험", pay18 },
			{ "식대", pay7, "", pay19 }, { "교통비", pay8, "", pay20 }, { "복지후생", pay9, "", pay21 },
			{ "", pay10, "", pay22 }, { "", pay11, "공제합계", pay23 }, { "급여계", pay12, "차감수령액", pay24 } };

	String saveusername;
	String savejposition;
	String savedivision;
	String savecomid ;
	public Ex02_A pay;

	public Ex02_A(LogIn_A login) {
		// super("급여명세서");
		this.login_A = login;

		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		LineBorder lb = new LineBorder(Color.BLACK);

		jp1 = new JPanel();
		jl1 = new JLabel("                      월급 명세서                     ");
		jl1.setFont(new Font("Serif", Font.BOLD, 40));
		jl1.setBorder(lb);
		jp1.add(jl1);
		jp.add(jp1);

		jp3 = new JPanel();
		jl2 = new JLabel("성명:");
		jl3 = new JLabel();
		jl4 = new JLabel("부서:");
		jl5 = new JLabel();
		jl6 = new JLabel("직책:");
		jl7 = new JLabel();
		jl8 = new JLabel("사번:");
		jl9 = new JLabel();
		jl2.setFont(new Font("Serif", Font.BOLD, 20));
		jl3.setFont(new Font("Serif", Font.BOLD, 15));
		jl4.setFont(new Font("Serif", Font.BOLD, 20));
		jl5.setFont(new Font("Serif", Font.BOLD, 15));
		jl6.setFont(new Font("Serif", Font.BOLD, 20));
		jl7.setFont(new Font("Serif", Font.BOLD, 15));
		jl8.setFont(new Font("Serif", Font.BOLD, 20));
		jl9.setFont(new Font("Serif", Font.BOLD, 15));
		jp3.setBorder(new EmptyBorder(0, 0, 0, 280));
		jp3.add(jl2);
		jp3.add(jl3);
		jp3.add(jl4);
		jp3.add(jl5);
		jp3.add(jl6);
		jp3.add(jl7);
		jp3.add(jl8);
		jp3.add(jl9);
		jp.add(jp3);
		add(jp);
		pay1 = new JLabel();
		pay2 = new JLabel();
		pay3 = new JLabel();
		pay4 = new JLabel();
		pay5 = new JLabel();
		pay6 = new JLabel();
		pay7 = new JLabel();
		pay8 = new JLabel();
		pay9 = new JLabel();
		pay10 = new JLabel();
		pay11 = new JLabel();
		pay12 = new JLabel();
		pay13 = new JLabel();
		pay14 = new JLabel();
		pay15 = new JLabel();
		pay16 = new JLabel();
		pay17 = new JLabel();
		pay18 = new JLabel();
		pay18.setEnabled(true);
		pay19 = new JLabel();
		setVisible(false);
		pay20 = new JLabel();
		setVisible(false);
		pay21 = new JLabel();
		pay22 = new JLabel();
		pay23 = new JLabel();
		pay24 = new JLabel();

		jp4 = new JPanel();
		String[] columName = { "지급항목", "지급금액", "공제항목", "공제액" };

		jtb = new JTable(data, columName);
		jsp = new JScrollPane(jtb);
		jtb.setEnabled(false);
		jsp = new JScrollPane(jtb);

		DefaultTableCellRenderer Left = new DefaultTableCellRenderer();
		Left.setHorizontalAlignment(JLabel.LEFT);
		DefaultTableCellRenderer Center = new DefaultTableCellRenderer();
		Center.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer Right = new DefaultTableCellRenderer();
		Right.setHorizontalAlignment(JLabel.RIGHT);

		jtb.getColumn("지급항목").setPreferredWidth(0);
		jtb.getColumn("지급항목").setCellRenderer(Center);
		jtb.getColumn("지급금액").setPreferredWidth(150);
		jtb.getColumn("지급금액").setCellRenderer(Right);
		jtb.getColumn("공제항목").setPreferredWidth(0);
		jtb.getColumn("공제항목").setCellRenderer(Center);
		jtb.getColumn("공제액").setPreferredWidth(150);
		jtb.getColumn("공제액").setCellRenderer(Right);
		jtb.getTableHeader().setReorderingAllowed(false);

		jb1 = new JButton("수정");
		jb2 = new JButton("확인");

		jsp.setPreferredSize(new Dimension(650, 287)); // 테이블 크기조절
		jtb.setRowHeight(22);

		jp4.add(jsp);
		jp.add(jp4);

		jp5 = new JPanel();

		jp5.add(jb1);
		jp5.add(jb2);
		jp5.setBorder(new EmptyBorder(20, 580, 0, 50));
		jp.add(jp5);

		jp6 = new JPanel();
		jp6.setBorder(new EmptyBorder(0, 300, 0, 0));
		jp.setBorder(new EmptyBorder(70, 0, 0, 0));
		jp.add(jp6);

		setVisible(true);

//		ssdd(pay_VO);

		// 수정버튼
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int msi = JOptionPane.showConfirmDialog(login_A, "급여를 수정하시겠습니까?", "공지수정", JOptionPane.YES_NO_OPTION);
				if (msi == JOptionPane.YES_OPTION) {
					// 수정확인 버튼
					jtb.setEnabled(true);
					
				} else {
					JOptionPane.showMessageDialog(login_A, "수정이 완료 되지 않았습니다", "수정 결정", JOptionPane.OK_CANCEL_OPTION);
				}
			}
		});

		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Pay_VO pay = new Pay_VO();
				pay.setBasic_1(data[0][1].toString());
				pay.setJob_1(data[1][1].toString());
				pay.setExtend_1(data[2][1].toString());
				pay.setHoliday(data[3][1].toString());
				pay.setBonus(data[4][1].toString());
				pay.setGuitar(data[5][1].toString());
				pay.setLunch(data[6][1].toString());
				pay.setTraffic(data[7][1].toString());
				pay.setWelfare(data[8][1].toString());
				pay.setSalary(data[11][1].toString());
				pay.setIncome(data[0][3].toString());
				pay.setCitizen(data[1][3].toString());
				pay.setIns(data[2][3].toString());
				pay.setPension(data[3][3].toString());
				pay.setCottage(data[4][3].toString());
				pay.setHealth(data[5][3].toString());
				pay.setSubtract(data[10][3].toString());
				pay.setTOTAL(data[11][3].toString());
				pay.setUSERNAME(saveusername);
				pay.setJPOSITION(savejposition);
				pay.setDIVISION(savedivision);

				D_Protocol pro = new D_Protocol();

				pro.setCmd(7);
				pro.setPayVo(pay);
				try {
					login_A.out.writeObject(pro);
					login_A.out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(login_A, "수정이 완료되었습니다.");
				jtb.clearSelection(); 
				jtb.setEnabled(false);
			}
		});
	}

	public void ssdd(Pay_VO pvo) {

		jl3.setText(pvo.getUSERNAME());
		saveusername = pvo.getUSERNAME();

		if (pvo.getJPOSITION() == null) {
			jl7.setText("관리자문의");

		} else {
			jl7.setText(pvo.getJPOSITION());
			savejposition = pvo.getJPOSITION();
		}

		if (pvo.getDIVISION() == null) {
			jl5.setText("관리자문의");
		} else {
			jl5.setText(pvo.getDIVISION());
			savedivision = pvo.getDIVISION();
		}
		
		jl9.setText(pvo.getcOMID());
		savecomid = pvo.getcOMID();

		try {
			data[0][1] = pvo.getBasic_1();
			data[1][1] = pvo.getJob_1();
			data[2][1] = pvo.getExtend_1();
			data[3][1] = pvo.getHoliday();
			data[4][1] = pvo.getBonus();
			data[5][1] = pvo.getGuitar();
			data[6][1] = pvo.getLunch();
			data[7][1] = pvo.getTraffic();
			data[8][1] = pvo.getWelfare();
			data[11][1] = pvo.getSalary();
			data[0][3] = pvo.getIncome();
			data[1][3] = pvo.getCitizen();
			data[2][3] = pvo.getIns();
			data[3][3] = pvo.getPension();
			data[4][3] = pvo.getCottage();
			data[5][3] = pvo.getHealth();
			data[10][3] = pvo.getSubtract();
			data[11][3] = pvo.getTOTAL();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public void ssdd2(Pay_VO pvo) {

		jl3.setText(pvo.getUSERNAME());
		saveusername = pvo.getUSERNAME();

		if (pvo.getJPOSITION() == null) {
			jl7.setText("관리자문의");

		} else {
			jl7.setText(pvo.getJPOSITION());
			savejposition = pvo.getJPOSITION();
		}

		if (pvo.getDIVISION() == null) {
			jl5.setText("관리자문의");
		} else {
			jl5.setText(pvo.getDIVISION());
			savedivision = pvo.getDIVISION();
		}
		
		jl9.setText(pvo.getcOMID());
		savecomid = pvo.getcOMID();

		try {
			data[0][1] = pvo.getBasic_1();
			data[1][1] = pvo.getJob_1();
			data[2][1] = pvo.getExtend_1();
			data[3][1] = pvo.getHoliday();
			data[4][1] = pvo.getBonus();
			data[5][1] = pvo.getGuitar();
			data[6][1] = pvo.getLunch();
			data[7][1] = pvo.getTraffic();
			data[8][1] = pvo.getWelfare();
			data[11][1] = pvo.getSalary();
			data[0][3] = pvo.getIncome();
			data[1][3] = pvo.getCitizen();
			data[2][3] = pvo.getIns();
			data[3][3] = pvo.getPension();
			data[4][3] = pvo.getCottage();
			data[5][3] = pvo.getHealth();
			data[10][3] = pvo.getSubtract();
			data[11][3] = pvo.getTOTAL();

		} catch (Exception e) {
			System.out.println(e);
		}
		Ex02_A pay = new Ex02_A(login_A); 
	}
}