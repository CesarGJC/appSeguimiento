<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<td data-label="INDICADORES ESTRATEGICOS">${item.indicador_estrategico}</td>
<td data-label="CATALOGOS DE INDICADORES">${item.catalogo_indicador}</td>
<td data-label="METAS">${item.meta}</td>
<td data-label="LINEAS BASE">${item.linea_base}</td>
<td data-label="ACCION" id="td${item.id_catalogo_indicador}">
    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1"
                data-bs-toggle="dropdown" aria-expanded="false">
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li><a data-update="true"
                   data-atributes='"id_catalogo_indicador": ${item.id_catalogo_indicador}'
                   data-id="${item.id_catalogo_indicador}"
                   href="#"
                   class="dropdown-item">Modificar</a></li>
            <li>
                <a class="dropdown-item" data-delete="true"
                   data-atributes='"id_catalogo_indicador": ${item.id_catalogo_indicador}'
                   data-id="${item.id_catalogo_indicador}"
                   href="#">Eliminar</a>
            </li>
        </ul>
    </div>
</td>