package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IFormularioPoaFacade;
import com.moxos.uab.business.service.*;
import com.moxos.uab.domain.dto.response.formulariopoa.FormularioPoaGestionResponse;
import com.moxos.uab.domain.dto.response.formulariopoa.FormularioPoaResponse;
import com.moxos.uab.domain.dto.response.objetivogestionpoa.ObjetivoGestionPoaFormularioResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormularioPoaFacadeImpl implements IFormularioPoaFacade {
    private final IFormularioService formularioService;
    private final IResultadosService resultadosService;
    private final IObjetivoGestionPoaService objetivoGestionPoaService;
    private final IConfigurationService configurationService;


    @Override
    public FormularioPoaGestionResponse getFormularioProgramacionPoaCabecera(Integer id) {
        var responseConfiguracion = configurationService.getConfigurationByValor("id_periodo_gestion");
        int idDetallePeriodoGestion = 0;
        if (responseConfiguracion.isSuccess()) {
            idDetallePeriodoGestion = Integer.parseInt(responseConfiguracion.getResult());
        }
        var response = formularioService.getFormularioProgramacionPoaDetallePorPeriodoGestion(id, idDetallePeriodoGestion).getResult();
        var resultados = resultadosService.getListarResultadosPorGestionFormulario(response.getId_detalle_periodos_programacion(), response.getId_formulario()).getResult();
        response.setResultadosEsperados(resultados);
        return response;
    }

    @Override
    public FormularioPoaGestionResponse getFormularioProgramacionPoaDetalle(Integer id, Integer idPrograma, Integer idDepartamento, Integer idDetallePeriodoGestion) {
        var response = formularioService.getFormularioProgramacionPoaDetallePorPeriodoGestion(id, idDetallePeriodoGestion).getResult();
        response.setId_programa(idPrograma);
        response.setId_departamento(idDepartamento);
        var resultados = resultadosService.getListarResultadosPorGestionFormulario(response.getId_detalle_periodos_programacion(), response.getId_formulario()).getResult();
        response.setResultadosEsperados(resultados);
        response.setObjetivoGestionPoaFormularioResponses(objetivoGestionPoaService.getObjetivosGestionPoa(ObjetivoGestionPoaFormularioResponse.builder()
                .id_formulario(response.getId_formulario())
                .id_detalle_periodos_programacion(response.getId_detalle_periodos_programacion())
                .id_resultados(response.getId_resultados())
                .id_programa(response.getId_programa())
                .id_departamento(response.getId_departamento())
                .build()).getResult());
        return response;
    }

    @Override
    public List<ListView> getResultados(Integer id) {
        return resultadosService.listarResultados(id).getResult();
    }

}
