<%--
  Created by IntelliJ IDEA.
  User: ltx7
  Date: 02.06.2021
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accounts panel</title>
</head>
<body>
<table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
    <thead>
    <tr>
        <th>Login</th>
        <th>Name</th>
        <th>Role</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="account" items="${requestScope.accountsList}">
        <tr class="odd gradeX">
            <td>${account.login}</td>
            <td>${account.name}</td>
            <td>${account.role}</td>
            <td><a href="?command=delete_account&userId=${account.id}" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
