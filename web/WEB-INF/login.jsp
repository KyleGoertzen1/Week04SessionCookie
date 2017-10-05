<%-- 
    Document   : login
    Created on : Oct 2, 2017, 6:24:42 PM
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
        <h1>Login</h1>
<div>
    <form action="login" method="POST">
        Username: <input type="text" name="uname" value="${username}"><br>
        Password: <input type="password" name="pword" value="${password}"><br>
        <input type="submit" value="Login"><br>
        <input type="checkbox" name="remember"> Remember me
    </form>
</div>
${errorMessage}
<c:if test="${error}">
${logOutMessage}
</c:if>
    </body>
</html>
