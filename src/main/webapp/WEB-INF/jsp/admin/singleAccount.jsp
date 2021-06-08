<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.account.name}'s account</title>
</head>
<body>
    <jsp:include page="../header.jsp"/>
    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
        <thead>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Name</th>
            <th>Role</th>
            <th>Ticket</th>
            <th>New role</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
            <tr class="odd gradeX">
                <td>${account.id}</td>
                <td>${account.login}</td>
                <td>${account.name}</td>
                <td>${account.role}</td>
                <td>
                    <c:choose>
                        <c:when test="${account.role eq 'User'}">
                            ${account.amountCurrent}/${account.amountMax}
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form action="?command=change_role&accountId=${account.id}" method="post">
                        <p><select name="roleId">
                            <option disabled>${account.role}</option>
                            <c:forEach var="role" items="${requestScope.rolesList}">
                                <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="set"></p>
                    </form>
                </td>
                <td>
                    <a href="?command=delete_account&accountId=${account.id}" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Delete</a>
                </td>
            </tr>
        </tbody>
    </table>
    <jsp:include page="../footer.jsp"/>
</body>
</html>
