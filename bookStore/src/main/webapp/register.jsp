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
        <fmt:message key="register.title"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<form action="/registerUser" method="POST" id="register_form">
    <!-- Email input -->
    <div class="form-outline mb-4">
        <input type="email" name = "email" id="form2Example1" class="form-control" />
        <label class="form-label" for="form2Example1"><fmt:message key="register.email"/></label>
    </div>

    <!-- Password input -->
    <div class="form-outline mb-4">
        <input type="password" name = "password" id="form2Example2" class="form-control" />
        <label class="form-label" for="form2Example2"><fmt:message key="register.password"/></label>
    </div>

    <%--Name input--%>
    <div class="form-outline mb-4">
        <input type="text" name = "name" id="form2Example3" class="form-control" />
        <label class="form-label" for="form2Example3"><fmt:message key="register.name"/></label>
    </div>
    <%--Surname input--%>
    <div class="form-outline mb-4">
        <input type="text" name = "surname" id="form2Example4" class="form-control" />
        <label class="form-label" for="form2Example4"><fmt:message key="register.surname"/></label>
    </div>
    <%--Birthday input--%>
    <div class="form-outline mb-4">
        <input type="date" name = "birthday" id="form2Example5" class="form-control" />
        <label class="form-label" for="form2Example5"><fmt:message key="register.birthday"/></label>
    </div>
    <%--Phone number input--%>
    <div class="form-outline mb-4">
        <input type="text" name = "phonenumber" id="form2Example6" class="form-control" />
        <label class="form-label" for="form2Example6"><fmt:message key="register.phonenumber"/></label>
    </div>
    <%--Address--%>
    <div class="form-outline mb-4">
        <input type="text" name = "address" id="form2Example7" class="form-control" />
        <label class="form-label" for="form2Example7"><fmt:message key="register.address"/></label>
    </div>
    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4" form="register_form"><fmt:message key="register.register"/></button>

    <!-- Register buttons -->
    <div class="text-center">
        <p><fmt:message key="register.member"/> <a href="login.jsp"><fmt:message key="register.login"/></a></p>
    </div>
</form>
</body>
</html>
