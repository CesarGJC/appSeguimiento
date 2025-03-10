package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.entity.die.FormularioProgramacion;
import com.moxos.uab.domain.entity.die.Permisos;
import com.moxos.uab.persistence.die.provider.FormularioSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface FormularioDao {
    @SelectProvider(type = FormularioSqlProvider.class, method = "getFormularios")
    List<FormularioProgramacion> getFormularios(@Param("filtro") FilterRequest<FormularioFilterRequest> filtro);

    @SelectProvider(type = FormularioSqlProvider.class, method = "getCantidadFormularios")
    Integer getCantidadFormularios(@Param("filtro") FilterRequest<FormularioFilterRequest> filtro);

    Integer saveCategoriaIndicador(FormularioProgramacion formularioProgramacion);

    FormularioProgramacion getByid(int id_formulario);

    void deleteFormulario(Integer id_formulario) throws DataAccessException;

    FormularioProgramacion getFormularioDetalle(int idFormulario) throws DataAccessException;

    List<FormularioProgramacion> getFormularioPlan(Integer id_plan_pei) throws DataAccessException;

    List<FormularioProgramacion> getListaFormularioHabilitados(Permisos permisos) throws DataAccessException;
}
