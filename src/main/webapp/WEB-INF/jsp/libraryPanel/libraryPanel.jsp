<%--
  Created by IntelliJ IDEA.
  User: ltx7
  Date: 21.05.2021
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library panel</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div>
    <a href="?command=add_book_panel" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Add books</a>
</div>
<div>
    <a href="?command=confirm_order_panel" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Confirm orders</a>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
