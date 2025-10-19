package com.schoolproject.servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.schoolproject.pojo.UserBean;
import com.schoolproject.model.Login;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private Login loginDao; 

    public void init() {
        loginDao = new Login();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserBean loginBean = new UserBean();
        loginBean.setEmail(email); 
        loginBean.setPassword(password);

        try {
            
            UserBean user = loginDao.userLogin(loginBean); 

            if (user != null) {
         
                HttpSession session = request.getSession();
                
                // Bütün Kullanıcı nesnesini oturuma kaydet (ID, İsim, Rol vb. içerir)
                session.setAttribute("currentUser", user); 
                
                // 2. ADIM: ROL KONTROLÜ VE YÖNLENDİRME
                String role = user.getRole();

                if ("Student".equals(role)) {
                    response.sendRedirect("StudentDashboard.jsp");
                } else if ("Teacher".equals(role)) {
                    response.sendRedirect("TeacherDashboard.jsp");
                } else {
                    response.sendRedirect("default/dashboard.jsp"); 
                }

            } else {
             
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", "Geçersiz E-posta veya Şifre.");
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); 
        }
    }
}