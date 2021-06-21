<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
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
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/i18n/defaults-*.min.js"></script>
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
                <li class="tm-nav-item"><a href="?command=main" class="tm-nav-link">
                    <i class="fas fa-home"></i>
                    <lc:lc_tag key="home"/>
                </a></li>
                <c:if test="${sessionScope.account.role eq 'Librarian'}">
                <li class="tm-nav-item"><a href="?command=library_panel" class="tm-nav-link">
                    <i class="fas fa-pen"></i>
                    <lc:lc_tag key="librarian_panel"/>
                </a></li>
                </c:if>
                <c:if test="${sessionScope.account.role eq 'Admin'}">
                <li class="tm-nav-item"><a href="?command=accounts_panel" class="tm-nav-link">
                    <i class="fas fa-users"></i>
                    <lc:lc_tag key="admin_panel"/>
                </a></li>
                </c:if>
                <c:if test="${sessionScope.account.role eq 'User'}">
                    <li class="tm-nav-item"><a href="?command=view_order_panel" class="tm-nav-link">
                        <i class="fas fa-pen"></i>
                        <lc:lc_tag key="user_panel"/>
                    </a></li>
                </c:if>
                <li class="tm-nav-item"><a href="?command=about" class="tm-nav-link">
                    <i class="far fa-comments"></i>
                    <lc:lc_tag key="about_library"/>
                </a></li>
            </ul>
        </nav>

        <div class="tm-mb-65">
            <c:if test="${empty sessionScope.account.name}">
                <a href="#" data-target="#loginModal" data-toggle="modal" class="tm-social-link">
                    <i class="fas fa-sign-in-alt tm-social-icon"></i>
                </a>
            </c:if>
            <c:if test="${not empty sessionScope.account.name}">
                <a href="?command=logout" class="tm-social-link">
                    <i class="fas fa-sign-out-alt tm-social-icon"></i>
                </a>
                <br>
                Id: ${sessionScope.account.id}
                <br>
                Name: ${sessionScope.account.name}
                <br>
                Role: ${sessionScope.account.role}
                <c:if test="${sessionScope.account.role eq 'User'}">
                <br>
                Ticket: ${sessionScope.account.amountCurrent}/${sessionScope.account.amountMax}
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



