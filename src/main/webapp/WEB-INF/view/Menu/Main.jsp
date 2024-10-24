<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Sistema - Moxos</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link href="<c:url value="/public/css/main.css" />" rel="stylesheet">
    <link href="<c:url value="/public/css/font-awesome.min.css" />" rel="stylesheet">
</head>
<body>
<div id="loader"></div>
<div class="container">
    <div class="px-4 py-5 my-5 text-center">
        <h1 class="display-5 fw-bold">BIENVENIDO</h1>
        <div class="col-lg-6 mx-auto">
            <p class="lead mb-4">${snombre}</p>
        </div>
    </div>
</div>
<script src="<c:url value="/public/js/bootstrap.bundle.min.js" />"></script>
<script src="<c:url value="/public/js/popper.min.js" />"></script>
<script src="<c:url value="static/js/loader.js?v=3" />"></script>
<script>
    let loadercontent = new Loader(document.getElementById('loader'), { textAction: 'Cargando...', UrlImage: '<c:url value="/static/image/logominiatura.png" />' });

    document.addEventListener("DOMContentLoaded", function () {
        loadercontent.show();
        setTimeout(function () {
            loadercontent.hide();
        }, 2700);
    });
    document.oncontextmenu = function () {
        return false;
    }
</script>
</body>
</html>
