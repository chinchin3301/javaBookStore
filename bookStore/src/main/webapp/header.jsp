
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locals"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp"><fmt:message key="store.name"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp"><fmt:message key="header.home"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="filterBooks"><fmt:message key="header.catalog"/></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="header.languages"/>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:forEach items="${sessionScope.locals}" var="local">
                            <li><a class="dropdown-item" href="changeLanguage?new_local_id=${local.id}" value="${local.id}">${local.name}</a></li>
                            <li><hr class="dropdown-divider"></li>
                        </c:forEach>
                        <%--<li><a class="dropdown-item" href="#"><fmt:message key="header.english"/></a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#"><fmt:message key="header.russian"/></a></li>--%>
                    </ul>
                </li>
                <c:if test="${sessionScope.user_id eq null}">
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><fmt:message key="header.login"/></a>
                </li>
                </c:if>
                <c:if test="${sessionScope.user_id ne null}">
                    <li class="nav-item">
                        <a class="nav-link" href="cabinet.jsp"><fmt:message key="header.cabinet"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.isAdmin eq true}">
                    <li class="nav-item">
                        <a class="nav-link" href="admin_cabinet.jsp"><fmt:message key="header.admin.cabinet"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user_id ne null}">
                    <li class="nav-item">
                        <a class="nav-link" href="logout"><fmt:message key="header.logout"/></a>
                    </li>
                </c:if>
            </ul>
            <form action="search" method="get" class="d-flex">
                <input class="form-control me-2" name="searchTitle" type="search" placeholder="<fmt:message key="header.search"/>" aria-label="Search">
                <button class="btn btn-outline-success" type="submit"><fmt:message key="header.search"/></button>
            </form>
        </div>
    </div>
</nav>