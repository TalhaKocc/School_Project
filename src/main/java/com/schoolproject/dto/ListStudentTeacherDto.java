package com.schoolproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListStudentTeacherDto {
	private int studentId;
	private String firstName;
	private String lastName;
}
