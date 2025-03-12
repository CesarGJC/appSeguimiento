<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<td data-label="DESCRIPCION">${item.area_estrategica}</td>
<td data-label="PLAN PEI">${item.descripcion}</td>
<td data-label="OPERACION" id="td${item.id_area_estrategica}">
    <div class="dropdown">
        <button class="btn btn-flat text-black" type="button" id="dropdownMenuButton1"
                data-bs-toggle="dropdown" aria-expanded="false">
            <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li><a data-update="true"
                   data-atributes='"id_area_estrategica": ${item.id_area_estrategica}'
                   data-id="${item.id_area_estrategica}"
                   href="#"
                   class="dropdown-item"><i class="fa fa-pencil" aria-hidden="true"></i> Modificar</a></li>
            <li>
                <a class="dropdown-item" data-delete="true"
                   data-atributes='"id_area_estrategica": ${item.id_area_estrategica}'
                   data-id="${item.id_area_estrategica}"
                   href="#"><i class="fa fa-trash" aria-hidden="true"></i> Eliminar</a>
            </li>
            <li>
                <a href="<c:url value="/catalogo-indicadores/index/${item.id_area_estrategica}"/>"
                   class="dropdown-item"><i class="fa fa-list" aria-hidden="true"></i> Catalogo indicadores</a></li>
            <li>
        </ul>
    </div>
</td>