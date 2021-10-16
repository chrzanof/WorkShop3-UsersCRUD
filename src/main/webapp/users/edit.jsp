<%--
  Created by IntelliJ IDEA.
  User: filip
  Date: 16.10.2021
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/user/edit" method="post">
        <input type="hidden" name="id" value="${user.getId()}">
        Nazwa:
        <input type="text" name="name" value="${user.getUserName()}"><br>
        Email:
        <input type="text" name="email" value="${user.getEmail()}"><br>
        Hasło:
        <input type="text" name="password" value="Hasło użytkownika"><br>
        <input type="submit" name="submit" value="Edytuj">
    </form>
</body>
</html>
