<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../../_css.jsp" %>
    <link href="<c:url value="/static/css/table.bootstrap.css?v=6" />" rel="stylesheet">
</head>
<body class="app sidebar-mini">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-cog"></i> CONFIGURACIONES</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fs-6"></i></li>
            <li class="breadcrumb-item"><a class="enlace" href="<c:url value="/areas-estrategicas/index"/>">Areas</a>
            </li>
        </ul>
    </div>
    <div class="row" id="contenedor">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title"></h3>
                </div>
                <div class="tile-body">
                    <c:forEach var="item" items="${configuraciones}" varStatus="count">
                        <div class="row mb-5">
                            <div class="col-md-6"><h5>${item.etiqueta}</h5></div>
                            <div class="col-md-4 text-center"><span id="${item.clave}">${item.descripcion}</span></div>
                            <div class="col-md-2">
                                <button data-sistema-clave="${item.clave}" data-bs-open="true"
                                        data-url="${pageContext.request.contextPath}${item.url}"
                                        class="btn btn-primary">Cambiar
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="row">
                        <div id="table-container" data-toolbar="true" data-add="true">
                            <jsp:include page="${request.contextPath}/configuracion/listar">
                                <jsp:param name="cookie" value="false"/>
                            </jsp:include>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../../_js.jsp" %>
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script src="<c:url value="/static/js/ajax-form.js?v=1" />"></script>
<script type="module">
    let formulario = new AjaxForm(document.getElementById('contenedor'));
</script>
</body>
</html>