package com.schoolproject.dao;

import java.util.List;

import com.schoolproject.dto.AddCourseAdminDto;
import com.schoolproject.dto.AddTeacherAdminDto;
import com.schoolproject.dto.ListCourseAdminDto;
import com.schoolproject.dto.ListStudentAdminDto;
import com.schoolproject.dto.ListTeacherAdminDto;

public interface AdminDao {
	List<ListTeacherAdminDto> listTeacher();
	List<ListStudentAdminDto> listStudent();
	List<ListCourseAdminDto> listCourse();
	void addTeacher(AddTeacherAdminDto addTEacher);
	void addCourse(AddCourseAdminDto addCourse);
	
}
