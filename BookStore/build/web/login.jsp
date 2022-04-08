<%-- 
    Document   : login
    Created on : Jun 28, 2021, 10:02:47 PM
    Author     : longh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <c:set var="error_message" value="${requestScope.ERROR_MESSAGE}" />
        <c:if test="${not empty error_message}">
            <script>
                alert("${error_message}");
            </script>
        </c:if>
        <form action="DispathController" method="POST">
            UserName<input type="text" name="txtUserName" value=""/><br/>
            Password<input type="password" name="txtPassword" value=""/><br/>
            <input type="submit" value="Login" name="btAction" />
        </form>
        <a href="DispathController?btAction=Shopping">Go to shopping !!!</a>
    </body>
</html>
