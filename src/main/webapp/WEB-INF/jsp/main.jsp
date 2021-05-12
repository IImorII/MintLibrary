<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container-fluid">
        <main class="tm-main">
            <!-- Search form -->
            <div class="row tm-row">
                <div class="col-12">
                    <form method="GET" class="form-inline tm-mb-80 tm-search-form">
                        <input class="form-control tm-search-input" name="search" type="text" placeholder="Search..." aria-label="Search">
                        <button class="tm-search-button" type="submit">
                            <i class="fas fa-search tm-search-icon" aria-hidden="true"></i>
                        </button>
                    </form>
                </div>
            </div>
            <c:if test="${not empty requestScope.booksList}">
                <div class="row tm-row">
                    <c:forEach var="book" items="${requestScope.booksList}">
                    <article class="col-12 col-md-6 tm-post">
                        <hr class="tm-hr-primary">
                        <h2 class="tm-pt-30 tm-color-primary tm-post-title"><c:out value="${book.name}"/></h2>
                        <a href="post.html" class="effect-lily tm-post-link tm-pt-60">
                            <div class="tm-post-link-inner">
                                <img src="${book.photoUrl}" alt="Image" class="img-fluid">
                            </div>
                            <span class="position-absolute tm-new-badge">New</span>
                        </a>
                        <p class="tm-pt-30">
                            <c:out value="${book.description}"/>
                        </p>
                    </article>
                    </c:forEach>
                </div>
            </c:if>
            <div class="row tm-row tm-mt-100 tm-mb-75">
                <div class="tm-prev-next-wrapper">
                    <c:if test="${requestScope.page > 1}">
                        <a href="?page=${requestScope.page - 1}" class="mb-2 tm-btn tm-btn-primary tm-prev-next tm-mr-20">Prev</a>
                    </c:if>
                    <c:if test="${requestScope.page <= 1}">
                        <a href="#" class="mb-2 tm-btn tm-btn-primary tm-prev-next disabled tm-mr-20">Prev</a>
                    </c:if>
                    <c:if test="${requestScope.page >= requestScope.lastPage}">
                        <a href="#" class="mb-2 tm-btn tm-btn-primary tm-prev-next disabled">Next</a>
                    </c:if>
                    <c:if test="${requestScope.page < requestScope.lastPage}">
                        <a href="?page=${requestScope.page + 1}" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Next</a>
                    </c:if>


                </div>
                <div class="tm-paging-wrapper">
                    <span class="d-inline-block mr-3">Page</span>
                    <nav class="tm-paging-nav d-inline-block">
                        <ul>
                            <c:if test="${requestScope.page > 1}">
                                <c:if test="${requestScope.page > 2}">
                                    <li class="tm-paging-item">
                                        <a href="?page=1" class="mb-2 tm-btn tm-paging-link">1</a>
                                    </li>
                                    <li class="tm-paging-item">
                                        <h1>...</h1>
                                    </li>
                                </c:if>
                                <li class="tm-paging-item">
                                    <a href="?page=${requestScope.page - 1}" class="mb-2 tm-btn tm-paging-link">${requestScope.page - 1}</a>
                                </li>
                            </c:if>
                            <li class="tm-paging-item active">
                                <a href="?page=${requestScope.page}" class="mb-2 tm-btn tm-paging-link">${requestScope.page}</a>
                            </li>
                            <c:if test="${requestScope.page < requestScope.lastPage}">
                                <li class="tm-paging-item">
                                    <a href="?page=${requestScope.page + 1}" class="mb-2 tm-btn tm-paging-link">${requestScope.page + 1}</a>
                                </li>
                                <c:if test="${requestScope.page < requestScope.lastPage - 1}">
                                    <li class="tm-paging-item">
                                        <h1>...</h1>
                                    </li>
                                    <li class="tm-paging-item">
                                        <a href="?page=${requestScope.lastPage}" class="mb-2 tm-btn tm-paging-link">${requestScope.lastPage}</a>
                                    </li>
                                </c:if>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
            <footer class="row tm-row">
                <hr class="col-12">
                <div class="col-md-6 col-12 tm-color-gray">
                    Design: IImorII
                </div>
                <div class="col-md-6 col-12 tm-color-gray tm-copyright">
                    Copyright 2021 Mint Library, JWD.
                </div>
            </footer>
        </main>
    </div>
</body>
</html>
