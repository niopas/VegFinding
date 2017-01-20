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
import javax.servlet.RequestDispatcher;

/**
 *
 * @author nick
 */
public class DbLogin extends HttpServlet {
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    
    @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
         
        int SessionNum;
        
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        LoginListener loginSession=new LoginListener();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        conn=MySqlConnect.ConnectDB();
        if (conn!= null) {
            out.println("connection established");
        }
        String sql = "SELECT * FROM credentials WHERE username=? AND password=?";
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                out.println("Good");
                RequestDispatcher view = request.getRequestDispatcher("MainPage.html");
                view.forward(request, response);
            }
            else {
                out.println("Wrong username and password. Try again.");
            }
        } catch (Exception e) {
            out.println(e);
        }
     }
}
