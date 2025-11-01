package com.schoolproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeBean {
	private int id;
	private StudentBean student;
	private TeacherBean teacher;
	private CourseBean course;
	private long grade1;
	private long grade2;
	private String result;
	
	public GradeBean(StudentBean student, TeacherBean teacher, CourseBean course, long grade1, long grade2,String result) {
		this.student = student;
		this.teacher = teacher;
		this.course = course;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.result = result;
	}
	
	
}
