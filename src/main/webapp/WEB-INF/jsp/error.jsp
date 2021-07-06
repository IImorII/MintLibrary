<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title><lc:lc_tag key="error"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <h1>Sorry :(</h1>
    <c:if test="${requestScope.info != null}">
        <h4>${requestScope.info}</h4>
    </c:if>
    <p>You are on the wrong way :(</p>
    <p>Please, return back to home page!</p>
    <div class="tm-back-button">
        <a href="?command=main"
           class="mb-2 tm-btn tm-btn-primary tm-prev-next">Go to home page</a>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
