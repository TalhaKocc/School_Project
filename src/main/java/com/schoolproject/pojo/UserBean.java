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
	private String name;
	private String email;
	private String password;
	private String role;
}
