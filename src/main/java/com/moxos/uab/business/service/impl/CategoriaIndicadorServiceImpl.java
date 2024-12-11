package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.ICategoriaIndicadorService;
import com.moxos.uab.common.enums.SearchCategoriaIndicador;
import com.moxos.uab.domain.dto.request.categoriaindicador.CategoriaIndicadorRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.categoriaindicador.CategoriaIndicadorResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.CategoriaIndicador;
import com.moxos.uab.persistence.die.CategoriaIndicadorDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaIndicadorServiceImpl implements ICategoriaIndicadorService {
    private ModelMapper modelMapper;
    private final CategoriaIndicadorDao categoriaIndicadorDao;

    public CategoriaIndicadorServiceImpl(CategoriaIndicadorDao categoriaIndicadorDao) {
        this.categoriaIndicadorDao = categoriaIndicadorDao;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Response<Integer> saveCategoriaIndicador(CategoriaIndicadorRequest categoriaIndicador) {
        try {
            Integer result = categoriaIndicadorDao.saveCategoriaIndicador(modelMapper.map(categoriaIndicador, CategoriaIndicador.class));
            return new Response<Integer>(true, "", result);
        } catch (Exception e) {
            return new Response<Integer>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deleteCategoriaIndicador(CategoriaIndicadorRequest categoriaIndicador) {
        try {
            categoriaIndicadorDao.deleteCategoriaIndicador(modelMapper.map(categoriaIndicador, CategoriaIndicador.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<CategoriaIndicadorResponse> getByid(int id_categoria) {
        try {
            CategoriaIndicadorResponse categoriaIndicador = modelMapper.map(categoriaIndicadorDao.getByid(id_categoria), CategoriaIndicadorResponse.class);
            return new Response<>(true, "", categoriaIndicador);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<CategoriaIndicadorResponse>> listarCategoriaIndicadorByTipo(String buscar, SearchCategoriaIndicador searchCategoriaIndicador, int pagina, int nroPagina) {
        CategoriaIndicador categoriaIndicador = new CategoriaIndicador();
        categoriaIndicador.setPagina(pagina);
        categoriaIndicador.setNro_pagina(nroPagina);
        categoriaIndicador.setBuscar(buscar);
        try {
            List<CategoriaIndicadorResponse> categorias = new ArrayList<>();
            switch (searchCategoriaIndicador) {
                case DESCRIPCION:
                    categorias = categoriaIndicadorDao.getCategoriaiIndicadorByDescripcion(categoriaIndicador).stream().map(p -> modelMapper.map(p, CategoriaIndicadorResponse.class)).toList();
                    break;
                case ABREVIACION:
                    categorias = categoriaIndicadorDao.getCategoriaiIndicadorByAbreviacion(categoriaIndicador).stream().map(p -> modelMapper.map(p, CategoriaIndicadorResponse.class)).toList();
                    break;
                default:
                    break;
            }
            return new Response<>(true, "", categorias);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> getCantidadByTipo(String buscar, SearchCategoriaIndicador searchCategoriaIndicador) {
        CategoriaIndicador categoriaIndicador = new CategoriaIndicador();
        categoriaIndicador.setBuscar(buscar);
        try {
            Integer cantidad = 0;
            switch (searchCategoriaIndicador) {
                case DESCRIPCION:
                    cantidad = categoriaIndicadorDao.getCantidadCategoriaIndicadorByDescripcion(categoriaIndicador);
                    break;
                case ABREVIACION:
                    cantidad = categoriaIndicadorDao.getCantidadCategoriaIndicadorByAbreviacion(categoriaIndicador);
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
    public Response<CategoriaIndicadorRequest> getByidCategoriaIndicador(int id_categoria) {
        try {
            CategoriaIndicadorRequest categoriaIndicador = modelMapper.map(categoriaIndicadorDao.getByid(id_categoria), CategoriaIndicadorRequest.class);
            return new Response<>(true, "", categoriaIndicador);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<ListView>> getCategorias() {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : categoriaIndicadorDao.getAllCategoriaIndicador())
                listViews.add(new ListView(String.valueOf(item.getId_categoria()), String.format("%s(%s)", item.getDescripcion(), item.getAbreviacion())));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
