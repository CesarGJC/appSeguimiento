<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sistema - Moxos</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#305496">
    <link rel="icon" type="image/jpg" href="<c:url value="/public/image/logo.ico" />"/>
    <link href="<c:url value="/public/css/main.css" />" rel="stylesheet">
    <link href="<c:url value="/public/css/font-awesome.min.css" />" rel="stylesheet">
</head>
<body class="app sidebar-mini rtl">
<header class="app-header"><a class="app-header__logo" href=".">moxos</a>
    <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
    <ul class="app-nav">
        <li class="app-search">
            <form method="post" id="formcambiarol" action="${pageContext.request.contextPath}/cambiar-rol">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="id_rol" id="id_rol" value="${_csrf.token}"/>
            </form>
            <select class="form-select form-select-sm" onchange="cambiarRol(this);" name="id_rol">
                <optgroup label="Seleccionar rol">
                    <c:forEach var="roles" items="${cliente.roles}">
                        <option value="<c:out value='${roles.id_rol}' />" <c:if test="${cliente.id_rol==roles.id_rol}">selected</c:if> ><c:out
                                value="${roles.rol}"/></option>
                    </c:forEach>
                </optgroup>
            </select>
        </li>
        <li class="dropdown">
            <a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Show notifications">
                <span id="status" class="text-success"><i class="fa fa-circle" aria-hidden="true"></i></span>
            </a>
        </li>
        <li class="dropdown">
            <a class="app-nav__item" href="#" data-bs-toggle="dropdown" aria-expanded="false"
               aria-label="Open Profile Menu"><i
                    class="fa fa-user fa-lg"></i></a>
            <ul class="dropdown-menu settings-menu dropdown-menu-right">
                <li>
                    <form id="logout" action="<c:url value="/logout" />" method="post"><input type="hidden"
                                                                                              name="${_csrf.parameterName}"
                                                                                              value="${_csrf.token}"/>
                    </form>
                    <a class="dropdown-item" href="javascript:document.getElementById('logout').submit();"
                       target="_top"><i class="fa fa-sign-out" aria-hidden="true"></i> Salir</a></li>
            </ul>
        </li>
    </ul>
</header>
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user"><img id="idimagenpersona" class="app-sidebar__user-avatar rounded-circle"
                                        src="${simagen}" alt="Docente">
        <div>
            <p class="app-sidebar__user-name" style="">${snombre}</p>
            <p class="app-sidebar__user-designation">Seguimiento</p>
        </div>
    </div>
    <ul class="app-menu">
        <li>
            <a class="app-menu__item active" href=".">
                <i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Administracion</span>
            </a>
        </li>
        ${fil}
    </ul>
</aside>
<main class="app-content2">
    <div class="frame">
        <iframe id="marco" width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" allowfullscreen>
            Contenido de IFRAME
        </iframe>
    </div>
    <div id="toast"></div>
</main>
<script src="<c:url value="/public/js/bootstrap.bundle.min.js" />"></script>
<script src="<c:url value="/public/js/popper.min.js" />"></script>
<script src="<c:url value="/public/js/main.js" />"></script>
<script src="<c:url value="/static/js/toast.boostrap.js" />"></script>
<script src="<c:url value="/static/js/menu.js?v=2" />"></script>
<script>
    function cambiarRol(input) {
        document.getElementById('id_rol').value = input.value;
        document.getElementById('formcambiarol').submit();
    }
</script>
</body>
</html>
