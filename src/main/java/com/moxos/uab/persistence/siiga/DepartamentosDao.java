package com.moxos.uab.persistence.siiga;

import com.moxos.uab.domain.entity.siiga.Departamentos;
import com.moxos.uab.domain.entity.siiga.Facultades;
import com.moxos.uab.domain.entity.siiga.Universidades;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface DepartamentosDao {

    Departamentos getDptBuscarDepartamento(Departamentos departamento) throws DataAccessException;

    List<Departamentos> getFclListarDepartamentos(Facultades facultad) throws DataAccessException;

    List<Departamentos> getUnvListarDepartamentos(Universidades universidad) throws DataAccessException;

    Departamentos getDepartamentoPorId(Integer idDepartamento) throws DataAccessException;

    List<Departamentos> getListarUnidadesDepartamentos() throws DataAccessException;
}