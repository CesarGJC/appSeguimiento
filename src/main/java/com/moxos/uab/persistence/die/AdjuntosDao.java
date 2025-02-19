package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.Adjuntos;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface AdjuntosDao {

    Integer saveAdjuntos(Adjuntos adjuntos) throws DataAccessException;

    List<Adjuntos> getListarDocumentosAdjuntosPoActividad(Adjuntos adjuntos);

    Adjuntos getByid(Integer idAdjunto);

    Adjuntos getByDetalleid(Integer idAdjunto);

    Adjuntos getAdjuntoPorIdActividad(Integer idOperacion);
}
