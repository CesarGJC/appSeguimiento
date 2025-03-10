<jsp:useBean id="now" class="java.util.Date"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../../_css.jsp" %>
</head>
<body>
<section class="material-half-bg">
    <div class="cover"></div>
</section>
<section id="main" data-context="${pageContext.request.contextPath}" class="lockscreen-content">
    <div class="lock-box"><img class="rounded-circle user-image" src="${simagen}">
        <h4 class="text-center user-name">${nombres}</h4>
        <p class="text-center text-muted">Seleccionar Unidad</p>
        <form:form method="post" cssClass="unlock-form" modelAttribute="model"
                   action="${pageContext.request.contextPath}/configuracion/permisos">
            <form:hidden path="id"/>
            <div class="mb-3">
                <form:select path="tipoUnidad" cssClass="form-select" items="${tiposUnidades}" itemValue="id"
                             itemLabel="value"/>
            </div>
            <div class="mb-3 btn-container">
                <button class="btn btn-primary btn-block  btn-enviar-solicitud" data-name="model">
                       Siguiente
                </button>
            </div>
            <div class="mb-3 btn-container">
                <a class="btn btn-danger btn-block enlace " href="<c:url value="/configuracion/index"/>" data-name="model">
                       Cancelar
                </a>
</div>
        </form:form>
    </div>
</section>
<%@ include file="../../_js.jsp" %>
</body>
</html>