package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IObjetivosEstrategicosService;
import com.moxos.uab.domain.dto.request.objetivosestrategicos.ObjetivosEstrategicosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResultadosResponse;
import com.moxos.uab.domain.dto.response.objetivosestrategicos.ObjetivosEstrategicosResponse;
import com.moxos.uab.domain.dto.response.resultados.ResultadosGestionPeriodosResponse;
import com.moxos.uab.domain.dto.response.resultados.ResultadosResponse;
import com.moxos.uab.domain.entity.die.ObjetivoEstrategicos;
import com.moxos.uab.domain.entity.die.Resultados;
import com.moxos.uab.persistence.die.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObjtivosEstrategicosServiceImpl implements IObjetivosEstrategicosService {
    private final ModelMapper modelMapper;
    private final ObjetivosEstrategicosDao objetivosEstrategicosDao;
    private final AccionEstrategicaDao accionEstrategicaDao;
    private final ResultadosDao resultadosDao;

    public ObjtivosEstrategicosServiceImpl(ModelMapper modelMapper, ObjetivosEstrategicosDao objetivosEstrategicosDao, AccionEstrategicaDao accionEstrategicaDao, ResultadosDao resultadosDao) {
        this.modelMapper = modelMapper;
        this.objetivosEstrategicosDao = objetivosEstrategicosDao;
        this.accionEstrategicaDao = accionEstrategicaDao;
        this.resultadosDao = resultadosDao;
    }


    @Override
    public Response<Integer> saveObjetivosEstrategicos(ObjetivosEstrategicosRequest objetivoEstrategicos) {
        try {
            Integer result = objetivosEstrategicosDao.saveObjetivosEstrategicos(modelMapper.map(objetivoEstrategicos, ObjetivoEstrategicos.class));
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deleteObjetivosEstrategicos(ObjetivosEstrategicosRequest objetivoEstrategicos) {
        try {
            objetivosEstrategicosDao.deleteObjetivosEstrategicos(modelMapper.map(objetivoEstrategicos, ObjetivoEstrategicos.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<ObjetivosEstrategicosResponse> getByid(int id_objetivos_estrategicos) {
        try {
            ObjetivosEstrategicosResponse objetivosEstrategicos = modelMapper.map(objetivosEstrategicosDao.getByid(id_objetivos_estrategicos), ObjetivosEstrategicosResponse.class);
            return new Response<>(true, "", objetivosEstrategicos);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<FormularioResultadosResponse>> getObjetivosEstrategicos(FormularioResultadosResponse formularioResultadosRequest) {
        try {
            List<FormularioResultadosResponse> formularioResultadosResponseList = new ArrayList<>();
            var objetivos = objetivosEstrategicosDao.getObjetivosFormulario(formularioResultadosRequest.getId_formulario());

            for (var item : objetivos) {
                var acciones = accionEstrategicaDao.getAcciones(item.getId_objetivos_estrategicos());

                for (var accion : acciones) {
                    Resultados resultadosFormulario = new Resultados();
                    resultadosFormulario.setId_acciones_estrategica(accion.getId_acciones_estrategica());
                    resultadosFormulario.setId_formulario(formularioResultadosRequest.getId_formulario());
                    var resultados = resultadosDao.getResultadosPeriodos(resultadosFormulario);
                    for (var resultado : resultados) {
                        formularioResultadosResponseList.add(createObjetivosEstrategicosResponse(formularioResultadosRequest,
                                new ObjetivosEstrategicosResponse(item.getId_objetivos_estrategicos(), item.getObjetivos_estrategicos(), acciones.isEmpty() ? 1 : acciones.size()),
                                new AccionEstrategicaResponse(accion.getId_acciones_estrategica(), accion.getAcciones_estrategica(), resultados.isEmpty() ? 1 : resultados.size()),
                                ResultadosResponse.builder().codigo(resultado.getCodigo()).abreviacionUnidadMedida(resultado.getAbreviacionUnidadMedida()).id_resultados(resultado.getId_resultados()).id_acciones_estrategica(resultado.getId_acciones_estrategica()).articulacion(resultado.getArticulacion()).linea_base(resultado.getLinea_base()).meta_base(resultado.getMeta_base()).denominacion_indicador(resultado.getDenominacion_indicador()).descripcion(resultado.getDescripcion()).formula(resultado.getFormula()).fuente_informacion(resultado.getFuente_informacion())
                                        .resultadosGestionResponseList(resultado.getResultadosGestion().stream()
                                                .map(producto -> modelMapper.map(producto, ResultadosGestionPeriodosResponse.class))
                                                .collect(Collectors.toList())).build()));
                    }
                    if (resultados.isEmpty()) {
                        formularioResultadosResponseList.add(createObjetivosEstrategicosResponse(formularioResultadosRequest,
                                new ObjetivosEstrategicosResponse(item.getId_objetivos_estrategicos(), item.getObjetivos_estrategicos(), acciones.isEmpty() ? 1 : acciones.size()),
                                new AccionEstrategicaResponse(accion.getId_acciones_estrategica(), accion.getAcciones_estrategica(), resultados.isEmpty() ? 1 : resultados.size()), null));
                    }
                }
                if (acciones.isEmpty()) {
                    formularioResultadosResponseList.add(createObjetivosEstrategicosResponse(formularioResultadosRequest,
                            new ObjetivosEstrategicosResponse(item.getId_objetivos_estrategicos(), item.getObjetivos_estrategicos(), acciones.isEmpty() ? 1 : acciones.size()), null, null));
                }
            }
            if (objetivos.isEmpty()) {
                formularioResultadosResponseList.add(createObjetivosEstrategicosResponse(formularioResultadosRequest, null, null, null));
            }
            return new Response<>(true, "", formularioResultadosResponseList);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    private FormularioResultadosResponse createObjetivosEstrategicosResponse(FormularioResultadosResponse formularioResultadosRequest, ObjetivosEstrategicosResponse objetivoEstrategiaResponse, AccionEstrategicaResponse accionEstrategicaResponse, ResultadosResponse resultadosResponse) {
        FormularioResultadosResponse formularioResultadosResponse = new FormularioResultadosResponse(formularioResultadosRequest.getId_formulario(), formularioResultadosRequest.getId_area_estrategica(), formularioResultadosRequest.getEncargado(), formularioResultadosRequest.getColumnas());
        formularioResultadosResponse.setResultadosResponse(resultadosResponse);
        formularioResultadosResponse.setAccionEstrategicaResponse(accionEstrategicaResponse);
        formularioResultadosResponse.setObjetivoEstrategiaResponse(objetivoEstrategiaResponse);
        return formularioResultadosResponse;
    }
}
