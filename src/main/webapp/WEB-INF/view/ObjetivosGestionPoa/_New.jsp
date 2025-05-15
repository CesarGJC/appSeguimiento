<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h4>${programaResponse.programa}</h4>
<form:form data-form="true" modelAttribute="model" method="post" data-target="contenido"
           action="${pageContext.request.contextPath}/objetivo-gestion-poa/new">
    <form:hidden path="id_objetivos_gestion_poa"/>
    <form:hidden path="id_detalle_periodos_programacion"/>
    <form:hidden path="id_formulario"/>
    <form:hidden path="id_programa"/>
    <form:hidden path="id_departamento"/>
    <div class="mb-3">
        <label class="form-label" for="id_detalle_periodos_programacion">Detalle Periodo Programacion</label>
        <div class="">
                ${detalle.descripcion}
        </div>
    </div>
    <div class="mb-3">
        <label class="form-label" for="objetivos_gestion_poa">Objetivos de Gestion</label>
        <form:textarea path="objetivos_gestion_poa" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="objetivos_gestion_poa"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="orden">Orden</label>
        <form:input type="number" path="orden" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="orden"/>
    </div>
</form:form>