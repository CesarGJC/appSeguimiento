package com.moxos.uab.domain.dto.request.resultados;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResultadosRequest {
    private int id_resultados;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int id_acciones_estrategica ;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String descripcion ;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String formula ;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String linea_base ;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String meta_base ;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String fuente_informacion ;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String denominacion_indicador ;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int id_unidad_medida ;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int id_tipo_indicador ;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int id_categoria ;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String articulacion  ;
}
