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
                <li class="tm-nav-item"><a href="#" class="tm-nav-link">
                    <i class="fas fa-pen"></i>
                    Page
                </a></li>
                <li class="tm-nav-item"><a href="#" class="tm-nav-link">
                    <i class="fas fa-users"></i>
                    Page
                </a></li>
                <li class="tm-nav-item"><a href="#" class="tm-nav-link">
                    <i class="far fa-comments"></i>
                    Page
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
                Name: ${sessionScope.userName} Role: ${sessionScope.userRole}
            </c:if>
        </div>
    </div>
</header>
</body>

<script src="js/templatemo-script.js"></script>

<jsp:include page="modal/loginModal.jsp"/>
<jsp:include page="modal/signUpModal.jsp"/>



