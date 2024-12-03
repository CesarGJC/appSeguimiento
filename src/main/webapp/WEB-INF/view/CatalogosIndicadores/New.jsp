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
<main class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> ${areaEstrategica.descripcion}</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title">CATÁLOGO DE INDICADORES DE LA UNIVERSIDAD AUTONOMA DEL BENI "JOSE BALLIVIAN"</h3>
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
                        <form:form data-form="true" modelAttribute="model" method="post"
                                   action="${pageContext.request.contextPath}/catalogo-indicadores/new">
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
                                    <button data-target="id_unidad_medida" data-url="${pageContext.request.contextPath}/unidad-medida/new" class="btn btn-primary btn-add-item" type="button"><i class="fa fa-plus"
                                                                                     aria-hidden="true"></i>
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
                                    <button data-target="id_tipo_indicador" data-url="${pageContext.request.contextPath}/tipo-indicador/new" class="btn btn-primary btn-add-item" type="button"><i class="fa fa-plus"
                                                                                     aria-hidden="true"></i>
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
                                    <button  data-target="id_categoria" data-url="${pageContext.request.contextPath}/categoria-indicador/new" class="btn btn-primary btn-add-item" type="button"><i class="fa fa-plus"
                                                                                     aria-hidden="true"></i>
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
                                <form:textarea path="articulacion" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="articulacion"/>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../_js.jsp" %>
<script type="application/javascript" src="<c:url value="/static/js/items-combobox.js?v=3"/>"></script>
<script>
    let itemsCombo= new itemsComboBox(document.getElementById('target')
    ,{});
</script>
</body>
</html>