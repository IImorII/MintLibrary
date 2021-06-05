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
<div>
    <a href="?command=release_order_panel" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Release orders</a>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
