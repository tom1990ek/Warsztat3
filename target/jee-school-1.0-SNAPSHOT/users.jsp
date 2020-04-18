<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 18.04.2020
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Uzytkownicy</title>
</head>
<body>
<%@include file="header.jsp"%>

<table>
    <thead>
    <td>Name</td>
    <td>Actions</td>
    </thead>

    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.username}</td>
            <td><a href="/editUser">Details</a> </td>
        </tr>
    </c:forEach>
</table>

<%@include file="footer.jsp"%>
</body>
</html>
