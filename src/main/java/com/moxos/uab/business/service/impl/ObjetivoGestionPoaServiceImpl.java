package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IObjetivoGestionPoaService;
import com.moxos.uab.domain.dto.request.objetivogestionpoa.ObjetivoGestionPoaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.accioncortoplazopoa.AccionCortoPlazoPoaResponse;
import com.moxos.uab.domain.dto.response.descripcionoperacionespoa.DescripcionOperacionesPoaResponse;
import com.moxos.uab.domain.dto.response.objetivogestionpoa.ObjetivoGestionPoaFormularioResponse;
import com.moxos.uab.domain.dto.response.objetivogestionpoa.ObjetivoGestionPoaResponse;
import com.moxos.uab.domain.dto.response.trimestre.TrimestreResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.DescripcionOperacionesPoa;
import com.moxos.uab.domain.entity.die.ObjetivoGestionPoa;
import com.moxos.uab.domain.entity.die.Trimestre;
import com.moxos.uab.persistence.die.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ObjetivoGestionPoaServiceImpl implements IObjetivoGestionPoaService {
    private final ModelMapper modelMapper;
    private final ObjetivoGestionPoaDao objetivoGestionPoaDao;
    private final AccionCortoPlazoPoaDao accionCortoPlazoPoaDao;
    private final DescripcionOperacionesPoaDao descripcionOperacionesPoaDao;
    private final TrimestreDao trimestreDao;
    private final OperacionesDao operacionesDao;


    @Override
    public Response<Integer> saveObjetivosGestionPoa(ObjetivoGestionPoaRequest objetivoGestionPoaRequest) {
        try {
            var value = modelMapper.map(objetivoGestionPoaRequest, ObjetivoGestionPoa.class);
            Integer result = objetivoGestionPoaDao.saveObjetivosGestionPoa(value);
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public GeneralResponse deleteObjetivosGestionPoa(ObjetivoGestionPoaRequest objetivoGestionPoaRequest) {
        try {
            objetivoGestionPoaDao.deleteObjetivosGestionPoa(modelMapper.map(objetivoGestionPoaRequest, ObjetivoGestionPoa.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<ObjetivoGestionPoaResponse> getByid(int id_objetivos_gestion_poa) {
        try {
            ObjetivoGestionPoaResponse objetivoGestionPoaResponse = modelMapper.map(objetivoGestionPoaDao.getByid(id_objetivos_gestion_poa), ObjetivoGestionPoaResponse.class);
            return new Response<>(true, "", objetivoGestionPoaResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<ObjetivoGestionPoaFormularioResponse>> getObjetivosGestionPoa(ObjetivoGestionPoaFormularioResponse objetivoGestionPoaFormularioResponse) {
        try {
            List<ObjetivoGestionPoaFormularioResponse> objetivoGestionPoaFormularioResponseList = new ArrayList<>();
            var objetivo = objetivoGestionPoaDao.getObjetivosGestionPoaFormulario(objetivoGestionPoaFormularioResponse.getId_detalle_periodos_programacion(),objetivoGestionPoaFormularioResponse.getId_formulario(), objetivoGestionPoaFormularioResponse.getId_programa(), objetivoGestionPoaFormularioResponse.getId_departamento());
            for (var item : objetivo) {
                var acciones = accionCortoPlazoPoaDao.getAccionesCortoPlazo(item.getId_objetivos_gestion_poa());
                for (var accion : acciones) {
                    DescripcionOperacionesPoa descripcionOperacionesPoa = new DescripcionOperacionesPoa();
                    descripcionOperacionesPoa.setId_accion_corto_plazo_poa(accion.getId_accion_corto_plazo_poa());
                    descripcionOperacionesPoa.setId_detalle_periodos_programacion(objetivoGestionPoaFormularioResponse.getId_detalle_periodos_programacion());
                    var descripciones = descripcionOperacionesPoaDao.getDescripcionOperacionesPoa(descripcionOperacionesPoa);
                    for (var descripcion : descripciones) {

                        objetivoGestionPoaFormularioResponseList.add(createObjetivoGestionPoaResponse(objetivoGestionPoaFormularioResponse, new ObjetivoGestionPoaResponse(item.getId_objetivos_gestion_poa(), item.getObjetivos_gestion_poa(), acciones.isEmpty() ? 1 : acciones.size()), new AccionCortoPlazoPoaResponse(accion.getId_accion_corto_plazo_poa(), accion.getAccion_corto_plazo_poa(), descripciones.isEmpty() ? 1 : descripciones.size()),
                                DescripcionOperacionesPoaResponse.builder()
                                        .id_descripcion_operaciones_poa(descripcion.getId_descripcion_operaciones_poa())
                                        .descripcion_operaciones_poa(descripcion.getDescripcion_operaciones_poa())
                                        .codigo(descripcion.getCodigo())
                                        .denominacion_indicador(descripcion.getDenominacion_indicador())
                                        .tipo_unidad(descripcion.getTipo_unidad())
                                        .formula(descripcion.getFormula())
                                        .linea_base(descripcion.getLinea_base())
                                        .meta_base_estimada(descripcion.getMeta_base_estimada())
                                        .meta_base(descripcion.getMeta_base())
                                        .cantidad(descripciones.isEmpty() ? 1 : descripciones.size())
                                        .detalleTrimestre(getTrimestreProgramadosYEjecutados(descripcion.getId_descripcion_operaciones_poa()))
                                        .resultadosEsperados(getActividades(descripcion.getId_descripcion_operaciones_poa()))
                                        .build()));
                    }
                    if (descripciones.isEmpty()) {
                        objetivoGestionPoaFormularioResponseList.add(createObjetivoGestionPoaResponse(objetivoGestionPoaFormularioResponse, new ObjetivoGestionPoaResponse(item.getId_objetivos_gestion_poa(), item.getObjetivos_gestion_poa(), acciones.isEmpty() ? 1 : acciones.size()), new AccionCortoPlazoPoaResponse(accion.getId_accion_corto_plazo_poa(), accion.getAccion_corto_plazo_poa(), descripciones.isEmpty() ? 1 : descripciones.size()), null));
                    }
                }
                if (acciones.isEmpty()) {
                    objetivoGestionPoaFormularioResponseList.add(createObjetivoGestionPoaResponse(objetivoGestionPoaFormularioResponse, new ObjetivoGestionPoaResponse(item.getId_objetivos_gestion_poa(), item.getObjetivos_gestion_poa(), acciones.isEmpty() ? 1 : acciones.size()), null, null));
                }

            }
            if (objetivo.isEmpty()) {
                objetivoGestionPoaFormularioResponseList.add(createObjetivoGestionPoaResponse(objetivoGestionPoaFormularioResponse, null, null, null));
            }
            return new Response<>(true, "", objetivoGestionPoaFormularioResponseList);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    private List<ListView> getActividades(int idDescripcionOperacionesPoa) {
        List<ListView> actividadesList = new ArrayList<>();
        for (var item : operacionesDao.getListaOperaciones(idDescripcionOperacionesPoa)) {
            actividadesList.add(new ListView(String.valueOf(item.getId_operaciones_actividad()), item.getResultado()));
        }
        return actividadesList;
    }

    private List<TrimestreResponse> getTrimestreProgramadosYEjecutados(int idDescripcionOperacionesPoa) {
        List<TrimestreResponse> trimestresList = new ArrayList<>();
        for (var item : trimestreDao.getTrimestreProgramadosYEejecutados(idDescripcionOperacionesPoa)) {
            trimestresList.add(TrimestreResponse.of(item.getProgramado(), item.getEjecutado()));
        }
        return trimestresList;
    }

    private ObjetivoGestionPoaFormularioResponse createObjetivoGestionPoaResponse(ObjetivoGestionPoaFormularioResponse objetivoGestionPoaFormularioResponse, ObjetivoGestionPoaResponse objetivoGestionPoaResponse, AccionCortoPlazoPoaResponse accionCortoPlazoPoaResponse, DescripcionOperacionesPoaResponse descripcionOperacionesPoaResponse) {
        ObjetivoGestionPoaFormularioResponse objetivoGestionPoaformularioResponse = ObjetivoGestionPoaFormularioResponse.builder().id_detalle_periodos_programacion(objetivoGestionPoaFormularioResponse.getId_detalle_periodos_programacion()).build();
        objetivoGestionPoaformularioResponse.setDescripcionOperacionesPoaResponse(descripcionOperacionesPoaResponse);
        objetivoGestionPoaformularioResponse.setAccionCortoPlazoPoaResponse(accionCortoPlazoPoaResponse);
        objetivoGestionPoaformularioResponse.setObjetivoGestionPoaResponse(objetivoGestionPoaResponse);

        return objetivoGestionPoaformularioResponse;
    }

}
