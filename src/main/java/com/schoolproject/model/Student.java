package com.schoolproject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.schoolproject.dao.StudentDao;
import com.schoolproject.pojo.CourseBean;
import com.schoolproject.pojo.GradeBean;
import com.schoolproject.pojo.StudentBean;
import com.schoolproject.pojo.UserBean;

import static com.schoolproject.model.Database.getConnection;

public class Student implements StudentDao {
	private String sqlListCourse = "SELECT * FROM courses";
	@Override
	public void listCourse(StudentBean studentBean, CourseBean courseBean) {
		
		try(Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(sqlListCourse);
			ResultSet rs = ps.executeQuery();){
			while(rs.next()) {
				courseBean.setId(rs.getInt("courseId"));
				courseBean.setName(rs.getString("courseName"));
				System.out.println("Ders:"+courseBean.getId()+courseBean.getName());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	
	@Override
	public void listGrade(StudentBean studentBean, CourseBean courseBean, GradeBean gradeBean) {
		
		
	}

}	
