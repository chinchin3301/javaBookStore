
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
        <fmt:message key="orders.title"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<section class = 'content'>
    <h1 class = "text-center"><fmt:message key="orders.main"/></h1>
    <table class="table table-dark text-center ">
        <tr>
            <th><fmt:message key="orders.order.detail"/></th>
            <th><fmt:message key="orders.total.price"/></th>
            <th><fmt:message key="orders.status"/></th>
        </tr>
        <c:forEach var="order" items="${sessionScope.orders}">
            <tr>
                <td>
                    <form action="order" method="get">
                        <input type="hidden" name="orderId" value="${order.id}">
                        <input type="submit" value="<fmt:message key="orders.see.details"/>">
                    </form>
                </td>
                <td>
                    <h3>${order.totalCost}KZT</h3>
                </td>

                <td>
                    <h3>${sessionScope.statusMap[order.statusId]}</h3>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
