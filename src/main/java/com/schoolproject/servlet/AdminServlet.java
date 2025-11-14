package com.schoolproject.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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
			case "ADMIN_LIST_TEACHER" : {listTeacher(request, response, userId);}
			
			case "ADMIN_LIST_STUDENT" : {listStudent(request, response, userId);}
			
			case "ADMIN_LIST_COURSE" : {listCourse(request, response, userId);}
			
			case "ADMIN_ADD_TEACHER" : {addTeacher(request, response, userId);}
			
			case "ADMIN_ADD_COURSE" : {addCourse(request, response, userId);}
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "İşlem sırasında hata oluştu");
	        request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	private void listTeacher(HttpServletRequest request,HttpServletResponse response,int userId) {
		
	}
	
	private void listStudent(HttpServletRequest request,HttpServletResponse response,int userId) {
		
	}
	
	private void listCourse(HttpServletRequest request,HttpServletResponse response,int userId) {
		
	}
	
	private void addTeacher(HttpServletRequest request,HttpServletResponse response,int userId) {
		
	}
	
	private void addCourse(HttpServletRequest request,HttpServletResponse response,int userId) {
		
	}
}
