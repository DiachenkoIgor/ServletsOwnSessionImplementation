<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 6/29/2018
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<h1>Product</h1>
<h3>Product Name</h3>
<br><c:out value="${product.name}"/>
<h3>Product Price</h3>
<br><c:out value="${product.price}"/>
<br>
<br>
<br><a href="/product/buy?id=${product.id}">Купить</a>
<hr>
<c:if test="${not empty products}">
<ul>
    <c:forEach items="${products}" var="entry">
        <li><c:out value="${entry.key}"/> &nbsp;(<c:out value="${entry.value}"/>)</li>
    </c:forEach>
</ul>
</c:if>
</body>
</html>
