package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.DescripcionOperacionesPoa;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface DescripcionOperacionesPoaDao {
    Integer saveDescripcionOperacionesPoa(DescripcionOperacionesPoa descripcion) throws DataAccessException;

    void deleteDescripcionOperacionesPoa(DescripcionOperacionesPoa descripcion) throws DataAccessException;

    DescripcionOperacionesPoa getByid(int id_descripcion_operaciones_poa) throws DataAccessException;

    List<DescripcionOperacionesPoa> getDescripcionOperacionesPoa(DescripcionOperacionesPoa id_accion_corto_plazo_poa);

    DescripcionOperacionesPoa getDescripcionOperacionesPoa(int id_descripcion_operaciones_poa) throws DataAccessException;

    Boolean esPorcentaje(int id_descripcion_operaciones_poa) throws DataAccessException;
}
