<%@page import="com.example.web.User"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,javax.servlet.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>



<% Class.forName("com.mysql.jdbc.Driver"); %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS/GoogleMapsStyle.css">
        <title>VegFinding</title>
    </head>
    <body>
        
        <% 
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/userdb", "root", "12312312333");
            
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            ps = con.prepareStatement("select * from restaurants");
            rs = ps.executeQuery();
            
        List<String> RestaurantNames = new ArrayList<String>();
        List<String> foodOptions = new ArrayList<String>();
        List<String> descriptions = new ArrayList<String>();
        List<String> ratings = new ArrayList<String>();
        List<String> latArray = new ArrayList<String>();
        List<String> lngArray = new ArrayList<String>();

        %>
        <%  String val = "";
            while(rs.next()){ %>
        <%
            RestaurantNames.add(rs.getString(1));
            foodOptions.add(rs.getString(2));
            descriptions.add(rs.getString(3));
            ratings.add(rs.getString(4));
            latArray.add(rs.getString(5));
            lngArray.add(rs.getString(6));
         }
        String names = RestaurantNames.toString();
        String options = foodOptions.toString();
        String dscrs = descriptions.toString();
        String rtns = ratings.toString();
        String lats = latArray.toString();
        String lngs = lngArray.toString();
        %>

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
  
        <div id="map"></div>
        <script>
        var latLng;
        var dbmarker;
        var namesdb="<%=names%>";
        var optionsdb="<%=options%>";
        var dscrsdb="<%=dscrs%>";
        var rtnsdb="<%=rtns%>";
        var latdb="<%=lats%>";
        var lngdb="<%=lngs%>";
        var splinamest;
        var splitoptions;
        var splitdscrs;
        var splitrtns;
        var splitlat;
        var splitlng;
        var message;
        
        
        function initMap() {
            var uluru = {lat: 39.357698, lng: 22.950571};
        
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 16,
                center: uluru
            });
            
            namesdb = namesdb.slice(1, -1);
            optionsdb = optionsdb.slice(1, -1);
            dscrsdb = dscrsdb.slice(1, -1);
            rtnsdb = rtnsdb.slice(1, -1);
            latdb = latdb.slice(1, -1);
            lngdb = lngdb.slice(1, -1);


            splitnames = namesdb.split(", ");
            splitoptions = optionsdb.split(", ");
            splitdscrs = dscrsdb.split(", ");
            splitrtns = rtnsdb.split(", ");
            splitlat = latdb.split(", ");
            splitlng = lngdb.split(", ");
            
            for (var i=0; i<splitlat.length; i++) {
                latLng = {lat: parseFloat(splitlat[i]), lng: parseFloat(splitlng[i])};
                message = '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h1 id="firstHeading" class="firstHeading">' + splitnames[i] + '</h1>'+
            '<div id="bodyContent">'+
            '<p>Food option: <b>' + splitoptions[i] + '</b><br>' +
            'Description : ' + splitdscrs[i] + '<br>' +
            'Rating : ' + splitrtns[i] + '/5</p>' +
            '</div>'+
            '</div>';

            placeMarker(map, latLng, message);
            }
        }
        function placeMarker(map, location, message) {
            var marker = new google.maps.Marker({
              position: location,
              map: map
            });
            var infowindow = new google.maps.InfoWindow({
              content: message
            });
            marker.addListener('click', function() {
                infowindow.open(map, marker);
            });
        }
        </script>
        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBi9YJTCE7PH9ROrXyeIbydF7GrHf_byNo&callback=initMap">
        </script>
    </body>
</html>