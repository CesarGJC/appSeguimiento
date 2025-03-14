<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<td data-label="DETALLES PERIODOS DE PROGRAMACION">${item.descripcion}</td>
<td data-label="PLAN PEI">${item.plan_pei}</td>
<td data-label="ACCION" id="td${item.id_detalle_periodos_programacion}">
    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1"
                data-bs-toggle="dropdown" aria-expanded="false">
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li><a data-update="true"
                   data-atributes='"id_detalle_periodos_programacion": ${item.id_detalle_periodos_programacion}'
                   data-id="${item.id_detalle_periodos_programacion}"
                   href="#"
                   class="dropdown-item">Modificar</a></li>
            <li>
                <a class="dropdown-item" data-delete="true"
                   data-atributes='"id_detalle_periodos_programacion": ${item.id_detalle_periodos_programacion}'
                   data-id="${item.id_detalle_periodos_programacion}"
                   href="#">Eliminar</a>
            </li>
        </ul>
    </div>
</td>