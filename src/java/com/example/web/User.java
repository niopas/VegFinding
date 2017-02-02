package com.example.web;

import java.io.Serializable;
import java.util.Calendar;

public class User implements Serializable{
	
	private static final long serialVersionUID = 6297385302078200511L;
	
	private String name;
	private String email;
	private int id;
        private java.sql.Date date;
	
	public User(String nm, String em, int i, java.sql.Date startDate){
		this.name=nm;
		this.email=em;
                this.id=i;
                this.date=startDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
        public java.sql.Date getDate() {
		return date;
	}
	public int getId() {
		return id;
	}
	
	@Override
	public String toString(){
		return "Name="+this.name+", Email="+this.email;
	}
}