package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.ResultadosGestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface ResultadosGestionDao {
    Integer saveResultadosGestion(ResultadosGestion resultadosGestion);

    void deleteResultadoGestion(Integer id_resultados_gestion) throws DataAccessException;
}
