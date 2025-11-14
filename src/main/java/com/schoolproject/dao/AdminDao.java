package com.schoolproject.dao;

import java.util.List;

import com.schoolproject.dto.ListCourseAdminDto;
import com.schoolproject.dto.ListStudentAdminDto;
import com.schoolproject.dto.ListTeacherAdminDto;

public interface AdminDao {
	List<ListTeacherAdminDto> listTeacher();
	List<ListStudentAdminDto> listStudent();
	List<ListCourseAdminDto> listCourse();
	void addTeacher();
	void addCourse();
	
}
