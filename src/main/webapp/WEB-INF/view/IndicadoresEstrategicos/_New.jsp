<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post"
           action="${pageContext.request.contextPath}/indicadores-estrategicos/new">
    <form:hidden path="id_indicador_estrategico"/>
    <div class="mb-3">
        <label class="form-label" for="id_area_estrategica">Areas Estrategicas</label>
        <form:select path="id_area_estrategica" cssClass="form-select filter" data-target="id_politica_desarrollo" data-url="${pageContext.request.contextPath}/indicadores-estrategicos/politicas" items="${model.areasEstrategicas}"
                     itemLabel="value" itemValue="id"/>
        <form:errors cssClass="invalid" path="id_area_estrategica"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="id_politica_desarrollo">Politicas de Desarrollo</label>
        <form:select path="id_politica_desarrollo" cssClass="form-select" items="${model.politicasDesarrollo}"
                     itemLabel="value" itemValue="id"/>
        <form:errors cssClass="invalid" path="id_politica_desarrollo"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="indicador_estrategico">Descripcion de Indicadores Estrategicos</label>
        <form:textarea path="indicador_estrategico" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="indicador_estrategico"/>
    </div>
</form:form>
