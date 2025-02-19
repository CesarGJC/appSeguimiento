package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesFilterRequest;
import com.moxos.uab.domain.entity.die.Operaciones;
import com.moxos.uab.domain.entity.die.Programas;
import com.moxos.uab.persistence.die.provider.OperacionesSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface OperacionesDao {

    @SelectProvider(type = OperacionesSqlProvider.class, method = "getFormulariosActividades")
    List<Operaciones> getFormulariosActividades(@Param("filtro") FilterRequest<OperacionesFilterRequest> filtro, @Param("idFormulario") Integer id, @Param("idPrograma") Integer idPrograma, @Param("idDepartamento") Integer idDepartamento);

    @SelectProvider(type = OperacionesSqlProvider.class, method = "getCantidadByTipo")
    Integer getCantidadByTipo(@Param("filtro") FilterRequest<OperacionesFilterRequest> filtro, @Param("idFormulario") Integer id, @Param("idPrograma") Integer idPrograma, @Param("idDepartamento") Integer idDepartamento);

    Integer saveOperaciones(Operaciones operaciones) throws DataAccessException;

    void deleteOperaciones(Operaciones operaciones) throws DataAccessException;

    List<Operaciones> getAllOperaciones() throws DataAccessException;

    Operaciones getByid(int id_operaciones) throws DataAccessException;

    List<Operaciones> getOperacionesByid(Integer id) throws DataAccessException;

    List<Programas> getProgramasPorFacultad(Integer id_facultad) throws DataAccessException;

    @SelectProvider(type = OperacionesSqlProvider.class, method = "getProgramaFacultad")
    Programas getProgramaFacultad(@Param("idFacultad") int idFacultad, @Param("idPrograma") int idPrograma);
}
