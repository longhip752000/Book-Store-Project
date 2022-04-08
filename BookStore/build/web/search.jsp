 <%-- 
    Document   : search
    Created on : Jun 28, 2021, 10:38:48 PM
    Author     : longh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search Page</h1>
        <h2>Hello ${sessionScope.USER_DTO.fullName}</h2>
        <form action="DispathController" method="POST">
            Search Value <input type="text" name="txtSearchValue" value="${search_value}" />
            <input type="submit" name="btAction" value="Search"/><br/>
        </form>
        <br>
        <form action="DispathController" method="GET">
            <input type="submit" name="btAction" value="Add Account"/>
            <input type="hidden" name="txtSearchValue"  value="${search_value}"/>
            <br/><br/>              
        </form>

        <c:set var="message" value="${requestScope.MESSAGE}" />

        <c:if test="${not empty message}">
            <script>
                alert("${message}");
            </script>
        </c:if>

        <c:set var="list" value="${requestScope.LIST_SEARCH}" /> 
        <table border = "1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>User Name</th>
                    <th>Password</th>
                    <th>Full Name</th>
                    <th>Role</th>
                    <th>Action 1</th>
                    <th>Action 2</th>
                    <th>Action 3</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" varStatus="counter" var="acc">
                    <tr>
                <form action="DispathController" method="POST"> 
                    <td>
                        ${counter.count}
                    </td>
                    <td>
                        <input type="text" name="userName" value="${acc.userName}" readonly />
                    </td>
                    <td>
                        <input type="text" name="password" value="${acc.password}"/>
                    </td>
                    <td>
                        <input type="text" name="fullName" value="${acc.fullName}"/>
                    </td>
                    <td>
                        <input type="checkbox" name="role" value="admin" ${acc.role ? 'checked' : ''} />
                    </td>
                    <td>
                        <input type="submit" name="btAction" value="Update" />
                        <input type="hidden" name="txtSearchValue" value="${search_value}" />
                    </td>
                </form>
                <td>
                    <a onclick="${acc.userName eq sessionScope.USER_DTO.userName ? 'alert(\'Cannot delete yourself!\'); return false;' : 'return delConfirm()'}" href="DispathController?btAction=Delete&userName=${acc.userName}&txtSearchValue=${search_value}" >Delete</a>
                </td>
                <td>
                    <form action="DispathController" method="POST">
                        <input type="submit" name="btAction" value="Edit" />
                        <input type="hidden" name="userName" value="${acc.userName}"/>
                        <input type="hidden" name="txtSearchValue" value="${search_value}" />
                    </form>
                </td>
            </tr>    
        </c:forEach>
        <script>
            function delConfirm() {
                var result = confirm('Are you sure to delete this book?');
                if (result) {
                    return true;
                }
                return false;
            }
        </script>
    </tbody>
</table>
</body>
</html>
