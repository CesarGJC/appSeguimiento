<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<tr>
    <td>
        ${formulario.encargado}
        <button class="btn btn-primary" data-sistema-align="true" data-sistema-id="1" data-sistema-name="name" onclick="evaluar(this)"><i class="fa fa-plus"></i> Objetivos Est.</button>
    </td>
</tr>
