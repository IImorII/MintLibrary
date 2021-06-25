<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add author</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="col-lg-7 tm-contact-left">
    <form method="POST" action="?command=add_author" class="mb-5 ml-auto mr-0 tm-contact-form">
        <div class="form-group row mb-4">
            <label for="name" class="col-sm-3 col-form-label text-right tm-color-primary">Name</label>
            <div class="col-sm-9">
                <input class="form-control mr-0 ml-auto" name="name" id="name" type="text" required/>
            </div>
        </div>
        <div class="form-group row mb-4">
            <label for="year" class="col-sm-3 col-form-label text-right tm-color-primary">Year of birth</label>
            <div class="col-sm-9">
                <input class="form-control mr-0 ml-auto" name="year" id="year" type="number" required/>
            </div>
        </div>
        <div class="form-group row mb-4">
            <label for="languages" class="col-sm-3 col-form-label text-right tm-color-primary">Languages</label>
            <div class="col-sm-9">
                <select name="languages" id="languages" class="selectpicker" multiple data-min-options="1" data-max-options="3" required>
                    <c:forEach var="language" items="${requestScope.languagesList}">
                        <option value="${language.id}">${language.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group row text-right">
            <div class="col-12">
                <button class="tm-btn tm-btn-primary tm-btn-small">Submit</button>
            </div>
        </div>
    </form>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
