package com.moxos.uab.persistence.die.provider;


import java.util.Map;

public class ObjetivoGestionPoaSqlProvider {
    public static String getObjetivosGestionPoaFormulario(Map<String, Object> params) {
        Integer idPrograma = (Integer) params.get("idPrograma");
        StringBuilder sql = new StringBuilder(" SELECT id_objetivos_gestion_poa,  objetivos_gestion_poa  FROM tobjetivos_gestion_poa  WHERE id_detalle_periodos_programacion = #{id_detalle_periodos_programacion} AND id_formulario=#{id_formulario}  AND id_estado = 'A' ");
        if (idPrograma == null) {
            sql.append(" AND id_departamento=#{idDepartamento}  ORDER BY orden;");
        } else {
            sql.append(" AND id_programa=#{idPrograma}  ORDER BY orden;");
        }
        return sql.toString();
    }
}
