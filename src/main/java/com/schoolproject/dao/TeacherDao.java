package com.schoolproject.dao;

import java.util.List;

import com.schoolproject.dto.AddGradeTeacherDto;
import com.schoolproject.dto.ListCourseTeacherDto;
import com.schoolproject.dto.ListGradeTeacherDto;
import com.schoolproject.dto.ListStudentTeacherDto;

public interface TeacherDao {
	void updateGrade(AddGradeTeacherDto updateGradeDto, int userId);
	void addGrade(AddGradeTeacherDto addGradeTeacherDto,int userId);
	List<ListCourseTeacherDto> listCourse(int userId);
	List<ListGradeTeacherDto> listGrade(int userId);
	List<ListStudentTeacherDto> listStudent(int userId,int courseId);

}
