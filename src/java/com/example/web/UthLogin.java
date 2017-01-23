/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import com.example.model.UthLdap;
import com.example.model.LoginListener;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UthLogin extends HttpServlet {
    
     @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
         
        int SessionNum;
         
        response.setContentType("text/html");
        LoginListener loginSession=new LoginListener();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UthLdap login=new UthLdap(username,password);
        PrintWriter out=response.getWriter();
        List<String> info = new ArrayList<String>();
        if(login.auth()){
            info.add(login.getName());
            info.add(login.getMail());
            info.add(login.getBirthYear());
            request.setAttribute("info",info);
            SessionNum=loginSession.getSessionNum();
            request.setAttribute("Active_Sessions",SessionNum);
            RequestDispatcher view = request.getRequestDispatcher("LoginResults.jsp");
            view.forward(request,response);
         }
        else {
             //out.println(username + password + "incorrect");
             //out.println("Wrong username and password. Try again.");
         }
         
     }
        
}