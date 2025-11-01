package com.schoolproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherBean {
	private int id;
	private int no;
	private long salary;
	private UserBean user;
	
	public TeacherBean(int no, long salary, UserBean user) {
		this.no = no;
		this.salary = salary;
		this.user = user;
	}
	
}
