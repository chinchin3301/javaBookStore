<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locals"/>
<aside class="sidebar">
    <form action="filterBooks" method="get" id="filter_form">
    <ul>
        <li>
            <fieldset id="genres">
                <legend><fmt:message key="sidebar.genres"/></legend>
                <c:forEach items="${sessionScope.allGenres}" var = "genre">
                    <input type="checkbox" id = "${genre.title}" value="${genre.title}" name="genres">
                    <label for="${genre.title}">${genre.title}</label>
                </c:forEach>
            </fieldset>
        </li>
        <li>
            <fieldset id="countries">
                <legend><fmt:message key="sidebar.countries"/></legend>
                <c:forEach items="${sessionScope.allCountries}" var="country">
                    <input type="radio" id="${country.name}" value="${country.name}" name = "countries">
                    <label for="${country.name}">${country.name}</label>
                </c:forEach>
            </fieldset>
        </li>

    </ul>
    <button type="submit"><fmt:message key="sidebar.filter"/></button>
    </form>
</aside>