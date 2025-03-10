<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="list-group">
    <c:forEach items="${formularioResponsesList}" var="item">
        <a  href="<c:url value="/configuracion/permisos"><c:param name="id" value="${item.id_formulario}"/></c:url> "
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