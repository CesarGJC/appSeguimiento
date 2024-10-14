<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../_css.jsp" %>
    <link href="<c:url value="/static/css/table.bootstrap.css?v=6" />" rel="stylesheet">
</head>
<body>
<main class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-address-card" aria-hidden="true"></i> Administraci&oacute;n de Grupos</h1>
            <p>Programa: <c:out value="${programa.programa}"/> Plan: <c:out value="${datosPrgPlan.id_plan}"/> Tipo
                Evaluacion: <c:out value="${datosTipoEvaluacion.tipo_evaluacion}"/> Gestíon: <c:out
                        value="${model.gestion}"/> Periodo: <c:out value="${model.periodo}"/></p>
            <p>${nombres}</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home"></i></li>
            <li class="breadcrumb-item"><a class="enlace"
                                           href="<c:url value="/grupos/entrada"></c:url>">periodo/gestion</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title">Grupos</h3>
                    <p>
                        <a class="btn btn-warning icon-btn"
                           data-periodo="${model.periodo}" data-gestion="${model.gestion}"
                           data-id_programa="${model.id_programa}"
                           data-id_tipo_evaluacion="${datosTipoEvaluacion.id_tipo_evaluacion}"
                           data-id_plan="${datosPrgPlan.id_plan}"
                           data-id_prg_plan="${datosPrgPlan.id_prg_plan}"
                           data-id_tipo_grado="${datosPrgPlan.id_tipo_grado}"
                           data-url="${pageContext.request.contextPath}/grupos/grupos"
                           href="#" onclick="generarGruposA(event)"><i class="fa fa-plus"></i> Generar grupos</a>
                    </p>
                </div>
                <div class="tile-body responsive">
                    <button id="addButton" type="button"
                            data-atributes='"periodo": ${model.periodo},
                     "id_programa": ${model.id_programa}, "id_prg_plan": ${model.id_prg_plan},
                     "id_tipo_evaluacion": ${datosTipoEvaluacion.id_tipo_evaluacion},  "gestion": ${model.gestion},
                     "id_plan": "${datosPrgPlan.id_plan}", "id_departamento": ${programa.id_departamento}, "id_tipo_grado": ${datosPrgPlan.id_tipo_grado}'></button>
                    <div id="table-container" data-toolbar="true"
                         data-search="false"
                         data-add="true"
                         id="table-container"
                         data-url-add="<c:url value="/grupos/nuevo"/>"
                         data-url-data=""
                         data-url-update="<c:url value="/grupos/modificar"/>"
                         data-url-delete="<c:url value="/grupos/eliminar"/>"
                         class="table-main-container">
                        <table data-table="true" class="table table-sm table-column-header">
                            <thead>
                            <tr>
                                <th data-resize="true" data-sortable="true" data-filter="true"
                                    data-type="text" data-row-sort="0">Materia
                                </th>
                                <th data-resize="true" data-sortable="true" data-filter="true"
                                    data-type="text" data-row-sort="1">Lugar y<br> Tipo sistema
                                </th>
                                <th data-resize="true" data-sortable="true" data-filter="true"
                                    data-type="text" data-row-sort="2">Grupo
                                </th>
                                <th>Cupo Actual</th>
                                <th>Cupo M&aacute;ximo</th>
                                <th>Horas</th>
                                <th>Nro. Resoluci&oacute;n</th>
                                <th>Fecha Resoluci&oacute;n</th>
                                <th>Accion</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="grupo" items="${lGrupos}" varStatus="cont">
                                <tr>
                                    <td data-label="Materia"><c:out value="${grupo.materia}"/></td>
                                    <td data-label="lugar"><c:out value="${grupo.tipo_sistema}"/></td>
                                    <td data-label="Grupo"><c:out value="${grupo.grupo}"/></td>
                                    <td data-label="Cupo Actual"><c:out value="${grupo.cupo_actual}"/></td>
                                    <td data-label="upo M&aacute;ximo"><c:out value="${grupo.cupo_max}"/></td>
                                    <td data-label="Horas"><c:out value="${grupo.horas}"/></td>
                                    <td data-label="Nro. Resoluci&oacute;n"><c:out
                                            value="${grupo.nro_resolucion}"/></td>
                                    <td data-label="Fecha Resoluci&oacute;n"><c:out value="${grupo.fechita}"/></td>
                                    <td data-label="Accion" id="td${grupo.id_dpto_grupo}">
                                        <div class="dropdown">
                                            <button class="btn btn-light text-dark dropdown-toggle" type="button"
                                                    id="dropdownMenuButton1" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                            </button>
                                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                                <li><a class="dropdown-item" data-update="true"
                                                       data-atributes='"id_dpto_grupo": ${grupo.id_dpto_grupo}, "id_programa": ${model.id_programa},
"id_prg_plan":${model.id_prg_plan}, "id_plan":  ${datosPrgPlan.id_plan},"id_tipo_grado": ${datosPrgPlan.id_tipo_grado}'
                                                       data-id="${grupo.id_dpto_grupo}"
                                                       href="#">Modificar</a>
                                                </li>
                                                <li><a class="dropdown-item" data-delete="true"
                                                       data-id="${grupo.id_dpto_grupo}"
                                                       data-atributes='"id_dpto_grupo": ${grupo.id_dpto_grupo}, "id_programa": ${model.id_programa},
"id_prg_plan":${model.id_prg_plan}, "id_plan":  ${datosPrgPlan.id_plan},"id_tipo_grado": ${datosPrgPlan.id_tipo_grado}'
                                                       href="#">Eliminar</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../_js.jsp" %>
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script src="<c:url value="/static/js/table.bootstrap.js?v=58"/>"></script>
<script>
    //let modal = new bootstrap.Modal(document.getElementById("grupoModal"));
    let table = new TableBoostrap(document.getElementById('table-container'), {
        container: document.getElementById('table-container'),
        title: "Lista de paralelos habilitados",
        add: document.getElementById('addButton'),
        params: {
            gestion: 2024,
            search: null,
            pagina: 1
        },
        loadData: (data) => {
            console.log('hola');
        }
    });
    let getGrupos = async (input) => {
        let url = input.dataset.url;
        let data = {
            id_materia: input.value,
            gestion: document.getElementById('gestion').value,
            periodo: document.getElementById('periodo').value,
            id_grupo: document.getElementById('id_grupo').value
        }
        console.log(data);
        document.getElementById('id_grupo').innerHTML = await Get(url, data);
    };

    async function generarGruposA(event) {
        event.preventDefault();
        let data = {
            periodo: event.target.dataset.periodo,
            gestion: event.target.dataset.gestion,
            id_programa: event.target.dataset.id_programa,
            id_tipo_evaluacion: event.target.dataset.id_tipo_evaluacion,
            id_plan: event.target.dataset.id_plan,
            id_tipo_grado: event.target.dataset.id_tipo_grado,
            id_prg_plan: event.target.dataset.id_prg_plan
        };
        let result = await Get(event.target.dataset.url, data);
        table.Body.innerHTML = result.toString();
        table.Send.setAttribute("data-option", "data");
        table.Modal.show();
    }
</script>
</body>
</html>