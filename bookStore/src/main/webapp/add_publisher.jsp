
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
        <fmt:message key="add.publisher.title"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<c:if test="${sessionScope.isAdmin eq true}">
    <form action="addPublisher" method="get" id="add_publisher_form">
        <!-- Email input -->
        <div class="form-outline mb-4">
            <input name="new_publisher_name" type="text" id="form2Example1" class="form-control" />
            <label class="form-label" for="form2Example1"><fmt:message key="add.publisher.new.publisher"/></label>
        </div>

        <div class="form-outline mb-4">
            <select name="new_publisher_country_id" type="text" id="form2Example2" class="form-control">
                <c:forEach items="${sessionScope.adminCountries}" var="country">
                    <option value="${country.id}">${country.name}</option>
                </c:forEach>
            </select>
            <label class="form-label" for="form2Example2"><fmt:message key="add.publisher.country"/></label>
        </div>

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4" form="add_publisher_form"><fmt:message key="add.publisher.add.publisher"/></button>

    </form>
</c:if>
</body>
</html>
