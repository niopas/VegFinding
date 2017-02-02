<%@page import="com.example.web.User"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<title>VegFinding</title>

<link rel="stylesheet" type="text/css" href="CSS/Profile.css">

<body>


<ul>
  <li><a class="active" href="MainPage.jsp">Veg<span>Finding</span></a></li>
  <li><a href="FindFood.jsp">Find food</a></li>
  <li><a href="AddRestaurant.jsp">Add a Restaurant</a></li>
  <li><a href="ContactUs.jsp">Contact us</a></li>
  <%User user = (User) session.getAttribute("User"); %>
  <li><a class="profile" href="Profile.jsp">Hi, <span><%=user.getName()%></span></a></li>
  <li><form action="Logout" method="post">
      <input type="submit" value="Logout" class="logout">
      </form></li>
</ul>

  <div class="body"></div>
  <div class="grad"></div>

  <div class="prof">
      <a> Username: <%=user.getName()%> <br></a>
      <a> E-mail: <%=user.getEmail()%> <br></a>
      <a> Signed up date: <%=user.getDate()%></a>
  </div>
  
</body>
</html>