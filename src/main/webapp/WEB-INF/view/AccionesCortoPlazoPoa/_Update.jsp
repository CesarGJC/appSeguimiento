<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post" data-target="contenido"
           action="${pageContext.request.contextPath}/accion-corto-plazo/update">
    <form:hidden path="id_accion_corto_plazo_poa"/>
    <form:hidden path="id_objetivos_gestion_poa"/>
    <form:hidden path="id_formulario"/>
    <form:hidden path="id_programa"/>
    <form:hidden path="id_departamento"/>
    <div class="mb-3">
        <label class="form-label" for="id_objetivos_gestion_poa">Objetivo Gestion</label>
        <div class="">
                ${objetivo.objetivos_gestion_poa}
        </div>
    </div>
    <div class="mb-3">
        <label class="form-label" for="accion_corto_plazo_poa">Acciones Corto Plazo</label>
        <form:textarea path="accion_corto_plazo_poa" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="accion_corto_plazo_poa"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="orden">Orden</label>
        <form:input type="number" path="orden" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="orden"/>
    </div>
</form:form>