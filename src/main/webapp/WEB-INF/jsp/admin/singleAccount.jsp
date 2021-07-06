<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title>${requestScope.account.name}'s <lc:lc_tag key="account"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="row tm-row">
    <div class="col-12">
        <c:if test="${not empty requestScope.account}">
            <p><lc:lc_tag key="accounts"/>: </p>
        </c:if>
        <c:if test="${empty requestScope.account}">
            <p><lc:lc_tag key="empty_accounts"/>! </p>
        </c:if>
        <c:if test="${not empty requestScope.account}">
            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable1">
                <thead>
                <tr class="tm-table-text">
                    <th><lc:lc_tag key="id"/></th>
                    <th><lc:lc_tag key="login"/></th>
                    <th><lc:lc_tag key="name"/></th>
                    <th><lc:lc_tag key="role"/></th>
                    <th><lc:lc_tag key="ticket"/></th>
                    <th><lc:lc_tag key="delete"/></th>
                </tr>
                </thead>
                <tbody class="tm-table-text">
                    <tr class="odd gradeX">
                        <td>${account.id}</td>
                        <td>${account.login}</td>
                        <td>${account.name}</td>
                        <td>
                            <form action="?command=change_role&accountId=${account.id}" method="post">
                                <p>
                                    <select class="selectpicker" name="roleId">
                                    <option disabled>${account.role}</option>
                                    <c:forEach var="role" items="${requestScope.rolesList}">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </select>
                                    <input type="submit" value="set"></p>
                            </form>
                        </td>
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
                        <td><a href="?command=delete_account&accountId=${account.id}"
                               class="mb-2 tm-btn tm-btn-primary tm-prev-next"><lc:lc_tag key="delete"/></a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
    <jsp:include page="../footer.jsp"/>
</body>
</html>
