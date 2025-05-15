<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fomr" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<img src="<c:url value="${item.ruta_documento}"/>" id="image-${item.id_adjunto_operaciones_actividad}"
     alt="Imagen ${count.count}">
<div class="gallery-info" data-preview="true" data-id="image-${item.id_adjunto_operaciones_actividad}"
     data-description="${item.descripcion_documento}">
    <p>${item.descripcion_documento}</p>
</div>
<button class="btn btn-dark btn-sm btn-top-right rounded-circle"
        data-bs-open="true"
        data-url="${pageContext.request.contextPath}/documento/adjunto/upload"
        data-sistema-id="${item.id_adjunto_operaciones_actividad}"
        data-type="${item.tipo_documento}"
><i
        class="fa fa-pencil"></i></button>
<form name="download" action="<c:url value="/documento/adjunto/download"/>"
      method="get">
    <input name="id" value="${item.id_adjunto_operaciones_actividad}" type="hidden">
    <button type="submit" class="button-download btn-bottom-center"><i
            class="fa fa-cloud-download"></i> Descargar
    </button>
</form>