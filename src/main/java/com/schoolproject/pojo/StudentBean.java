package com.schoolproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentBean {
	private int id;
	private int no;
	private UserBean user;
	
	public StudentBean(String name, String surname, int no, UserBean user) {
		this.no = no;
		this.user = user;
	}
}
