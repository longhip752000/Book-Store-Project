<%-- 
    Document   : shopping
    Created on : Jul 1, 2021, 8:17:05 PM
    Author     : longh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop Page</title>
    </head>
    <body>
        <h1>Shop Page</h1>
        
        <c:set var="message" value="${requestScope.MESSAGE}" />

        <c:if test="${not empty message}">
            <script>
                alert("${message}");
            </script>
        </c:if>

        <c:set var="list" value="${requestScope.LIST_SEARCH}"/>
        
        <form action="DispathController" method="GET">
            <select name="bookId">
                <c:forEach items="${list}" var="book">
                    <option value="${book.bookId}">
                       ${book.bookName}
                    </option>
                </c:forEach>
            </select>
            <button type="submit" name="btAction" value="Add to cart">Add to cart</button>
        </form>

        <form action="DispathController" method="GET">
            <button type="submit" name="btAction" value="View Cart">View Cart</button>
        </form>
    </body>
</html>
