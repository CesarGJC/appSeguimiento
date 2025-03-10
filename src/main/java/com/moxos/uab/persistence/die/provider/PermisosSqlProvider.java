package com.moxos.uab.persistence.die.provider;

import com.moxos.uab.common.util.filter.FiltersUtils;
import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;

import java.util.Map;

public class PermisosSqlProvider {
    public static String getListaUnidadesAdministrativas(Map<String, Object> params) {
        Integer idTipoUnidad = (Integer) params.get("idTipoUnidad");
        StringBuilder sql = new StringBuilder();
        if (idTipoUnidad == 1)
            sql.append("SELECT id_permisos,  fd.departamento  as unidad,  habilitado FROM tpermisos tp inner join  public.fcl_departamentos fd on fd.id_departamento=tp.id_unidad ");
        else
            sql.append("SELECT id_permisos,  fp.programa as unidad,  habilitado  FROM public.tpermisos tp inner join fcl_programas fp on fp.id_programa=tp.id_unidad");

        sql.append(" where tp.id_estado='A' and tp.id_tipo_unidad=#{idTipoUnidad} and tp.id_formulario=#{idFormulario}");
        return sql.toString();
    }
}
