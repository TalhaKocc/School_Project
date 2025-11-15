package com.schoolproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.schoolproject.dao.AdminDao;
import com.schoolproject.dto.AddCourseAdminDto;
import com.schoolproject.dto.AddTeacherAdminDto;
import com.schoolproject.dto.ListCourseAdminDto;
import com.schoolproject.dto.ListStudentAdminDto;
import com.schoolproject.dto.ListTeacherAdminDto;

public class Admin implements AdminDao {
	private DataSource dataSource;

	public Admin(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<ListTeacherAdminDto> listTeacher() {
		List<ListTeacherAdminDto> teacherList = new ArrayList<>();
		
		String sql = "SELECT users.first_name,users.last_name,teachers.salary "+
				 "FROM teachers " +
				 "INNER JOIN users ON teachers.user_id = users.user_id ";
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {

			
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()){
					ListTeacherAdminDto listTeacherDto = new ListTeacherAdminDto();
					listTeacherDto.setName(rs.getString("first_name"));
					listTeacherDto.setSurname(rs.getString("last_name"));
					listTeacherDto.setSalary(rs.getDouble("salary"));
					teacherList.add(listTeacherDto);

				}
			}
			
		} catch (Exception e) {
			System.out.println("ERROR in listCourse: " + e.getMessage());
		      e.printStackTrace();
		}
		
		return teacherList;
	}

	@Override
	public List<ListStudentAdminDto> listStudent() {
		List<ListStudentAdminDto> studentList = new ArrayList<>();
		
		String sql = "SELECT users.first_name,users.last_name,students.student_no "+
				  "FROM students "+
				  "INNER JOIN users ON students.user_id = users.user_id";
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {

				
				try(ResultSet rs = pstmt.executeQuery()){
					while(rs.next()){
						ListStudentAdminDto listStudentDto = new ListStudentAdminDto();
						listStudentDto.setName(rs.getString("first_name"));
						listStudentDto.setSurname(rs.getString("last_name"));
						listStudentDto.setNo(rs.getLong("student_no"));
						studentList.add(listStudentDto);

					}
				}
				
			} catch (Exception e) {
				System.out.println("ERROR in listCourse: " + e.getMessage());
			      e.printStackTrace();
			}

		
		return studentList;
	}

	@Override
	public List<ListCourseAdminDto> listCourse() {
		List<ListCourseAdminDto> courseList = new ArrayList<>();
		
		String sql = "SELECT courses.course_id,courses.course_name FROM courses";

		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {

				
				try(ResultSet rs = pstmt.executeQuery()){
					while(rs.next()){
						ListCourseAdminDto listCourseDto = new ListCourseAdminDto();
						listCourseDto.setCourseId(rs.getInt("course_id"));
						listCourseDto.setCourseName(rs.getString("course_name"));
						courseList.add(listCourseDto);
					}
				}
				
			} catch (Exception e) {
				System.out.println("ERROR in listCourse: " + e.getMessage());
			      e.printStackTrace();
			}
		
		return courseList;
	}

	@Override
	public void addTeacher(AddTeacherAdminDto addTeacher) {
		String userSql= "INSERT INTO users (first_name, last_name, email, password, role) " +
						"VALUES (?, ?, ?, ?, 'Teacher')";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(userSql,Statement.RETURN_GENERATED_KEYS)) {			
	    		pstmt.setString(1, addTeacher.getName());
	    		pstmt.setString(2, addTeacher.getSurname());
	    		pstmt.setString(3, addTeacher.getEmail());
	    		pstmt.setString(4, addTeacher.getPassword());
	    		pstmt.executeUpdate();
	    		
	    		ResultSet rs = pstmt.getGeneratedKeys();
	    		int userId = 0;
	    		if(rs.next()) {
	    			userId = rs.getInt(1);
	    		}
				
	    		rs.close();
	    		
	    		String teacherSql = "INSERT INTO teachers (salary, user_id) VALUES (?, ?)";
	    		try(PreparedStatement stmt = connection.prepareStatement(teacherSql)){
	    			stmt.setString(1, addTeacher.getSalary());
	    			stmt.setInt(2, userId);
	    			
	    			stmt.executeUpdate();
	    		}
	    		
	    		
			} catch (Exception e) {
				e.printStackTrace();
	            
			}
		
	}

	@Override
	public void addCourse(AddCourseAdminDto addCourse) {
		String sql = "INSERT INTO courses (course_name) VALUES (?)";
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)){
			
			pstmt.setString(1,addCourse.getCourseName());
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
