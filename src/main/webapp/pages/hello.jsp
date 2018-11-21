<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
     <head>

     </head>
           <body>
                 <h1>Products</h1>
           <c:forEach items="${products}" var="item">
               <br><a href="/product?id=${item.id}"><c:out value="${item.name}"/></a>
               <br>
           </c:forEach>
           </body>
</html>