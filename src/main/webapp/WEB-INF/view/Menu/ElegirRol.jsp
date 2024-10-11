<jsp:useBean id="now" class="java.util.Date"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Ingreso - Moxos</title>
    <meta name="theme-color" content="#305496">
    <link rel="icon" type="image/jpg" href="<c:url value="/public/image/logo.ico" />"/>
    <link href="<c:url value="/public/css/main.css" />" rel="stylesheet">
    <link href="<c:url value="/public/css/font-awesome.min.css" />" rel="stylesheet">
</head>
<body>
<section class="material-half-bg">
    <div class="cover"></div>
</section>
<section class="lockscreen-content">
    <div class="lock-box">
        <img class="rounded-circle user-image" src="${cliente.imagen}">
        <h4 class="text-center user-name">BIENVENIDO</h4>
        <p class="text-center text-muted">
            Seleccionar el rol deseado
        </p>
        <form method="post" action="${pageContext.request.contextPath}/cambiar-rol">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="row">
                <div class="col">
                    <div class="mb-3">
                        <select class="form-select" name="id_rol" id="id_rol">
                            <optgroup label="Seleccionar rol">
                                <c:forEach var="roles" items="${cliente.roles}">
                                    <option value="<c:out value='${roles.id_rol}' />"><c:out
                                            value="${roles.rol}"/></option>
                                </c:forEach>
                            </optgroup>
                        </select>
                    </div>
                </div>
            </div>
            <div class="mb-3 btn-container">
                <button class="btn btn-primary btn-block" type="submit"><i class="fa fa-search"></i>  Ingresar
                </button>
            </div>
        </form>
    </div>
</section>
<script src="<c:url value="/public/js/bootstrap.bundle.min.js" />"></script>
<script src="<c:url value="/public/js/popper.min.js" />"></script>
</body>
</html>