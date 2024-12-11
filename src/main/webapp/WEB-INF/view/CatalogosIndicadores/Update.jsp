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
            <li class="breadcrumb-item"><a class="enlace"
                                           href="<c:url value="/catalogo-indicadores/index/${model.id_area_estrategica}"/>">Catalogo</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title">MODIFICAR CATÁLOGO DE INDICADORES DE LA UNIVERSIDAD AUTONOMA DEL BENI "JOSE
                        BALLIVIAN"</h3>
                </div>
                <div class="tile-body">
                    <div class="row">
                        <div class="col">
                            <p><strong>Área Estratégica:</strong>
                                ${areaEstrategica.area_estrategica}
                            </p>
                        </div>
                    </div>
                    <div class="row" id="target">
                        <form:form modelAttribute="model" method="post"
                                   action="${pageContext.request.contextPath}/catalogo-indicadores/update">
                            <form:hidden path="id_catalogo_indicador_pei"/>
                            <form:hidden path="id_area_estrategica"/>
                            <div class="mb-3">
                                <label class="form-label" for="id_unidad_medida">Unidades de Medida</label>
                                <div class="input-group mb-3">
                                    <form:select path="id_unidad_medida" cssClass="form-select filter"
                                                 data-target="id_catalogo_indicador_pei"
                                                 data-url="${pageContext.request.contextPath}/catalogo-indicadores/indicadores"
                                                 items="${model.tiposUnidades}"
                                                 itemLabel="value" itemValue="id"/>
                                    <button data-target="id_unidad_medida"
                                            data-url="${pageContext.request.contextPath}/unidad-medida/new"
                                            class="btn btn-primary btn-add-item" type="button"><i
                                            class="fa fa-plus"></i>
                                    </button>
                                </div>
                                <form:errors cssClass="invalid" path="id_unidad_medida"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="id_tipo_indicador">Tipos Indicadores</label>
                                <div class="input-group mb-3">
                                    <form:select path="id_tipo_indicador" cssClass="form-select filter"
                                                 data-target="id_tipo_indicador"
                                                 data-url="${pageContext.request.contextPath}/catalogo-indicadores/indicadores"
                                                 items="${model.tiposIndicadores}"
                                                 itemLabel="value" itemValue="id"/>
                                    <button data-target="id_tipo_indicador"
                                            data-url="${pageContext.request.contextPath}/tipo-indicador/new"
                                            class="btn btn-primary btn-add-item" type="button"><i
                                            class="fa fa-plus"></i>
                                    </button>
                                </div>
                                <form:errors cssClass="invalid" path="id_tipo_indicador"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="id_categoria">Categorias</label>
                                <div class="input-group mb-3">
                                    <form:select path="id_categoria" cssClass="form-select filter"

                                                 items="${model.categorias}"
                                                 itemLabel="value" itemValue="id"/>
                                    <button data-target="id_categoria"
                                            data-url="${pageContext.request.contextPath}/categoria-indicador/new"
                                            class="btn btn-primary btn-add-item" type="button"><i
                                            class="fa fa-plus"></i>
                                    </button>
                                </div>
                                <form:errors cssClass="invalid" path="id_categoria"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="denominacion_indicador">Denominacion Indicadores</label>
                                <form:textarea path="denominacion_indicador" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="denominacion_indicador"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="articulacion">Articulaciones</label>
                                <form:input path="articulacion" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="articulacion"/>
                            </div>
                        </form:form>
                    </div>
                </div>
                <div class="tile-footer">
                    <button class="btn btn-primary btn-enviar-solicitud" data-name="model" type="button"><i
                            class="bi bi-check-circle-fill me-2"></i>Registrar
                    </button>
                    <a class="btn btn-secondary enlace"
                       href="<c:url value="/catalogo-indicadores/index/${model.id_area_estrategica}"/>"><i
                            class="bi bi-x-circle-fill me-2"></i>Cancelar</a>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../_js.jsp" %>
<script src="<c:url value="/static/js/sweetalert.min.js" />"></script>
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script type="application/javascript" src="<c:url value="/static/js/items-combobox.js?v=14"/>"></script>
<script>
    let itemsCombo = new itemsComboBox(document.getElementById('target')
        , {});
</script>
</body>
</html>