<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<div class="row">
    <div id="datostabla" class="col-xl responsive">
        <table data-table="true" class="table table-sm table-hover table-striped table-column-header">
            <thead>
            <tr>
                <th data-resize="false" data-filter="false"
                    data-type="text" data-row-sort="0">Descripcion
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${model.lista}" var="item">
                <tr>
                    <td id="td${item.id_operaciones}">
                        <div class="mb-3">
                            <div class="card p-2">
                                <div class="card-header text-end">
                                    <a class="btn btn-light text-black enlace"
                                       href="<c:url value="/operaciones/update"><c:param name="id_operaciones" value="${item.id_operaciones}"/></c:url> "><i
                                            class="fa fa-pencil"></i></a>
                                    <a class="btn btn-light text-black" data-delete="true"
                                       data-atributes='"id_operaciones": ${item.id_operaciones}'
                                       data-id="${item.id_operaciones}"
                                       href="#"><i class="fa fa-trash-o" ></i></a>
                                </div>
                                <div class="d-flex flex-row">
                                    <img src="${pageContext.request.contextPath}${item.ruta}"
                                         class="rounded-start card-img-custom" alt="Imagen">
                                    <div class="card-body">
                                        <h5 class="card-title">${item.titulo}</h5>
                                        <strong>Descripcion:</strong>
                                        <p class="card-text">${item.descripcion}</p>
                                        <p class="card-text">
                                            <strong>Operaciones / Actividades: </strong>${item.operaciones}<br>
                                            <strong>Resultado logrado: </strong>${item.resultado}<br>
                                            <strong>Programa: </strong>${item.programa}<br>
                                            <strong>Gestion: </strong>${item.gestion}<br>
                                            <strong>Elaborado por: </strong>${item.elaborador}<br>
                                            <strong>Indicador: </strong>${item.indicador}<br>
                                        </p>
                                        <a href="<c:url value="/documento/adjunto/index"><c:param name="id" value="${item.id_operaciones}"/></c:url>" class="btn btn-primary enlace">Ver galeria</a>
                                    </div>
                                </div>
                                <div class="card-footer text-end">
                                    <a href="<c:url value="/documento/adjunto/download-documento"><c:param name="id" value="${item.id_operaciones}"/></c:url>"><i class="fa fa-cloud-download"></i> Descargar documento</a>  |
                                    <strong>Elbaborado en fecha <fmt:formatDate pattern="dd/MM/yyyy" value="${item.fec_publicacion}"/></strong>
                                </div>
                            </div>
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
        <select class="form-select" id="mostrar" name="mostrar" onchange="cargarElementoCategoriaIndicador(1)">
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
                            <a class="page-link" href="javascript:cargarElementoCategoriaIndicador(1);">Anterior</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link"
                               href="javascript:cargarElementoCategoriaIndicador(${model.paginaActual-1});">Anterior</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${model.paginaActual == cantidadpaginas}">
                        <li class="page-item disabled">
                            <a class="page-link"
                               href="javascript:cargarElementoCategoriaIndicador(${cantidadpaginas});">Siguiente</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link"
                               href="javascript:cargarElementoCategoriaIndicador(${model.paginaActual+1});">Siguiente</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="page-item">
                    <a class="page-link" href="javascript:cargarElementoCategoriaIndicador(1);">Primero</a>
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
                                <a class="page-link" href="javascript:cargarElementoCategoriaIndicador(${i});">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item" aria-current="page">
                                <a class="page-link" href="javascript:cargarElementoCategoriaIndicador(${i});">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <li class="page-item">
                    <a class="page-link"
                       href="javascript:cargarElementoCategoriaIndicador(${cantidadpaginas});">Ultimo</a>
                </li>
            </ul>
        </nav>
    </div>
</div>

