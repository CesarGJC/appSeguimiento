package com.moxos.uab.persistence.die.provider;


import com.moxos.uab.common.util.filter.FiltersUtils;
import com.moxos.uab.domain.dto.request.general.FilterParamsRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesFilterRequest;

import java.util.List;
import java.util.Map;

public class OperacionesSqlProvider {
    public static String getFormulariosActividades(Map<String, Object> params) {
        FilterRequest<OperacionesFilterRequest> filtro = (FilterRequest<OperacionesFilterRequest>) params.get("filtro");
        Integer idPrograma = (Integer) params.get("idPrograma");
        StringBuilder sql = new StringBuilder(idPrograma == 0 ? "SELECT * FROM public.vw_operaciones WHERE  id_departamento=#{idDepartamento} and id_formulario=#{idFormulario}" : "SELECT * FROM public.vw_operaciones WHERE id_programa=#{idPrograma} and id_formulario=#{idFormulario}");
        setParametros(filtro.getParams(), sql);
        sql.append(" ORDER BY titulo LIMIT #{filtro.filter.pagina} OFFSET #{filtro.filter.nro_pagina};");
        return sql.toString();
    }

    public static String getCantidadByTipo(Map<String, Object> params) {
        FilterRequest<OperacionesFilterRequest> filtro = (FilterRequest<OperacionesFilterRequest>) params.get("filtro");
        Integer idPrograma = (Integer) params.get("idPrograma");
        StringBuilder sql = new StringBuilder(idPrograma == 0 ? "SELECT count(id_operaciones) FROM public.vw_operaciones WHERE id_departamento=#{idDepartamento} and id_formulario=#{idFormulario}" : "SELECT count(id_operaciones) FROM public.vw_operaciones WHERE id_programa=#{idPrograma} and id_formulario=#{idFormulario}");
        setParametros(filtro.getParams(), sql);
        return sql.toString();
    }

    public static String getProgramaFacultad(Map<String, Object> params) {
        Integer idPrograma = (Integer) params.get("idPrograma");
        if (idPrograma == 0)
            return "SELECT id_programa, id_facultad, programa, facultad from  fcl_programas where id_facultad=#{idFacultad} limit 1;";
        else
            return "SELECT id_programa, id_facultad, programa, facultad from  fcl_programas where id_programa=#{idPrograma} limit 1;";
    }

    private static void setParametros(List<FilterParamsRequest> params, StringBuilder sql) {
        for (var item : params) {
            String name = String.format("#{filtro.filter.%s}", item.getOption());
            sql.append(" AND ");
            sql.append(String.format(FiltersUtils.condiciones(item.getType()), item.getOption(), name).replace("|", "%"));
        }
    }
}
