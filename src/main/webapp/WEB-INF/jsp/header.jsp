<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="fontawesome/css/all.min.css"> <!-- https://fontawesome.com/ -->
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <!-- https://fonts.google.com/ -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

    <script src="js/header-script.js"></script>
    <title>Login</title>
</head>

<body>
<header class="tm-header" id="tm-header">
    <div class="tm-header-wrapper">
        <button class="navbar-toggler" type="button" aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>
        <div class="tm-site-header">
            <div class="mb-5 mx-auto tm-site-locale">
                <a href="?command=change_locale&language=en">EN</a>
            <a href="?command=change_locale&language=ru">RU</a>
            <a href="?command=change_locale&language=be">BE</a>
            <a href="?command=change_locale&language=pl">PL</a>
            </div>
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
                    <li class="tm-nav-item"><a href="?command=admin_panel" class="tm-nav-link">
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
        <div class="tm-mb-ml-40 fixed-bottom">
            <c:if test="${empty sessionScope.account.name}">
                <a href="#" data-target="#loginModal" data-toggle="modal" class="tm-social-link">
                    <i class="fas fa-sign-in-alt tm-social-icon"></i>
                </a>
                <lc:lc_tag key="login_register"/>
            </c:if>
            <c:if test="${not empty sessionScope.account.name}">
                <a href="?command=logout" class="tm-social-link">
                    <i class="fas fa-sign-out-alt tm-social-icon"></i>
                </a>
                <lc:lc_tag key="sign_out"/>
            </c:if>
        </div>
    </div>
</header>
<div class="container-fluid">
    <main class="tm-main">
        <div class="row tm-row">
            <div class="col-12 tm-header-icons tm-mb-40">
                <c:if test="${not empty sessionScope.account.name}">
                    <!--ID-->
                        <a href="?command=logout" class="tm-social-link">
                            <i class="fa fa-tag tm-social-icon" aria-hidden="true"></i>
                        </a>
                        <h2 class="tm-color-primary tm-header-title mb-3">
                            <lc:lc_tag key="id"/>: ${sessionScope.account.id}
                        </h2>
                    <!--Name-->
                        <a href="?command=logout" class="tm-social-link">
                            <i class="fa fa-user-circle tm-social-icon" aria-hidden="true"></i>
                        </a>
                        <h2 class="tm-color-primary tm-header-title mb-3">
                                ${sessionScope.account.name}
                        </h2>
                    <!--Role-->
                    <c:if test="${!(sessionScope.account.role eq 'User')}">
                            <a href="?command=logout" class="tm-social-link">
                                <i class="fa fa-users tm-social-icon" aria-hidden="true"></i>
                            </a>
                            <h2 class="tm-color-primary tm-header-title mb-3">
                                    ${sessionScope.account.role}
                            </h2>
                    </c:if>
                    <!--Ticket-->
                    <c:if test="${sessionScope.account.role eq 'User'}">
                            <a href="?command=logout" class="tm-social-link">
                                <i class="fa fa-book tm-social-icon" aria-hidden="true"></i>
                            </a>
                            <h2 class="tm-color-primary tm-header-title mb-3">
                                    ${sessionScope.account.amountCurrent}/${sessionScope.account.amountMax}
                            </h2>
                    </c:if>
                </c:if>
            </div>
        </div>

        <!-- Search form -->
        <div class="row tm-row">
            <div class="col-12">
                <form action="?command=search_book" method="POST" class="form-inline tm-mb-40 tm-search-form">
                    <div>
                        <div class="form-group row">
                            <label for="genres" style="justify-content: normal;"
                                   class="col-sm-3 col-form-label tm-search-filter tm-color-primary"><lc:lc_tag
                                    key="genre"/></label>
                            <div class="col-sm-9">
                                <select name="genres" id="genres" class="selectpicker" multiple data-max-options="3">
                                    <c:forEach var="genre" items="${requestScope.genresList}">
                                        <option value="${genre.name}">${genre.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="authors" style="justify-content: normal;"
                                   class="col-sm-3 col-form-label tm-search-filter text-right tm-color-primary"><lc:lc_tag
                                    key="author"/></label>
                            <div class="col-sm-9">
                                <select name="authors" id="authors" class="selectpicker" multiple data-max-options="3">
                                    <c:forEach var="author" items="${requestScope.authorsList}">
                                        <option value="${author.name}">${author.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <input class="form-control tm-search-input" name="search" type="text"
                           placeholder="<lc:lc_tag key="search"/>" aria-label="Search">
                    <button class="tm-search-button" type="submit">
                        <i class="fas fa-search tm-search-icon" aria-hidden="true"></i>
                    </button>
                </form>
            </div>
            <div class="col-12">
                <hr class="tm-hr-primary tm-mb-55">
            </div>
        </div>
</body>

<jsp:include page="modal/loginModal.jsp"/>
<jsp:include page="modal/signUpModal.jsp"/>



