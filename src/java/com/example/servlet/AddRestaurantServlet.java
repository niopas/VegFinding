package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet(name = "AddRestaurant", urlPatterns = { "/AddRestaurant" })
public class AddRestaurantServlet extends HttpServlet {

	static Logger logger = Logger.getLogger(RegisterServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
            String RestaurantName = request.getParameter("RestaurantName");
            String FoodOption = request.getParameter("FoodOption");
            String Description = request.getParameter("Description");
            String Rating = request.getParameter("Rating");
            String lat = request.getParameter("lat");
            String lng = request.getParameter("lng");
            String errorMsg = null;
            		
            if (lat.equals("")) {
                errorMsg = "PLease select a marker on the map.";
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddRestaurant.jsp");
                PrintWriter out= response.getWriter();
                out.println("<font color=red size=\"4\">"+errorMsg+"</font>");
                rd.include(request, response);
            }else{
		
		Connection con = null;
                    try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root", "12312312333");
                    } catch (SQLException ex) {
                            java.util.logging.Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {   
                        java.util.logging.Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }   

                
		PreparedStatement ps = null;
                float a=5;
                int b=4;
		try {
//                    ps = con.prepareStatement("INSERT INTO restaurants VALUES (?,?,?,?,?,?,?)");

                    ps = con.prepareStatement("INSERT INTO restaurants(name, foodOptions, description, rating, lat, lng) VALUES (?,?,?,?,?,?)");
                    
                    //ps.setInt(1, b);
                    ps.setString(1, RestaurantName);
                    ps.setString(2, FoodOption);
                    ps.setString(3, Description);
                    ps.setInt(4, Integer.parseInt(Rating));
                    ps.setFloat(5, Float.parseFloat(lat));
                    ps.setFloat(6, Float.parseFloat(lng));
                    ps.execute();
                    

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/FindFood.jsp");
                    PrintWriter out= response.getWriter();
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