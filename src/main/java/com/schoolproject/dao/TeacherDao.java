package com.schoolproject.dao;

import com.schoolproject.pojo.CourseBean;
import com.schoolproject.pojo.GradeBean;
import com.schoolproject.pojo.StudentBean;
import com.schoolproject.pojo.TeacherBean;

public interface TeacherDao {
	void addCourse(TeacherBean teacherBean,CourseBean courseBean);
	void addGrade(TeacherBean teacherBean,StudentBean studentBean,CourseBean courseBean,GradeBean gradeBean);
	void listCourse(TeacherBean teacherBean);
	void listGrade(TeacherBean teacherBean,CourseBean courseBean);
	void updateGrade(TeacherBean teacherBean,StudentBean studentBean,CourseBean courseBean,GradeBean gradeBean);

}
