<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../_css.jsp" %>
    <link href="<c:url value="/static/css/table.bootstrap.css?v=6" />" rel="stylesheet">
</head>
<body class="app sidebar-mini">
<main class="app-content3">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Detalle de Indicadores Estrategicos</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tile border border-primary">
                <div class="tile-title-w-btn">
                    <h3 class="title">Indiadores Estrategicos</h3>

                </div>
                <div class="tile-body">
                    <button id="addButton" type="button"
                            data-atributes='"id_indicador_estrategico": 0'></button>
                    <div class="row d-flex flex-row-reverse mb-3">

                        <div class="col-md-4">
                            <div class="small fw-light">Buscar</div>
                            <div class="input-group">
                                <input onkeyup="filtrarResultados(${model.pagina})"
                                       class="form-control border-end-0 border" id="buscar"
                                       type="search" placeholder="Buscar.."
                                       aria-describedby="btnBuscar">
                                <button class="btn btn-primary border-start-0 border-bottom-0 border ms-n5"
                                        type="button" onclick="cargarElementoIndicadores(${model.pagina})"><i
                                        class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="small fw-light">Buscar por</div>
                            <select id="option" class="form-select">
                                <c:forEach var="item" items="${opciones}">
                                    <option value="${item.id}"
                                            <c:if test="${item.id == model.option}"> selected </c:if>>${item.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div id="table-container" data-toolbar="true" data-add="true"
                             data-url-add="${pageContext.request.contextPath}/indicadores-estrategicos/new"
                             data-url-data="${pageContext.request.contextPath}/indicadores-estrategicos/listar-filtrar"
                             data-url-update="${pageContext.request.contextPath}/indicadores-estrategicos/update"
                             data-url-delete="${pageContext.request.contextPath}/indicadores-estrategicos/delete"
                             class="table-main-container col-xl">
                            <jsp:include page="${request.contextPath}/indicadores-estrategicos/listar">
                                <jsp:param name="pagina" value="${model.pagina}"/>
                                <jsp:param name="mostrar" value="${model.mostrar}"/>
                                <jsp:param name="option" value="${model.option}"/>
                            </jsp:include>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../_js.jsp" %>
<script src="<c:url value="/static/js/sweetalert.min.js" />"></script>
<script src="<c:url value="/static/js/toast.boostrap.js"/>"></script>
<script src="<c:url value="/static/js/table.bootstrap.js?v=63"/>"></script>
<script src="<c:url value="/static/js/ComboBoxBoostrap.js?v=6"/>"></script>
<c:if test="${result !=null}">
    <script>
        setTimeout(function () {
            document.querySelector('.alert').classList.add('d-none');
        }, 5000);
    </script>
</c:if>
<script>
    let comboboxArea;
    let table = new TableBoostrap(document.getElementById('table-container'), {
        title: "Lista de Indicadores Estrategicos",
        add: document.getElementById('addButton'),
        loadContent: () => {
            comboboxArea = new ComboBoxBoostrap('.filter', {});
        }
    });

    function cargarElementoIndicadores(pagina) {
        let params = {
            pagina: pagina,
            mostrar: document.getElementById("mostrar").value,
            buscar: document.getElementById("buscar").value,
            option: document.getElementById("option").value
        };
        table.getUpdateTable(params);
    }

    function filtrarResultados(pagina) {
        if (event.keyCode === 13) {
            cargarElementoAreas(pagina);
        }
    }

</script>
</body>
</html>
