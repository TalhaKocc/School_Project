package com.schoolproject.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.schoolproject.model.DataBase;
import com.schoolproject.model.Register;
import com.schoolproject.pojo.UserBean;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Register register;   
	
	public void init() throws ServletException{
		super.init();
		
		try {
			DataSource dataSource = DataBase.getDataSource();
			
			register = new Register(dataSource);
			
		}catch (Exception e) {
			
		}
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		UserBean userBean = new UserBean(firstName,lastName,email,password,role);
	
		    boolean result = register.userRegister(userBean);

		    if (result) {
		        response.sendRedirect("login.jsp");
		    } else {
		        request.setAttribute("error", "Kayıt başarısız oldu!");
		        request.getRequestDispatcher("register.jsp").forward(request, response);
		    }
		
		
	}

}
