package com.schoolproject.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static final String URL ="jdbc:mysql://localhost:3306/student_project";
	private static final String USER="root";
	private static final String PASSWORD="";
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("Database Connected");
		} catch (Exception e) {
			System.err.println("!!! VERITABANI BAĞLANTI HATASI !!!");
	        e.printStackTrace();
		}
		return connection;
	}
}
