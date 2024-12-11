package com.moxos.uab.persistence.die.provider;

import com.moxos.uab.common.util.filter.FiltersUtils;
import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;

import java.util.Map;

public class FormularioSqlProvider {
    public static String getFormularios(Map<String, Object> params) {
        FilterRequest<FormularioFilterRequest> filtro = (FilterRequest<FormularioFilterRequest>) params.get("filtro");
        StringBuilder sql = new StringBuilder("SELECT * FROM public.vw_formularios WHERE  1=1");
        for (var item : filtro.getParams()) {
            sql.append(" AND ");
            String condition = String.format(FiltersUtils.condiciones(item.getType()), item.getOption(), String.format("#{filtro.filter.%s}", item.getOption()));
            if (item.getType() == 2) condition = condition.replace("|", "%");
            sql.append(condition);
        }
        sql.append(" ORDER BY titulo LIMIT #{filtro.filter.pagina} OFFSET #{filtro.filter.nro_pagina};");
        return sql.toString();
    }

    public static String getCantidadFormularios(Map<String, Object> params) {
        FilterRequest<FormularioFilterRequest> filtro = (FilterRequest<FormularioFilterRequest>) params.get("filtro");
        StringBuilder sql = new StringBuilder("SELECT count(id_formulario) FROM public.vw_formularios WHERE  1=1");
        for (var item : filtro.getParams()) {
            String name = String.format("#{filtro.filter.%s}", item.getOption());
            sql.append(" AND ");
            sql.append(String.format(FiltersUtils.condiciones(item.getType()), item.getOption(), name));
        }
        return sql.toString();
    }
}
