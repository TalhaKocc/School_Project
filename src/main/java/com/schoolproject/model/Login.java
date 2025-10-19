package com.schoolproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.schoolproject.dao.UserDao;
import com.schoolproject.pojo.UserBean;
import static com.schoolproject.model.Database.getConnection;

public class Login implements UserDao {
	
	private String sqlLogin="SELECT userId, fullName, email, role FROM users WHERE email=? AND password=?";
	
	@Override
	public UserBean userLogin(UserBean userBean) {
		UserBean loggedInUser = null; 
		
		try(Connection connection = getConnection();
			PreparedStatement pstm = connection.prepareStatement(sqlLogin)){
			
			pstm.setString(1, userBean.getEmail());
			pstm.setString(2, userBean.getPassword());
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) {
				System.out.println("Kullanıcı bulundu: " + rs.getString("email"));
				loggedInUser = new UserBean(); 

				loggedInUser.setId(rs.getInt("userId"));
				loggedInUser.setName(rs.getString("fullName"));
				loggedInUser.setEmail(rs.getString("email"));
				loggedInUser.setRole(rs.getString("role"));
			}
			else {
				System.out.println("Kullanıcı bulunamadı (sorgu boş döndü)");
			}
		} catch (Exception e) {
            
			e.printStackTrace(); 
		}
		return loggedInUser;
	}
}