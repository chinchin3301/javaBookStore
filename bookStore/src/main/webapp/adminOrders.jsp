
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
    <fmt:message key="admin.orders.title"/>
  </title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
<section class = 'content'>
  <c:if test="${sessionScope.isAdmin eq true}">
  <h1 class = "text-center"><fmt:message key="admin.orders.main"/></h1>
  <table class="table table-dark text-center ">
    <tr>
      <th><fmt:message key="admin.orders.details"/></th>
      <th><fmt:message key="admin.orders.email"/></th>
      <th><fmt:message key="admin.orders.total.price"/></th>
      <th><fmt:message key="admin.orders.status"/></th>
      <th><fmt:message key="admin.orders.edit"/></th>
    </tr>
    <c:forEach var="order" items="${sessionScope.adminOrders}">
      <tr>
        <td>
          <form action="orderAdmin" method="get">
            <input type="hidden" name="orderId" value="${order.id}">
            <%--<input type="hidden" name="orderUserId" value="${order.usersId}">--%>
            <input type="submit" value="<fmt:message key="admin.orders.see.order"/>">
          </form>
        </td>
        <td>
          <h3>${sessionScope.adminUserEmailMap[order.usersId]}</h3>
        </td>
        <td>
          <h3>${order.totalCost}KZT</h3>
        </td>

        <td>
          <h3>${sessionScope.adminStatusMap[order.statusId]}</h3>
        </td>
        <td>
          <form action="adminChangeStatus" method="get">
            <select name="newStatus">
              <c:forEach items="${sessionScope.adminStatusMap}" var="status">
                <option value="${status.value}">${status.value}</option>
              </c:forEach>
            </select>
            <input type="hidden" name="orderId" value="${order.id}">
            <input type="submit" value="<fmt:message key="admin.orders.changestatus"/>">
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
  </c:if>
</section>
</body>
</html>
