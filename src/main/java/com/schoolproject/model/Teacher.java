package com.schoolproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.schoolproject.dao.TeacherDao;
import com.schoolproject.dto.AddGradeTeacherDto;
import com.schoolproject.dto.ListCourseTeacherDto;
import com.schoolproject.dto.ListGradeTeacherDto;
import com.schoolproject.dto.StudentCourseDto;

public class Teacher implements TeacherDao{
	
	private DataSource dataSource;
	
	public Teacher(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void addGrade(AddGradeTeacherDto addGradeTeacherDto) {
		
		
	}

	@Override
	public List<ListCourseTeacherDto> listCourse(int userId) {
		
		List<ListCourseTeacherDto> courseList = new ArrayList<>();
		
		String sql = "SELECT DISTINCT courses.course_name " +
                "FROM teacher_courses " +
                "INNER JOIN courses ON teacher_courses.course_id = courses.course_id " +
                "INNER JOIN teachers ON teacher_courses.teacher_id = teachers.teacher_id " +
                "INNER JOIN users ON teachers.user_id = users.user_id " +
                "WHERE users.user_id = ?";
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);) {
			
			pstmt.setInt(1, userId);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					System.out.println("Bulunan kurs: " + rs.getString("course_name"));

					
					ListCourseTeacherDto courseTeacherDto = new ListCourseTeacherDto();
					courseTeacherDto.setCourseName(rs.getString("course_name"));
					courseList.add(courseTeacherDto);
				}
					
				
				}
			} catch (Exception e) {
				// TODO: handle exception
				
		}
		
		System.out.println("User ID: " + userId);
		System.out.println("SQL çalıştı, kurslar alınıyor...");

		
		return courseList;
	}

	@Override
	public List<ListGradeTeacherDto> listGrade(int userId) {

		return null;
	}




}
