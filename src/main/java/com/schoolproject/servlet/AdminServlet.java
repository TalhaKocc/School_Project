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

import com.schoolproject.dto.ListCourseAdminDto;
import com.schoolproject.dto.ListStudentAdminDto;
import com.schoolproject.dto.ListTeacherAdminDto;
import com.schoolproject.model.Admin;
import com.schoolproject.model.DataBase;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Admin admin;
	
	public void init() throws ServletException {
		super.init();
		
		try {
			DataSource dataSource = DataBase.getDataSource();
			admin = new Admin(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("user_id")==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		int userId = (int) session.getAttribute("user_id");
		
		try {
			String command = request.getParameter("command");
			
			switch (command) {
			case "ADMIN_LIST_TEACHER" : {listTeacher(request, response); break;}
			
			case "ADMIN_LIST_STUDENT" : {listStudent(request, response, userId); break;}
			
			case "ADMIN_LIST_COURSE" : {listCourse(request, response, userId); break;}
			
			case "ADMIN_ADD_TEACHER" : {addTeacher(request, response, userId); break;}
			
			case "ADMIN_ADD_COURSE" : {addCourse(request, response, userId); break;}
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "İşlem sırasında hata oluştu");
	        request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	private void listTeacher(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<ListTeacherAdminDto> teacher = admin.listTeacher();
		request.setAttribute("Teacher_List", teacher);
		request.getRequestDispatcher("admin-list-teacher.jsp").forward(request, response);
	}
	
	private void listStudent(HttpServletRequest request,HttpServletResponse response,int userId) throws Exception {
		List<ListStudentAdminDto> student = admin.listStudent();
		request.setAttribute("Student_List", student);
		request.getRequestDispatcher("admin-list-student.jsp").forward(request, response);
	}
	
	private void listCourse(HttpServletRequest request,HttpServletResponse response,int userId)throws Exception {
		List<ListCourseAdminDto> course = admin.listCourse();
		request.setAttribute("Course_List", course);
		request.getRequestDispatcher("admin-list-course.jsp").forward(request, response);

	}
	
	private void addTeacher(HttpServletRequest request,HttpServletResponse response,int userId) {
		
	}
	
	private void addCourse(HttpServletRequest request,HttpServletResponse response,int userId) {
		
	}
}
