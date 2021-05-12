<%--
  Created by IntelliJ IDEA.
  User: ltx7
  Date: 03.05.2021
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <form method="post" action="?command=submit_login">
        <input name="login">
        <input type="password" name="password">
        <input type="submit" value="submit">
    </form>
</body>
</html>
