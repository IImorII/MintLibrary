<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add language</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="col-lg-7 tm-contact-left">
    <form method="POST" action="?command=add_language" class="mb-5 ml-auto mr-0 tm-contact-form">
        <div class="form-group row mb-4">
            <label for="name" class="col-sm-3 col-form-label text-right tm-color-primary">Language</label>
            <div class="col-sm-9">
                <input class="form-control mr-0 ml-auto" name="name" id="name" type="text" required/>
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
