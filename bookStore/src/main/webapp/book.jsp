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
        <fmt:message key="book.title"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<section class = 'content'>
    <h1 class = "text-center"><fmt:message key="book.main"/></h1>
    <table class="table table-dark text-center ">
        <tr>
            <th><fmt:message key="book.book.title"/></th>
            <th><fmt:message key="book.book.description"/></th>
            <th><fmt:message key="book.book.price"/></th>
            <th><fmt:message key="book.book.count"/></th>
            <th><fmt:message key="book.book.publisher"/></th>
            <th><fmt:message key="book.book.genre"/></th>
            <th><fmt:message key="book.book.language"/></th>
            <th><fmt:message key="book.book.author"/></th>
            <th><fmt:message key="book.book.available"/></th>
        </tr>
        <tr>
            <td>
                <h2>${sessionScope.aBook.title}</h2>
            </td>
            <td>
                <h4>${sessionScope.aBook.description}</h4>
            </td>
            <td>
                <h3>${sessionScope.aBook.price}KZT</h3>
            </td>
            <td>
                <h3>${sessionScope.aBook.count}</h3>
            </td>
            <td>
                <h3>${sessionScope.publisherMap[aBook.id]}</h3>
            </td>
            <td>
                <h3>${sessionScope.genreMap[aBook.id]}</h3>
            </td>
            <td>
                <h3>${sessionScope.languageMap[aBook.id]}</h3>
            </td>
            <td>
                <c:forEach items="${sessionScope.authorMap[aBook.id]}" var = "bookAuthor">
                    <h4>${bookAuthor.fullName}</h4>
                        </c:forEach>
            </td>
            <td>
                <c:if test="${sessionScope.aBook.count >= 1}">
                    <form action="addToBasket" method="get">
                        <input type="hidden" name="bookId" value="${sessionScope.aBook.id}">
                        <input type="submit" value="<fmt:message key="book.add.basket"/>">
                    </form>
                </c:if>
                <c:if test="${sessionScope.aBook.count < 1}">
                    <h2><fmt:message key="book.out.stock"/></h2>
                </c:if>
            </td>
        </tr>
    </table>
</section>
</body>
</html>
