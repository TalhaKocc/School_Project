package com.schoolproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.schoolproject.pojo.CourseBean;




public class Student {
	private DataSource dataSource;

	public Student(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<CourseBean> listCourse(int userId) throws Exception{
		
		List<CourseBean> courseList = new ArrayList<>();
		
		String sql = "SELECT courses.course_id, courses.course_name " +
					  "FROM courses " +
					  "INNER JOIN grades ON courses.course_id = grades.course_id " +
					  "INNER JOIN students ON students.student_id = grades.student_id " +
					  "WHERE students.user_id = ?";
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, userId);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					CourseBean course = new CourseBean();
					course.setId(rs.getInt("course_id"));
					course.setName(rs.getString("course_name"));
					
					
					courseList.add(course);
		               
				}
			
		    } 
		} catch (Exception e) {
			  System.out.println("ERROR in listCourse: " + e.getMessage());
		      e.printStackTrace();
		      throw e;
		}
		
		return courseList;
		
		
	    
    }
}	