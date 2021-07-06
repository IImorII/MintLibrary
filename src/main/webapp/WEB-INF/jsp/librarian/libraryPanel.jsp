<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title><lc:lc_tag key="librarian_panel"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="row tm-row container-fluid">
            <div class="col-12 tm-panel-form">
                <div>
                    <form action="?command=confirm_order_panel" method="POST">
                        <button class="tm-panel-button" type="submit">
                            <i class="fa fa-plus tm-panel-icon" aria-hidden="true"></i>
                            <br>
                            <lc:lc_tag key="confirm_orders"/>
                        </button>
                    </form>
                </div>
                <div>
                    <form action="?command=release_order_panel" method="POST">
                        <button class="tm-panel-button" type="submit">
                            <i class="fa fa-minus tm-panel-icon" aria-hidden="true"></i>
                            <br>
                            <lc:lc_tag key="release_orders"/>
                        </button>
                    </form>
                </div>
                <div>
                    <form action="?command=add_book_panel" method="POST">
                        <button class="tm-panel-button" type="submit">
                            <i class="fa fa-book tm-panel-icon" aria-hidden="true"></i>
                            <br>
                            <lc:lc_tag key="add_books"/>
                        </button>
                    </form>
                </div>
                <div>
                    <form action="?command=add_author_panel" method="POST">
                        <button class="tm-panel-button" type="submit">
                            <i class="fas fa-pen tm-panel-icon" aria-hidden="true"></i>
                            <br>
                            <lc:lc_tag key="add_authors"/>
                        </button>
                    </form>
                </div>
                <div>
                    <form action="?command=add_genre_panel" method="POST">
                        <button class="tm-panel-button" type="submit">
                            <i class="fa fa-cube tm-panel-icon" aria-hidden="true"></i>
                            <br>
                            <lc:lc_tag key="add_genres"/>
                        </button>
                    </form>
                </div>
                <div>
                    <form action="?command=add_language_panel" method="POST">
                        <button class="tm-panel-button" type="submit">
                            <i class="fa fa-language tm-panel-icon" aria-hidden="true"></i>
                            <br>
                            <lc:lc_tag key="add_languages"/>
                        </button>
                    </form>
                </div>
            </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
