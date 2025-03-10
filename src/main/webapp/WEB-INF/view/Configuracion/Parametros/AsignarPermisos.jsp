<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../../_css.jsp" %>
    <link href="<c:url value="/static/css/bootstrap-duallistbox.css" />" rel="stylesheet">
</head>
<body class="app sidebar-mini rtl">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-cog"></i> Asignar permisos </h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a class="enlace" href="<c:url value="/configuracion/index"/>">Configuracion</a>
            </li>
            <li class="breadcrumb-item"><a class="enlace"
                                           href="<c:url value="/configuracion/permisos"><c:param value="${model.id}" name="id"/></c:url>">Seleccionar
                tipo unidad</a></li>
            <li class="breadcrumb-item"><a class="enlace"
                                           href="<c:url value="/configuracion/permisos-unidad"><c:param name="tipoUnidad" value="${model.tipoUnidad}"/><c:param value="${model.id}" name="id"/></c:url>">Unidades</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title"><c:if test="${model.tipoUnidad==2}">Carreras / Programas</c:if><c:if
                            test="${model.tipoUnidad==1}">Unidades Administrativas</c:if></h3>
                </div>
                <div id="contenido" class="tile-body">
                    <div class="col">
                        <form:form method="post" modelAttribute="asignar"
                                   action="${pageContext.request.contextPath}/configuracion/asignar">
                            <form:hidden path="id_formulario"/>
                            <form:hidden path="id_tipo_unidad"/>
                            <form:select path="id_unidad" items="${unidadesPermisos}" itemLabel="value" size="30"
                                         title="permisos[]" itemValue="id" multiple="true"/>
                            <button type="button"
                                    class="btn btn-primary btn-block btn-enviar-solicitud" data-name="asignar">Asignar
                            </button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../../_js.jsp" %>
<script src="<c:url value="/static/js/duallistbox.js?v=4" />"></script>
<script src="<c:url value="/static/js/toast.boostrap.js" />"></script>
<script>
    let listBox = new ListBox(document.getElementById('id_unidad'), {
        nonSelectedListLabel: 'Unidades no seleccionadas',
        selectedListLabel: 'Unidades seleccionadas',
        preserveSelectionOnMove: 'moved',
        moveOnSelect: false,
        helperSelectNamePostfix: 'id_unidad'
    });
</script>
</body>
</html>