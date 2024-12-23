package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.Adjuntos;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface AdjuntosDao {

    Integer saveAdjuntos(Adjuntos adjuntos) throws DataAccessException;

    void deleteAdjuntos(Integer id_adjunto) throws DataAccessException;
}
