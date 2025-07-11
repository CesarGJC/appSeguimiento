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
    <link href="<c:url value="/static/css/introjs.min.css" />" rel="stylesheet">
</head>
<body class="app sidebar-mini">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Resultados</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title">Resultados esperados por cada accion</h3>
                    <p> <button onclick="iniciartutorial()" class="btn btn-primary rounded-5">
                        <i class="fa fa-question"></i>
                    </button></p>
                </div>
                <div class="tile-body">
                    <div class="row">
                        <div class="col">
                            <strong>Area Estrategica: </strong> ${acciones.area_estrategica}<br>
                            <strong>Objetivo Estrategica: </strong> ${acciones.objetivos_estrategicos}<br>
                            <strong>Accion Estrategica: </strong> ${acciones.acciones_estrategica}<br>
                        </div>
                    </div>
                    <div class="row" id="target">
                        <form:form modelAttribute="model" method="post"
                                   action="${pageContext.request.contextPath}/resultados/new">
                            <form:hidden path="id_acciones_estrategica"/>
                            <form:hidden path="id_formulario"/>
                            <form:hidden path="id_area_estrategica"/>
                            <form:hidden path="id_plan_pei"/>
                            <div data-step="1" data-intro="<br>Selecciona el Indicador del Catálogo Básico de Indicadores." class="mb-3">
                                <label class="form-label" for="id_catalogo_indicador_pei">Indicadores</label>
                                <div class="input-group mb-3">
                                    <form:select path="id_catalogo_indicador_pei" cssClass="form-select filter"
                                                 items="${model.catalogosIndicadores}"
                                                 itemLabel="value" itemValue="id"/>
                                    <button data-target="id_catalogo_indicador_pei"
                                            data-sistema-id_area_estrategica="${model.id_area_estrategica}"
                                            data-url="${pageContext.request.contextPath}/catalogo-indicadores/new-ajax"
                                            class="btn btn-primary btn-add-item" type="button"><i
                                            class="fa fa-plus"></i>
                                    </button>
                                </div>
                                <form:errors cssClass="invalid" path="id_catalogo_indicador_pei"/>
                            </div>
                            <div data-step="2" data-intro="<br>Ingresa la Descripcion de la Operación." class="mb-3 mt-3">
                                <label class="form-label" for="descripcion">Descripcion de Operaciones/Actividades</label>
                                <form:textarea path="descripcion" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="descripcion"/>
                            </div>
                            <div data-step="3" data-intro="<br>Describe la Formula del Indicador. (N/%)" class="mb-3">
                                <label class="form-label" for="formula">Formula</label>
                                <form:input path="formula" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="formula"/>
                            </div>
                            <div data-step="4" data-intro="<br>Describe la situación inicial, considerando el último quinquenio de planificación, es el punto de orientación para medir el grado de avance de la planificación en los procesos de seguimiento y evaluación." class="mb-3">
                                <label class="form-label" for="linea_base">Linea Base</label>
                                <form:input type="number" path="linea_base" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="linea_base"/>
                            </div>
                            <div data-step="5" data-intro="<br>Describe la cuantificación o cualificación del indicador. Cuando se le asigna una meta o valor a un indicador éste empieza a tener significado." class="mb-3">
                                <label class="form-label" for="meta_base">Meta Base</label>
                                <form:input type="number" path="meta_base" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="meta_base"/>
                            </div>
                            <div data-step="6" data-intro="<br>Se describe los Medios de Verificación." class="mb-3">
                                <label class="form-label" for="fuente_informacion">Medios de Verificación</label>
                                <form:textarea path="fuente_informacion" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="fuente_informacion"/>
                            </div>
                            <div data-step="7" data-intro="Se describe los Resultados por Gestión." class="mb-3">
                                <h1>Resultados por Gestion</h1>
                            </div>
                            <c:forEach var="item" items="${periodos}">
                                <div class="mb-3">
                                    <input type="hidden"
                                           id="id_resultados_gestion_${item.id_detalle_periodos_programacion}"
                                           name="id_resultados_gestion_${item.id_detalle_periodos_programacion}"
                                           value="${item.id_resultados_gestion}">
                                    <label class="form-label"
                                           for="descripcion_${item.id_detalle_periodos_programacion}">${item.descripcionPeriodo}</label>
                                    <input type="number" class="form-control"
                                           id="descripcion_${item.id_detalle_periodos_programacion}"
                                           name="descripcion_${item.id_detalle_periodos_programacion}"
                                           value="${item.descripcion}">
                                </div>
                            </c:forEach>
                        </form:form>
                    </div>
                </div>
                <div class="tile-footer">
                    <button class="btn btn-primary btn-enviar-solicitud" data-name="model" type="button"><i
                            class="bi bi-check-circle-fill me-2"></i>Registrar
                    </button>
                    <a class="btn btn-secondary enlace"
                       href="<c:url value="/programacion/detalle/index/${model.id_formulario}"/>"><i
                            class="bi bi-x-circle-fill me-2"></i>Cancelar</a>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../../_js.jsp" %>
<script src="<c:url value="/static/js/sweetalert.min.js" />"></script>
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script src="<c:url value="/static/js/intro.min.js" />"></script>
<script type="application/javascript" src="<c:url value="/static/js/items-combobox.js?v=17"/>"></script>
<script>
    let itemsCombo = new itemsComboBox(document.getElementById('target')
        , {});
    function iniciartutorial() {
        introJs().setOptions({
            nextLabel: "Siguiente",
            prevLabel: "Atras",
            skipLabel: "Salir",
            doneLabel: "Gracias",
            tooltipClass: "",
            exitOnOverlayClick: false,
            showStepNumbers: true
        }).start();
    }
</script>
</body>
</html>
