<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<c:set var="columns" value="${fn:length(formulario.periodos)}"/>
<c:if test="${fn:length(formulario.formularioResultadosResponseList)==0}">
    <tr>
        <td>
                ${formulario.encargado}
            <button id="addButton"
                    data-url="${pageContext.request.contextPath}/objetivos-estrategicos/new"
                    class="btn btn-primary"
                    data-bs-open="true"
                    data-sistema-id_formulario="${formulario.id_formulario}"
                    data-sistema-id_area_estrategica="${formulario.id_area_estrategica}"><i
                    class="fa fa-plus"></i>
                Objetivos Est.
            </button>
        </td>
    </tr>
</c:if>
<c:if test="${fn:length(formulario.formularioResultadosResponseList)>0}">
    <c:set var="id_formulario" value="-1"/>
    <c:set var="id_objetivos_estrategicos" value="-1"/>
    <c:set var="id_acciones_estrategica" value="-1"/>
    <c:forEach var="item" items="${formulario.formularioResultadosResponseList}" varStatus="cont">
        <tr>
            <c:if test="${item.id_formulario != id_formulario}">
                <td rowspan="<c:out value="${fn:length(formulario.formularioResultadosResponseList)}"/>">
                        ${formulario.encargado}
                    <button id="addButton"
                            data-url="${pageContext.request.contextPath}/objetivos-estrategicos/new"
                            class="btn btn-primary"
                            data-bs-open="true"
                            data-sistema-id_formulario="${formulario.id_formulario}"
                            data-sistema-id_area_estrategica="${formulario.id_area_estrategica}"><i
                            class="fa fa-plus"></i>
                        Objetivos Est.
                    </button>
                </td>
                <c:set var="id_formulario" value="${item.id_formulario}"/>
            </c:if>
            <c:if test="${item.objetivoEstrategiaResponse == null}">
                <c:forEach var="i" begin="1" end="${columns+10}">
                    <td></td>
                </c:forEach>
            </c:if>
            <c:if test="${item.objetivoEstrategiaResponse != null}">
                <c:if test="${item.objetivoEstrategiaResponse.id_objetivos_estrategicos != id_objetivos_estrategicos}">
                    <c:set var="rowspan" value="0"/>
                    <c:if test="${item.accionEstrategicaResponse.cantidad>item.objetivoEstrategiaResponse.cantidad}">
                        <c:set var="rowspan" value="${item.accionEstrategicaResponse.cantidad}"/>
                    </c:if>
                    <c:if test="${item.accionEstrategicaResponse.cantidad<=item.objetivoEstrategiaResponse.cantidad}">
                        <c:set var="rowspan" value="${item.objetivoEstrategiaResponse.cantidad}"/>
                    </c:if>
                    <td rowspan="<c:out value="${rowspan}"/>">
                            ${item.objetivoEstrategiaResponse.objetivos_estrategicos}
                        <div class="dropdown">
                            <button class="btn btn-flat text-black" type="button" id="dropdownMenuButton1"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li>
                                    <a data-url="${pageContext.request.contextPath}/acciones-estrategicas/new"
                                       class="dropdown-item" data-bs-open="true"
                                       data-sistema-id_formulario="${formulario.id_formulario}"
                                       data-sistema-id_objetivos_estrategicos="${item.objetivoEstrategiaResponse.id_objetivos_estrategicos}"><i
                                            class="fa fa-plus"> </i> Acciones Estrategicas</a></li>
                                <li><a data-url="${pageContext.request.contextPath}/objetivos-estrategicos/update"
                                       class="dropdown-item" data-bs-open="true"
                                       data-sistema-id_formulario="${formulario.id_formulario}"
                                       data-sistema-id_objetivos_estrategicos="${item.objetivoEstrategiaResponse.id_objetivos_estrategicos}"><i
                                        class="fa fa-pencil"></i> Modificar</a></li>
                                <li>
                                    <a data-url="${pageContext.request.contextPath}/objetivos-estrategicos/delete"
                                       class="dropdown-item" data-bs-open="true"
                                       data-sistema-id_formulario="${formulario.id_formulario}"
                                       data-sistema-id_objetivos_estrategicos="${item.objetivoEstrategiaResponse.id_objetivos_estrategicos}"><i
                                            class="fa fa-trash"> </i> Eliminar</a></li>
                            </ul>
                        </div>
                    </td>
                    <c:set var="id_objetivos_estrategicos"
                           value="${item.objetivoEstrategiaResponse.id_objetivos_estrategicos}"/>
                </c:if>
                <c:if test="${item.accionEstrategicaResponse == null}">
                    <c:forEach var="i" begin="1" end="${columns+9}">
                        <td></td>
                    </c:forEach>
                </c:if>
                <c:if test="${item.accionEstrategicaResponse != null}">
                    <c:if test="${item.accionEstrategicaResponse.id_acciones_estrategica != id_acciones_estrategica}">
                        <td rowspan="<c:out value="${item.accionEstrategicaResponse.cantidad}"/>">
                                ${item.accionEstrategicaResponse.acciones_estrategica}
                            <div class="dropdown">
                                <button class="btn btn-flat text-black" type="button"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li>
                                        <a href="<c:url value="/resultados/new"> <c:param name="id_plan_pei" value="${formulario.id_plan_pei}"/>  <c:param name="id_area_estrategica" value="${item.id_area_estrategica}"/><c:param name="id_formulario" value="${formulario.id_formulario}"/><c:param name="id_acciones_estrategica" value="${item.accionEstrategicaResponse.id_acciones_estrategica}"/></c:url>"
                                           class="dropdown-item enlace"><i
                                                class="fa fa-plus"> </i> Resultados</a></li>
                                    <li><a data-url="${pageContext.request.contextPath}/acciones-estrategicas/update"
                                           class="dropdown-item" data-bs-open="true"
                                           data-sistema-id_formulario="${formulario.id_formulario}"
                                           data-sistema-id_acciones_estrategica="${item.accionEstrategicaResponse.id_acciones_estrategica}"><i
                                            class="fa fa-pencil"></i> Modificar</a></li>
                                    <li>
                                        <a data-url="${pageContext.request.contextPath}/acciones-estrategicas/delete"
                                           class="dropdown-item" data-bs-open="true"
                                           data-sistema-id_formulario="${formulario.id_formulario}"
                                           data-sistema-id_acciones_estrategica="${item.accionEstrategicaResponse.id_acciones_estrategica}"><i
                                                class="fa fa-trash"> </i> Eliminar</a></li>
                                </ul>
                            </div>
                        </td>
                        <c:set var="id_acciones_estrategica"
                               value="${item.accionEstrategicaResponse.id_acciones_estrategica}"/>
                    </c:if>
                    <c:if test="${item.resultadosResponse == null}">
                        <c:forEach var="i" begin="1" end="${columns+8}">
                            <td></td>
                        </c:forEach>
                    </c:if>
                    <c:if test="${item.resultadosResponse != null}">
                        <td>
                                ${item.resultadosResponse.descripcion}
                            <div class="dropdown">
                                <button class="btn btn-flat text-black" type="button" id="btnResultados"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li>
                                        <a href="<c:url value="/resultados/update"><c:param name="id_plan_pei" value="${formulario.id_plan_pei}"/> <c:param name="id" value="${item.resultadosResponse.id_resultados}"/></c:url> "
                                           class="dropdown-item enlace"><i
                                                class="fa fa-pencil"></i> Modificar</a></li>
                                    <li>
                                        <a data-url="${pageContext.request.contextPath}/resultados/delete"
                                           class="dropdown-item" data-bs-open="true"
                                           data-sistema-id="${item.resultadosResponse.id_resultados}"><i
                                                class="fa fa-trash"> </i> Eliminar</a></li>
                                </ul>
                            </div>
                        </td>
                        <td>${item.resultadosResponse.codigo}</td>
                        <td>${item.resultadosResponse.denominacion_indicador}</td>
                        <td>${item.resultadosResponse.abreviacionUnidadMedida}</td>
                        <td>${item.resultadosResponse.formula}</td>
                        <c:choose>
                            <c:when test="${item.resultadosResponse.abreviacionUnidadMedida=='%'}">
                                <td>${item.resultadosResponse.linea_base}${item.resultadosResponse.abreviacionUnidadMedida}</td>
                                <td>${item.resultadosResponse.meta_base}${item.resultadosResponse.abreviacionUnidadMedida}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${item.resultadosResponse.linea_base}</td>
                                <td>${item.resultadosResponse.meta_base}</td>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="periodo" items="${item.resultadosResponse.resultadosGestionResponseList}">
                            <c:choose>
                                <c:when test="${item.resultadosResponse.abreviacionUnidadMedida=='%'}">
                                    <td>${periodo.descripcionResultado}${item.resultadosResponse.abreviacionUnidadMedida}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${periodo.descripcionResultado}</td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <td>${item.resultadosResponse.fuente_informacion}</td>
                    </c:if>
                </c:if>
            </c:if>
        </tr>
    </c:forEach>
</c:if>

