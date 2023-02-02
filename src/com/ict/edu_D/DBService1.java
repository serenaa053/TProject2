package com.ict.edu_D;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBService1 {
	// MyBatis를 사용하기 위해서  SqlSession클래스가 필요하고
	// SqlSessionFactory클래스를 가지고 SqlSession를 만든다.
	static private SqlSessionFactory factory;
	static String resource = "com/ict/edu_D/D_Config.xml";
	//static 초기화
	static {
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// DAO에서 factory를 호출할 메서드
	public static SqlSessionFactory getFactory() {
		return factory;
	}
	
}