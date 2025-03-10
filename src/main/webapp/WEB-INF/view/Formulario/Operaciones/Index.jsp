<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../../_css.jsp" %>
    <link href="<c:url value="/static/css/table.bootstrap.css?v=6" />" rel="stylesheet">
    <style>
        .sombra {
            cursor: pointer;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .sombra:hover {
            transform: scale(1.05);
            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body class="app sidebar-mini">
<main id="main" data-context="${pageContext.request.contextPath}" class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> ${programaResponse.programa}</h1>
            <p>Seleccionar formulario</p>
        </div>
    </div>
    <div class="row g-5">
        <c:forEach var="item" items="${formularioResponsesList}">
            <div class="col-sm-6">
                <div class="tile border border-primary rounded-3 sombra">
                    <div class="tile-title-w-btn">
                        <h3 class="title">${item.codigo} - ${item.apertura_programatica}</h3>
                    </div>
                    <div class="tile-body">
                        <p style="text-align: justify"><strong>Gestion: </strong>${item.gestion}<br>
                            <strong>Descripcion del P.E.I.: </strong>${item.plan_pei}<br>
                            <strong>Area Estrategica: </strong>${item.area_estrategica}<br>
                            <strong>Meta: </strong>${item.meta}<br>
                            <strong>Resultado: </strong>${item.resultado}<br>
                            <strong>Eje: </strong>${item.eje}<br></p>
                        <hr>
                        <div class="mb-5">
                            <a href="<c:url value="/operaciones/actividades"><c:param value="${model.id_programa}" name="id_programa"/> <c:param value="${model.id_departamento}" name="id_departamento"/>  <c:param value="${item.id_formulario}" name="id"/> </c:url> "
                               class="btn btn-primary btn-lg px-4">Registrar Actividades</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</main>
<%@ include file="../../_js.jsp" %>
</body>
</html>
