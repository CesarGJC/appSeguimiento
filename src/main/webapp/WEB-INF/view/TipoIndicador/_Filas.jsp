<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<td data-label="DESCRIPCION">${item.descripcion}</td>
<td data-label="ABREVIACION">${item.abreviacion}</td>
<td data-label="ACCION" id="td${item.id_tipo_indicador}">
    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1"
                data-bs-toggle="dropdown" aria-expanded="false">
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li><a data-update="true"
                   data-atributes='"id_tipo_indicador": ${item.id_tipo_indicador}'
                   data-id="${item.id_tipo_indicador}"
                   href="#"
                   class="dropdown-item">Modificar</a>
            </li>
            <li>
                <a class="dropdown-item" data-delete="true"
                   data-atributes='"id_tipo_indicador": ${item.id_tipo_indicador}'
                   data-id="${item.id_tipo_indicador}"
                   href="#">Eliminar</a>
            </li>
        </ul>
    </div>
</td>