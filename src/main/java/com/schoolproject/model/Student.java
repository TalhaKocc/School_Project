package com.schoolproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.schoolproject.pojo.CourseBean;

import com.schoolproject.pojo.UserBean;


public class Student {
	private DataSource dataSource;

	public Student(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<CourseBean> listCourse(int userId) throws Exception{
		
		List<CourseBean> courseList = new ArrayList<>();
		
		String sql = "SELECT courses.course_id,courses.course_name"+
					  "FROM courses"+
					  "INNER JOIN grades ON courses.course_id = grades.course_id"+
					  "INNER JOIN students ON students.student_id = grades.student_id"+
					  "WHERE students.user_id = ?";
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			UserBean userBean = new UserBean();
			pstmt.setInt(1, userBean.getId());
			
			try(ResultSet rs = pstmt.executeQuery()) {
				
				if (rs.next()) {
					CourseBean course = new CourseBean();
					course.setId(rs.getInt("course_id"));
					course.setName(rs.getString("course_name"));
					
					courseList.add(course);
					
				}
				
			
		    } 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
		
	    
    }
}	