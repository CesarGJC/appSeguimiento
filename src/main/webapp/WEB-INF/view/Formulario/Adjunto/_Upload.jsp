<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fomr" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<form:form data-form="true" data-target="itemDiv${model.id_adjunto_operaciones_actividad}" modelAttribute="model" method="post"
           enctype="multipart/form-data"
           action="${pageContext.request.contextPath}/documento/adjunto/upload">
    <form:hidden path="id_adjunto_operaciones_actividad"/>
    <form:hidden path="tipo_documento"/>
    <form:hidden path="documento"/>
    <div class="mb-3">
        <div class="upload-area" id="dropArea">
            <input type="file" id="file" name="file" class="file-input">
            <label for="file" class="upload-label">
                <i class="fa fa-cloud-upload"></i>
                Arrastra tus archivos aquí o haz clic para seleccionar
            </label>
        </div>
        <div class="file-details">
            <p id="fileName">Ningún archivo seleccionado</p>
            <p id="uploadStatus"></p>
        </div>
        <form:errors cssClass="invalid" path="file"/>
    </div>
    <div class="mb-3">
        <div class="form-floating">
            <form:textarea path="descripcion_documento" cssClass="form-control" placeholder="Descrpcion del documento"
                           maxlength="100"/>
            <label for="descripcion_documento">Descripcion</label>
        </div>
        <form:errors cssClass="invalid" path="descripcion_documento"/>
    </div>
</form:form>