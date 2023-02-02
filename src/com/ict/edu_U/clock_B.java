package com.ict.edu_U;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class clock_B extends JPanel implements Runnable{
    JPanel jp;
    JLabel d_day;
    LocalDateTime Datetime; // 시간 값 담기
    DateTimeFormatter formatter; // 시간값 형태변환
    String DT_Now; // 시간 값을 형태변환한 상태로 만든다.

    Font MyFont;

    public clock_B() {
        MyFont = new Font("굴림", Font.BOLD, 15);

        jp = new JPanel();
        d_day = new JLabel("HH. mm. ss");
        d_day.setFont(MyFont);
        d_day.setPreferredSize(new Dimension(150, 50));

            try {
                new Thread(this).start();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }

        jp.add(d_day, BorderLayout.CENTER);
        add(jp);

//        setLocation(10, 10);
//        setSize(800, 800);
//        setResizable(false);
//        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Datetime = LocalDateTime.now();
                formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                DT_Now = Datetime.format(formatter);
                d_day.setText(String.valueOf(DT_Now));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new clock_B();
    }
}