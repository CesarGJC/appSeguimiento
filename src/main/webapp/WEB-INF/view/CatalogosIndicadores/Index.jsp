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
            <h1><i class="fa fa-th-list"></i> ${areaEstrategica.descripcion}</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fs-6"></i></li>
            <li class="breadcrumb-item"><a class="enlace" href="<c:url value="/areas-estrategicas/index"/>">Areas</a>
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
                    <h3 class="title">CATÁLOGO DE INDICADORES DE LA UNIVERSIDAD AUTONOMA DEL BENI "JOSE BALLIVIAN"</h3>
                </div>
                <div class="tile-body">
                    <div class="row">
                        <div class="col">
                            <a class="btn btn-primary enlace"
                               href="<c:url value="/catalogo-indicadores/new"><c:param name="id_area_estrategica" value="${areaEstrategica.id_area_estrategica}"/></c:url>"><i
                                    class="fa fa-plus" aria-hidden="true"></i> Catalogo indicador</a>
                        </div>
                    </div>
                    <div class="row d-flex flex-row-reverse mb-3">
                        <div class="col-md-4">
                            <div class="small fw-light">Buscar</div>
                            <div class="input-group">
                                <input onkeyup="filtrarResultados(${model.pagina})"
                                       class="form-control border-end-0 border" id="buscar"
                                       type="search" placeholder="Buscar.."
                                       aria-describedby="btnBuscar">
                                <button class="btn btn-primary border-start-0 border-bottom-0 border ms-n5"
                                        type="button" onclick="cargarElementoCatalogos(${model.pagina})"><i
                                        class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="small fw-light">Buscar por</div>
                            <input type="hidden" id="id" name="id" value="${model.option.id}">
                            <select id="option" class="form-select">
                                <c:forEach var="item" items="${opciones}">
                                    <option value="${item.id}"
                                            <c:if test="${item.id == model.option.opcion}"> selected </c:if>>${item.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <p><strong>Área Estratégica:</strong>
                                ${areaEstrategica.area_estrategica}
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div id="table-container" data-toolbar="true" data-add="true"
                             data-url-data="${pageContext.request.contextPath}/catalogo-indicadores/listar-filtrar"
                             data-url-delete="${pageContext.request.contextPath}/catalogo-indicadores/delete"
                             class="table-main-container col-xl">
                            <jsp:include page="${request.contextPath}/catalogo-indicadores/listar">
                                <jsp:param name="pagina" value="${model.pagina}"/>
                                <jsp:param name="mostrar" value="${model.mostrar}"/>
                                <jsp:param name="id" value="${model.option.id}"/>
                                <jsp:param name="opcion" value="${model.option.opcion}"/>
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
<script src="<c:url value="/static/js/app/catalogo-indicadores/index.js"/>"></script>
<c:if test="${result !=null}">
    <script>
        setTimeout(function () {
            document.querySelector('.alert').classList.add('d-none');
        }, 5000);
    </script>
</c:if>
</body>
</html>