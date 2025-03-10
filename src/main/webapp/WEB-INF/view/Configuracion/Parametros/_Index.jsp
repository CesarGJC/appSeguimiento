<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<div class="col-sm-12">
    <div class="tile border border-primary">
        <div class="tile-title-w-btn">
            <h3 class="title"></h3>
        </div>
        <div class="tile-body">
            <div class="row mb-5">
                <div class="col-md-6"><h5>${configuraciones.periodoGesionPlan.etiqueta}</h5></div>
                <div class="col-md-4 text-center"><span
                        id="${configuraciones.periodoGesionPlan.clave}">${configuraciones.periodoGesionPlan.descripcion}</span>
                </div>
                <div class="col-md-2">
                    <button data-sistema-clave="${configuraciones.periodoGesionPlan.clave}" data-bs-open="true"
                            data-url="${pageContext.request.contextPath}/configuracion/cambiar-gestion"
                            class="btn btn-primary">Cambiar
                    </button>
                </div>
            </div>
            <div class="row mb-5">
                <div class="list-group">
                    <c:forEach items="${configuraciones.formularios}" var="item">
                        <a href="<c:url value="/configuracion/permisos"><c:param name="id" value="${item.id_formulario}"/></c:url> "
                           class="list-group-item list-group-item-action d-flex justify-content-between align-items-center w-100 mb-3 enlace"
                           aria-current="true">
            <span>
                <strong>Apertura: </strong>${item.codigo} - ${item.apertura_programatica}<br>
                <strong>Area Estrategica: </strong>${item.area_estrategica}
            </span>
                            <i class="fa fa-chevron-right"></i>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>