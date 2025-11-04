package com.schoolproject.dao;

import java.util.List;

import com.schoolproject.dto.StudentCourseDto;
import com.schoolproject.dto.StudentGradeDto;
import com.schoolproject.pojo.GradeBean;

public interface StudentDao {
	List<StudentCourseDto> listCourse(int userId);
	List<StudentGradeDto> listGrade(int UserId);

}
