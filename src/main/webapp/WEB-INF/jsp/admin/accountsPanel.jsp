<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title><lc:lc_tag key="manage_accounts"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="row tm-row">
    <div class="col-12">
        <c:if test="${not empty requestScope.accountsList}">
            <p><lc:lc_tag key="accounts"/>: </p>
        </c:if>
        <c:if test="${empty requestScope.accountsList}">
            <p><lc:lc_tag key="empty_accounts"/>! </p>
        </c:if>
        <c:if test="${not empty requestScope.accountsList}">
            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable1">
                <thead>
                <tr class="tm-table-text">
                    <th><lc:lc_tag key="id"/></th>
                    <th><lc:lc_tag key="login"/></th>
                    <th><lc:lc_tag key="name"/></th>
                    <th><lc:lc_tag key="role"/></th>
                    <th><lc:lc_tag key="ticket"/></th>
                    <th><lc:lc_tag key="change"/></th>
                </tr>
                </thead>
                <tbody class="tm-table-text">
                <c:forEach var="account" items="${requestScope.accountsList}">
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
                        <td><a href="?command=single_account&accountId=${account.id}"
                               class="mb-2 tm-btn tm-btn-primary tm-prev-next"><lc:lc_tag key="change"/></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
