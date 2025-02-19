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
</head>
<body class="app sidebar-mini">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-cog"></i> CONFIGURACIONES</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fs-6"></i></li>
            <li class="breadcrumb-item"><a class="enlace" href="<c:url value="/areas-estrategicas/index"/>">Areas</a>
            </li>
        </ul>
    </div>
    <div class="row" id="contenedor">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title"></h3>
                </div>
                <div class="tile-body">
                    <c:forEach var="item" items="${configuraciones}" varStatus="count">
                        <div class="row mb-5">
                            <div class="col-md-6"><h5>${item.etiqueta}</h5></div>
                            <div class="col-md-4 text-center"><span id="${item.clave}">${item.descripcion}</span></div>
                            <div class="col-md-2">
                                <button data-sistema-clave="${item.clave}" data-bs-open="true"
                                        data-url="${pageContext.request.contextPath}${item.url}"
                                        class="btn btn-primary">Cambiar
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                    <table data-table="true" class="table table-sm table-hover table-striped table-column-header">
                        <thead>
                        <tr>
                            <th data-resize="true" data-filter="false"
                                data-type="text" data-row-sort="0">Encargado
                            </th>
                            <th data-resize="true" data-filter="true"
                                data-type="text" data-row-sort="1">Descripcion
                            </th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${formularioResponsesList}" var="item">
                            <tr>
                                <td data-label="ENCARGADO">${item.encargado}</td>
                                <td data-label="ABREVIACION">
                                    <strong>${item.titulo}</strong><br>
                                    <strong>Gestion: </strong>${item.gestion}<br>
                                    <strong>Descripcion del P.E.I.: </strong>${item.plan_pei}<br>
                                    <strong>Area Estrategica: </strong>${item.area_estrategica}<br>
                                    <strong>Apertura: </strong>${item.codigo} - ${item.apertura_programatica}<br>
                                    <strong>Eje: </strong>${item.eje}<br>
                                    <strong>Meta: </strong>${item.meta}<br>
                                    <strong>Resultado: </strong>${item.resultado}<br>

                                </td>
                                <td data-label="OPERACION" id="td${item.id_formulario}">
                                    <div class="dropdown">
                                        <button class="btn btn-flat text-black" type="button" id="dropdownMenuButton1"
                                                data-bs-toggle="dropdown" aria-expanded="false">
                                            <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                            <li>
                                                <a href="<c:url value="/programacion/detalle/index/${item.id_formulario}"></c:url>"
                                                   class="dropdown-item enlace"><i class="fa fa-id-card-o"></i> Elaborar
                                                    programacion</a></li>
                                            <li>
                                                <a href="<c:url value="/formulario/programacion/update"><c:param name="id" value="${item.id_formulario}"/></c:url>"
                                                   class="dropdown-item enlace"><i
                                                        class="fa fa-pencil"></i>Modificar</a></li>
                                            <li>
                                                <a class="dropdown-item" data-delete="true"
                                                   data-atributes='"id_formulario": ${item.id_formulario}'
                                                   data-id="${item.id_formulario}"
                                                   href="#"><i class="fa fa-trash-o"></i> Eliminar</a>
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
</main>
<%@ include file="../../_js.jsp" %>
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script src="<c:url value="/static/js/ajax-form.js?v=1" />"></script>
<script type="module">
    let formulario = new AjaxForm(document.getElementById('contenedor'));

</script>
</body>
</html>