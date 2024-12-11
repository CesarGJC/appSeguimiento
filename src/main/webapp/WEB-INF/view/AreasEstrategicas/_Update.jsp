<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post"
           action="${pageContext.request.contextPath}/areas-estrategicas/update">
    <form:hidden path="id_area_estrategica"/>
    <div class="mb-3">
        <label class="form-label" for="id_plan_pei">Planes estrategicos</label>
        <form:select cssClass="form-select" path="id_plan_pei" items="${planes}" itemLabel="value" itemValue="id"/>
        <form:errors cssClass="invalid" path="id_plan_pei"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="area_estrategica">Areas estrategicas</label>
        <form:textarea path="area_estrategica" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="area_estrategica"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="area_estrategica">Orden</label>
        <form:input type="number" path="orden" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="orden"/>
    </div>
</form:form>