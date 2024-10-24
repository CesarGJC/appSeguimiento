<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<td data-label="DESCRIPCION">${item.area_estrategica}</td>
<td data-label="GESTION">${item.gestion}</td>
<td data-label="OPERACION" id="td${item.id_area_estrategica}">
    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1"
                data-bs-toggle="dropdown" aria-expanded="false">
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li><a data-update="true"
                   data-atributes='"id_area_estrategica": ${item.id_area_estrategica}'
                   data-id="${item.id_area_estrategica}"
                   href="#"
                   class="dropdown-item">Modificar</a></li>
            <li>
                <a class="dropdown-item" data-delete="true"
                   data-atributes='"id_area_estrategica": ${item.id_area_estrategica}'
                   data-id="${item.id_area_estrategica}"
                   href="#">Eliminar</a>
            </li>
        </ul>
    </div>
</td>