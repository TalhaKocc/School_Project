package com.schoolproject.dao;

import java.util.List;

import com.schoolproject.dto.AddGradeTeacherDto;
import com.schoolproject.dto.ListCourseTeacherDto;
import com.schoolproject.dto.ListGradeTeacherDto;

public interface TeacherDao {
	void addGrade(AddGradeTeacherDto addGradeTeacherDto);
	List<ListCourseTeacherDto> listCourse(int userId);
	List<ListGradeTeacherDto> listGrade(int userId);

}
