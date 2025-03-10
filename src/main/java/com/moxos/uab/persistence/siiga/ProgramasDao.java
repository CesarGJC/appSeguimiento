package com.moxos.uab.persistence.siiga;

import com.moxos.uab.domain.entity.siiga.Facultades;
import com.moxos.uab.domain.entity.siiga.Programas;
import com.moxos.uab.domain.entity.siiga.Universidades;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface ProgramasDao {
    Programas getPrgBuscarPrograma(Programas programa) throws DataAccessException;

    List<Programas> getFclListarProgramas(Facultades facultad) throws DataAccessException;

    List<Programas> getUnvListarProgramas(Universidades id_universidad) throws DataAccessException;

    Programas getProgramaFacultad(int idPrograma) throws DataAccessException;

    List<Programas> getProgramasPorFacultad(Integer idFacultad) throws DataAccessException;

    List<Programas> getListaUnidadesProgramas() throws DataAccessException;
}