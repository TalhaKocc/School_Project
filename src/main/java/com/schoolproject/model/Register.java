package com.schoolproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import com.schoolproject.pojo.UserBean;

public class Register {
	private DataSource dataSource;
	
	public Register(DataSource dataSource) {
		this.dataSource=dataSource;
	}

	public boolean userRegister(UserBean userBean) {
		String sql = "insert into users(first_name,last_name,email,password,role) values(?,?,?,?,?)";
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt =connection.prepareStatement(sql)) {
			
			pstmt.setString(1, userBean.getFirst_name());
			pstmt.setString(2, userBean.getLast_name());
			pstmt.setString(3, userBean.getEmail());
			pstmt.setString(4, userBean.getPassword());
			pstmt.setString(5, "Student");
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
}
