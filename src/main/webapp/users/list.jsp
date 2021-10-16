<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${users}" var="user">
    <tr>
        <td>${user.getId()} ${user.getUserName()} ${user.getEmail()}
            <a href="/user/delete?id=${user.getId()}">Usuń</a>
            <a href="/user/edit?id=${user.getId()}">Edit</a>
            <a href="/user/show?id=${user.getId()}">Pokaż</a></td><br>
    </tr>
</c:forEach>
<a href="/user/add">Dodaj</a>
</body>
</html>
