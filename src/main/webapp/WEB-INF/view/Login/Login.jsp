<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="<c:url value="public/image/logo.ico"/>" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#305496">
    <link href="<c:url value="/public/css/main.css" />" rel="stylesheet">
    <link href="<c:url value="/public/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="/public/css/login.css"/>" rel="stylesheet">
    <title>MOXOS - INOVATECH</title>
</head>
<body>
<div class="container-login">
    <div class="logo">
        <img src="<c:url value="/public/image/inovatech.jpg"/>" alt=""/>
    </div>
    <form id="forma" name='forma' action='${pageContext.request.contextPath}/authentication' method='post'>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="mb-3">
            <label for="apodo" class="form-label">Correo electronica</label>
            <input type="email" class="form-control" placeholder="Correo" required autofocus id="apodo" name='apodo'
                   value="${login}">
        </div>
        <div class="mb-3">
            <label for="clave" class="form-label">Contraseña</label>
            <input type="password" class="form-control" placeholder="Contraseña" required id="clave" name='clave'>
        </div>
        <div class="capchat">
            <img id="captcha_id" name="imgCaptcha" src="captcha.jpg">
            <a href="javascript:;" title="Cambiar captcha"
               onclick="document.getElementById('captcha_id').src = 'captcha.jpg?' + Math.random();  return false">
                <i style="cursor: pointer;" class="fa fa-refresh" aria-hidden="true"></i></a>
        </div>
        <div class="mb-3">
            <label for="clave" class="form-label">Capchat</label>
            <input type="text" class="form-control" placeholder="Introducir codigo capchat" required autofocus
                   name='captcha'>
        </div>
        <div class="mb-3">
            <button class="btn btn-primary btn-block" type="submit">Iniciar sesión</button>
        </div>
    </form>
</div>
<div id="toastcontainer" class="toast-container position-fixed top-0 end-0 p-3">
    <c:if test="${authenticationError != null}">
        <div id="errortoast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header bg-danger text-white">
                <i class="fa fa-bell" aria-hidden="true"></i>&nbsp;&nbsp;
                <strong class="me-auto"> Notificacion</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                <c:out value="${authenticationError}"/>
            </div>
        </div>
    </c:if>
</div>
<script src="<c:url value="/public/js/bootstrap.bundle.min.js" />"></script>
<script src="<c:url value="/public/js/popper.min.js" />"></script>
<script src="<c:url value="/public/js/togglepassword.js" />"></script>
<script>
    <c:if test="${authenticationError != null}">
    let toast = document.getElementById('errortoast');
    function load() {
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toast)
        toastBootstrap.show();
    }
    </c:if>
</script>
</body>
</html>