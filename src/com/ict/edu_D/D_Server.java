package com.ict.edu_D;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class D_Server {

	ServerSocket ss = null;
	ArrayList<D_Client> list = null;

	public D_Server() {
		list = new ArrayList<>();
		try {
			ss = new ServerSocket(7010);
			System.out.println("서버 시작합니다요");
			exec();

		} catch (Exception e) {
		}
	}

//	@Override   ??? 왜???
	void exec() {
		while (true) {
			try {
				Socket s = ss.accept();
				D_Client cc = new D_Client(s, this);
				cc.start();
				list.add(cc);
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		new D_Server();
	}
}
