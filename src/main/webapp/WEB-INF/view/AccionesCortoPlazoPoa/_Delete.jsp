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
        <form:form data-form="true" method="post"  data-target="contenido" modelAttribute="model"
                   action="${pageContext.request.contextPath}/accion-corto-plazo/delete">
            <form:hidden path="id_accion_corto_plazo_poa"/>
            <form:hidden path="id_objetivos_gestion_poa"/>
            <form:hidden path="id_formulario"/>
            <form:hidden path="id_departamento"/>
            <form:hidden path="id_programa"/>
            <form:hidden path="id_detalle_periodos_programacion"/>
            <div class="row">
                <label class="control-label col-md-3">Accion Corto Plazo:</label>
                <div class="col-md-9">
                    <c:out value="${model.accion_corto_plazo_poa}"/>
                </div>
            </div>
        </form:form>
    </div>
</div>
