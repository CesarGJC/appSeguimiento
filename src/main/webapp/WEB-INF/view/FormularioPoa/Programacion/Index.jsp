<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../../_css.jsp" %>
    <style>
        .table-formulario {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        .table-formulario td, .table-formulario th {
            border: 1px solid #16365C;
            padding: 8px;
        }

        .table-formulario tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .table-formulario tr:hover {
            background-color: #ddd;
        }

        .table-formulario th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #16365C;
            text-align: center;
            color: white;
        }
    </style>
</head>
<body class="app sidebar-mini">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> ${programaResponse.programa}</h1>
            <p>Programacion de actividades</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fs-6"></i></li>
            <li class="breadcrumb-item"><a class="enlace" href="<c:url value="/poa/seleccionar-unidad"/>">Formulario</a>
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
                    <h3 class="title"></h3>
                </div>
                <div class="tile-body">
                    <div id="contenedor" class="invoice">
                        <div class="row mb-4">
                            <div class="col-12 text-center">
                                <img style="width: 45px" src="<c:url value="/static/image/logominiatura.png"/>">
                                <h2 class="page-header">UNIVERSIDAD AUTONOMA DEL BENI "JOSE BALLIVIAN"</h2>
                                <h3>Gestion ${formulario.gestion_periodo}</h3>
                            </div>
                        </div>
                        <div class="row invoice-info">
                            <div class="col-6">
                                <address>
                                    <strong>ÁREA ESTRATÉGICA: </strong> ${formulario.area_estrategica}<br>
                                    <strong>PROGRAMA: </strong> ${formulario.apertura_programatica}<br>
                                    <strong>CODIGO: </strong> ${formulario.codigo}<br>
                                </address>
                            </div>
                            <div class="col-6">
                                <address class="text-end">
                                    <strong>${formulario.plan_pei}</strong><br>
                                    <strong>OBJETIVOS ESTRATÉGICOS INSTITUCIONALES</strong><br>
                                    <strong>${formulario.titulo}</strong><br>
                                </address>
                            </div>
                        </div>
                        <div class="row invoice-info mb-4">
                            <div class="col-12">
                                <address class="text-center">
                                    <strong>${formulario.descripcion}</strong>
                                </address>
                            </div>
                        </div>
                        <div class="row invoice-info">
                            <div class="col-12">
                                <address>
                                    <strong>Eje 5: </strong> ${formulario.eje}<br>
                                    <strong>Meta: </strong> ${formulario.meta}<br>
                                    <strong>Resultado: </strong> ${formulario.resultado}<br>
                                </address>
                            </div>
                        </div>
                        <div data-step="1"
                             data-intro="<br>Selecciona el Resultados Esperados al cual desea registrar la actividad."
                             class="mb-3">
                            <div class="col-3 col-md-3">
                                <button class="btn btn-primary btn-block"
                                        id="addButton"
                                        data-url="${pageContext.request.contextPath}/objetivo-gestion-poa/new"
                                        data-bs-open="true"
                                        data-sistema-id_detalle_periodos_programacion="${formulario.id_detalle_periodos_programacion}"
                                        data-sistema-id_formulario="${formulario.id_formulario}"
                                        data-sistema-id_programa="${id_programa}"
                                        data-sistema-id_departamento="${id_departamento}"
                                        data-sistema-id_resultados="-1">
                                    <i class="fa fa-plus"></i> Objetivos de gestion
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 table-responsive">
                                <table class="table-formulario">
                                    <thead>
                                    <tr>
                                        <th rowspan="3">OBJETIVO DE GESTIÓN INSTITUCIONAL</th>
                                        <th rowspan="3">ACCIÓN DE CORTO PLAZO</th>
                                        <th rowspan="3">DESCRIPCIÓN DE OPERACIONES</th>
                                        <th colspan="3">BIEN NORMA O SERVICIO</th>
                                        <th colspan="7">INDICADOR</th>
                                        <th colspan="15">PROGRAMACIÓN Y EJECUCIÓN DE METAS</th>
                                    </tr>
                                    <tr>
                                        <th rowspan="2">RESULTADOS LOGRADOS</th>
                                        <th rowspan="2">MEDIOS DE VERIFICACIÓN</th>
                                        <th rowspan="2">COD.</th>
                                        <th rowspan="2">DESCRIPCION DEL INDICADOR</th>
                                        <th rowspan="2">UNIDAD DE MEDIDA</th>
                                        <th rowspan="2">FORMULA</th>
                                        <th rowspan="2">LINEA BASE ${formulario.gestion}</th>
                                        <th rowspan="2">META ESTIMADA ${formulario.gestion}</th>
                                        <th rowspan="2">META</th>
                                        <th colspan="3">PRIMER TRIMESTRE</th>
                                        <th colspan="3" >SEGUNDO TRIMESTRE</th>
                                        <th colspan="3">TERCER TRIMESTRE</th>
                                        <th colspan="3">CUARTO TRIMESTRE</th>
                                        <th colspan="3">TOTAL</th>
                                    </tr>
                                    <tr>
                                        <th>Prog.</th>
                                        <th>Ejec.</th>
                                        <th>%</th>
                                        <th>Prog.</th>
                                        <th>Ejec.</th>
                                        <th>%</th>
                                        <th>Prog.</th>
                                        <th>Ejec.</th>
                                        <th>%</th>
                                        <th>Prog.</th>
                                        <th>Ejec.</th>
                                        <th>%</th>
                                        <th>Prog.</th>
                                        <th>Ejec.</th>
                                        <th>%</th>
                                    </tr>
                                    </thead>
                                    <tbody id="contenido">
                                    <jsp:include page="${request.contextPath}/poa/formulario">
                                        <jsp:param name="id" value="${formulario.id_formulario}"/>
                                        <jsp:param name="id_programa" value="${id_programa}"/>
                                        <jsp:param name="id_departamento" value="${id_departamento}"/>
                                        <jsp:param name="id_detalle_periodos_programacion"
                                                   value="${formulario.id_detalle_periodos_programacion}"/>
                                    </jsp:include>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</main>
<%@ include file="../../_js.jsp" %>
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script src="<c:url value="/static/js/ajax-form.js?v=1" />"></script>
<script>
    let formulario = new AjaxForm(document.getElementById('contenedor'));
</script>

</body>
</html>
