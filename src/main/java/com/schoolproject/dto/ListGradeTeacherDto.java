package com.schoolproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListGradeTeacherDto {
	private int gradeId;
	private int studentId;
	private int courseId;
	private String studentFirstName;
	private String studentLastName;
	private String courseName;
	private double grade1;
	private double grade2;
	private String result;
	
}
