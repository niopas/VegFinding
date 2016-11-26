/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author nick
 */

public class LoginListener implements HttpSessionListener {

    private static int SessionNum;
    

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        SessionNum++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        SessionNum--;
    }
    public int getSessionNum() {
        return(SessionNum);
    }
}
