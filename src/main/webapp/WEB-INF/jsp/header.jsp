<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="fontawesome/css/all.min.css"> <!-- https://fontawesome.com/ -->
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet"> <!-- https://fonts.google.com/ -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-xtra-blog.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="../../js/templatemo-script.js"></script>
    <title>Login</title>
</head>

<body>

<header class="tm-header" id="tm-header">
    <div class="tm-header-wrapper">
        <button class="navbar-toggler" type="button" aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>
        <div class="tm-site-header">
            <div class="mb-3 mx-auto tm-site-logo"><i class="fas fa-book-reader"></i></div>
            <h1 class="text-center">Mint Library</h1>
        </div>
        <nav class="tm-nav" id="tm-nav">
            <ul>
                <li class="tm-nav-item active"><a href="?command=main" class="tm-nav-link">
                    <i class="fas fa-home"></i>
                    Home
                </a></li>
                <c:if test="${sessionScope.userRole eq 'Librarian'}">
                <li class="tm-nav-item"><a href="?command=library_panel" class="tm-nav-link">
                    <i class="fas fa-pen"></i>
                    Librarian panel
                </a></li>
                </c:if>
                <c:if test="${sessionScope.userRole eq 'Admin'}">
                <li class="tm-nav-item"><a href="?command=accounts_panel" class="tm-nav-link">
                    <i class="fas fa-users"></i>
                    Admin panel
                </a></li>
                </c:if>
                <c:if test="${sessionScope.userRole eq 'User'}">
                    <li class="tm-nav-item"><a href="?command=view_order_panel" class="tm-nav-link">
                        <i class="fas fa-pen"></i>
                        User panel
                    </a></li>
                </c:if>
                <li class="tm-nav-item"><a href="?command=about" class="tm-nav-link">
                    <i class="far fa-comments"></i>
                    About library
                </a></li>
            </ul>
        </nav>

        <div class="tm-mb-65">
            <c:if test="${empty sessionScope.userName}">
                <a href="#" data-target="#loginModal" data-toggle="modal" class="tm-social-link">
                    <i class="fas fa-sign-in-alt tm-social-icon"></i>
                </a>
            </c:if>
            <c:if test="${not empty sessionScope.userName}">
                <a href="?command=logout" class="tm-social-link">
                    <i class="fas fa-sign-out-alt tm-social-icon"></i>
                </a>
                <br>
                Id: ${sessionScope.accountId}
                <br>
                Name: ${sessionScope.userName}
                <br>
                Role: ${sessionScope.userRole}
                <c:if test="${sessionScope.userRole eq 'User'}">
                <br>
                Ticket: ${sessionScope.booksCurrent}/${sessionScope.booksMax}
                </c:if>
            </c:if>
        </div>
    </div>
</header>
<div class="container-fluid">
    <main class="tm-main">
        <!-- Search form -->
        <div class="row tm-row">
            <div class="col-12">
                <form action="?command=search_book" method="POST" class="form-inline tm-mb-80 tm-search-form">
                    <input class="form-control tm-search-input" name="search" type="text" placeholder="Search..." aria-label="Search">
                    <button class="tm-search-button" type="submit">
                        <i class="fas fa-search tm-search-icon" aria-hidden="true"></i>
                    </button>
                </form>
            </div>
        </div>
</body>

<script src="js/templatemo-script.js"></script>

<jsp:include page="modal/loginModal.jsp"/>
<jsp:include page="modal/signUpModal.jsp"/>



