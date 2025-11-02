package com.schoolproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;


import com.schoolproject.pojo.UserBean;

public class Login{
	private DataSource dataSource;

	public Login(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public UserBean userLogin(UserBean userBean) {
		
		String sql ="select * from users where email=? and password=?";
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);) {
			
			pstmt.setString(1,userBean.getEmail());
			pstmt.setString(2, userBean.getPassword());
			
			

			
			try(ResultSet rs = pstmt.executeQuery();){
				
				System.out.println("SQL: " + pstmt);

				if(rs.next()) {
					userBean.setId(rs.getInt("user_id"));
					userBean.setEmail(rs.getString("email"));
					userBean.setRole(rs.getString("role"));
					System.out.println("E-posta: " + userBean.getEmail());
					System.out.println("Şifre: " + userBean.getPassword());
					
					return userBean;
			    }else {
					System.out.println("Kullanıcı Bulunamadı");
					return null;
				}

			}
		} catch (Exception e) {
			   e.printStackTrace();
			    System.out.println("Login hatası: " + e.getMessage());
		}
		return null;
	}
}