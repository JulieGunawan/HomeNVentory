<%-- 
    Document   : registration
    Created on : Dec 2, 2020, 11:11:44 PM
    Author     : 751682
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>
        <h1>Welcome to Home nVentory</h1>
        <h2>Registration</h2>
        <form method="post" action="login">
            
            First Name: <input type="text" name="firstName"> <br>
            Last Name: <input type="text" name="lastName" > <br>
            Email: <input type="text" name="email" > <br>
            Password: <input type="password" name="password" > <br><br>
            
            <input type="hidden" name="action" value="register">
            <input type="submit" value="Register">  <br>
        
            <form method="post" action="login">
                <input type="hidden" name="action" value="cancel">
                <input type="submit" value="Cancel">
             </form>
        </form>
        
    </body>
</html>