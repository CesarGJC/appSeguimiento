<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fomr" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<div class="content-area">
    <div class="page-title text-center">
        <h1>Documento adjunto a la actividad</h1>
        <h2>${item.descripcion_documento}</h2>
        <form id="downloadForm" action="<c:url value="/documento/adjunto/download"/>"
              method="get">
            <input name="id" value="${item.id_adjunto_operaciones_actividad}" type="hidden">
        </form>
    </div>
</div>
<div class="converter-container">
    <div class="converter-wrapper">
        <div class="left">
            <button data-bs-open="true"
                    data-url="${pageContext.request.contextPath}/documento/adjunto/upload"
                    data-sistema-id="${item.id_adjunto_operaciones_actividad}"
                    data-type="${item.tipo_documento}" class="btn-attcah">
                <i class="fa fa-paperclip"></i>
                Añadir archivo
            </button>
            <span>${item.nombre_documento}</span>
        </div>
        <button onclick="document.getElementById('downloadForm').submit()" class="right"><i
                class="fa fa-cloud-download"></i>
            Descargar
        </button>
    </div>
</div>