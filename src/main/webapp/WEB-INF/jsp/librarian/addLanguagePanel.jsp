<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title><lc:lc_tag key="add_languages"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="row tm-row">
    <div class="col-12">
        <form action="?command=add_language" method="POST" class="tm-mb-40 tm-add-form form-inline">
            <input class="tm-add-input form-control" name="name" type="text" aria-label="name" placeholder="<lc:lc_tag key="language"/>">
            <button class="tm-add-button" type="submit">
                <i class="fa fa-check tm-add-icon" aria-hidden="true"></i>
                <lc:lc_tag key="add"/>
            </button>
        </form>
    </div>
</div>

<div class="row tm-row">
    <div class="col-12">
        <c:if test="${not empty requestScope.languagesList}">
            <p><lc:lc_tag key="languages"/>: </p>
        </c:if>
        <c:if test="${empty requestScope.languagesList}">
            <p><lc:lc_tag key="empty_languages"/>! </p>
        </c:if>
        <c:if test="${not empty requestScope.languagesList}">
            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable1">
                <thead>
                <tr class="tm-table-text">
                    <th><lc:lc_tag key="language"/></th>
                    <th><lc:lc_tag key="delete"/></th>
                </tr>
                </thead>
                <tbody class="tm-table-text">
                <c:forEach var="language" items="${requestScope.languagesList}">
                    <tr class="odd gradeX">
                        <td>${language.name}</td>
                        <td><a href="?command=delete_language&languageId=${language.id}"
                               class="mb-2 tm-btn tm-btn-primary tm-prev-next"><lc:lc_tag key="delete"/></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
