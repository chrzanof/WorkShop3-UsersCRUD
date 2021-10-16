<%--
  Created by IntelliJ IDEA.
  User: filip
  Date: 16.10.2021
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/add" method="post">
    Nazwa:
    <input type="text" name="name"><br>
    Email:
    <input type="text" name="email"><br>
    Hasło:
    <input type="password" name="password"><br>
    <input type="submit" name="submit" value="Dodaj">
</form>
</body>
</html>
