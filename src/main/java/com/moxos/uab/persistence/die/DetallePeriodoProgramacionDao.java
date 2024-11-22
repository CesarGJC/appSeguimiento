package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.DetallePeriodoProgramacion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
@Mapper
public interface DetallePeriodoProgramacionDao {

    Integer saveDetallePeriodoProgramacion(DetallePeriodoProgramacion dpp) throws DataAccessException;

    void deleteDetallePeriodoProgramacion(DetallePeriodoProgramacion dpp) throws DataAccessException;

    List<DetallePeriodoProgramacion> getAllDetallePeriodoProgramacion() throws DataAccessException;

    DetallePeriodoProgramacion getByid(int id_detalle_periodos_programacion) throws DataAccessException;

    List<DetallePeriodoProgramacion> getDetallePeriodoProgramacionByPlan(DetallePeriodoProgramacion dpp) throws DataAccessException;

    Integer getCantidadDetallePeriodoProgramacionByPlan(DetallePeriodoProgramacion dpp) throws DataAccessException;

    List<DetallePeriodoProgramacion> getDetallePeriodoProgramacionByDetalle(DetallePeriodoProgramacion dpp) throws DataAccessException;

    Integer getCantidadDetallePeriodoProgramacionByDetalle(DetallePeriodoProgramacion dpp) throws DataAccessException;

    List<DetallePeriodoProgramacion> getAllDetallePeriodoProgramacionByPlan(Integer id_plan_pei);

}
