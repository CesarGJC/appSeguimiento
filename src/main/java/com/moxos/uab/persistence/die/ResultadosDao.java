package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.Resultados;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface ResultadosDao {
    Integer saveResultados(Resultados resultados);

    Resultados getByid(Integer id) throws DataAccessException;

    void deleteResultado(Integer id_resultados) throws DataAccessException;

    List<Resultados> getResultados(Resultados resultados) throws DataAccessException;

    List<Resultados> getResultadosPeriodos(Resultados resultados) throws DataAccessException;

    List<Resultados> getListaResultadoPorGestionFormulario(Resultados resultados) throws DataAccessException;

    Resultados getResultadosDetallePorPeriodo(Resultados resultados) throws DataAccessException;
}
