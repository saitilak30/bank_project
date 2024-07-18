<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Close Account</title>
</head>
<body>
    <h2>Close Account</h2>
    <form action="CloseAccountServlet" method="post">
        <input type="submit" value="Close Account">
    </form>
    <%
        if(request.getParameter("error") != null) {
            out.println("<p style='color:red;'>" + request.getParameter("error") + "</p>");
        }
    %>
</body>
</html>
