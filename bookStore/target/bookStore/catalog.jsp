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
        <fmt:message key="catalog.title"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    <style>
        .sidebar {
        height: 100%; /* Full-height: remove this if you want "auto" height */
        width: 240px; /* Set the width of the sidebar */
        position: fixed; /* Fixed Sidebar (stay in place on scroll) */
        z-index: 1; /* Stay on top */
        top: 75px; /* Stay at the top */
        left: 0;
        background-color: #111; /* Black */
        overflow-x: hidden; /* Disable horizontal scroll */
        padding-top: 20px;
        color: white;
    }

    /* The navigation menu links */
    .sidebar a {
        padding: 6px 8px 6px 16px;
        text-decoration: none;
        font-size: 25px;
        color: white;
        display: block;
    }

    /* When you mouse over the navigation links, change their color */
    .sidebar a:hover {
        color: #f1f1f1;
    }
    .content {
            margin-left: 240px; /* Same as the width of the sidebar */
            padding: 0px 10px;
    }
    </style>
</head>
<body>
    <section class = 'content'>
        <h1 class = "text-center"><fmt:message key="catalog.books"/></h1>
        <table class="table table-dark text-center ">
            <tr>
                <th><fmt:message key="catalog.book.title"/></th>
                <th><fmt:message key="catalog.book.price"/></th>
                <th><fmt:message key="catalog.book.genre"/></th>
            </tr>
            <c:forEach var="book" items="${sessionScope.bookList}">
                <tr>
                    <td>
                        <form action="book" method="get">
                            <input type="hidden" name="bookId" value="${book.id}">
                            <input type="submit" value="${book.title}">
                        </form>
                    </td>
                    <td>
                        <h3>${book.price}KZT</h3>
                    </td>
                    <%--<td>
                        <h3>${book.count}</h3>
                    </td>
                    <td>
                        <h3>${sessionScope.publisherMap[book.id]}</h3>
                    </td>--%>
                    <td>
                        <h3>${sessionScope.genreMap[book.id]}</h3>
                    </td>

                    <%--<td>
                        <h3>${sessionScope.languageMap[book.id]}</h3>
                    </td>
                    <td>
                        <c:forEach items="${sessionScope.authorMap[book.id]}" var = "bookAuthor">
                        <h4>${bookAuthor.fullName}</h4>
                        </c:forEach>
                    </td>--%>
                </tr>
            </c:forEach>
        </table>
    </section>
<jsp:include page="sidebar.jsp"/>
</body>
</html>
