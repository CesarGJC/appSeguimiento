<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<form:form data-target="${model.clave}" data-form="true" modelAttribute="model" method="post"
           action="${pageContext.request.contextPath}/configuracion/cambiar">
    <form:hidden path="clave"/>
    <div class="mb-3">
        <label class="form-label" for="valor">Planes Estrategicos</label>
        <form:select cssClass="form-select" path="valor" items="${planes}" itemValue="id" itemLabel="value"/>
        <form:errors cssClass="invalid" path="valor"/>
    </div>
</form:form>