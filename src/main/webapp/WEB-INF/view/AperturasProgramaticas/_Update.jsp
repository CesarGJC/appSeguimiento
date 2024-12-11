<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form data-form="true" modelAttribute="model" method="post"
           action="${pageContext.request.contextPath}/aperturas-programaticas/update">
    <form:hidden path="id_apertura_programatica"/>
    <div class="mb-3">
        <label class="form-label" for="apertura_programatica">Descripcion</label>
        <form:textarea path="apertura_programatica" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="apertura_programatica"/>
    </div>
    <div class="mb-3">
        <label class="form-label" for="codigo">Codigo</label>
        <form:input path="codigo" cssClass="form-control"/>
        <form:errors cssClass="invalid" path="codigo"/>
    </div>
</form:form>