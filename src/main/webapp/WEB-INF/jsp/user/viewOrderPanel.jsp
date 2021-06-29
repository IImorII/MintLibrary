<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><lc:lc_tag key="user_panel"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="col-lg-7 tm-contact-left">
    <c:if test="${(not empty requestScope.confirmedBooks) or (not empty requestScope.unconfirmedBooks)}">
        <p><lc:lc_tag key="hello"/>, ${sessionScope.account.name}! </p>
        <p><lc:lc_tag key="this_is_your_books"/>: </p>
    </c:if>
    <c:if test="${(empty requestScope.confirmedBooks) and (empty requestScope.unconfirmedBooks)}">
        <p><lc:lc_tag key="empty_ticket"/>! </p>
    </c:if>
    <c:if test="${not empty requestScope.confirmedBooks}">
        <p><lc:lc_tag key="rented"/>: </p>
        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable1">
            <thead>
            <tr>
                <th><lc:lc_tag key="book"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${requestScope.confirmedBooks}">
                <tr class="odd gradeX">
                    <td>${book.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${not empty requestScope.unconfirmedBooks}">
        <p><lc:lc_tag key="ordered"/>: </p>
        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable2">
            <thead>
            <tr>
                <th><lc:lc_tag key="book"/></th>
                <th><lc:lc_tag key="delete"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${requestScope.unconfirmedBooks}">
                <tr class="odd gradeX">
                    <td>${book.name}</td>
                    <td><a href="?command=remove_order&accountId=${sessionScope.user.id}&bookId=${book.id}"
                           class="mb-2 tm-btn tm-btn-primary tm-prev-next"><lc:lc_tag key="delete"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>
