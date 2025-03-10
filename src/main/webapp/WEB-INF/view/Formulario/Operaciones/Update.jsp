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
    <link href="<c:url value="/static/css/quill.snow.css?v=6" />" rel="stylesheet">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Maven+Pro:wght@400..900&family=Roboto:ital,wght@0,100..900;1,100..900&family=Smooch&family=Smooch+Sans:wght@100..900&display=swap');

        .ql-font-arial {
            font-family: Arial, sans-serif;
        }

        .ql-font-georgia {
            font-family: Georgia, serif;
        }

        .ql-font-comic {
            font-family: "Comic Sans MS", cursive;
        }

        .ql-font-roboto {
            font-family: "Roboto", serif;
            font-optical-sizing: auto;
            font-style: normal;
            font-variation-settings: "wdth" 100;
        }

        .ql-font-montserrat {
            font-family: "Montserrat", sans-serif;
        }

        .bd-callout {
            padding: 1.25rem;
            margin-top: 1.25rem;
            margin-bottom: 1.25rem;
            border: 1px solid #e9ecef;
            border-left-width: .25rem;
            border-radius: .25rem
        }

        .bd-callout-info {
            border-left-color: #5bc0de;
        }
    </style>
</head>
<body class="app sidebar-mini">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
        <div>
            <c:if test="${!model.institutoInvestigacion}">
                <h1><i class="fa fa-th-list"></i> ${programaResponse.programa}</h1>
            </c:if>
            <c:if test="${model.institutoInvestigacion}">
                <h1><i class="fa fa-th-list"></i> ${programaResponse.facultad}</h1>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title">Registro de Actividades</h3>
                </div>
                <div class="tile-body">
                    <form:form modelAttribute="model" method="post"
                               action="${pageContext.request.contextPath}/operaciones/update">
                        <form:hidden path="id_operaciones"/>
                        <form:hidden path="id_resultados_gestion"/>
                        <form:hidden path="id_detalle_periodos_programacion"/>
                        <form:hidden path="id_programa"/>
                        <form:hidden path="id_formulario"/>
                        <form:hidden path="id_departamento"/>
                        <div class="mb-3">
                            <label class="form-label" for="id_resultados">Resultados esperados</label>
                            <form:select path="id_resultados"
                                         data-sistema-idPeriodoGestion="${model.id_detalle_periodos_programacion}"
                                         cssClass="form-select filter"
                                         data-url="${pageContext.request.contextPath}/operaciones/resultado"
                                         data-target="result-detail"
                                         items="${model.resultadosEsperados}"
                                         itemLabel="value" itemValue="id"/>
                            <form:errors cssClass="invalid" path="id_resultados"/>
                        </div>
                        <div class="mb-3" id="result-detail">
                            <c:if test="${resultadoEsperado!=null}">
                                <div class="bd-callout bd-callout-info">
                                    <strong>Acciones
                                        estrategicas: </strong> ${resultadoEsperado.acciones_estrategica}<br>
                                    <strong>Indicador: </strong> ${resultadoEsperado.denominacion_indicador} <strong>Formulario: </strong> ${resultadoEsperado.formula}
                                    <strong>Categoria: </strong> ${resultadoEsperado.categoria_indicador} <strong>Unidad: </strong> ${resultadoEsperado.unidad_medidad}(${resultadoEsperado.abreviacion})<br>
                                    <strong>Resultados esperado: </strong> ${resultadoEsperado.descripcion} <strong>Gestion: </strong> ${resultadoEsperado.gestion}
                                    <strong>Linea base: </strong> ${resultadoEsperado.linea_base} <strong>Meta
                                    base: </strong> ${resultadoEsperado.meta_base}<br>
                                </div>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="resultado">OPERACIÓN Y/O ACTIVIDAD</label>
                            <form:textarea path="operaciones" cssClass="form-control"/>
                            <form:errors cssClass="invalid" path="operaciones"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="resultado">Resultados logrados</label>
                            <form:textarea path="resultado" cssClass="form-control"/>
                            <form:errors cssClass="invalid" path="resultado"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="titulo">Titulo de la publicacion</label>
                            <form:input path="titulo" cssClass="form-control"/>
                            <form:errors cssClass="invalid" path="titulo"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="elaborador">Elaborado por</label>
                            <form:input path="elaborador" cssClass="form-control"/>
                            <form:errors cssClass="invalid" path="elaborador"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="elaborador">Fecha de publicacion</label>
                            <form:input type="date" path="fec_publicacion" cssClass="form-control date"/>
                            <form:errors cssClass="invalid" path="fec_publicacion"/>
                        </div>
                        <div class="mb-3">
                            <form:textarea hidden="true" path="descripcion" cssClass="form-control"/>
                            <form:errors cssClass="invalid" path="descripcion"/>
                        </div>
                    </form:form>
                    <div class="mb-3">
                        <label class="form-label" for="id_programa">Descripcion de la publicacion</label>
                        <div id="toolbar">
                            <!-- Selección de fuente -->
                            <select class="ql-font">
                                <option selected></option>
                                <option value="arial">Arial</option>
                                <option value="georgia">Georgia</option>
                                <option value="comic">Comic Sans</option>
                                <option value="roboto">Roboto</option>
                                <option value="montserrat">Montserrat</option>
                            </select>

                            <!-- Selección de tamaño -->
                            <select class="ql-size"></select>

                            <!-- Color de texto -->
                            <select class="ql-color">
                                <option value="red"></option>
                                <option value="blue"></option>
                                <option value="green"></option>
                                <option value="purple"></option>
                                <option value="orange"></option>
                                <option value="pink"></option>
                                <option value="brown"></option>
                                <option value="black"></option>
                                <option value="white"></option>
                                <option selected></option> <!-- Default -->
                            </select>
                            <!-- Negrita, cursiva, subrayado y tachado -->
                            <button class="ql-bold"></button>
                            <button class="ql-italic"></button>
                            <button class="ql-underline"></button>
                            <button class="ql-strike"></button>

                            <!-- Alineación -->
                            <button class="ql-align" value=""></button>
                            <button class="ql-align" value="center"></button>
                            <button class="ql-align" value="right"></button>
                            <button class="ql-align" value="justify"></button>
                        </div>
                        <div id="editor">
                            ${model.descripcion}
                        </div>
                    </div>
                </div>
                <div class="tile-footer">
                    <button class="btn btn-primary btn-enviar-solicitud" data-name="model" type="button"><i
                            class="bi bi-check-circle-fill me-2"></i>Registrar
                    </button>
                    <a class="btn btn-secondary enlace"
                       href="<c:url value="/operaciones/actividades"><c:param name="id" value="${model.id_formulario}"/><c:param name="id_departamento" value="${model.id_departamento}"/><c:param name="id_programa" value="${model.id_programa}"/></c:url>"><i
                            class="bi bi-x-circle-fill me-2"></i>Cancelar</a>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../../_js.jsp" %>
<script src="<c:url value="/static/js/quill.js?v=5"/>"></script>
<script src="<c:url value="/static/js/ComboBoxBoostrap.js?v=5"/>"></script>
<script>
    let comboboxArea = new ComboBoxBoostrap('.filter', {});
    let Font = Quill.import('formats/font');
    Font.whitelist = ['arial', 'georgia', 'comic', 'roboto', 'montserrat'];
    Quill.register(Font, true);
    const quill = new Quill('#editor', {
        theme: 'snow',
        modules: {
            toolbar: '#toolbar'
        }
    });
    quill.on('text-change', function () {
        document.getElementById('descripcion').value = quill.root.innerHTML;
    });
</script>
</body>
</html>

