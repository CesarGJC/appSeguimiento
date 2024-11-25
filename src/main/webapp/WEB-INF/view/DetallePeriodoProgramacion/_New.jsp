<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post"
           action="${pageContext.request.contextPath}/detalle-periodo-programacion/new">
    <form:hidden path="id_detalle_periodos_programacion"/>
    <form:hidden path="id_plan_pei"/>
    <div class="mb-3">
        <label class="form-label" for="descripcion">Detalle Periodo de Programacion</label>
        <form:textarea type="number" path="descripcion" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="descripcion"/>
    </div>
</form:form>
