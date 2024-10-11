package com.moxos.uab.persistence.siiga;

import com.moxos.uab.domain.entity.siiga.Facultades;
import com.moxos.uab.domain.entity.siiga.Universidades;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface FacultadesDao {

    Facultades getFclBuscarFacultad(Facultades facultad) throws DataAccessException;
    List<Facultades> getUnvListarFacultades(Universidades id_universidad) throws DataAccessException;
}
