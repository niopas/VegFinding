/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import java.io.PrintWriter;
import java.sql.*;

/**
 *
 * @author nick
 */
public class MySqlConnect {
    Connection conn=null;
    
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "12312312333"); //XUpa0m0R3x
            return conn;
        } catch (Exception e) {
            return null;
        }
    }
}
