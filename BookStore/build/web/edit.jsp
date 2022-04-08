<%-- 
    Document   : update
    Created on : Jun 29, 2021, 3:37:41 PM
    Author     : longh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
    </head>
    <body>
        <h1>Edit Page</h1>
        
        <c:set var="message" value="${requestScope.MESSAGE}" />

        <c:if test="${not empty message}">
            <script>
                alert("${message}");
            </script>
        </c:if>
        
        <c:set  var="acc" value="${requestScope.ACCOUNT}"/>
        <form action="DispathController" method="POST">
            User Name: <input type="text" name="userName" value="${acc.userName}" readonly/> 
            
            <br/>
            Password: <input type="text" name="password" value="${acc.password}" />
            <font style="color : red">${requestScope.ERROR_PASSWORD}</font>
            <br/>
            Full Name: <input type="text" name="fullName" value="${acc.fullName}" />
            <font style="color : red">${requestScope.ERROR_FULLNAME}</font>
            <br/>
            Is Admin: <input type="checkbox" name="role" value="admin" ${acc.role ? 'checked' : ''} /> 
            
            <br/>
            
            <input type="submit" name="btAction" value="Save" />
            <input type="hidden" name="txtSearchValue"  value="${param.txtSearchValue}"/>
        </form>
    </body>
</html>
