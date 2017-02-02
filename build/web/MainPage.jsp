<%@page import="com.example.web.User"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<title>VegFinding</title>

<link rel="stylesheet" type="text/css" href="CSS/MainPageStyle.css">

<body>


<ul>
  <li><a class="active" href="MainPage.jsp">Veg<span>Finding</span></a></li>
  <li><a href="FindFood.jsp">Find food</a></li>
  <li><a href="AddRestaurant.jsp">Add a Restaurant</a></li>
  <li><a href="#contact">Contact us</a></li>
  <%User user = (User) session.getAttribute("User"); %>
  <li><a class="profile" href="#">Hi, <span><%=user.getName()%></span></a></li>
  <li><form action="Logout" method="post">
      <input type="submit" value="Logout" class="logout">
      </form></li>
</ul>
  
  
<!-- Automatic Slideshow Images -->
  <div class="img">
    <img src="CSS/images/autoimage4.jpg">
  </div>
  <div class="img">
    <img src="CSS/images/autoimage1.jpg">
  </div>
  <div class="img">
    <img src="CSS/images/autoimage3.jpg">
  </div>



<script>
// Automatic Slideshow - change image every 4 seconds
var myIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("img");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1;}    
    x[myIndex-1].style.display = "block";  
    setTimeout(carousel, 5000);    
}
</script>

</body>
</html>