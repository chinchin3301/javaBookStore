
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
        <fmt:message key="basket.title"/>
    </title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<div class="content">
    <table class="table table-dark text-center">
        <tr>
            <th><fmt:message key="basket.book.title"/></th>
            <th><fmt:message key="basket.book.count"/></th>
            <th><fmt:message key="basket.book.delete"/></th>
        </tr>
        <c:forEach items="${sessionScope.baskets}" var="basket">
                <tr>
                    <td>${sessionScope.bookTitleMap[basket.booksId]}</td>
                    <td>${basket.count}</td>
                    <td>
                        <form action="removeFromBasket" method="get">
                            <input type="hidden" name="basketId" value="${basket.id}">
                            <input type="submit" value="<fmt:message key="basket.delete.from"/>">
                        </form>
                    </td>
                </tr>
        </c:forEach>

    </table>
    <form action="checkout" method="get" class="text-center">
        <input type="submit" value="<fmt:message key="basket.checout"/>" class="text-center">
    </form>
</div>
</body>
</html>
