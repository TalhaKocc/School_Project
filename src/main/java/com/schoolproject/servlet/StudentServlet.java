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

import com.schoolproject.model.DataBase;
import com.schoolproject.model.Student;
import com.schoolproject.pojo.CourseBean;


@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Student student;
	
	public void init() throws ServletException{
		super.init();
		
		try {
			DataSource dataSource = DataBase.getDataSource();
			
			student = new Student(dataSource);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    doPost(request, response); 
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String command = request.getParameter("command");
			
			if(command == null) {
				command ="LIST_COURSE";
			}
			switch(command) {
			
			case "LIST_COURSE": {listCourse(request,response); break;}
			
			case "LIST_GRADE": {listGrade(request,response); break; }
			
			}
			
			
		} catch (Exception e) {
			   e.printStackTrace();
		        request.setAttribute("error", "İşlem sırasında hata oluştu");
		        request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	private void listCourse (HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("user_id");
		
		List<CourseBean> courses = student.listCourse(userId);
		request.setAttribute("Course_List", courses);
		request.getRequestDispatcher("student-course-list.jsp").forward(request, response);
		
		
		
	}
	
	private void listGrade (HttpServletRequest request,HttpServletResponse response) throws Exception{
		
	}
}
