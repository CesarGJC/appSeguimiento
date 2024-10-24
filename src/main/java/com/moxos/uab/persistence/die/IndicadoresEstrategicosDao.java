package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.IndicadoresEstrategicos;
import com.moxos.uab.domain.entity.die.PoliticasDesarrollo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface IndicadoresEstrategicosDao {
    Integer saveIndicadoresEstrategicos(IndicadoresEstrategicos indicadoresEstrategicos) throws DataAccessException;

    void deleteIndicadoresEstrategicos(IndicadoresEstrategicos indicadoresEstrategicos) throws DataAccessException;

    List<IndicadoresEstrategicos> selectIndicadoresEstrategicos(int id_indicador_estrategico) throws DataAccessException;

    List<IndicadoresEstrategicos> getAllIndicadoresEstrategicos() throws DataAccessException;

    IndicadoresEstrategicos getById(int id_indicador_estrategico) throws DataAccessException;

    List<IndicadoresEstrategicos> getIndicadoresEstrategicosByPolitica(IndicadoresEstrategicos indicadoresEstrategicos) throws DataAccessException;

    Integer getCantidadIndicadoresEstrategicosByPolitica(IndicadoresEstrategicos indicadoresEstrategicos) throws DataAccessException;

    List<IndicadoresEstrategicos> getIndicadoresEstrategicosByIndicadores(IndicadoresEstrategicos indicadoresEstrategicos) throws DataAccessException;

    Integer getCantidadIndicadoresEstrategicosByIndicadores(IndicadoresEstrategicos indicadoresEstrategicos) throws DataAccessException;
}
