<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title><lc:lc_tag key="add_authors"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="row tm-row">
    <div class="col-12">
        <form action="?command=add_author" method="POST" class="tm-mb-40 tm-add-form form-inline">
            <input class="tm-add-input form-control" name="name" type="text" aria-label="name" placeholder="<lc:lc_tag key="name"/>">
            <input class="tm-add-input form-control" name="birth" type="text" aria-label="birth" placeholder="<lc:lc_tag key="birth"/>">
            <select name="languages" id="languages" class="selectpicker" multiple data-min-options="1"
                    data-max-options="3" required>
                <c:forEach var="language" items="${requestScope.languagesList}">
                    <option value="${language.id}">${language.name}</option>
                </c:forEach>
            </select>
            <button class="tm-add-button" type="submit">
                <i class="fa fa-check tm-add-icon" aria-hidden="true"></i>
                <lc:lc_tag key="add"/>
            </button>
        </form>
    </div>
</div>

<div class="row tm-row">
    <div class="col-12">
        <c:if test="${not empty requestScope.authorsList}">
            <p><lc:lc_tag key="authors"/>: </p>
        </c:if>
        <c:if test="${empty requestScope.authorsList}">
            <p><lc:lc_tag key="empty_authors"/>! </p>
        </c:if>
        <c:if test="${not empty requestScope.authorsList}">
            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable1">
                <thead>
                <tr class="tm-table-text">
                    <th><lc:lc_tag key="author"/></th>
                    <th><lc:lc_tag key="delete"/></th>
                </tr>
                </thead>
                <tbody class="tm-table-text">
                <c:forEach var="author" items="${requestScope.authorsList}">
                    <tr class="odd gradeX">
                        <td>${author.name}</td>
                        <td><a href="?command=delete_author&authorId=${author.id}"
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