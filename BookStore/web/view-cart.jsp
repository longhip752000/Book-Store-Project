<%-- 
    Document   : view-cart
    Created on : Jul 3, 2021, 9:27:09 PM
    Author     : GF65
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Your cart!</h1>
        <c:set var="list" value="${requestScope.LIST_BOOKS}" />

        <c:choose>
            <c:when test="${empty list}">
                <h3>No items in your cart</h3>
            </c:when>
            <c:otherwise>
                <form action="DispathController" method="POST">
                    <table border="1" style="text-align: center">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${list}" var="book" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${book.bookName}</td>
                                    <td>${book.quantity}</td>
                                    <td>${book.price * book.quantity}</td>
                                    <td>
                                        <input name="removeBookIds" type="checkbox" value="${book.bookId}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="4">
                                    <a href="DispathController?btAction=Shopping">Back for shopping</a>
                                </td>
                                <td>
                                    <button type="submit" title="tick to remove" name="btAction" value="Remove">Remove</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <form action="DispathController" method="POST">
                    <button type="submit" name="btAction" value="Checkout">Checkout Now</button>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>
