package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.IndicadoresEstrategicos;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface IndicadoresEstrategicosDao {
    void saveIndicadoresEstrategicos(IndicadoresEstrategicos indicadoresEstrategicos) throws DataAccessException;

    void updateIndicadoresEstrategicos(IndicadoresEstrategicos indicadoresEstrategicos) throws DataAccessException;

    void deleteIndicadoresEstrategicos(IndicadoresEstrategicos indicadoresEstrategicos) throws DataAccessException;

    List<IndicadoresEstrategicos> selectIndicadoresEstrategicos(int id_indicador_estrategico) throws DataAccessException;

    List<IndicadoresEstrategicos> getAllIndicadoresEstrategicos() throws DataAccessException;

    IndicadoresEstrategicos getById(int id_indicador_estrategico) throws DataAccessException;
}
