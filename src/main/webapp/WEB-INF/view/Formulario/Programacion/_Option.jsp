<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="item" items="${areasEstrategicas}">
    <option value="${item.id}">${item.value}</option>
</c:forEach>
