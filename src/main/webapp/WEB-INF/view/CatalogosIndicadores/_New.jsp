<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post"
           action="${pageContext.request.contextPath}/catalogo-indicadores/new">
    <form:hidden path="id_catalogo_indicador"/>
    <div class="mb-3">
        <label class="form-label" for="id_politica_desarrollo">Politicas de Desarrollo</label>
        <form:select path="id_politica_desarrollo" cssClass="form-select filter" data-target="id_indicador_estrategico" data-url="${pageContext.request.contextPath}/catalogo-indicadores/indicadores" items="${model.politicasDesarrollo}"
                     itemLabel="value" itemValue="id"/>
        <form:errors cssClass="invalid" path="id_politica_desarrollo"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="id_indicador_estrategico">Indicador Estrategico</label>
        <form:select path="id_indicador_estrategico" cssClass="form-select" items="${model.indicador_estrategico}"
                     itemLabel="value" itemValue="id"/>
        <form:errors cssClass="invalid" path="id_indicador_estrategico"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="catalogo_indicador">Descripcion de Catalogo Indicador</label>
        <form:textarea path="catalogo_indicador" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="catalogo_indicador"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="meta">Meta</label>
        <form:textarea path="meta" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="meta"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="linea_base">Linea Base</label>
        <form:textarea path="linea_base" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="linea_base"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="denominacion_indicador">Denominacion de Indicador</label>
        <form:textarea path="denominacion_indicador" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="denominacion_indicador"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="formula">Formula</label>
        <form:textarea path="formula" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="formula"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="medios_verificacion">Medios de Verificacion</label>
        <form:textarea path="medios_verificacion" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="medios_verificacion"/>
    </div>
</form:form>
