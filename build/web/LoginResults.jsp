<%@ page import="java.util.*" %>

<html>
    <head>
        <title>VegFinding</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
<body>
    
    <link rel="stylesheet" type="text/css" href="CSS/MainPageStyle.css">
        <h1 align="center">In Progress...</h1>
        <div align="right">

        <%
        List<String> info = (List) request.getAttribute("info");
        Iterator it = info.iterator();
        out.print("Welcome,");
        while(it.hasNext()) {
            out.print("<br>" + it.next());
       }
        int SessionNumber = (Integer)request.getAttribute("Active_Sessions");
        out.print("<br>Users loged-in: " + SessionNumber);
        %>
        </div>

</body>