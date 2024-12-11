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
            <h1><i class="fa fa-th-list"></i> Programacion de actividades</h1>
        </div>
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
                    <div class="invoice">
                        <div class="row mb-4">
                            <div class="col-12 text-center">
                                <img style="width: 45px" src="<c:url value="/static/image/logominiatura.png"/>">
                                <h2 class="page-header">UNIVERSIDAD AUTONOMA DEL BENI "JOSE BALLIVIAN"</h2>
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
                    </div>
                    <div class="row">
                        <div class="col-12 table-responsive">
                            <table class="table-formulario">
                                <thead>
                                <tr>
                                    <th rowspan="2">ÁREA ORGANIZACIONAL RESPONSABLE</th>
                                    <th rowspan="2">OBJETIVOS ESTRATÉGICOS (RESULTADO INSTITUCIONAL - IMPACTO)</th>
                                    <th rowspan="2">ACCIONES ESTRATÉGICAS INSTITUCIONALES (Producto)</th>
                                    <th rowspan="2">DESCRIPCIÓN DE LAS OPERACIONES</th>
                                    <th colspan="6">INDICADOR</th>
                                    <th colspan="${fn:length(formulario.periodos)}">PROGRAMACIÓN FÍSICA</th>
                                    <th rowspan="2">FUENTE DE INFORMACIÓN</th>
                                </tr>
                                <tr>
                                    <th>N°</th>
                                    <th>DESCRIPCION DEL INDICADOR (Resultado/Acción)</th>
                                    <th>UNIDAD DE MEDIDA</th>
                                    <th>FORMULA</th>
                                    <th>LINEA BASE</th>
                                    <th>META</th>
                                    <c:forEach var="item" items="${formulario.periodos}">
                                        <th>${item.gestion}</th>
                                    </c:forEach>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../../_js.jsp" %>
</body>
</html>
