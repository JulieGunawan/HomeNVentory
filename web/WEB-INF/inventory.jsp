<%-- 
    Document   : user
    Created on : Dec 2, 2020, 1:39:29 AM
    Author     : 751682
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
    </head>
    <body>
        <h1>Hello ${user.firstName}</h1>
        
        <h2>Manage Inventory</h2>
        <table>
            <tr>
                <th>Category</th>
                <th>Item Name</th>
                <th>Price</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${items}" var="readItem">
                <td>${readItem.category}</td>
                
            </c:forEach>
        </table>
        
        <div>
            <c:if test="${empty editItem}">
                <h1>Add Item</h1>
                <form method="post" action="account">
                    <select name="category">
                        <c:forEach items="${items}" var="item">
                            <option value="${item.itemId}">${item.itemName}</option>
                        </c:forEach>
                    </select>
                    <input type="text" name="itemname" placeholder="Item Name"> <br>
                    <input type="text" name="price" placeholder="Price"> <br>                    
                </form>                
            </c:if>
        </div>
        <div>
            <c:if test="${not empty editItem}">
                <h1>Edit Item</h1>
                <form method="post" action="account">
                    <select name="category">
                        <c:forEach items="${items}" var="item">
                            <option value="${item.itemId}">
                                <c:if test="${editItem.itemId eq item.itemId}"> selected  </c:if> ${item.itemName}
                             </option>
                        </c:forEach>
                    </select>
                    <input type="text" name="itemname" placeholder="Item Name" value="${editItem.itemName}"> <br>
                    <input type="text" name="price" placeholder="Price" value="${editItem.price}"> <br>  
                    
                    <form method="post" action="user">
                        <input type="hidden" name="action" value="saveItem">
                        <input type=""
                    </form>
                </form>
            </c:if>
        </div>
         <br>
        <a href="login?logout">Log Out</a>
        
    </body>
</html>
