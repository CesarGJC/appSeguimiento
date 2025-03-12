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
            <h1><i class="fa fa-cog"></i> Configuraciones</h1>
        </div>
    </div>
    <div class="row" id="contenedor">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title"></h3>
                </div>
                <div class="tile-body">
                    <div class="row mb-5">
                        <div class="col-md-6"><h5>${configuraciones.planInstitucional.etiqueta}</h5></div>
                        <div class="col-md-6 text-center">
                            <select onchange="cambiarPlanInstitucional(this)"
                                    data-url="${pageContext.request.contextPath}/configuracion/cambiar-plan"
                                    data-context="${pageContext.request.contextPath}"
                                    class="form-select">
                                <option value="-1">Seleccionar</option>
                                <c:forEach var="item" items="${planes}">
                                    <option value="${item.id}"
                                            <c:if test="${configuraciones.planInstitucional.valor==item.id}">selected</c:if> >${item.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title"></h3>
                </div>
                <div class="tile-body">
                    <div class="row mb-5">
                        <div class="col-md-6"><h5>${configuraciones.periodoGesionPlan.etiqueta}</h5></div>
                        <div class="col-md-6 text-center">
                            <select id="planPeriodoGestion" class="form-select" onchange="cambiarPeriodoGestion(this)"
                                    data-url="${pageContext.request.contextPath}/configuracion/cambiar-gestion">
                                <option value="-1">Seleccionar</option>
                                <c:forEach var="item" items="${gestiones}">
                                    <option value="${item.id}"
                                            <c:if test="${configuraciones.periodoGesionPlan.valor==item.id}">selected</c:if> >${item.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" id="parametros">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title">Formularios</h3>
                </div>
                <div class="tile-body">
                    <div class="row mb-5">
                        <div class="list-group" id="planesFormularios">
                            <c:forEach items="${configuraciones.formularios}" var="item">
                                <a href="<c:url value="/configuracion/permisos"><c:param name="id" value="${item.id_formulario}"/></c:url> "
                                   class="list-group-item list-group-item-action d-flex justify-content-between align-items-center w-100 mb-3 enlace"
                                   aria-current="true">
            <span>
                <strong>Apertura: </strong>${item.codigo} - ${item.apertura_programatica}<br>
                <strong>Area Estrategica: </strong>${item.area_estrategica}
            </span>
                                    <i class="fa fa-chevron-right"></i>
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
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script src="<c:url value="/static/js/app/configuracion/index.js"/>"></script>
</body>
</html>