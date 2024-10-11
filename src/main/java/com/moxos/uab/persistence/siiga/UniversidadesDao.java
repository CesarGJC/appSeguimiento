package com.moxos.uab.persistence.siiga;

import com.moxos.uab.domain.entity.siiga.Universidades;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface UniversidadesDao {
    Universidades getUnvBuscarUniversidad(Universidades id_universidad) throws DataAccessException;

    List<Universidades> getUnvListarUniversidades() throws DataAccessException;
}
