
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
        <fmt:message key="admin.cabinet.title"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<c:if test="${sessionScope.isAdmin eq true}">
    <h1 class="text-center"><fmt:message key="admin.cabinet.main"/></h1>
    <table class="table table-dark text-center">
        <tr>
            <th><fmt:message key="admin.cabinet.actions"/></th>
        </tr>
        <tr>
            <td>
                <h2><a href="usersAdmin" class="text-light"><fmt:message key="admin.cabinet.users"/></a></h2>
            </td>
        </tr>
        <tr>
            <td>
                <h2><a href="ordersAdmin" class="text-light"><fmt:message key="admin.cabinet.orders"/></a></h2>
            </td>
        </tr>
        <tr>
        <td>
            <h2><a href="add_genre.jsp" class="text-light"><fmt:message key="admin.cabinet.add.genre"/></a></h2>
        </td>
        </tr>
        <tr>
            <td>
                <h2><a href="add_author.jsp" class="text-light"><fmt:message key="admin.cabinet.add.author"/></a></h2>
            </td>
        </tr>
        <tr>
            <td>
                <h2><a href="preparePublisher" class="text-light"><fmt:message key="admin.cabinet.add.publisher"/></a></h2>
            </td>
        </tr>
        <tr>
            <td>
                <h2><a href="prepareBookAdd" class="text-light"><fmt:message key="admin.cabinet.add.book"/></a></h2>
            </td>
        </tr>
    </table>
</c:if>
</body>
</html>
