<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post" data-target="contenido"
           action="${pageContext.request.contextPath}/descripcion-operaciones/new">
    <form:hidden path="id_descripcion_operaciones_poa"/>
    <form:hidden path="id_accion_corto_plazo_poa"/>
    <form:hidden path="id_formulario"/>
    <div class="mb-3">
        <label class="form-label">Accion Corto Plazo</label>
        <div class="">
                ${descripcion.accion_corto_plazo_poa}
        </div>
    </div>
    <div class="mb-3">
        <label class="form-label" for="id_resultados">Nombre del Proceso</label>
        <form:select path="id_resultados" cssClass="form-select">
            <form:option value="-1" label="Seleccionar"/>
            <form:options items="${formulario.resultadosEsperados}" itemValue="id" itemLabel="value"/>
        </form:select>
        <form:errors cssClass="invalid" path="id_resultados"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="descripcion_operaciones_poa">Descripcion Operaciones</label>
        <form:textarea path="descripcion_operaciones_poa" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="descripcion_operaciones_poa"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="linea_base">Linea Base</label>
        <form:input type="number" path="linea_base" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="linea_base"/>
    </div>
    <h4>Programacion de Metas</h4>
    <div class="mb-3">
        <label class="form-label" for="primerTrimestre">Primer Trimestre</label>
        <form:input type="number" path="primerTrimestre" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="primerTrimestre"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="segundoTrimestre">Segundo Trimestre</label>
        <form:input type="number" path="segundoTrimestre" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="segundoTrimestre"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="tercerTrimestre">Tercer Trimestre</label>
        <form:input type="number" path="tercerTrimestre" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="tercerTrimestre"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="cuartoTrimestre">Cuarto Trimestre</label>
        <form:input type="number" path="cuartoTrimestre" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="cuartoTrimestre"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="orden">Orden</label>
        <form:input type="number" path="orden" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="orden"/>
    </div>
</form:form>