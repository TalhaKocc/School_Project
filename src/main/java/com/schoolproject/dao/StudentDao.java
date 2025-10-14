package com.schoolproject.dao;

import com.schoolproject.pojo.CourseBean;
import com.schoolproject.pojo.GradeBean;
import com.schoolproject.pojo.StudentBean;

public interface StudentDao {
	void listCourse(StudentBean studentBean,CourseBean courseBean);
	void listGrade(StudentBean studentBean,CourseBean courseBean,GradeBean gradeBean);
}
