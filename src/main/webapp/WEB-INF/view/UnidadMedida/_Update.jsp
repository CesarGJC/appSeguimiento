<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post"
           action="${pageContext.request.contextPath}/unidad-medida/update">
    <form:hidden path="id_unidad_medida"/>
    <div class="mb-3">
        <label class="form-label" for="descripcion">Tipo Indicador</label>
        <form:textarea path="descripcion" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="descripcion"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="abreviacion">Abreviacion</label>
        <form:input path="abreviacion" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="abreviacion"/>
    </div>
</form:form>