<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<div class="bd-callout bd-callout-info">
    <strong>Acciones estrategicas: </strong> ${resultadoEsperado.acciones_estrategica}<br>
    <strong>Indicador: </strong> ${resultadoEsperado.denominacion_indicador} <strong>Formulario: </strong> ${resultadoEsperado.formula}
    <strong>Categoria: </strong> ${resultadoEsperado.categoria_indicador} <strong>Unidad: </strong> ${resultadoEsperado.unidad_medidad}(${resultadoEsperado.abreviacion})<br>
    <strong>Resultados esperado: </strong> ${resultadoEsperado.descripcion} <strong>Gestion: </strong> ${resultadoEsperado.gestion}
    <strong>Linea base: </strong> ${resultadoEsperado.linea_base} <strong>Meta base: </strong> ${resultadoEsperado.meta_base}<br>
</div>