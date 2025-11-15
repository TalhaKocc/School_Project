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
import com.schoolproject.dto.ListStudentTeacherDto;

public class Teacher implements TeacherDao{
	
	private DataSource dataSource;
	
	public Teacher(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void updateGrade(AddGradeTeacherDto updateGradeDto, int userId) {
		String sqlTeacher = "SELECT teacher_id FROM teachers WHERE user_id = ?";
	    int teacherId = 0;

	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sqlTeacher)) {

	        ps.setInt(1, userId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                teacherId = rs.getInt("teacher_id");
	            } else {
	                throw new Exception("Teacher not found!");
	            }
	        }

	        String sqlUpdate = "UPDATE grades SET grade1 = ?, grade2 = ?, result = ? " +
	                           "WHERE student_id = ? AND course_id = ? AND teacher_id = ?";
	        try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
	            pstmt.setDouble(1, updateGradeDto.getGrade1());
	            pstmt.setDouble(2, updateGradeDto.getGrade2());
	            pstmt.setString(3, updateGradeDto.getResult());
	            pstmt.setInt(4, updateGradeDto.getStudentId());
	            pstmt.setInt(5, updateGradeDto.getCourseId());
	            pstmt.setInt(6, teacherId);

	            int rows = pstmt.executeUpdate();
	            if (rows == 0) {
	                System.out.println("Uyarı: Güncellenecek kayıt bulunamadı!");
	            } else {
	                System.out.println("Not başarıyla güncellendi.");
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void addGrade(AddGradeTeacherDto addGradeTeacherDto, int userId) {
	    String sqlTeacher = "SELECT teacher_id FROM teachers WHERE user_id = ?";
	    int teacherId = 0;
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sqlTeacher)) {

	        ps.setInt(1, userId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if(rs.next()) {
	                teacherId = rs.getInt("teacher_id");
	            } else {
	                throw new Exception("Teacher not found!");
	            }
	        }

	        String sql = "INSERT INTO grades (student_id, teacher_id, course_id, grade1, grade2, result) " +
	                     "VALUES (?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setInt(1, addGradeTeacherDto.getStudentId());
	            pstmt.setInt(2, teacherId);
	            pstmt.setInt(3, addGradeTeacherDto.getCourseId());
	            pstmt.setDouble(4, addGradeTeacherDto.getGrade1());
	            pstmt.setDouble(5, addGradeTeacherDto.getGrade2());
	            pstmt.setString(6, addGradeTeacherDto.getResult());

	            pstmt.executeUpdate();
	        }

	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	}

	
	@Override
	public List<ListCourseTeacherDto> listCourse(int userId) {
		
		List<ListCourseTeacherDto> courseList = new ArrayList<>();
		
		String sql = "SELECT DISTINCT courses.course_id,courses.course_name " +
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
					courseTeacherDto.setCourseId(rs.getInt("course_id"));
					courseTeacherDto.setCourseName(rs.getString("course_name"));
					courseList.add(courseTeacherDto);
				}
					
				
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		
		return courseList;
	}

	@Override
	public List<ListGradeTeacherDto> listGrade(int userId) {
	    List<ListGradeTeacherDto> gradeList = new ArrayList<>();
	    String sql = "SELECT grades.grade_id,grades.student_id ,grades.course_id ,courses.course_name, users.first_name, users.last_name, grades.grade1, grades.grade2, grades.result " +
	                 "FROM grades " +
	                 "INNER JOIN students ON grades.student_id = students.student_id " +
	                 "INNER JOIN users ON students.user_id = users.user_id " +
	                 "INNER JOIN teachers ON grades.teacher_id = teachers.teacher_id " +
	                 "INNER JOIN courses ON grades.course_id = courses.course_id " +
	                 "WHERE teachers.user_id = ?";
	    try(Connection connection = dataSource.getConnection();
	        PreparedStatement pstmt = connection.prepareStatement(sql);) {
	        pstmt.setInt(1, userId);
	        try(ResultSet rs = pstmt.executeQuery()){
	            while(rs.next()) {
	                ListGradeTeacherDto grade = new ListGradeTeacherDto();
	                grade.setGradeId(rs.getInt("grade_id")); // burası önemli
	                grade.setStudentId(rs.getInt("student_id"));     // EKLENDİ
	                grade.setCourseId(rs.getInt("course_id"));
	                grade.setCourseName(rs.getString("course_name"));
	                grade.setStudentFirstName(rs.getString("first_name"));
	                grade.setStudentLastName(rs.getString("last_name"));
	                grade.setGrade1(rs.getDouble("grade1"));
	                grade.setGrade2(rs.getDouble("grade2"));
	                grade.setResult(rs.getString("result"));
	                gradeList.add(grade);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return gradeList;
	}



	@Override
	public List<ListStudentTeacherDto> listStudent(int userId, int courseId) {
	    List<ListStudentTeacherDto> listStudent = new ArrayList<>();
	    
	    String sql = "SELECT DISTINCT students.student_id, users.first_name, users.last_name " +
	                 "FROM students " +
	                 "JOIN users ON students.user_id = users.user_id " +
	                 "JOIN student_courses ON students.student_id = student_courses.student_id " +
	                 "JOIN teacher_courses ON student_courses.course_id = teacher_courses.course_id " +
	                 "JOIN teachers ON teacher_courses.teacher_id = teachers.teacher_id " +
	                 "JOIN users AS teacher_users ON teachers.user_id = teacher_users.user_id " +
	                 "WHERE teacher_users.user_id = ? AND student_courses.course_id = ?";
	    
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        
	        pstmt.setInt(1, userId);
	        pstmt.setInt(2, courseId);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                ListStudentTeacherDto student = new ListStudentTeacherDto();
	                student.setStudentId(rs.getInt("student_id"));
	                student.setFirstName(rs.getString("first_name"));
	                student.setLastName(rs.getString("last_name"));
	                listStudent.add(student);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return listStudent;
	}

	public ListGradeTeacherDto getGradeById(int gradeId, int userId) {
	    ListGradeTeacherDto grade = null;
	    String sql = "SELECT grades.grade_id, grades.student_id, grades.course_id, " +
	                 "courses.course_name, users.first_name, users.last_name, " +
	                 "grades.grade1, grades.grade2, grades.result " +
	                 "FROM grades " +
	                 "INNER JOIN students ON grades.student_id = students.student_id " +
	                 "INNER JOIN users ON students.user_id = users.user_id " +
	                 "INNER JOIN courses ON grades.course_id = courses.course_id " +
	                 "INNER JOIN teachers ON grades.teacher_id = teachers.teacher_id " +
	                 "WHERE grades.grade_id = ? AND teachers.user_id = ?";

	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        pstmt.setInt(1, gradeId);
	        pstmt.setInt(2, userId);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                grade = new ListGradeTeacherDto(); 

	                grade.setGradeId(rs.getInt("grade_id"));
	                grade.setStudentId(rs.getInt("student_id"));     
	                grade.setCourseId(rs.getInt("course_id"));       
	                grade.setCourseName(rs.getString("course_name"));
	                grade.setStudentFirstName(rs.getString("first_name"));
	                grade.setStudentLastName(rs.getString("last_name"));
	                grade.setGrade1(rs.getDouble("grade1"));
	                grade.setGrade2(rs.getDouble("grade2"));
	                grade.setResult(rs.getString("result"));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return grade;
	}


}
