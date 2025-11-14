
package com.schoolproject.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import com.schoolproject.dto.AddGradeTeacherDto;
import com.schoolproject.dto.ListCourseTeacherDto;
import com.schoolproject.dto.ListGradeTeacherDto;
import com.schoolproject.dto.ListStudentTeacherDto;
import com.schoolproject.model.DataBase;
import com.schoolproject.model.Teacher;



@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Teacher teacher;
	
	public void init() throws ServletException{
		super.init();
		
		try {
			DataSource dataSource = DataBase.getDataSource();
			
			teacher = new Teacher(dataSource);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		doPost(request, response); 
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(false);
		if(session ==null || session.getAttribute("user_id")==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		int userId = (int) session.getAttribute("user_id");
		
		try {
			
			String command = request.getParameter("command");
			
			if(command == null) {
				command ="TEACHER_LIST_COURSE";
			}
			switch(command) {
			
			case "TEACHER_LIST_COURSE": {listTeacherCourse(request,response,userId); break;}
			
			case "TEACHER_LIST_GRADE": {listTeacherGrade(request,response,userId); break; }
			
			case "TEACHER_ADD_GRADE": {addTeacherGrade(request,response,userId); break;}
			
			case "TEACHER_LOAD_GRADE":{loadGrade(request, response, userId);};break;
			
			case "TEACHER_UPDATE_GRADE":{updateGrade(request, response, userId);};break;
			
			case "LOAD_ADD_GRADE_PAGE": {loadAddGradePage(request, response, userId);break;}

			}
			
			
		} catch (Exception e) {
			   e.printStackTrace();
		        request.setAttribute("error", "İşlem sırasında hata oluştu");
		        request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	private void listTeacherCourse (HttpServletRequest request,HttpServletResponse response, int userId) throws Exception {
		
		List<ListCourseTeacherDto> courses = teacher.listCourse(userId);
		request.setAttribute("Teacher_Course_List", courses);
		request.getRequestDispatcher("teacher-list-course.jsp").forward(request, response);
		
		
		
	}
	
	private void listTeacherGrade (HttpServletRequest request,HttpServletResponse response, int userId) throws Exception{
		
		List<ListGradeTeacherDto> grades = teacher.listGrade(userId);
		request.setAttribute("Teacher_Grade_List", grades);
		request.getRequestDispatcher("teacher-list-grade.jsp").forward(request, response);
	}

	private void loadAddGradePage(HttpServletRequest request, HttpServletResponse response, int userId)
	        throws Exception {

	    String courseIdStr = request.getParameter("courseId");
	    Integer selectedCourseId = null;

	    if (courseIdStr != null && !courseIdStr.isEmpty()) {
	        selectedCourseId = Integer.parseInt(courseIdStr);
	    }

	    List<ListCourseTeacherDto> courses = teacher.listCourse(userId);
	    request.setAttribute("Teacher_Course_List", courses);

	    if (selectedCourseId != null) {
	        List<ListStudentTeacherDto> students = teacher.listStudent(userId, selectedCourseId);
	        request.setAttribute("Student_List", students);
	    }

	    List<ListGradeTeacherDto> grades = teacher.listGrade(userId);
	    request.setAttribute("Teacher_Grade_List", grades);

	    request.setAttribute("selectedCourseId", selectedCourseId);
	    request.getRequestDispatcher("teacher-add-grade.jsp").forward(request, response);
	}



	
	
	private void addTeacherGrade (HttpServletRequest request , HttpServletResponse response,int userId) throws Exception{

		    String studentIdStr = request.getParameter("studentId");
		    String courseIdStr = request.getParameter("courseId");
		    String grade1Str = request.getParameter("grade1");
		    String grade2Str = request.getParameter("grade2");
		    String result = request.getParameter("result");

		    if (studentIdStr == null || studentIdStr.isEmpty() ||
		        courseIdStr == null || courseIdStr.isEmpty() ||
		        grade1Str == null || grade1Str.isEmpty() ||
		        grade2Str == null || grade2Str.isEmpty() ||
		        result == null || result.isEmpty()) {

		        request.setAttribute("error", "Tüm alanları doldurmak zorundasın!");
		        loadAddGradePage(request, response, userId);
		        return;
		    }

		    try {
		        int studentId = Integer.parseInt(studentIdStr);
		        int courseId = Integer.parseInt(courseIdStr);
		        long grade1 = Long.parseLong(grade1Str);
		        long grade2 = Long.parseLong(grade2Str);

		        AddGradeTeacherDto dto = new AddGradeTeacherDto(studentId, courseId, grade1, grade2, result);
		        teacher.addGrade(dto, userId);

		        request.setAttribute("success", "Not başarıyla kaydedildi!");
		        listTeacherGrade(request, response, userId);

		    } catch (Exception e) {
		        request.setAttribute("error", "Notlar sadece rakam olmalı! (0-100)");
		        loadAddGradePage(request, response, userId);
		    }
		
		
	}
	
	private void loadGrade(HttpServletRequest request,HttpServletResponse response,int userId) throws Exception {
		    String gradeIdStr = request.getParameter("gradeId");

		    if (gradeIdStr == null || gradeIdStr.isEmpty()) {
		        request.setAttribute("error", "Geçersiz not seçimi!");
		        listTeacherGrade(request, response, userId);
		        return;
		    }

		    int gradeId = Integer.parseInt(gradeIdStr);

		    // Veritabanından o gradeId’ye ait notu çek
		    ListGradeTeacherDto grade = teacher.getGradeById(gradeId,userId);

		    if (grade == null) {
		        request.setAttribute("error", "Not bulunamadı!");
		        listTeacherGrade(request, response, userId);
		        return;
		    }

		    // JSP’ye gönder
		    request.setAttribute("grade", grade);
		    request.getRequestDispatcher("teacher-update-grade.jsp").forward(request, response);
	}

		
	

	private void updateGrade(HttpServletRequest request,HttpServletResponse response,int userId)throws Exception{

		    String studentIdStr = request.getParameter("studentId");
		    String courseIdStr = request.getParameter("courseId");
		    String grade1Str = request.getParameter("grade1");
		    String grade2Str = request.getParameter("grade2");
		    String result = request.getParameter("result");

		    if (studentIdStr == null || studentIdStr.isEmpty() ||
		        courseIdStr == null || courseIdStr.isEmpty() ||
		        grade1Str == null || grade1Str.isEmpty() ||
		        grade2Str == null || grade2Str.isEmpty() ||
		        result == null || result.isEmpty()) {

		        request.setAttribute("error", "Tüm alanları doldurmak zorundasın!");
		        listTeacherGrade(request, response, userId);
		        return;
		    }

		    try {
		        int studentId = Integer.parseInt(studentIdStr);
		        int courseId = Integer.parseInt(courseIdStr);
		        double grade1 = Double.parseDouble(grade1Str);
		        double grade2 = Double.parseDouble(grade2Str);

		        AddGradeTeacherDto updateDto = new AddGradeTeacherDto(studentId, courseId, grade1, grade2, result);
		        teacher.updateGrade(updateDto, userId);

		        request.setAttribute("success", "Not başarıyla güncellendi!");
		        listTeacherGrade(request, response, userId);

		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("error", "Güncelleme sırasında hata oluştu!");
		        listTeacherGrade(request, response, userId);
		    }
	}

	
}
