
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
    <fmt:message key="admin.order.title"/>
  </title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<section class = 'content'>
  <c:if test="${sessionScope.isAdmin eq true}">
  <h1 class = "text-center"><fmt:message key="admin.order.details"/></h1>
  <table class="table table-dark text-center ">
    <tr>
      <th><fmt:message key="admin.order.book.title"/></th>
      <th><fmt:message key="admin.order.book.count"/></th>
      <th><fmt:message key="admin.order.total.price"/></th>
    </tr>
    <c:forEach var="orderItem" items="${sessionScope.adminOrderItemList}">
      <tr>
        <td>
          <h2>${sessionScope.adminBookTitleMap[orderItem.booksId]}</h2>
        </td>
        <td>
          <h3>${orderItem.count}</h3>
        </td>

        <td>
          <h3>${orderItem.cost}</h3>
        </td>
      </tr>
    </c:forEach>
  </table>
  </c:if>
</section>
</body>
</html>
