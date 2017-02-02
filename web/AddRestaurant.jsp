<%@page import="com.example.web.User"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS/AddingFormStyle.css">
        <title>VegFinding</title>
    </head>
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
            
        <form class="AddRestaurant" action="AddRestaurant" method="post">
            <div class="body"></div>
            <div class="grad"></div>
            <br>
            <div class="header">
		<div>Add new Veg<span>Option</span></div>
            </div><br>
            <div class="entry">
                <input type="text" placeholder="Restaurant name" name="RestaurantName" required><br>
                <input type="text" placeholder="Food option" name="FoodOption" required><br>
                <textarea rows="3" cols="50" name="Description" placeholder="Describe the food option..."></textarea><br>
                <input type="number" placeholder="Rating (1-5)" name="Rating" min="1" max="5"><br>
                <input type="hidden" id="latbox" name="lat">
                <input type="hidden" id="lngbox" name="lng">
                <input type="submit" value="Add Restaurant"><br>

            </div>
        </form>

        <div id="map"></div>
        <script>
        var gmarkers = [];
        function initMap() {
            var uluru = {lat: 39.357698, lng: 22.950571};
        
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 16,
                center: uluru
            });
            google.maps.event.addListener(map, 'click', function(event) {
            removeMarkers();
            placeMarker(map, event.latLng);
            
            var clickLat = event.latLng.lat();
            var clickLon = event.latLng.lng();
            
            document.getElementById("latbox").value = clickLat.toFixed(5);
            document.getElementById("lngbox").value = clickLon.toFixed(5);
            });
        }
        function placeMarker(map, location) {
            var marker = new google.maps.Marker({
              position: location,
              map: map
            });
            gmarkers.push(marker)
        }
        function removeMarkers(){
            for(i=0; i<gmarkers.length; i++){
                gmarkers[i].setMap(null);
                gmarkers = [];
            }
        }
        </script>
        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBi9YJTCE7PH9ROrXyeIbydF7GrHf_byNo&callback=initMap">
        </script>

    </body>
</html>