<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title><lc:lc_tag key="add_genres"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="row tm-row">
    <div class="col-12">
        <form action="?command=add_genre" method="POST" class="tm-mb-40 tm-add-form form-inline">
            <input class="tm-add-input form-control" name="name" type="text" aria-label="name" placeholder="<lc:lc_tag key="genre"/>">
            <button class="tm-add-button" type="submit">
                <i class="fa fa-check tm-add-icon" aria-hidden="true"></i>
                <lc:lc_tag key="add"/>
            </button>
        </form>
    </div>
</div>

<div class="row tm-row">
    <div class="col-12">
        <c:if test="${not empty requestScope.genresList}">
            <p><lc:lc_tag key="genres"/>: </p>
        </c:if>
        <c:if test="${empty requestScope.genresList}">
            <p><lc:lc_tag key="empty_genres"/>! </p>
        </c:if>
        <c:if test="${not empty requestScope.genresList}">
            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable1">
                <thead>
                <tr class="tm-table-text">
                    <th><lc:lc_tag key="genre"/></th>
                    <th><lc:lc_tag key="delete"/></th>
                </tr>
                </thead>
                <tbody class="tm-table-text">
                <c:forEach var="genre" items="${requestScope.genresList}">
                    <tr class="odd gradeX">
                            <td>${genre.name}</td>
                        <td><a href="?command=delete_genre&genreId=${genre.id}"
                               class="mb-2 tm-btn tm-btn-primary tm-prev-next"><lc:lc_tag key="delete"/></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <jsp:include page="../footer.jsp"/>
    </div>
</div>
</body>
</html>
