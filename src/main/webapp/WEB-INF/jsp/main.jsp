<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container-fluid">
            <c:if test="${not empty requestScope.booksList}">
                <div class="row tm-row tm-mb-60">
                    <div class="col-12">
                        <hr class="tm-hr-primary  tm-mb-55">
                    </div>
                    <c:forEach var="book" items="${requestScope.booksList}">
                        <div class="col-lg-6 tm-mb-60 tm-person-col">
                            <div class="media tm-person">
                                <a href="?command=show_book&bookId=${book.id}" class="effect-lily">
                                <img src="${book.photoUrl}" alt="Image" class="img-fluid mr-4">
                                </a>
                                <div class="">
                                    <a href="?command=show_book&bookId=${book.id}">
                                    <h2 class="tm-color-primary tm-post-title mb-2"><c:out value="${book.name}"/></h2>
                                    </a>
                                    <h3 class="tm-h3 mb-3">Genre</h3>
                                    <p class="mb-0 tm-line-height-short">
                                        <c:out value="${book.description}"/>
                                        <br>
                                        Left: <c:out value="${book.count}"/>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <div class="row tm-row tm-mt-100 tm-mb-75">
                <div class="tm-prev-next-wrapper">
                    <c:if test="${requestScope.page > 1}">
                        <a href="?command=switch_page&page=${requestScope.page - 1}" class="mb-2 tm-btn tm-btn-primary tm-prev-next tm-mr-20">Prev</a>
                    </c:if>
                    <c:if test="${requestScope.page <= 1}">
                        <a href="#" class="mb-2 tm-btn tm-btn-primary tm-prev-next disabled tm-mr-20">Prev</a>
                    </c:if>
                    <c:if test="${requestScope.page >= requestScope.lastPage}">
                        <a href="#" class="mb-2 tm-btn tm-btn-primary tm-prev-next disabled">Next</a>
                    </c:if>
                    <c:if test="${requestScope.page < requestScope.lastPage}">
                        <a href="?command=switch_page&page=${requestScope.page + 1}" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Next</a>
                    </c:if>


                </div>
                <div class="tm-paging-wrapper">
                    <span class="d-inline-block mr-3">Page</span>
                    <nav class="tm-paging-nav d-inline-block">
                        <ul>
                            <c:if test="${requestScope.page > 1}">
                                <c:if test="${requestScope.page > 2}">
                                    <li class="tm-paging-item">
                                        <a href="?command=switch_page&page=1" class="mb-2 tm-btn tm-paging-link">1</a>
                                    </li>
                                    <li class="tm-paging-item">
                                        <h1>...</h1>
                                    </li>
                                </c:if>
                                <li class="tm-paging-item">
                                    <a href="?command=switch_page&page=${requestScope.page - 1}" class="mb-2 tm-btn tm-paging-link"><c:out value="${requestScope.page - 1}"/></a>
                                </li>
                            </c:if>
                            <li class="tm-paging-item active">
                                <a href="?command=switch_page&page=${requestScope.page}" class="mb-2 tm-btn tm-paging-link"><c:out value="${requestScope.page}"/></a>
                            </li>
                            <c:if test="${requestScope.page < requestScope.lastPage}">
                                <li class="tm-paging-item">
                                    <a href="?command=switch_page&page=${requestScope.page + 1}" class="mb-2 tm-btn tm-paging-link"><c:out value="${requestScope.page + 1}"/></a>
                                </li>
                                <c:if test="${requestScope.page < requestScope.lastPage - 1}">
                                    <li class="tm-paging-item">
                                        <h1>...</h1>
                                    </li>
                                    <li class="tm-paging-item">
                                        <a href="?command=switch_page&page=${requestScope.lastPage}" class="mb-2 tm-btn tm-paging-link"><c:out value="${requestScope.lastPage}"/></a>
                                    </li>
                                </c:if>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
