package com.schoolproject.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import com.schoolproject.model.DataBase;
import com.schoolproject.model.Login;
import com.schoolproject.pojo.UserBean;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Login login;
    
	// @Resource(name = "jdbc/web_"); eğer ki content.xml sayfası oluştursaydım bunu kullanacaktım name değişkenlik gösterebilir 
	// ben zaten pool için bir sınıf oluşturdum o yüzden resource gerek yok 
	

	public void init() throws ServletException{
		super.init();
		
		try {
			DataSource dataSource = DataBase.getDataSource();
			
			login = new Login(dataSource);
			
		}catch (Exception e) {
			throw new ServletException("Login servlet initialization failed", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email    = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserBean userBean = new UserBean();
		userBean.setEmail(email);
		userBean.setPassword(password);
		
		UserBean loggedUser = login.userLogin(userBean);
		
		if(loggedUser!=null) {
			
			HttpSession session = request.getSession();
			
			session.setAttribute("currentUser", loggedUser);
			session.setAttribute("user_id", loggedUser.getId());
			
			String role = loggedUser.getRole();
			
			if("Admin".equalsIgnoreCase(role)) {
				response.sendRedirect("admin-dashboard");
			} else if ("Teacher".equalsIgnoreCase(role)) {
				response.sendRedirect("teacher-dashboard.jsp");
			}else if ("Student".equalsIgnoreCase(role)) {
				response.sendRedirect("student-dashboard.jsp");
			}else {
				response.sendRedirect("index.jsp");
			}
		
		}else {
			request.setAttribute("errorMessage", "Eposta veya şifre hatalı");
			RequestDispatcher dispatcher = request.getRequestDispatcher("deneme.jsp");
			dispatcher.forward(request, response);
		}
		
    }
	
}
