<%@page import="com.example.web.User"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS/GoogleMapsStyle.css">
        <title>Restaurants</title>
    </head>
    <body>
        <div id="map"></div>
        <script>
        function initMap() {
            var uluru = {lat: 39.357698, lng: 22.950571};
        
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 18,
                center: uluru
            });
            var marker = new google.maps.Marker({
                position: uluru,
                map: map
            });
        }
        </script>
        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBi9YJTCE7PH9ROrXyeIbydF7GrHf_byNo&callback=initMap">
        </script>
    </body>
</html>