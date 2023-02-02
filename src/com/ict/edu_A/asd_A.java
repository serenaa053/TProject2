package com.ict.edu_A;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
 
 
public class asd_A extends  JPanel{
	


JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp12, jp13, jp14, jp15, jp16, jp17, jp18, jp19,
		jp20, jp21, jp22, jp23, jp24, jp25, jp26, jp27, jp28, jp29;
JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10, jl11, jl12, jl13, jl14, jl15, jl16, jl17, jl18, jl19,
		jl20, jl21, jl22, jl23, jl24, jl25, jl26, jl27, jl28, jl29, jl30, jl31, jl32, jl33, jl34, jl35;
JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10, jtf11, jtf12, jtf13, jtf14;
JButton jb1, jb2, jb3;
JTextArea jta, jta1, jta2, jta3, jta4;
JScrollPane jsp;
JCheckBox jbc1;
JTable jtb, jtb1, jtb2, jtb3;
DefaultTableModel dtm;
DefaultTableCellRenderer dtcr ;


public asd_A() {
//	super("급여명세서");
	JPanel jp = new JPanel();
	jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
	LineBorder lb = new LineBorder(Color.BLACK); // 색과 두께가 같다면 하나만 생성하면 모두 적용가능하다

    // 데이터 값
    String rows[][] = { { "너구리", "치킨" }, { "돼    지", "피자" }, { "족제비", "족발" } };
    // 항목 값
    String headers[] = { "이름", "음식" };
    // 선택 할 수 있는 값(콤보 박스에 넣을 값)
    String sports[] = {"치킨", "피자", "족발","기타"};
    
    // 콤보박스 생성
    JComboBox<String> comboBox = new JComboBox<String>(sports);
    // 콤보박스 항목 최대 4개
    comboBox.setMaximumRowCount(4);
    
    // 테이블에 생성
    TableCellEditor editor = new DefaultCellEditor(comboBox);

    // JFrame을 상속받지 않고 코드상으로 사용하는 방법
    JFrame frame = new JFrame("JTable Test");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 테이블 생성
    JTable table = new JTable(new DefaultTableModel(rows, headers));
    
    // 테이블에 콤보박스  가능
    table.getColumnModel().getColumn(1).setCellEditor(editor);

    JScrollPane scrollPane = new JScrollPane(table);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.setSize(300, 150);
    frame.setVisible(true);
		
	
	
	
//	Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
//	setBounds(ds.width / 2 - 500, ds.height / 2 - 500, 1000, 1000);
//	setResizable(true);
	setVisible(true);
//	setDefaultCloseOperation(EXIT_ON_CLOSE);
//}
//
//public static void main(String[] args) {
//	new Ex02();
}
}