<%-- 
    Document   : add
    Created on : Jun 29, 2021, 6:42:22 PM
    Author     : longh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Page</title>
    </head>
    <body>
        <h1>Add Page</h1>
        <form action="DispathController" method="POST">
            <input type="hidden" name="txtSearchValue"  value="${param.txtSearchValue}"/>
            <table border="0">
                <tbody>
                    <tr>
                        <td>
                            UserName:
                        </td>
                        <td>
                            <input type="text" name="userName" value="${param.userName}" /> 
                        </td>
                        <td>
                            <font style="color : red">${requestScope.ERROR_USERNAME}</font>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Password:
                        </td>
                        <td>
                            <input type="password" name="password" value="${param.password}" />
                        </td>
                        <td>
                            <font style="color : red">${requestScope.ERROR_PASSWORD}</font>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Retyped Password:
                        </td>
                        <td>
                            <input type="password" name="retypedPassword" value="${param.retypedPassword}" /> 
                        </td>
                        <td>
                            <font style="color : red">${requestScope.ERROR_RETYPED_PASSWORD}</font>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            FullName:
                        </td>
                        <td>
                            <input type="text" name="fullName" value="${param.fullName}" /> 
                        </td>
                        <td>
                            <font style="color : red">${requestScope.ERROR_FULLNAME}</font>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Is Admin
                        </td>
                        <td>
                            <input type="checkbox" name="role" value="admin" ${param.role ? 'checked' : ''}/>
                        </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" name="btAction" value="Store" />
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
