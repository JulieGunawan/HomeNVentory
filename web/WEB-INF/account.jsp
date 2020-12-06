<%-- 
    Document   : account
    Created on : Dec 4, 2020, 11:31:48 AM
    Author     : 751682
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account</title>
    </head>
    <body>
        <h1>User Account</h1>
        <table>
            <c:if text="${user ne null}">
            <form method="post" action="account">
                <input type="text" name="firstName" placeholder="First Name" value="${user.firstName}}"> <br>
                <input type ="text" name="lastName" placeholder="Last Name" value="${user.lastName}"> <br>
                <input type="text" name="email" placeholder="Email" value="${user.email}"><br>
                <input type="text" name="password" placeholder="Password" value="${user.password}"><br>
                
                <form method="post" action="inventory">
                    <input type="hidden" name="action" value="update">
                    <input type="submit" value="Save"> <br>
                </form>
                
                <form method="post" action="login">
                    <input type="hidden" name="action" value="exit">
                    <input type="submit" value="Log Out"> <br>
                </form>
                
                <form method="post" action="login">
                    <input type="hidden" name="action" value="deactivate">
                    <input type="submit" value="Deactivate">
                </form>
                
            </form>
        </c:if>
        </table>
    </body>
</html>
