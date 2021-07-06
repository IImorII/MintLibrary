<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title><lc:lc_tag key="error"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid">
    <main class="tm-main">
        <c:out value="${requestScope.info}"/>
    </main>
    <div class="tm-back-button">
        <a href="?command=main"
           class="mb-2 tm-btn tm-btn-primary tm-prev-next">Go to home page</a>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
