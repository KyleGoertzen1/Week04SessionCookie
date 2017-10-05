<%-- 
    Document   : home
    Created on : Oct 3, 2017, 1:29:19 PM
    Author     : 729347
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Home Page</h1>
        <h2>Hello ${userLogin.username}.</h2>
        <a href=login?action=logout>Logout</a>
    </body>
</html>
