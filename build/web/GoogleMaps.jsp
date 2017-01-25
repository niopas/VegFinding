<%@page import="com.example.web.User"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS/GoogleMapsStyle.css">
        <title>VegFinding</title>
    </head>
    <body>
        
        <ul>
            <li><a class="active" href="MainPage.jsp">Veg<span>Finding</span></a></li>
            <li><a href="#profile">Profile</a></li>
            <li><a href="GoogleMaps.jsp">Find food</a></li>
            <li><a href="#contact">Contact us</a></li>
            <%User user = (User) session.getAttribute("User"); %>
            <li><a class="profile" href="#">Hi, <span><%=user.getName()%></span></a></li>
            <li><form action="Logout" method="post">
                <input type="submit" value="Logout" class="logout">
                </form></li>
        </ul>
  
        <div id="map"></div>
        <script>
        var gmarkers = [];
        function initMap() {
            var uluru = {lat: 39.357698, lng: 22.950571};
        
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 16,
                center: uluru
            });
            var initmarker = new google.maps.Marker({
                position: uluru,
                map: map
            });
            gmarkers.push(initmarker);
            google.maps.event.addListener(map, 'click', function(event) {
            removeMarkers();
            placeMarker(map, event.latLng);
            });
        }
        function placeMarker(map, location) {
            var marker = new google.maps.Marker({
              position: location,
              map: map
            });
            gmarkers.push(marker);
            var infowindow = new google.maps.InfoWindow({
              content: "New Place to eat"
            });
            infowindow.open(map,marker);
        }
        function removeMarkers(){
            for(i=0; i<gmarkers.length; i++){
                gmarkers[i].setMap(null);
            }
        }
        </script>
        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBi9YJTCE7PH9ROrXyeIbydF7GrHf_byNo&callback=initMap">
        </script>
        

        
        
<!--        <script>
function myMap() {
  var mapCanvas = document.getElementById("map");
  var myCenter=new google.maps.LatLng(51.508742,-0.120850);
  var mapOptions = {center: myCenter, zoom: 5};
  var map = new google.maps.Map(mapCanvas, mapOptions);
  google.maps.event.addListener(map, 'click', function(event) {
    placeMarker(map, event.latLng);
  });
}

function placeMarker(map, location) {
  var marker = new google.maps.Marker({
    position: location,
    map: map
  });
  var infowindow = new google.maps.InfoWindow({
    content: 'Latitude: ' + location.lat() + '<br>Longitude: ' + location.lng()
  });
  infowindow.open(map,marker);
}
</script>-->
        
        
        
        
        
        
        
        
        
        
        
        
    </body>
</html>