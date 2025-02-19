package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.DetallePeriodoProgramacion;
import com.moxos.uab.domain.entity.die.ResultadosGestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface ResultadosGestionDao {
    Integer saveResultadosGestion(ResultadosGestion resultadosGestion) throws DataAccessException;

    void deleteResultadoGestion(ResultadosGestion resultadosGestion) throws DataAccessException;

    ResultadosGestion getByid(int id_resultados_gestion) throws DataAccessException;

    List<ResultadosGestion> getResultadosGestion(Integer id_resultados_gestion) throws DataAccessException;

    List<DetallePeriodoProgramacion> getPeriodosProgramacion(Integer id_plan_pei) throws DataAccessException;

    ResultadosGestion getDetalleProgramacionPorResultados(ResultadosGestion resultadosGestion) throws DataAccessException;


}
