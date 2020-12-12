<%-- 
    Document   : admin
    Created on : Dec 3, 2020, 8:35:26 PM
    Author     : 751682
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Hello ${user.firstName} (System Admin)</h1>
        
        <h2>Manage Users</h2>
        <table>
            <tr>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Status</th>
                <th>Reactivate</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            
             <c:forEach items="${users}" var="readUser">
                <tr>
                    <td>${readUser.email}</td>  
                    <td>${readUser.firstName}</td>
                    <td>${readUser.lastName}</td>
                    <td>${readUser.active}</td>
                    <td>
                        <form method="post" action="admin">
                            <input type="hidden" name="useremail" value="${readUser.email}">
                            <input type="hidden" name="action" value="reactivate">
                            <input type="submit" value="Reactivate">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="admin">
                            <input type="hidden" name="useremail" value="${readUser.email}">
                            <input type="hidden" name="action" value="edit">
                            <input type="submit" value="Edit">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="admin">
                            <input type="hidden" name="useremail" value="${readUser.email}">
                            <input type="hidden" name="action" value="delete">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>      
                    
        <br>
        <%--if no user selected, then add new account, currently is set for regular user--%>
        <c:if test="${empty editUser}">
        <h2>Add User</h2>        
            <form action="admin" method="post">
                <input type="text" name="email" value="" placeholder="Email"> <br>
                <input type="text" name="firstName" value="" placeholder="First Name"> <br>
                <input type="text" name="lastName" value="" placeholder="Last Name"> <br>
                <input type="text" name="password" value="" placeholder="Password"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>  
    
        <br>
        <%--if account selected, then edit user's information--%>
        <c:if test="${not empty editUser}">
        <h2>Edit User</h2>       
            <form action="admin" method="post">
                <input type="text" name="email" value="${editUser.email}" placeholder="Email"> <br>
                <input type="text" name="firstName" value="${editUser.firstName}" placeholder="First Name"> <br>
                <input type="text" name="lastName" value="${editUser.lastName}" placeholder="Last Name"> <br>
                <input type="text" name="password" value="${editUser.password}" placeholder="Password"><br>
                <input type="hidden" name="originalemail" value="${editUser.email}"> <br>
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Update">
            </form>
        </c:if>  

        <p>${message}</p>
        
         <%--part for admin to manage Item's Category--%>
        <h2>Manage Category</h2>
        <table>
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
                <th>Edit</th>
            </tr>

            <c:forEach items="${categories}" var="readCategory">
                <tr>
                    <td>${readCategory.categoryId}</td>
                    <td>${readCategory.categoryName}</td>    
                        
                    <td>
                        <form method ="post" action="admin">
                            <input type="hidden" name="catID" value="${readCategory.categoryId}">
                            <input type="hidden" name="action" value="editCat">
                            <input type="submit" value="Edit">
                        </form>    
                    </td>
                </tr>
            </c:forEach>
        </table>
                                  
        <br>
        <%--if no category selected, then add new category--%>
        <c:if test="${empty editCategory}">
        <h2>Add Category</h2>       
            <form action="admin" method="post">
                <input type="text" name="catName" value="" placeholder="Category Name">   
                <input type="hidden" name="catID" value="">
                <input type="hidden" name="action" value="addCat">
                <input type="submit" value="Save">
            </form>
        </c:if>  
    
        <br>
         <%--if category selected, edit category's name--%>
        <c:if test="${not empty editCategory}">
        <h2>Edit Category</h2>      
            <form action="admin" method="post">
                <input type="hidden" name="catID" value="${editCategory.categoryId}">
                <input type="text" name="catName" value="${editCategory.categoryName}">                             
                <input type="hidden" name="action" value="updateCat">
                <input type="submit" value="Update">
            </form>
        </c:if> 
       
        <%--error message for category update--%>
        <p>${catMessage}</p> 
        <br>
        
        <%--to log out--%>
        <a href="login?logout">Log Out</a>
    </body>
</html>
