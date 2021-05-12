<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="fontawesome/css/all.min.css"> <!-- https://fontawesome.com/ -->
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet"> <!-- https://fonts.google.com/ -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-xtra-blog.css" rel="stylesheet">
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
        <div>
            <!-- Кнопка, вызывающее модальное окно -->
            <a href="#" class="btn btn-primary" data-target="#loginModal" data-toggle="modal">login</a>
            <jsp:include page="modal/loginModal.jsp"/>
        </div>
        <div class="tm-mb-65">
            <a rel="nofollow" href="https://fb.com/templatemo" class="tm-social-link">
                <i class="fab fa-facebook tm-social-icon"></i>
            </a>
            <a href="https://twitter.com" class="tm-social-link">
                <i class="fab fa-twitter tm-social-icon"></i>
            </a>
            <a href="https://instagram.com" class="tm-social-link">
                <i class="fab fa-instagram tm-social-icon"></i>
            </a>
            <a href="https://linkedin.com" class="tm-social-link">
                <i class="fab fa-linkedin tm-social-icon"></i>
            </a>
        </div>
    </div>
</header>
<script src="js/templatemo-script.js"></script>
<script src="js/jquery.min.js"></script>
</body>



