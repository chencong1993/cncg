package com.cncg.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public abstract class Test {

	public static void main(String[] args) {

		try {
			BasicDataSource dataSource =null;
			dataSource = new BasicDataSource();	
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/mvc");
			dataSource.setUsername("root");
			dataSource.setPassword("123");
			Connection conn= dataSource.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
