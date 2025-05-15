<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row">
    <div id="datostabla" class="col-xl responsive">
        <table data-table="true" class="table table-sm table-hover table-striped table-column-header">
            <thead>
            <tr>
                <th data-resize="true" data-filter="false"
                    data-type="text" data-row-sort="0">Formulario
                </th>
                <th data-resize="true" data-filter="true"
                    data-type="text" data-row-sort="1">Descripcion
                </th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${formularioResponsesList}">
                <tr>
                    <td data-label="FORMULARIO">${item.codigo} - ${item.apertura_programatica}</td>
                    <td data-label="DESCRIPCION">
                        <strong>Gestion: </strong>${item.gestion}<br>
                        <strong>Formulario: </strong>${item.codigo} - ${item.apertura_programatica}<br>
                        <strong>Descripcion del P.E.I.: </strong>${item.plan_pei}<br>
                        <strong>Area Estrategica: </strong>${item.area_estrategica}<br>
                        <strong>Meta: </strong>${item.meta}<br>
                        <strong>Resultado: </strong>${item.resultado}<br>
                        <strong>Eje: </strong>${item.eje}<br>
                    </td>
                    <td data-label="OPERACION" id="td${model.id_programa}">
                        <div class="dropdown">
                            <button class="btn btn-flat text-black" type="button" id="dropdownMenuButton1"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-ellipsis-v" aria-hidden="true"></i></button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li><a href="<c:url value="/poa/new/${item.id_formulario}"></c:url>"
                                       class="dropdown-item enlace"><i class="fa fa-id-card-o"></i> Elaborar
                                    programacion</a></li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%--<div class="row">--%>
<%--    <div class="col-xl-2">--%>
<%--        <select class="form-select" id="mostrar" name="mostrar" onchange="cargarElementoCategoriaIndicador(1)">--%>
<%--            <c:forEach items="${model.mostrarElementos}" var="item">--%>
<%--                <option value="${item.id}" <c:if--%>
<%--                        test="${item.id eq model.mostrar}"> selected </c:if>>${item.value}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--    </div>--%>
<%--    <div class="col-xl-10">--%>
<%--        <nav aria-label="...">--%>
<%--            <ul class="pagination">--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${model.paginaActual == 1}">--%>
<%--                        <li class="page-item disabled">--%>
<%--                            <a class="page-link" href="javascript:cargarElementoCategoriaIndicador(1);">Anterior</a>--%>
<%--                        </li>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <li class="page-item disabled">--%>
<%--                            <a class="page-link"--%>
<%--                               href="javascript:cargarElementoCategoriaIndicador(${model.paginaActual-1});">Anterior</a>--%>
<%--                        </li>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${model.paginaActual == cantidadpaginas}">--%>
<%--                        <li class="page-item disabled">--%>
<%--                            <a class="page-link"--%>
<%--                               href="javascript:cargarElementoCategoriaIndicador(${cantidadpaginas});">Siguiente</a>--%>
<%--                        </li>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <li class="page-item disabled">--%>
<%--                            <a class="page-link"--%>
<%--                               href="javascript:cargarElementoCategoriaIndicador(${model.paginaActual+1});">Siguiente</a>--%>
<%--                        </li>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--                <li class="page-item">--%>
<%--                    <a class="page-link" href="javascript:cargarElementoCategoriaIndicador(1);">Primero</a>--%>
<%--                </li>--%>
<%--                <c:set var="inicial" value="1"/>--%>
<%--                <c:set var="radio" value="3"/>--%>
<%--                <c:set var="cantidadMaximadepaginas" value="${radio * 2 + 1}"/>--%>
<%--                <c:set var="ultimo"--%>
<%--                       value="${(cantidadpaginas > CantidadMaximadepaginas) ? CantidadMaximadepaginas : cantidadpaginas}"/>--%>
<%--                <c:if test="${model.paginaActual > radio + 1}">--%>
<%--                    <c:set var="inicial" value="${model.paginaActual - radio}"/>--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${cantidadpaginas > model.paginaActual + radio}">--%>
<%--                            <c:set var="ultimo" value="${model.paginaActual + radio}"/>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <c:set var="ultimo" value="${cantidadpaginas}"/>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </c:if>--%>
<%--                <c:forEach begin="${inicial}" step="1" end="${ultimo}" var="i">--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${i == model.paginaActual}">--%>
<%--                            <li class="page-item active" aria-current="page">--%>
<%--                                <a class="page-link" href="javascript:cargarElementoCategoriaIndicador(${i});">${i}</a>--%>
<%--                            </li>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <li class="page-item" aria-current="page">--%>
<%--                                <a class="page-link" href="javascript:cargarElementoCategoriaIndicador(${i});">${i}</a>--%>
<%--                            </li>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </c:forEach>--%>
<%--                <li class="page-item">--%>
<%--                    <a class="page-link"--%>
<%--                       href="javascript:cargarElementoCategoriaIndicador(${cantidadpaginas});">Ultimo</a>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </nav>--%>
<%--    </div>--%>
<%--</div>--%>
