<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post" data-target="contenido"
           action="${pageContext.request.contextPath}/objetivos-estrategicos/update">
    <form:hidden path="id_objetivos_estrategicos"/>
    <form:hidden path="id_area_estrategica"/>
    <form:hidden path="id_formulario"/>
    <div class="mb-3">
        <label class="form-label" for="id_area_estrategica">Areas Estrategicas</label>
        <div class="">
                ${area.area_estrategica}
        </div>
    </div>
    <div class="mb-3">
        <label class="form-label" for="objetivos_estrategicos">Objetivos Estrategicos</label>
        <form:textarea path="objetivos_estrategicos" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="objetivos_estrategicos"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="orden">Orden</label>
        <form:input type="number" path="orden" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="orden"/>
    </div>
</form:form>