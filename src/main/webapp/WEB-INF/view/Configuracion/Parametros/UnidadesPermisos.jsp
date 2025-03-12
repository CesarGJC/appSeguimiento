<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../../_css.jsp" %>
</head>
<body class="app sidebar-mini">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-cog"></i> Permisos por unidad</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fs-6"></i></li>
            <li class="breadcrumb-item"><a class="enlace" href="<c:url value="/configuracion/index"/>">Configuracion</a>
            <li class="breadcrumb-item"><a class="enlace"
                                           href="<c:url value="/configuracion/permisos"><c:param value="${model.id}" name="id"/></c:url>">Seleccionar
                tipo unidad</a></li>
            </li>
        </ul>
    </div>
    <div class="row" id="contenedor">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title"><c:if test="${model.tipoUnidad==2}">Carreras / Programas</c:if><c:if
                            test="${model.tipoUnidad==1}">Unidades Administrativas</c:if></h3>
                    <p>
                        <a class="btn btn-primary enlace"
                           href="<c:url value="/configuracion/seleccionar-unidad"><c:param name="id" value="${model.id}"/><c:param name="tipoUnidad" value="${model.tipoUnidad}"/></c:url>"><i
                                class="fa fa-plus"></i> Agregar Unidades</a>
                    </p>
                </div>
                <div class="tile-body">
                    <div class="row">
                        <div class="col-sm-6"></div>
                        <div class="col-sm-6">
                            <div class="small fw-light">Buscar</div>
                            <div class="input-group">
                                <input class="form-control border-end-0 border" id="filtro" type="search"
                                       placeholder="Buscar.." onkeyup="filtrarTabla()" aria-describedby="btnBuscar">
                                <button class="btn btn-primary border-start-0 border-bottom-0 border ms-n5"
                                        type="button" onclick="filtrarTabla()"><i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="mt-3">
                        <div class="list-group">
                            <c:forEach items="${unidadesPermisos}" var="item">
                                <a href="#" class="list-group-item list-group-item-action mb-3" aria-current="true">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1 descripcion-unidad">${item.unidad}</h5>
                                        <div class="form-check form-switch">
                                            <input class="form-check-input rounded-1" onchange="valueChanged(this)"
                                                   data-id="${item.id_permisos}"
                                                   data-url="${pageContext.request.contextPath}/configuracion/habilitar"
                                                   <c:if test="${item.habilitado}">checked</c:if> type="checkbox"
                                                   id="checked${item.id_permisos}">
                                            <label class="form-check-label" for="checked${item.id_permisos}"></label>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../../_js.jsp" %>

<script src="<c:url value="/static/js/app/configuracion/permisos-unidad.js"/>"></script>
</body>
</html>
