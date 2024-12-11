package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.TipoIndicador;
import com.moxos.uab.domain.entity.die.UnidadMedida;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface UnidadMedidaDao {
    Integer saveUnidadMedida(UnidadMedida unidadMedida) throws DataAccessException;

    void deleteUnidadMedida(UnidadMedida unidadMedida) throws DataAccessException;

    UnidadMedida getByid(int id_unidad_medida) throws DataAccessException;

    List<UnidadMedida> getUnidadMedidaByDescripcion(UnidadMedida unidadMedida) throws DataAccessException;

    Integer getCantidadUnidadMedidaByDescripcion(UnidadMedida unidadMedida) throws DataAccessException;

    List<UnidadMedida> getUnidadMedidaByAbreviacion(UnidadMedida unidadMedida) throws DataAccessException;

    Integer getCantidadUnidadMedidaByAbreviacion(UnidadMedida unidadMedida) throws DataAccessException;

    List<UnidadMedida> getAllUnidadMedida();
}
