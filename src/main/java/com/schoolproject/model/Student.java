package com.schoolproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.schoolproject.dao.StudentDao;
import com.schoolproject.dto.StudentCourseDto;
import com.schoolproject.dto.StudentGradeDto;
import com.schoolproject.pojo.CourseBean;
import com.schoolproject.pojo.GradeBean;
import com.schoolproject.pojo.TeacherBean;
import com.schoolproject.pojo.UserBean;




public class Student implements StudentDao{
	private DataSource dataSource;

	public Student(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<StudentCourseDto> listCourse(int userId){
		
		List<StudentCourseDto> courseList = new ArrayList<>();
		
		String sql = "SELECT DISTINCT courses.course_name, users.first_name, users.last_name " +
						"FROM student_courses " +
						"INNER JOIN courses ON student_courses.course_id = courses.course_id " +
						"INNER JOIN teacher_courses ON courses.course_id = teacher_courses.course_id " +
						"INNER JOIN teachers ON teacher_courses.teacher_id = teachers.teacher_id " +
						"INNER JOIN users ON teachers.user_id = users.user_id " +
						"INNER JOIN students ON student_courses.student_id = students.student_id " +
						"WHERE students.user_id = ?";

		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					StudentCourseDto courseDto = new StudentCourseDto();
					courseDto.setCourseName(rs.getString("course_name"));
					courseDto.setFirstName(rs.getString("first_name"));
					courseDto.setLastName(rs.getString("last_name"));
					
					courseList.add(courseDto);
		               
				}
			
		    } 
		} catch (Exception e) {
			  System.out.println("ERROR in listCourse: " + e.getMessage());
		      e.printStackTrace();
		      
		}
		
		return courseList;

    }
	
	@Override
	public List<StudentGradeDto> listGrade(int userId) {
		
		List<StudentGradeDto> gradeList = new ArrayList<>();
		
		String sql = "SELECT courses.course_name, users.first_name, users.last_name, grades.grade1, grades.grade2, grades.result " +
            	"FROM grades " +
            	"INNER JOIN courses ON grades.course_id = courses.course_id " +
            	"INNER JOIN teachers ON grades.teacher_id = teachers.teacher_id " +
            	"INNER JOIN users ON teachers.user_id = users.user_id " +
            	"INNER JOIN students ON grades.student_id = students.student_id " +
            	"WHERE students.user_id = ?";
		
		try(Connection connection =dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, userId);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					StudentGradeDto gradeDto = new StudentGradeDto();
					
					gradeDto.setCourseName(rs.getString("course_name"));
					gradeDto.setFirstName(rs.getString("first_name"));
					gradeDto.setLastName(rs.getString("last_name"));
					gradeDto.setNote1(rs.getLong("grade1"));
					gradeDto.setNote2(rs.getLong("grade2"));
					gradeDto.setResult(rs.getString("result"));
					
					gradeList.add(gradeDto);
					
				}
			}
			
		} catch (Exception e) {
			 System.out.println("ERROR in listCourse: " + e.getMessage());
		      e.printStackTrace();
		}
		
		return gradeList;
		
	}
}	