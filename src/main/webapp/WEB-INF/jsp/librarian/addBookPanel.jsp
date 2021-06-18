<%@ page import="javax.servlet.annotation.MultipartConfig" %>
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
            <label for="authors" class="col-sm-3 col-form-label text-right tm-color-primary">Author</label>
            <div class="col-sm-9">
                <select name="authors" id="authors" class="selectpicker" multiple data-max-options="2">
                    <option value="1">lox</option>
                    <option value="2">sad</option>
                    <option value="3">sss</option>
                </select>
            </div>
        </div>
        <div class="form-group row mb-4">
            <label for="language" class="col-sm-3 col-form-label text-right tm-color-primary">Language</label>
            <div class="col-sm-9">
                <input class="form-control mr-0 ml-auto" name="language" id="language" type="text" required>
            </div>
        </div>
        <div class="form-group row mb-4">
            <label for="genre" class="col-sm-3 col-form-label text-right tm-color-primary">Genre</label>
            <div class="col-sm-9">
                <input class="form-control mr-0 ml-auto" name="genre" id="genre" type="text" required>
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
