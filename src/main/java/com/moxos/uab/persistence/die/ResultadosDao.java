package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.Resultados;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface ResultadosDao {
    Integer saveResultados(Resultados resultados);

    void deleteResultado(Integer id_resultados) throws DataAccessException;
}
