<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<div class="row">
    <div class="col">
        <p><strong>Área Estratégica:</strong>
            ${areaEstrategica.area_estrategica}
        </p>
    </div>
</div>
<form:form data-form="true" modelAttribute="model" method="post"
           action="${pageContext.request.contextPath}/catalogo-indicadores/new-ajax">
    <form:hidden path="id_catalogo_indicador_pei"/>
    <form:hidden path="id_area_estrategica"/>
    <div class="mb-3">
        <label class="form-label" for="codigo">Codigo indicador</label>
        <form:input type="number" path="codigo" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="codigo"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="id_unidad_medida">Unidades de Medida</label>
        <form:select path="id_unidad_medida" cssClass="form-select filter"  items="${model.tiposUnidades}" itemLabel="value" itemValue="id"/>
        <form:errors cssClass="invalid" path="id_unidad_medida"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="id_tipo_indicador">Tipos Indicadores</label>
        <form:select path="id_tipo_indicador" cssClass="form-select filter"
                     items="${model.tiposIndicadores}"
                     itemLabel="value" itemValue="id"/>
        <form:errors cssClass="invalid" path="id_tipo_indicador"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="id_categoria">Categorias</label>
        <form:select path="id_categoria" cssClass="form-select filter"
                     items="${model.categorias}"
                     itemLabel="value" itemValue="id"/>
        <form:errors cssClass="invalid" path="id_categoria"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="denominacion_indicador">Denominacion Indicadores</label>
        <form:textarea path="denominacion_indicador" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="denominacion_indicador"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="articulacion">Articulaciones</label>
        <form:input path="articulacion" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="articulacion"/>
    </div>
</form:form>