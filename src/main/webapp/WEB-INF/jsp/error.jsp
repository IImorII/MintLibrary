<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <h1>Oops...</h1>
    <c:if test="${requestScope.info != null}">
        <h4>${requestScope.info}</h4>
    </c:if>
    <p>Oops, you are on the wrong way :(</p>
    <p>Please, return back to home page :3</p>
    <div>
        <a href="?command=main"
           class="mb-2 tm-btn tm-btn-primary tm-prev-next">Go to home page</a>
    </div>
</div>
</body>
</html>
