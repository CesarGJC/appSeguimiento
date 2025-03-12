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
    <style>
        .card-img-custom {
            width: 150px;height: 150px; object-fit: cover;
            transition: transform 0.3s ease-in-out;
            cursor: pointer;
        }
        /* Efecto de Zoom */
        .card img:hover {
            transform: scale(1.1);
        }

    </style>
</head>
<body class="app sidebar-mini">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> ${programaResponse.programa}</h1>
            <p>Registro de actividades</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fs-6"></i></li>
            <li class="breadcrumb-item"><a class="enlace" href="<c:url value="/operaciones/index"/>">Proyectos</a>
            </li>
        </ul>
    </div>
    <c:if test="${result !=null}">
        <div class="${result.className}" role="alert">
                ${result.message}
        </div>
    </c:if>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title">${item.codigo} ${item.apertura_programatica}</h3>
                </div>
                <div class="tile-body">
                    <div class="row mb-3">
                        <div class="col">
                            <strong>Gestion: </strong>${item.gestion}<br>
                            <strong>Meta: </strong>${item.meta}<br>
                            <strong>Resultado: </strong>${item.resultado}<br>
                            <strong>Area Estrategica: </strong>${item.area_estrategica}<br>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col">
                            <a class="btn btn-primary enlace"
                               href="<c:url value="/operaciones/new"><c:param name="id_programa" value="${modelPrograma.id_programa}"/><c:param name="id_departamento" value="${modelPrograma.id_departamento}"/><c:param name="id_formulario" value="${item.id_formulario}"/></c:url> "><i
                                    class="fa fa-plus"></i> Actividad</a>
                        </div>
                    </div>
                    <div class="row d-flex flex-row-reverse mb-3">
                        <div class="col-md-5">
                            <div class="input-group">
                                <input data-input="true" onkeyup="filtrarResultados(${model.pagina})"
                                       class="form-control border-end-0 border" id="buscar"
                                       data-id="${modelPrograma.id}"
                                       data-id_programa="${modelPrograma.id_programa}"
                                       data-id_departamento="${modelPrograma.id_departamento}"
                                       type="search" placeholder="Buscar.."
                                       aria-describedby="btnBuscar">
                                <button class="btn btn-primary border-start-0 border-bottom-0 border ms-n5"
                                        type="button" onclick="cargarElementoFormulario(${model.pagina})"><i
                                        class="fa fa-filter"></i>
                                </button>
                                <button class="btn btn-primary border-start-0 border-bottom-0 border ms-n5"
                                        type="button" onclick="limpiarCoockieElementoFormulario(${model.pagina})"><i
                                        class="fa fa-eraser"></i></button>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <select id="condiciones" data-condition="true" class="form-select">

                            </select>
                        </div>
                        <div class="col-md-4">
                            <select id="option" data-filter="true" class="form-select">
                                <c:forEach var="item" items="${opciones}">
                                    <option data-type="${item.type}" value="${item.id}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div id="table-container" data-toolbar="true" data-add="true"
                             data-url-data="${pageContext.request.contextPath}/operaciones/listar"
                             data-url-delete="${pageContext.request.contextPath}/operaciones/delete"
                             class="table-main-container col-xl">
                            <jsp:include page="${request.contextPath}/operaciones/listar">
                                <jsp:param name="pagina" value="${model.pagina}"/>
                                <jsp:param name="mostrar" value="${model.mostrar}"/>
                                <jsp:param name="id" value="${modelPrograma.id}"/>
                                <jsp:param name="id_programa" value="${modelPrograma.id_programa}"/>
                                <jsp:param name="id_departamento" value="${modelPrograma.id_departamento}"/>
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
<script src="<c:url value="/static/js/sweetalert.min.js" />"></script>
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script src="<c:url value="/static/js/table.bootstrap.js?v=63"/>"></script>
<script src="<c:url value="/static/js/filter-table.js?v=2"/>"></script>
<script src="<c:url value="/static/js/app/formulario/operaciones/actividades.js"/>"></script>
<c:if test="${result !=null}">
    <script>
        setTimeout(function () {
            document.querySelector('.alert').classList.add('d-none');
        }, 5000);
    </script>
</c:if>
</body>
</html>
