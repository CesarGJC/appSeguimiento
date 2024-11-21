<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post"
           action="${pageContext.request.contextPath}/areas-estrategicas/new">
    <form:hidden path="id_area_estrategica"/>
    <div class="mb-3">
        <label class="form-label" for="area_estrategica">Areas estrategicas</label>
        <form:textarea path="area_estrategica" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="area_estrategica"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="gestion">Gestion</label>
        <form:input path="gestion" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="gestion"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="codigo">Codigo</label>
        <form:input path="codigo" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="codigo"/>
    </div>
</form:form>