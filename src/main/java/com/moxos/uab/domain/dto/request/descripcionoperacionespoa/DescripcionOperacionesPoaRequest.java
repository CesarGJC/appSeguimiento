package com.moxos.uab.domain.dto.request.descripcionoperacionespoa;

import com.moxos.uab.domain.dto.request.trimestre.TrimestresRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DescripcionOperacionesPoaRequest {
    private Integer id_descripcion_operaciones_poa = 0;
    @NotNull(message = "La Accion a Corto Plazo no puede estar vacio.")
    private Integer id_accion_corto_plazo_poa;
    @NotNull(message = "La Descripcion de operacion no puede estar vacio.")
    @NotBlank(message = "La Descripcion de operacion es obligatorio.")
    private String descripcion_operaciones_poa;
    private Integer id_formulario;
    private Integer orden;
    private Integer linea_base;
    @NotNull(message = "El Resultado no puede estar vacio.")
    private Integer id_resultados;
    private Integer ult_usuario;
    @NotNull(message = "Debe introducir la programacion del Primer Trimestre.")
    private Integer primerTrimestre;
    @NotNull(message = "Debe introducir la programacion del Segundo Trimestre.")
    private Integer segundoTrimestre;
    @NotNull(message = "Debe introducir la programacion del Tercer Trimestre.")
    private Integer tercerTrimestre;
    @NotNull(message = "Debe introducir la programacion del Cuarto Trimestre.")
    private Integer cuartoTrimestre;
}
