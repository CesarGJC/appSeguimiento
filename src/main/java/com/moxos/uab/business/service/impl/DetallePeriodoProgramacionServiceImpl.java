package com.moxos.uab.business.service.impl;


import com.moxos.uab.business.service.IDetallePeriodoProgramacionService;
import com.moxos.uab.common.enums.SearchDetallePeriodo;
import com.moxos.uab.domain.dto.request.detallePeriodoProgramacion.DetallePeriodoProgramacionRequest;
import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.DetallePeriodoProgramacionResponse;
import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.PeriodosProgramacionResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.DetallePeriodoProgramacion;
import com.moxos.uab.persistence.die.DetallePeriodoProgramacionDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetallePeriodoProgramacionServiceImpl implements IDetallePeriodoProgramacionService {
    private ModelMapper modelMapper;
    private final DetallePeriodoProgramacionDao detallePeriodoProgramacionDao;


    public DetallePeriodoProgramacionServiceImpl(DetallePeriodoProgramacionDao detallePeriodoProgramacionDao, ModelMapper moodelMapper) {
        this.detallePeriodoProgramacionDao = detallePeriodoProgramacionDao;
        this.modelMapper = moodelMapper;
    }

    @Override
    public Response<DetallePeriodoProgramacionResponse> saveDetallePeriodoProgramacion(DetallePeriodoProgramacionRequest dpp) {
        try {
            var detalle = modelMapper.map(dpp, DetallePeriodoProgramacion.class);
            Integer id = detallePeriodoProgramacionDao.saveDetallePeriodoProgramacion(detalle);
            var result = detallePeriodoProgramacionDao.getById(id);
            DetallePeriodoProgramacionResponse detallePeriodoProgramacionResponse = modelMapper.map(result, DetallePeriodoProgramacionResponse.class);
            return new Response<>(true, "", detallePeriodoProgramacionResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public GeneralResponse deleteDetallePeriodoProgramacion(DetallePeriodoProgramacionRequest dpp) {
        try {
            detallePeriodoProgramacionDao.deleteDetallePeriodoProgramacion(modelMapper.map(dpp, DetallePeriodoProgramacion.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<List<ListView>> listDetallePeriodoProgramacionPorPei(Integer idPlanPei) {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : detallePeriodoProgramacionDao.getAllDetallePeriodoProgramacionByPlan(idPlanPei))
                listViews.add(new ListView(String.valueOf(item.getId_detalle_periodos_programacion()), item.getDescripcion()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<DetallePeriodoProgramacionRequest> getByid(int id_detalle_periodos_programacion) {
        try {
            DetallePeriodoProgramacionRequest detallePeriodoProgramacionRequest = modelMapper.map(detallePeriodoProgramacionDao.getById(id_detalle_periodos_programacion), DetallePeriodoProgramacionRequest.class);
            return new Response<>(true, "", detallePeriodoProgramacionRequest);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<DetallePeriodoProgramacionResponse>> listarDetallePeriodoProgramacionByTipo(String buscar, SearchDetallePeriodo searchPei, int pagina, int nroPagina, int id) {
        DetallePeriodoProgramacion detallePeriodoProgramacion = new DetallePeriodoProgramacion();
        detallePeriodoProgramacion.setPagina(pagina);
        detallePeriodoProgramacion.setNro_pagina(nroPagina);
        detallePeriodoProgramacion.setBuscar(buscar);
        detallePeriodoProgramacion.setId_plan_pei(id);
        try {
            List<DetallePeriodoProgramacionResponse> detalleperiodoprogramacion = new ArrayList<>();
            switch (searchPei) {
                case PLAN_PEI:
                    detalleperiodoprogramacion = detallePeriodoProgramacionDao.getDetallePeriodoProgramacionByPlan(detallePeriodoProgramacion).stream().map(p -> modelMapper.map(p, DetallePeriodoProgramacionResponse.class)).toList();
                    break;
                case DESCRIPCION:
                    detalleperiodoprogramacion = detallePeriodoProgramacionDao.getDetallePeriodoProgramacionByDetalle(detallePeriodoProgramacion).stream().map(p -> modelMapper.map(p, DetallePeriodoProgramacionResponse.class)).toList();
                    break;
                default:
                    break;
            }
            return new Response<>(true, "", detalleperiodoprogramacion);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }


    @Override
    public Response<Integer> getCantidadByTipo(String buscar, SearchDetallePeriodo searchPei, Integer id) {
        DetallePeriodoProgramacion detallePeriodoProgramacion = new DetallePeriodoProgramacion();
        detallePeriodoProgramacion.setBuscar(buscar);
        detallePeriodoProgramacion.setId_plan_pei(id);
        try {
            Integer cantidad = 0;
            switch (searchPei) {
                case PLAN_PEI:
                    cantidad = detallePeriodoProgramacionDao.getCantidadDetallePeriodoProgramacionByPlan(detallePeriodoProgramacion);
                    break;
                case DESCRIPCION:
                    cantidad = detallePeriodoProgramacionDao.getCantidadDetallePeriodoProgramacionByDetalle(detallePeriodoProgramacion);
                    break;
                default:
                    break;
            }
            return new Response<>(true, "", cantidad);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<PeriodosProgramacionResponse>> getPeriodosPlan(int idPlan) {
        try {
            List<PeriodosProgramacionResponse> listViews = new ArrayList<>();
            for (var item : detallePeriodoProgramacionDao.getPeriodos(idPlan))
                listViews.add(new PeriodosProgramacionResponse(item.getId_detalle_periodos_programacion(), Integer.valueOf(item.getDescripcion())));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<ListView>> getPeriodosGestionesByid(int idPlanPei) {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : detallePeriodoProgramacionDao.getPeriodos(idPlanPei))
                listViews.add(new ListView(item.getId_detalle_periodos_programacion().toString(), item.getDescripcion()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

}
