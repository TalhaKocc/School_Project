package com.schoolproject.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static final String URL ="jdbc:mysql://localhost:3306/student_project";
	private static final String USER="root";
	private static final String PASSWORD="";
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("Database Connected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
}
