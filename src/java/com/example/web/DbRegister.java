/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import com.example.model.LoginListener;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author nick
 */
public class DbRegister extends HttpServlet {

   Connection conn=null;
    PreparedStatement pst=null;
    
    @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
         
        int SessionNum;
        
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        LoginListener loginSession=new LoginListener();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String email = request.getParameter("email");
        
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
      
      
        if (!password.equals(re_password)) {
            out.println("Passwords do not match. Try again.");
        }
        else {
            
            conn=MySqlConnect.ConnectDB();
            if (conn!= null) {
                out.println("connection established");
            }
            String sql = "INSERT INTO credentials VALUES (?,?,?,?,?)";
            try {
                pst=conn.prepareStatement(sql);
                pst.setString(1, null);
                pst.setString(2, username);
                pst.setString(3, password);
                pst.setString(4, email);
                pst.setDate(5, startDate);
                pst.execute();
                
                out.println("Register successfull");
                RequestDispatcher view = request.getRequestDispatcher("MainPage.html");
                view.forward(request, response);

            } catch (Exception e) {
                out.println(e);
            }
            
        }
         
     }

}
