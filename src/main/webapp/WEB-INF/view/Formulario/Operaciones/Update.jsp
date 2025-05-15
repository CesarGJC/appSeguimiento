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
            <h1><i class="fa fa-th-list"></i> ${programaResponse.programa}</h1>
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
                                              <form:hidden path="id_operaciones_actividad"/>
                        <form:hidden path="id_descripcion_operaciones_poa"/>
                        <form:hidden path="id_programa"/>
                        <form:hidden path="id_formulario"/>
                        <form:hidden path="id_departamento"/>
                        <form:hidden path="porcentaje"/>
                        <div data-step="1"
                             data-intro="<br>Se describe Detalle las Actividades, Tareas y/o Proyectos (Inversión) ejecutados de acuerdo a las Operaciones. "
                             class="mb-3">
                            <label class="form-label" for="resultado">Resultados logrados</label>
                            <form:textarea path="resultado" cssClass="form-control"/>
                            <form:errors cssClass="invalid" path="resultado"/>
                        </div>
                        <div data-step="2" data-intro="<br> Seleccionar en el trimestre que sera reportado la actividad"
                             class="mb-3">
                            <label class="form-label" for="id_trimestre">Trimestre</label>
                            <form:select path="id_trimestre" cssClass="form-select" items="${model.trimestre}"
                                         itemLabel="value" itemValue="id"/>
                            <form:errors cssClass="invalid" path="id_trimestre"/>
                        </div>
                        <c:if test="${model.porcentaje}">
                            <div data-step="3"
                                 data-intro="<br>El porcentaje de avance de la actividad "
                                 class="mb-3">
                                <label class="form-label" for="resultado">Porcentaje de progreso</label>
                                <form:input type="number" path="progreso" cssClass="form-control"/>
                                <form:errors cssClass="invalid" path="progreso"/>
                            </div>
                        </c:if>
                        <h4>Detalle para su publicacion</h4>
                        <div data-step="4" data-intro="<br>Ingresa el Titulo de la Publicacion/Actividad." class="mb-3">
                            <label class="form-label" for="titulo">Titulo de la Actividad</label>
                            <form:input path="titulo" cssClass="form-control"/>
                            <form:errors cssClass="invalid" path="titulo"/>
                        </div>
                        <div data-step="5" data-intro="<br>Ingresa el nombre del responsable o encargado asignado."
                             class="mb-3">
                            <label class="form-label" for="elaborador">Elaborado por</label>
                            <form:input path="elaborador" cssClass="form-control"/>
                            <form:errors cssClass="invalid" path="elaborador"/>
                        </div>
                        <div data-step="6" data-intro="<br>Ingrese la fecha de Actividad." class="mb-3">
                            <label class="form-label" for="elaborador">Fecha de Actividad</label>
                            <form:input type="date" path="fec_publicacion" cssClass="form-control date"/>
                            <form:errors cssClass="invalid" path="fec_publicacion"/>
                        </div>
                        <div class="mb-3">
                            <form:textarea hidden="true" path="descripcion" cssClass="form-control"/>
                            <form:errors cssClass="invalid" path="descripcion"/>
                        </div>
                    </form:form>
                    <div class="mb-3">
                        <label class="form-label" for="id_programa">Descripcion de la Actividad</label>
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
                       href="<c:url value="/operaciones/actividades"><c:param name="id" value="${model.id_formulario}"/><c:param name="id_descripcion_operaciones_poa" value="${model.id_descripcion_operaciones_poa}"/><c:param name="id_departamento" value="${model.id_departamento}"/><c:param name="id_programa" value="${model.id_programa}"/></c:url>"><i
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

