package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.response.descripcionoperacionespoa.DescripcionOperacionesPoaResponse;
import com.moxos.uab.domain.dto.response.formulariopoa.FormularioPoaGestionResponse;
import com.moxos.uab.domain.dto.response.formulariopoa.FormularioPoaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IFormularioPoaFacade {

    FormularioPoaGestionResponse getFormularioProgramacionPoaCabecera(Integer id);

    FormularioPoaGestionResponse getFormularioProgramacionPoaDetalle(Integer id, Integer idPrograma, Integer idDepartamento, Integer idDetallePeriodoGestion);

    List<ListView> getResultados(Integer id);

}
