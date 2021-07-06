<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title><lc:lc_tag key="confirm_orders"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="row tm-row">
    <div class="col-12">
        <form action="?command=confirm_order_panel" method="POST" class="tm-mb-40 tm-add-form form-inline">
            <input class="tm-add-input form-control" name="accountId" type="text" aria-label="accountId" placeholder="<lc:lc_tag key="id"/>">
            <button class="tm-add-button" type="submit">
                <i class="fa fa-check tm-add-icon" aria-hidden="true"></i>
                <lc:lc_tag key="search"/>
            </button>
        </form>
    </div>
</div>

<div class="row tm-row">
    <div class="col-12">
        <c:if test="${not empty requestScope.account}">
            <p>${account.name}'s <lc:lc_tag key="books"/>: </p>
        </c:if>
        <c:if test="${empty requestScope.booksList}">
            <p><lc:lc_tag key="empty_books"/>! </p>
        </c:if>
        <c:if test="${not empty requestScope.booksList}">
            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable1">
                <thead>
                <tr class="tm-table-text">
                    <th><lc:lc_tag key="book"/></th>
                    <th><lc:lc_tag key="confirm"/></th>
                </tr>
                </thead>
                <tbody class="tm-table-text">
                <c:forEach var="book" items="${requestScope.booksList}">
                    <tr class="odd gradeX">
                        <td>${book.name}</td>
                        <td><a href="?command=confirm_order&accountId=${requestScope.account.id}&bookId=${book.id}"
                               class="mb-2 tm-btn tm-btn-primary tm-prev-next"><lc:lc_tag key="confirm"/></a>
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
