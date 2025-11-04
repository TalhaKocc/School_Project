package com.schoolproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.schoolproject.dao.TeacherDao;
import com.schoolproject.dto.AddGradeTeacherDto;
import com.schoolproject.dto.ListCourseTeacherDto;
import com.schoolproject.dto.ListGradeTeacherDto;

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
		
					ListCourseTeacherDto courseTeacherDto = new ListCourseTeacherDto();
					courseTeacherDto.setCourseName(rs.getString("course_name"));
					courseList.add(courseTeacherDto);
				}
					
				
				}
			} catch (Exception e) {
				// TODO: handle exception
				
		}
		
		return courseList;
	}

	@Override
	public List<ListGradeTeacherDto> listGrade(int userId) {
		
		List<ListGradeTeacherDto> gradeList = new ArrayList<>();
		
		String sql = "SELECT courses.course_name, users.first_name, users.last_name, grades.grade1, grades.grade2, grades.result " +
				"FROM grades " +
				"INNER JOIN students ON grades.student_id = students.student_id " +
				"INNER JOIN users ON students.user_id = users.user_id " +
				"INNER JOIN teachers ON grades.teacher_id = teachers.teacher_id " +
				"INNER JOIN courses ON grades.course_id = courses.course_id " +
				"WHERE teachers.user_id = ? ";
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);) {
			
			pstmt.setInt(1, userId);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					ListGradeTeacherDto listGrade = new ListGradeTeacherDto();
					
					listGrade.setCourseName(rs.getString("course_name"));
					listGrade.setStudentFirstName(rs.getString("first_name"));
					listGrade.setStudentLastName(rs.getString("last_name"));
					listGrade.setGrade1(rs.getLong("grade1"));
					listGrade.setGrade2(rs.getLong("grade2"));
					listGrade.setResult(rs.getString("result"));
					
					gradeList.add(listGrade);
					
				}
			}
			
		} catch (Exception e) {
			 System.out.println("ERROR in listCourse: " + e.getMessage());
		      e.printStackTrace();
		  
		}
		return gradeList;
	}

}
