package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.TipoIndicador;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface TipoIndicadorDao {
    Integer saveTipoIndicador(TipoIndicador tipoIndicador) throws DataAccessException;

    void deleteTipoIndicador(TipoIndicador tipoIndicador) throws DataAccessException;

    TipoIndicador getByid(int id_tipo_indicador) throws DataAccessException;

    List<TipoIndicador> getTipoIndicadorByDescripcion(TipoIndicador tipoIndicador) throws DataAccessException;

    Integer getCantidadTipoIndicadorByDescripcion(TipoIndicador tipoIndicador) throws DataAccessException;

    List<TipoIndicador> getTipoIndicadorByAbreviacion(TipoIndicador tipoIndicador) throws DataAccessException;

    Integer getCantidadTipoIndicadorByAbreviacion(TipoIndicador tipoIndicador) throws DataAccessException;

    List<TipoIndicador> getAllTipoIndicador() throws DataAccessException;

}
