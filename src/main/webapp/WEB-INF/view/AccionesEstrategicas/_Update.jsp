<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post" data-target="contenido"
           action="${pageContext.request.contextPath}/acciones-estrategicas/update">
    <form:hidden path="id_acciones_estrategica"/>
    <form:hidden path="id_objetivos_estrategicos"/>
    <form:hidden path="id_formulario"/>
    <div class="mb-3">
        <label class="form-label" for="id_objetivos_estrategicos">Objetivo Estrategico</label>
        <div class="">
                ${objetivo.objetivos_estrategicos}
        </div>
    </div>
    <div class="mb-3">
        <label class="form-label" for="acciones_estrategica">Acciones Estrategicas</label>
        <form:textarea path="acciones_estrategica" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="acciones_estrategica"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="orden">Orden</label>
        <form:input type="number" path="orden" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="orden"/>
    </div>
</form:form>