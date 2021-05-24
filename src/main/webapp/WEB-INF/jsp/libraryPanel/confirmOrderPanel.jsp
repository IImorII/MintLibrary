<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm order</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="col-lg-7 tm-contact-left">
    <form method="POST" action="?command=confirm_order_panel" class="mb-5 ml-auto mr-0 tm-contact-form">
        <div class="form-group row mb-4">
            <label for="userId" class="col-sm-3 col-form-label text-right tm-color-primary">User id</label>
            <div class="col-sm-9">
                <input class="form-control mr-0 ml-auto" name="userId" id="userId" type="number" required/>
            </div>
        </div>
        <div class="form-group row text-right">
            <div class="col-12">
                <button class="tm-btn tm-btn-primary tm-btn-small">Submit</button>
            </div>
        </div>
    </form>
</div>
<c:if test="${not empty requestScope.booksList}">
<div class="col-lg-7 tm-contact-left">
    <p>${requestScope.user.name}'s books:</p>
    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
<thead>
<tr>
    <th>Book</th>
    <th>Confirm</th>
</tr>
</thead>
<tbody>
<c:forEach var="book" items="${requestScope.booksList}">
<tr class="odd gradeX">
    <td>${book.name}</td>
    <td><a href="?command=confirm_order&userId=${requestScope.user.id}&bookId=${requestScope.book.id}" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Confirm</a></td>
</tr>
</c:forEach>
</tbody>
    </table>
</div>
</c:if>
<jsp:include page="../footer.jsp"/>
</body>
</html>
