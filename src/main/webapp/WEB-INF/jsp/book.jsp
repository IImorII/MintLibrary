<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title><lc:lc_tag key="book"/>: ${requestScope.book.name}</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="row tm-row">
    <div class="col-12">
        <div class="media tm-book">
            <img src="${requestScope.book.photoUrl}"
                 alt="${book.name}"
                 onerror="this.onerror=null;
                 this.src='img/mockImg.png';"
                 class="img-fluid mr-4 mb-5">
            <div class="media-body">
                <h2 class="tm-color-primary tm-post-title mb-5">${requestScope.book.name}</h2>
                <c:set var="genres" value="${fn:join(book.genresNames, ', ')}"/>
                <c:set var="authors" value="${fn:join(book.authorsNames, ', ')}"/>
                <h3 class="tm-line-text-single"><lc:lc_tag key="genres"/>: </h3>
                <p class="tm-bold-text"><c:out value="${genres}"/></p>
                <h3 class="tm-line-text-single"><lc:lc_tag key="authors"/>: </h3>
                <p class="tm-bold-text"><c:out value="${authors}"/></p>
                <h3 class="tm-line-text-single"><lc:lc_tag key="year_of_release"/>: </h3>
                <p class="tm-bold-text"><c:out value="${book.yearOfRelease}"/></p>
                <h3 class="tm-line-text-single"><lc:lc_tag key="count"/>: </h3>
                <p class="tm-bold-text"><c:out value="${book.count}"/></p>
            </div>
            <c:if test="${sessionScope.account.role eq 'User'}">
                <c:if test="${requestScope.book.count > 0}">
                    <div class="mb-4">
                        <a href="?command=order_book&bookId=${requestScope.book.id}"
                           class="mb-2 tm-btn tm-btn-primary tm-prev-next">Order</a>
                    </div>
                </c:if>
                <c:if test="${requestScope.book.count <= 0}">
                    <div class="mb-4">
                        <a href="?command=order_book&bookId=${requestScope.book.id}"
                           class="mb-2 tm-btn tm-btn-primary tm-prev-next disabled">No books</a>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.account.role eq 'Librarian'}">
                <div class="mb-4">
                    <a href="?command=delete_book&bookId=${requestScope.book.id}"
                       class="mb-2 tm-btn tm-btn-primary tm-prev-next">Delete</a>
                </div>
            </c:if>
        </div>
    </div>
</div>
<div class="row tm-row">
    <div class="col-12">
        <h3 class="tm-line-text-single  mb-3"><lc:lc_tag key="description"/>: </h3>
        <p>
            ${requestScope.book.description}
        </p>
    </div>
</div>

<div class="row tm-row">
    <div class="col-lg-8 tm-post-col">
        <div class="tm-post-full">

        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
