package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet(name = "Register", urlPatterns = { "/Register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(RegisterServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String re_password = request.getParameter("re_password");
            String email = request.getParameter("email");
            String errorMsg = null;
            
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
		
            if (!password.equals(re_password)) {
                errorMsg = "The passwords do not match.";
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.html");
                PrintWriter out= response.getWriter();
                //out.println("<font color=red size=\"4\">"+errorMsg+"</font>");
                rd.include(request, response);
            }else{
                
		
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		try {
                    ps = con.prepareStatement("INSERT INTO credentials VALUES (?,?,?,?,?)");
                    ps.setString(1, null);
                    ps.setString(2, username);
                    ps.setString(3, password);
                    ps.setString(4, email);
                    ps.setDate(5, startDate);
                    ps.execute();

                    logger.info("User registered with username="+username);

                    //forward to login page to login
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                    PrintWriter out= response.getWriter();
                    //out.println("<font color=green size=\"4\">Registration successful, please login below.</font>");
                    rd.include(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement");
			}
		}
		}
		
	}

}