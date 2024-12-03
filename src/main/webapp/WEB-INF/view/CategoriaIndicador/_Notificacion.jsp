<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<div class="row">
    <div class="col-md">
        <c:if test="${status}">
            <h1 class="display-4 mt-3 text-center text-success"><i class="fa fa-check-circle-o" aria-hidden="true"></i>
                Satisfactorio</h1>
        </c:if>
        <c:if test="${!status}">
            <h1 class="display-4 mt-3 text-center text-danger"><i class="fa fa-check-circle-o" aria-hidden="true"></i>
                Error</h1>
        </c:if>
        <p class="mt-3 mb-3 text-center">${message}.</p>
    </div>
</div>
