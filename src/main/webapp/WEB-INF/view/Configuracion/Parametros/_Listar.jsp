<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row">
    <div id="datostabla" class="col-xl responsive">
        <table data-table="true" class="table table-sm table-hover table-striped table-column-header">
            <thead>
            <tr>
                <th data-resize="true" data-filter="false"
                    data-type="text" data-row-sort="0">Encargado
                </th>
                <th data-resize="true" data-filter="true"
                    data-type="text" data-row-sort="1">Descripcion
                </th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${formularioResponsesList}" var="item">
                <tr>
                    <td data-label="ENCARGADO">${item.encargado}</td>
                    <td data-label="ABREVIACION">
                        <strong>${item.titulo}</strong><br>
                        <strong>Gestion: </strong>${item.gestion}<br>
                        <strong>Descripcion del P.E.I.: </strong>${item.plan_pei}<br>
                        <strong>Area Estrategica: </strong>${item.area_estrategica}<br>
                        <strong>Apertura: </strong>${item.codigo} - ${item.apertura_programatica}<br>
                        <strong>Eje: </strong>${item.eje}<br>
                        <strong>Meta: </strong>${item.meta}<br>
                        <strong>Resultado: </strong>${item.resultado}<br>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

