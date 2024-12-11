<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-md">
        <h4 class="mt-3 text-center"><i class="fa fa-bell-o" aria-hidden="true"></i> Notificacion</h4>
        <p class="mt-3 mb-3 text-center">Desea eliminar el siguiente contenido.</p>
    </div>
</div>
<div class="row">
    <div class="col-md mb-3 mt-3">
        <form:form data-form="true" method="post" modelAttribute="model"  action="${pageContext.request.contextPath}/detalle-periodo-programacion/delete">
            <form:hidden path="id_detalle_periodos_programacion"/>
            <div class="row">
                <label class="control-label col-md-3">DETALLE</label>
                <div class="col-md-9">
                    <c:out value="${model.descripcion}"/>
                </div>
            </div>
        </form:form>
    </div>
</div>
