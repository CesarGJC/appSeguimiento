package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.ObjetivoGestionPoa;
import com.moxos.uab.persistence.die.provider.ObjetivoGestionPoaSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;


@Mapper
public interface ObjetivoGestionPoaDao {

    Integer saveObjetivosGestionPoa(ObjetivoGestionPoa objetivoGestionPoa) throws DataAccessException;

    void deleteObjetivosGestionPoa(ObjetivoGestionPoa objetivoGestionPoa) throws DataAccessException;

    ObjetivoGestionPoa getByid(int id_objetivos_gestion_poa) throws DataAccessException;

    @SelectProvider(type = ObjetivoGestionPoaSqlProvider.class, method = "getObjetivosGestionPoaFormulario")
    List<ObjetivoGestionPoa> getObjetivosGestionPoaFormulario(int id_detalle_periodos_programacion,int id_formulario, Integer idPrograma, Integer idDepartamento);

}
