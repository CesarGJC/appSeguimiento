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
            <h1><i class="fa fa-th-list"></i> Formulario de programacion</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fs-6"></i></li>
            <li class="breadcrumb-item"><a class="enlace" href="<c:url value="/formulario/programacion/index"/>">Formulario</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title">FORMULARIO DE PROGRAMACION DE LA UNIVERSIDAD AUTONOMA DEL BENI "JOSE
                        BALLIVIAN"</h3>
                </div>
                <div class="tile-body">
                    <div class="row" id="target">
                        <form:form modelAttribute="model" method="post"
                                   action="${pageContext.request.contextPath}/formulario/programacion/update">
                            <form:hidden path="id_formulario"/>
                            <div class="mb-3">
                                <label class="form-label" for="id_plan_pei">Plan P.E.I.</label>
                                <form:select path="id_plan_pei" cssClass="form-select filter"
                                             data-target="id_area_estrategica"
                                             data-url="${pageContext.request.contextPath}/formulario/programacion/areas"
                                             items="${model.planes}"
                                             itemLabel="value" itemValue="id"/>
                                <form:errors cssClass="invalid" path="id_plan_pei"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="id_area_estrategica">Area estrategica</label>
                                <form:select path="id_area_estrategica" cssClass="form-select filter"
                                             items="${model.areaEstrategicas}"
                                             itemLabel="value" itemValue="id"/>
                                <form:errors cssClass="invalid" path="id_area_estrategica"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="id_apertura_programatica">Apertura Programatica</label>
                                <form:select path="id_apertura_programatica" cssClass="form-select filter"
                                             items="${model.aperturaProgramaticas}"
                                             itemLabel="value" itemValue="id"/>
                                <form:errors cssClass="invalid" path="id_apertura_programatica"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="titulo">Titulo</label>
                                <form:input path="titulo" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="titulo"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="descripcion">Descripcion</label>
                                <form:textarea path="descripcion" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="descripcion"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="eje">Eje</label>
                                <form:textarea path="eje" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="eje"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="meta">Meta</label>
                                <form:textarea path="meta" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="meta"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="resultado">Resultado</label>
                                <form:textarea path="resultado" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="resultado"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="encargado">Encargado</label>
                                <form:input path="encargado" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="encargado"/>
                            </div>
                        </form:form>
                    </div>
                </div>
                <div class="tile-footer">
                    <button class="btn btn-primary btn-enviar-solicitud" data-name="model" type="button"><i
                            class="bi bi-check-circle-fill me-2"></i>Registrar
                    </button>
                    <a class="btn btn-secondary enlace"
                       href="<c:url value="/formulario/programacion/index"/>"><i
                            class="bi bi-x-circle-fill me-2"></i>Cancelar</a>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../../_js.jsp" %>
<script src="<c:url value="/static/js/ComboBoxBoostrap.js?v=6"/>"></script>
<script>
    let comboboxArea = new ComboBoxBoostrap('.filter', {});
</script>
</body>
</html>