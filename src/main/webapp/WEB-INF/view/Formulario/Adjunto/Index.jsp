<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../../_css.jsp" %>
    <link href="<c:url value="/static/css/galeria-fotos.css" />?v=1" rel="stylesheet">
    <link href="<c:url value="/static/css/upload-document.css" />?v=3" rel="stylesheet">
</head>
<body class="app sidebar-mini">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-body">
                    <div class="container pb-5 portfolio-text">
                        <div class="row">
                            <div class="col-md-12">
                                <h1>Galeria de imagenes</h1>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="gallery-container">
                            <c:forEach items="${documentos.imagenes}" var="item" varStatus="count">
                                <div class="gallery-item" id="itemDiv${item.id_adjunto_operaciones_actividad}">
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
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="container" id="itemDiv${item.id_adjunto_operaciones_actividad}">
                        <c:forEach items="${documentos.documentos}" var="item" varStatus="count">
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
                                    <input name="id" value="${item.id_adjunto_operaciones_actividad}" type="hidden">
                                    <button onclick="document.getElementById('downloadForm').submit()" class="right"><i class="fa fa-cloud-download"></i>
                                        Descargar
                                    </button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../../_js.jsp" %>
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script src="<c:url value="/static/js/galeria-fotos.js?v=2"/>"></script>
<script src="<c:url value="/static/js/upoad-document.js?v=17"/>"></script>
<script src="<c:url value="/static/js/app/formulario/adjunto/index.js"/>"></script>
</body>
</html>
