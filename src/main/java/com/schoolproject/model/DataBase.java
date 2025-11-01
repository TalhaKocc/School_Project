package com.schoolproject.model;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBase {
	private static HikariDataSource dataSource;
	
	static {
		try {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl("jdbc:mysql://localhost:3306/student_project");
			config.setUsername("root");
			config.setPassword("");
			config.setDriverClassName("com.mysql.cj.jdbc.Driver");
			
			config.setMaximumPoolSize(10);
			config.setMinimumIdle(2);
			config.setIdleTimeout(30_000);
			config.setMaxLifetime(1_800_000);
			
			dataSource = new HikariDataSource(config);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	public static DataSource getDataSource(){
		return dataSource;
	}
}

