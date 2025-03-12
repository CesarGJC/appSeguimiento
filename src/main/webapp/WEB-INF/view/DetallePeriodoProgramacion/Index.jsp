<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../_css.jsp" %>
    <link href="<c:url value="/static/css/table.bootstrap.css?v=6" />" rel="stylesheet">
</head>
<body class="app sidebar-mini">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Plan ${plan.gestion}</h1>
            <p> ${plan.descripcion}</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fs-6"></i></li>
            <li class="breadcrumb-item"><a href="<c:url value="/pei/index"/>">Admin P.E.I.</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title">Detalle Periodos</h3>
                </div>
                <div class="tile-body">
                    <button id="addButton" type="button"
                            data-atributes='"id_plan_pei": ${model.option.id}'></button>
                    <div class="row d-flex flex-row-reverse mb-3">

                        <div class="col-md-4">
                            <div class="small fw-light">Buscar</div>
                            <div class="input-group">
                                <input onkeyup="filtrarResultados(${model.pagina})"
                                       class="form-control border-end-0 border" id="buscar"
                                       type="search" placeholder="Buscar.."
                                       aria-describedby="btnBuscar">
                                <button class="btn btn-primary border-start-0 border-bottom-0 border ms-n5"
                                        type="button"
                                        onclick="cargarElementoDetallePeriodoProgramacion(${model.pagina})"><i
                                        class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="small fw-light">Buscar por</div>
                            <input type="hidden" id="id" name="id" value="${model.option.id}">
                            <select id="opcion" class="form-select">
                                <c:forEach var="item" items="${opciones}">
                                    <option value="${item.id}"
                                            <c:if test="${item.id == model.option.opcion}"> selected </c:if>>${item.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div id="table-container" data-toolbar="true" data-add="true"
                             data-url-add="${pageContext.request.contextPath}/detalle-periodo-programacion/new"
                             data-url-data="${pageContext.request.contextPath}/detalle-periodo-programacion/listar-filtrar"
                             data-url-update="${pageContext.request.contextPath}/detalle-periodo-programacion/update"
                             data-url-delete="${pageContext.request.contextPath}/detalle-periodo-programacion/delete"
                             class="table-main-container col-xl">
                            <jsp:include page="${request.contextPath}/detalle-periodo-programacion/listar">
                                <jsp:param name="pagina" value="${model.pagina}"/>
                                <jsp:param name="mostrar" value="${model.mostrar}"/>
                                <jsp:param name="opcion" value="${model.option.opcion}"/>
                                <jsp:param name="id" value="${model.option.id}"/>
                            </jsp:include>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../_js.jsp" %>
<script src="<c:url value="/static/js/sweetalert.min.js" />"></script>
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script src="<c:url value="/static/js/table.bootstrap.js?v=63"/>"></script>
<script src="<c:url value="/static/js/ComboBoxBoostrap.js?v=6"/>"></script>
<script src="<c:url value="/static/js/app/detalle-periodo-programacion/index.js"/>"></script>
<c:if test="${result !=null}">
    <script>
        setTimeout(function () {
            document.querySelector('.alert').classList.add('d-none');
        }, 5000);
    </script>
</c:if>
</body>
</html>
