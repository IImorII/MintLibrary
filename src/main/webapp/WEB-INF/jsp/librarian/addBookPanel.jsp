<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="col-lg-7 tm-contact-left">
    <form method="POST" action="?command=add_book" class="mb-5 ml-auto mr-0 tm-contact-form">
        <div class="form-group row mb-4">
            <label for="name" class="col-sm-3 col-form-label text-right tm-color-primary">Name</label>
            <div class="col-sm-9">
                <input class="form-control mr-0 ml-auto" name="name" id="name" type="text" required/>
            </div>
        </div>
        <div class="form-group row mb-4">
            <label for="photoUrl" class="col-sm-3 col-form-label text-right tm-color-primary">Image URL</label>
            <div class="col-sm-9">
                <input class="form-control mr-0 ml-auto" name="photoUrl" id="photoUrl" type="text"/>
            </div>
        </div>
        <div class="form-group row mb-4">
            <label for="year" class="col-sm-3 col-form-label text-right tm-color-primary">Year of release</label>
            <div class="col-sm-9">
                <input class="form-control mr-0 ml-auto" name="year" id="year" type="number" required>
            </div>
        </div>
        <div class="form-group row mb-4">
            <label for="count" class="col-sm-3 col-form-label text-right tm-color-primary">Count</label>
            <div class="col-sm-9">
                <input class="form-control mr-0 ml-auto" name="count" id="count" type="number" required>
            </div>
        </div>
        <div class="form-group row mb-4">
            <label for="genres" class="col-sm-3 col-form-label text-right tm-color-primary">Genres</label>
            <div class="col-sm-9">
                <select name="genres" id="genres" class="selectpicker" multiple data-min-options="1" data-max-options="3" required>
                    <c:forEach var="genre" items="${requestScope.genresList}">
                        <option value="${genre.id}">${genre.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group row mb-4">
            <label for="authors" class="col-sm-3 col-form-label text-right tm-color-primary">Authors</label>
            <div class="col-sm-9">
                <select name="authors" id="authors" class="selectpicker" multiple data-min-options="1" data-max-options="3" required>
                    <c:forEach var="author" items="${requestScope.authorsList}">
                        <option value="${author.id}">${author.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group row mb-4">
            <label for="language" class="col-sm-3 col-form-label text-right tm-color-primary">Language</label>
            <div class="col-sm-9">
                <select name="language" id="language" class="selectpicker" required>
                    <c:forEach var="language" items="${requestScope.languagesList}">
                        <option value="${language.id}">${language.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group row mb-5">
            <label for="description" class="col-sm-3 col-form-label text-right tm-color-primary">Description</label>
            <div class="col-sm-9">
                <textarea class="form-control mr-0 ml-auto" name="description" id="description" rows="8" required></textarea>
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
