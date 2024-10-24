<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post"
           action="${pageContext.request.contextPath}/politicas-desarrollo/new">
    <form:hidden path="id_politica_desarrollo"/>
    <div class="mb-3">
        <label class="form-label" for="id_area_estrategica">Areas estrategicas</label>
        <form:select path="id_area_estrategica" cssClass="form-select"  items="${model.areasEstrategicas}" itemLabel="value" itemValue="id"/>
        <form:errors cssClass="invalid" path="id_area_estrategica"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="politica_desarrollo">Descripcion de Politica de Desarrollo</label>
        <form:textarea path="politica_desarrollo" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="politica_desarrollo"/>
    </div>
</form:form>
