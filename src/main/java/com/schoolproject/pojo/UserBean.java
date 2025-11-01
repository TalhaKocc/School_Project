package com.schoolproject.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBean implements Serializable{
	private static final long serialVersionUID =1L;
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String role;
	
	public UserBean(String first_name, String last_name, String email, String password, String role) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
}
