/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.servlet;

import com.example.web.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(LoginServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String errorMsg = null;
                
		if(username == null || username.equals("")){
			errorMsg ="User Email can't be null or empty";
		}
                
		if(password == null || password.equals("")){
			errorMsg = "Password can't be null or empty";
		}
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
			PrintWriter out= response.getWriter();
			//out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                if (con == null) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root", "12312312333");
                    } catch (SQLException ex) {
                            java.util.logging.Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {   
                        java.util.logging.Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                }

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select id, username, email from credentials where username=? and password=? limit 1");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs != null && rs.next()){
				
                            User user = new User(rs.getString("username"), rs.getString("email"), rs.getInt("id"));
                            logger.info("User found with details="+user);
                            HttpSession session = request.getSession();
                            session.setAttribute("User", user);
                            response.sendRedirect("MainPage.jsp");
			}else{
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                            PrintWriter out= response.getWriter();
                            logger.error("User not found with username="+username);
                            //out.println("<font color=red>No user found with given email id, please register first.</font>");
                            rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");;
			}
			
		}
		}
	}

}