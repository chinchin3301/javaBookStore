
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locals"/>
<html>
<head>
    <title>
        <fmt:message key="changepassword.title"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<form action="changeUserPassword" method="get" id="change_password_form">
    <!-- Email input -->
    <div class="form-outline mb-4">
        <input name="old_password" type="password" id="form2Example1" class="form-control" />
        <label class="form-label" for="form2Example1"><fmt:message key="changepassword.old.password"/></label>
    </div>

    <!-- Password input -->
    <div class="form-outline mb-4">
        <input name="new_password" type="password" id="form2Example2" class="form-control" />
        <label class="form-label" for="form2Example2"><fmt:message key="changepassword.new.password"/></label>
    </div>

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4" form="change_password_form"><fmt:message key="changepassword.changebutton"/></button>

</form>
</body>
</html>
