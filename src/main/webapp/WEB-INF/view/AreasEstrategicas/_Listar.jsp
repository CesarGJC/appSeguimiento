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
                <th data-resize="true" data-filter="true"
                    data-type="text" data-row-sort="0">DESCRIPCION
                </th>
                <th data-resize="true" data-filter="true"
                    data-type="text" data-row-sort="1">PLAN PEI
                </th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${model.lista}" var="item">
                <tr>
                    <td data-label="DESCRIPCION">${item.area_estrategica}</td>
                    <td data-label="PLAN PEI">${item.descripcion}</td>
                    <td data-label="OPERACION" id="td${item.id_area_estrategica}">
                        <div class="dropdown">
                            <button class="btn btn-flat text-black" type="button" id="dropdownMenuButton1"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li><a data-update="true"
                                       data-atributes='"id_area_estrategica": ${item.id_area_estrategica}'
                                       data-id="${item.id_area_estrategica}"
                                       href="#"
                                       class="dropdown-item"><i class="fa fa-pencil" aria-hidden="true"></i> Modificar</a></li>
                                <li>
                                    <a class="dropdown-item" data-delete="true"
                                       data-atributes='"id_area_estrategica": ${item.id_area_estrategica}'
                                       data-id="${item.id_area_estrategica}"
                                       href="#"><i class="fa fa-trash" aria-hidden="true"></i> Eliminar</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/catalogo-indicadores/index/${item.id_area_estrategica}"/>"
                                       class="dropdown-item"><i class="fa fa-list" aria-hidden="true"></i> Catalogo indicadores</a></li>
                                <li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-xl-2">
        <select class="form-select" id="mostrar" name="mostrar" onchange="cargarElementoAreas(1)">
            <c:forEach items="${model.mostrarElementos}" var="item">
                <option value="${item.id}" <c:if
                        test="${item.id eq model.mostrar}"> selected </c:if>>${item.value}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-xl-10">
        <nav aria-label="...">
            <ul class="pagination">
                <c:choose>
                    <c:when test="${model.paginaActual == 1}">
                        <li class="page-item disabled">
                            <a class="page-link" href="javascript:cargarElementoAreas(1);">Anterior</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link"
                               href="javascript:cargarElementoAreas(${model.paginaActual-1});">Anterior</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${model.paginaActual == cantidadpaginas}">
                        <li class="page-item disabled">
                            <a class="page-link" href="javascript:cargarElementoAreas(${cantidadpaginas});">Siguiente</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link"
                               href="javascript:cargarElementoAreas(${model.paginaActual+1});">Siguiente</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="page-item">
                    <a class="page-link" href="javascript:cargarElementoAreas(1);">Primero</a>
                </li>
                <c:set var="inicial" value="1"/>
                <c:set var="radio" value="3"/>
                <c:set var="cantidadMaximadepaginas" value="${radio * 2 + 1}"/>
                <c:set var="ultimo"
                       value="${(cantidadpaginas > CantidadMaximadepaginas) ? CantidadMaximadepaginas : cantidadpaginas}"/>
                <c:if test="${model.paginaActual > radio + 1}">
                    <c:set var="inicial" value="${model.paginaActual - radio}"/>
                    <c:choose>
                        <c:when test="${cantidadpaginas > model.paginaActual + radio}">
                            <c:set var="ultimo" value="${model.paginaActual + radio}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="ultimo" value="${cantidadpaginas}"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <c:forEach begin="${inicial}" step="1" end="${ultimo}" var="i">
                    <c:choose>
                        <c:when test="${i == model.paginaActual}">
                            <li class="page-item active" aria-current="page">
                                <a class="page-link" href="javascript:cargarElementoAreas(${i});">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item" aria-current="page">
                                <a class="page-link" href="javascript:cargarElementoAreas(${i});">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <li class="page-item">
                    <a class="page-link" href="javascript:cargarElementoAreas(${cantidadpaginas});">Ultimo</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
