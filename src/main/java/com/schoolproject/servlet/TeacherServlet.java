
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
import com.schoolproject.dto.StudentCourseDto;
import com.schoolproject.dto.StudentGradeDto;
import com.schoolproject.model.DataBase;
import com.schoolproject.model.Student;
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
	    doPost(request, response); 
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		request.getRequestDispatcher("teacher-grade-list.jsp").forward(request, response);
	}

	private void addTeacherGrade (HttpServletRequest request , HttpServletResponse response,int userId) throws Exception{
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String coursName = request.getParameter("coursName"); 
		int grade1 =Integer.parseInt(request.getParameter("grade1"));
		int grade2 = Integer.parseInt(request.getParameter("grade2"));
		String result = request.getParameter("result");
		
		AddGradeTeacherDto addTeacherGradeDto = new AddGradeTeacherDto(firstName,lastName,coursName,grade1,grade2,result);
		teacher.addGrade(addTeacherGradeDto);
		
		listTeacherGrade(request, response,userId);
		
	}
	
	private void loadGrade(HttpServletRequest request,HttpServletResponse response,int userId) throws Exception {
		
		
	}

	private void updateGrade(HttpServletRequest request,HttpServletResponse response,int userId) {
		
	}
}
