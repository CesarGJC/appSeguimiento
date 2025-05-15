<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<c:if test="${fn:length(formulario.objetivoGestionPoaFormularioResponses)==0}">
</c:if>
<c:if test="${fn:length(formulario.objetivoGestionPoaFormularioResponses)>0}">
    <c:set var="id_formulario" value="-1"/>
    <c:set var="id_objetivos_gestion_poa" value="-1"/>
    <c:set var="id_accion_corto_plazo_poa" value="-1"/>
    <c:set var="id_descripcion_operaciones_poa" value="-1"/>
    <c:forEach var="item" items="${formulario.objetivoGestionPoaFormularioResponses}" varStatus="cont">
        <tr>
            <c:if test="${item.id_formulario != id_formulario}">
                <c:set var="id_formulario" value="${item.id_formulario}"/>
            </c:if>
            <c:if test="${item.objetivoGestionPoaResponse == null}">
                <c:forEach var="i" begin="1" end="${11}">
                    <td></td>
                </c:forEach>
            </c:if>
            <c:if test="${item.objetivoGestionPoaResponse != null}">
                <c:if test="${item.objetivoGestionPoaResponse.id_objetivos_gestion_poa != id_objetivos_gestion_poa}">
                    <c:set var="rowspan" value="0"/>
                    <c:if test="${item.accionCortoPlazoPoaResponse.cantidad>item.objetivoGestionPoaResponse.cantidad}">
                        <c:set var="rowspan" value="${item.accionCortoPlazoPoaResponse.cantidad}"/>
                    </c:if>
                    <c:if test="${item.accionCortoPlazoPoaResponse.cantidad<=item.objetivoGestionPoaResponse.cantidad}">
                        <c:set var="rowspan" value="${item.objetivoGestionPoaResponse.cantidad}"/>
                    </c:if>
                    <td rowspan="<c:out value="${rowspan}"/>">
                            ${item.objetivoGestionPoaResponse.objetivos_gestion_poa}
                        <div class="dropdown">
                            <button class="btn btn-flat text-black" type="button" id="dropdownMenuButton1"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li>
                                    <a data-url="${pageContext.request.contextPath}/accion-corto-plazo/new"
                                       class="dropdown-item" data-bs-open="true"
                                       data-sistema-id_formulario="${formulario.id_formulario}"
                                       data-sistema-id_objetivos_gestion_poa="${item.objetivoGestionPoaResponse.id_objetivos_gestion_poa}"><i
                                            class="fa fa-plus"> </i> Acciones Corto Plazo</a></li>
                                <li><a data-url="${pageContext.request.contextPath}/objetivo-gestion-poa/update"
                                       class="dropdown-item" data-bs-open="true"
                                       data-sistema-id_formulario="${formulario.id_formulario}"
                                       data-sistema-id_programa="${formulario.id_programa}"
                                       data-sistema-id_departamento="${formulario.id_departamento}"
                                       data-sistema-id_objetivos_gestion_poa="${item.objetivoGestionPoaResponse.id_objetivos_gestion_poa}"><i
                                        class="fa fa-pencil"></i> Modificar</a></li>
                                <li>
                                    <a data-url="${pageContext.request.contextPath}/objetivo-gestion-poa/delete"
                                       class="dropdown-item" data-bs-open="true"
                                       data-sistema-id_formulario="${formulario.id_formulario}"
                                       data-sistema-id_programa="${formulario.id_programa}"
                                       data-sistema-id_departamento="${formulario.id_departamento}"
                                       data-sistema-id_objetivos_gestion_poa="${item.objetivoGestionPoaResponse.id_objetivos_gestion_poa}"><i
                                            class="fa fa-trash"> </i> Eliminar</a></li>
                            </ul>
                        </div>
                    </td>
                    <c:set var="id_objetivos_gestion_poa"
                           value="${item.objetivoGestionPoaResponse.id_objetivos_gestion_poa}"/>
                </c:if>
                <c:if test="${item.accionCortoPlazoPoaResponse == null}">
                    <c:forEach var="i" begin="1" end="${11}">
                        <td></td>
                    </c:forEach>
                </c:if>
                <c:if test="${item.accionCortoPlazoPoaResponse != null}">
                    <c:if test="${item.accionCortoPlazoPoaResponse.id_accion_corto_plazo_poa != id_accion_corto_plazo_poa}">
                        <td rowspan="<c:out value="${item.accionCortoPlazoPoaResponse.cantidad}"/>">
                                ${item.accionCortoPlazoPoaResponse.accion_corto_plazo_poa}
                            <div class="dropdown">
                                <button class="btn btn-flat text-black" type="button"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li>
                                        <a data-url="${pageContext.request.contextPath}/descripcion-operaciones/new"
                                           class="dropdown-item" data-bs-open="true"
                                           data-sistema-id_formulario="${formulario.id_formulario}"
                                           data-sistema-id_accion_corto_plazo_poa="${item.accionCortoPlazoPoaResponse.id_accion_corto_plazo_poa}"><i
                                                class="fa fa-plus"> </i> Descripcion Operaciones</a></li>
                                    <li><a data-url="${pageContext.request.contextPath}/accion-corto-plazo/update"
                                           class="dropdown-item" data-bs-open="true"
                                           data-sistema-id_formulario="${formulario.id_formulario}"
                                           data-sistema-id_programa="${formulario.id_programa}"
                                           data-sistema-id_departamento="${formulario.id_departamento}"
                                           data-sistema-id_accion_corto_plazo_poa="${item.accionCortoPlazoPoaResponse.id_accion_corto_plazo_poa}"><i
                                            class="fa fa-pencil"></i> Modificar</a></li>
                                    <li>
                                        <a data-url="${pageContext.request.contextPath}/accion-corto-plazo/delete"
                                           class="dropdown-item" data-bs-open="true"
                                           data-sistema-id_formulario="${formulario.id_formulario}"
                                           data-sistema-id_programa="${formulario.id_programa}"
                                           data-sistema-id_departamento="${formulario.id_departamento}"
                                           data-sistema-id_accion_corto_plazo_poa="${item.accionCortoPlazoPoaResponse.id_accion_corto_plazo_poa}"><i
                                                class="fa fa-trash"> </i> Eliminar</a></li>
                                </ul>
                            </div>
                        </td>
                        <c:set var="id_accion_corto_plazo_poa"
                               value="${item.accionCortoPlazoPoaResponse.id_accion_corto_plazo_poa}"/>
                    </c:if>
                    <c:if test="${item.descripcionOperacionesPoaResponse == null}">
                        <c:forEach var="i" begin="1" end="${10}">
                            <td></td>
                        </c:forEach>
                    </c:if>
                    <c:if test="${item.descripcionOperacionesPoaResponse != null}">
                        <td>
                                ${item.descripcionOperacionesPoaResponse.descripcion_operaciones_poa}
                            <div class="dropdown">
                                <button class="btn btn-flat text-black" type="button"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li>
                                        <a class="dropdown-item enlace"
                                           href="<c:url value="/operaciones/actividades"> <c:param name="id_programa" value="${formulario.id_programa}"/> <c:param name="id_departamento" value="${formulario.id_departamento}"/> <c:param name="id_descripcion_operaciones_poa" value="${item.descripcionOperacionesPoaResponse.id_descripcion_operaciones_poa}"/> <c:param name="id" value="${formulario.id_formulario}" /> </c:url>"><i
                                                class="fa fa-plus"> </i> Agregar Actividades</a></li>
                                    <li><a data-url="${pageContext.request.contextPath}/descripcion-operaciones/update"
                                           class="dropdown-item" data-bs-open="true"
                                           data-sistema-id_formulario="${formulario.id_formulario}"
                                           data-sistema-id_descripcion_operaciones_poa="${item.descripcionOperacionesPoaResponse.id_descripcion_operaciones_poa}"><i
                                            class="fa fa-pencil"></i> Modificar</a></li>
                                    <li>
                                        <a data-url="${pageContext.request.contextPath}/descripcion-operaciones/delete"
                                           class="dropdown-item" data-bs-open="true"
                                           data-sistema-id_formulario="${formulario.id_formulario}"
                                           data-sistema-id_descripcion_operaciones_poa="${item.descripcionOperacionesPoaResponse.id_descripcion_operaciones_poa}"><i
                                                class="fa fa-trash"> </i> Eliminar</a></li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ol>
                                <c:forEach items="${item.descripcionOperacionesPoaResponse.resultadosEsperados}"
                                           var="activiades">
                                    <li>${activiades.value}</li>
                                </c:forEach>
                            </ol>
                        </td>
                        <td></td>
                        <td>${item.descripcionOperacionesPoaResponse.codigo}</td>
                        <td>${item.descripcionOperacionesPoaResponse.denominacion_indicador}</td>
                        <td>${item.descripcionOperacionesPoaResponse.tipo_unidad}</td>
                        <td>${item.descripcionOperacionesPoaResponse.formula}</td>
                        <c:choose>
                            <c:when test="${item.descripcionOperacionesPoaResponse.tipo_unidad == '%'}">
                                <td>${item.descripcionOperacionesPoaResponse.linea_base}%</td>
                                <td>${item.descripcionOperacionesPoaResponse.meta_base_estimada}%</td>
                                <td>${item.descripcionOperacionesPoaResponse.meta_base}%</td>
                            </c:when>
                            <c:otherwise>
                                <td>${item.descripcionOperacionesPoaResponse.linea_base}</td>
                                <td>${item.descripcionOperacionesPoaResponse.meta_base_estimada}</td>
                                <td>${item.descripcionOperacionesPoaResponse.meta_base}</td>
                            </c:otherwise>
                        </c:choose>
                        <c:set value="0" var="programados"/>
                        <c:set value="0" var="ejecutados"/>
                        <c:forEach var="trimestre" items="${item.descripcionOperacionesPoaResponse.detalleTrimestre}">

                            <c:set var="porcentaje"
                                   value="${trimestre.programado==0?0:(trimestre.ejecutado*100)/trimestre.programado}"/>
                            <c:set value="${trimestre.programado+programados}" var="programados"/>
                            <c:set value="0${trimestre.ejecutado+ejecutados}" var="ejecutados"/>
                            <c:choose>
                                <c:when test="${item.descripcionOperacionesPoaResponse.tipo_unidad == '%'}">
                                    <td>${trimestre.programado}%</td>
                                    <td>${trimestre.ejecutado}%</td>
                                    <td><fmt:formatNumber value="${porcentaje}" type="number" maxFractionDigits="2"
                                                          minFractionDigits="2"/>%
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>${trimestre.programado}</td>
                                    <td>${trimestre.ejecutado}</td>
                                    <td><fmt:formatNumber value="${porcentaje}" type="number" maxFractionDigits="2"
                                                          minFractionDigits="2"/></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:set var="porcentaje" value="${programados==0?0:(ejecutados*100)/programados}"/>
                        <td>${programados}</td>
                        <td>${ejecutados}</td>
                        <td><fmt:formatNumber value="${porcentaje}" type="number" maxFractionDigits="2"
                                              minFractionDigits="2"/></td>
                    </c:if>
                </c:if>
            </c:if>
        </tr>
    </c:forEach>
</c:if>

