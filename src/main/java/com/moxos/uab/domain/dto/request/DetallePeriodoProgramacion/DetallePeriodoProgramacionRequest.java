package com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DetallePeriodoProgramacionRequest {
    private Integer id_detalle_periodos_programacion = 0;
    private Integer id_plan_pei;
    private String plan_pei;
    @NotNull(message = "La Descripcion del Periodo no puede estar vacía.")
    @NotBlank(message = "La Descripcion del Periodo es obligatoria. ")
    private String descripcion;
    private Integer ult_usuario;
}
