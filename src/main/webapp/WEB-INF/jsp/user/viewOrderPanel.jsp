<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title><lc:lc_tag key="user_panel"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="row tm-row">
    <div class="col-12">
        <c:if test="${not empty sessionScope.account}">
            <p>${account.name}'s <lc:lc_tag key="books"/>: </p>
        </c:if>
        <c:if test="${empty requestScope.confirmedBooks and empty requestScope.unconfirmedBooks}">
            <p><lc:lc_tag key="empty_books"/>! </p>
        </c:if>
        <c:if test="${not empty requestScope.unconfirmedBooks}">
            <p><lc:lc_tag key="ordered"/>:</p>
            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable1">
                <thead>
                <tr class="tm-table-text">
                    <th><lc:lc_tag key="book"/></th>
                    <th><lc:lc_tag key="confirm"/></th>
                </tr>
                </thead>
                <tbody class="tm-table-text">
                <c:forEach var="book" items="${requestScope.unconfirmedBooks}">
                    <tr class="odd gradeX">
                        <td>${book.name}</td>
                        <td><a href="?command=remove_order&bookId=${book.id}"
                               class="mb-2 tm-btn tm-btn-primary tm-prev-next"><lc:lc_tag key="delete"/></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${not empty requestScope.confirmedBooks}">
            <p><lc:lc_tag key="rented"/>:</p>
            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable1">
                <thead>
                <tr class="tm-table-text">
                    <th><lc:lc_tag key="book"/></th>
                </tr>
                </thead>
                <tbody class="tm-table-text">
                <c:forEach var="book" items="${requestScope.confirmedBooks}">
                    <tr class="odd gradeX">
                        <td>${book.name}</td>
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
