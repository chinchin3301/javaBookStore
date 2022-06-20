
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
        <fmt:message key="add.book.title"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<c:if test="${sessionScope.isAdmin eq true}">
    <form action="addBook" method="get" id="add_book_form">
        <!-- Email input -->
        <div class="form-outline mb-4">
            <input name="new_book_title" type="text" id="form2Example1" class="form-control" />
            <label class="form-label" for="form2Example1"><fmt:message key="add.book.new.book.title"/></label>
        </div>
        <div class="form-outline mb-4">
            <input name="new_book_description" type="text" id="form2Example6" class="form-control" />
            <label class="form-label" for="form2Example6"><fmt:message key="add.book.new.book.desc"/></label>
        </div>
        <div class="form-outline mb-4">
            <input name="new_book_price" type="number" id="form2Example7" class="form-control" />
            <label class="form-label" for="form2Example7"><fmt:message key="add.book.new.book.price"/></label>
        </div>
        <div class="form-outline mb-4">
            <input name="new_book_count" type="number" id="form2Example8" class="form-control" />
            <label class="form-label" for="form2Example8"><fmt:message key="add.book.new.book.count"/></label>
        </div>


        <div class="form-outline mb-4">
            <select name="new_book_language_id" type="text" id="form2Example2" class="form-control">
                <c:forEach items="${sessionScope.adminLanguages}" var="language">
                    <option value="${language.id}">${language.name}</option>
                </c:forEach>
            </select>
            <label class="form-label" for="form2Example2"><fmt:message key="add.book.language"/></label>
        </div>
        <div class="form-outline mb-4">
            <select name="new_book_genre_id" type="text" id="form2Example3" class="form-control">
                <c:forEach items="${sessionScope.adminGenres}" var="genre">
                    <option value="${genre.id}">${genre.title}</option>
                </c:forEach>
            </select>
            <label class="form-label" for="form2Example3"><fmt:message key="add.book.genre"/></label>
        </div>
        <div class="form-outline mb-4">
            <select name="new_book_publisher_id" type="text" id="form2Example4" class="form-control">
                <c:forEach items="${sessionScope.adminPublishers}" var="publisher">
                    <option value="${publisher.id}">${publisher.name}</option>
                </c:forEach>
            </select>
            <label class="form-label" for="form2Example4"><fmt:message key="add.book.publisher"/></label>
        </div>
        <div class="form-check mb-auto">
            <fieldset name="new_book_author_id" type="text" id="form2Example5" class="form-control">
                <c:forEach items="${sessionScope.adminAuthors}" var="author">
                    <input type="checkbox" id="${author.id}" value="${author.id}" name = "new_book_author_id">
                    <label for="${author.id}">${author.fullName}</label>
                </c:forEach>
            </fieldset>
            <label class="form-label" for="form2Example5"><fmt:message key="add.book.author"/></label>
        </div>
        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4 btn-center" form="add_book_form"><fmt:message key="add.book.add.book"/></button>

    </form>
</c:if>
</body>
</html>
