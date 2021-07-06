<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lc" uri="/WEB-INF/i18" %>
<html>
<head>
    <title><lc:lc_tag key="admin_panel"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="row tm-row container-fluid">
    <div class="col-12 tm-panel-form">
        <div>
            <form action="?command=accounts_panel" method="POST">
                <button class="tm-panel-button" type="submit">
                    <i class="fa fa-users tm-panel-icon" aria-hidden="true"></i>
                    <br>
                    <lc:lc_tag key="manage_accounts"/>
                </button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
