<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View orders</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="col-lg-7 tm-contact-left">
    <p>Hello, ${requestScope.user.name}! This is your books:</p>
    <c:if test="${not empty requestScope.confirmedBooks}">
        <p>Rent: </p>
        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable1">
            <thead>
            <tr>
                <th>Book</th>
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
        <p>Orders: </p>
        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable2">
            <thead>
            <tr>
                <th>Book</th>
                <th>Remove</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${requestScope.unconfirmedBooks}">
                <tr class="odd gradeX">
                    <td>${book.name}</td>
                    <td><a href="?command=remove_order&accountId=${requestScope.user.id}&bookId=${book.id}"
                           class="mb-2 tm-btn tm-btn-primary tm-prev-next">Remove</a>
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
