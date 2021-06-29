<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.book.name}</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="row tm-row">
    <div class="col-12">
        <div class="media tm-book">
            <img src="${requestScope.book.photoUrl}" alt="Image" class="img-fluid mr-4">
            <div class="media-body">
                <h2 class="tm-color-primary tm-post-title mb-2">${requestScope.book.name}</h2>
                <h3 class="tm-h3 mb-3">Genres: <c:forEach var="genre" items="${requestScope.book.genresNames}"><c:out value="${genre} "/></c:forEach></h3>
                <p class="mb-0">
                    ${requestScope.book.description}
                </p>
            </div>
            <c:if test="${sessionScope.account.role eq 'User'}">
                <c:if test="${requestScope.book.count > 0}">
                    <div class="mb-4">
                        <a href="?command=order_book&bookId=${requestScope.book.id}" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Order</a>
                    </div>
                </c:if>
                <c:if test="${requestScope.book.count <= 0}">
                    <div class="mb-4">
                        <a href="?command=order_book&bookId=${requestScope.book.id}" class="mb-2 tm-btn tm-btn-primary tm-prev-next disabled">Order</a>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.account.role eq 'Librarian'}">
                <div class="mb-4">
                    <a href="?command=delete_book&bookId=${requestScope.book.id}" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Delete</a>
                </div>
            </c:if>
        </div>
    </div>

</div>

<div class="row tm-row">
    <div class="col-lg-8 tm-post-col">
        <div class="tm-post-full">

        </div>
    </div>
</div>

</body>
</html>
