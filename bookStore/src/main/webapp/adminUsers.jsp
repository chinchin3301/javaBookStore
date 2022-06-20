
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
        <fmt:message key="admin.users.title"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<section class = 'content'>
    <c:if test="${sessionScope.isAdmin eq true}">
        <h1 class = "text-center"><fmt:message key="admin.users.main"/></h1>
        <table class="table table-dark text-center ">
            <tr>
                <th><fmt:message key="admin.users.email"/></th>
                <th><fmt:message key="admin.users.name"/></th>
                <th><fmt:message key="admin.users.surname"/></th>
                <th><fmt:message key="admin.users.birthday"/></th>
                <th><fmt:message key="admin.users.phonenumber"/></th>
                <th><fmt:message key="admin.users.address"/></th>
                <th><fmt:message key="admin.users.banned"/></th>
                <th><fmt:message key="admin.users.edit"/></th>
            </tr>
            <c:forEach var="user" items="${sessionScope.adminUserList}">
                <tr>
                    <td>
                        <h3>${user.email}</h3>
                    </td>
                    <td>
                        <h3>${user.name}</h3>
                    </td>
                    <td>
                        <h3>${user.surname}</h3>
                    </td>

                    <td>
                        <h3><fmt:formatDate pattern="yyyy-MM-dd" value="${user.birthday}" /></h3>
                    </td>
                    <td>
                        <h3>${user.phonenumber}</h3>
                    </td>
                    <td>
                        <h3>${user.address}</h3>
                    </td>
                    <td>
                        <h3>
                            <c:if test="${sessionScope.adminUserBanMap[user] eq true}"> <fmt:message key="admin.users.yes"/> </c:if>
                            <c:if test="${sessionScope.adminUserBanMap[user] eq false}"> <fmt:message key="admin.users.no"/></c:if>
                        </h3>
                    </td>

                    <td>
                        <form action="adminChangeUserStatus" method="get">
                            <select name="newBanStatus">
                                <option value="${true}"><fmt:message key="admin.users.yes"/></option>
                                <option value="${false}"><fmt:message key="admin.users.no"/></option>
                            </select>
                            <input type="hidden" name="userId" value="${user.id}">
                            <input type="submit" value="<fmt:message key="admin.users.changestatus"/>">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</section>
</body>
</html>
