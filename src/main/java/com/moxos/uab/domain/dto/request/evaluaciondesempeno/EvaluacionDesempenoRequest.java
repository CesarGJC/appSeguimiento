package com.moxos.uab.domain.dto.request.evaluaciondesempeno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EvaluacionDesempenoRequest {
    private int id_evaluacion_desempeno;
    @NotNull(message = "Debe seleccionar un Resultado.")
    private int id_resultados;
    @NotNull(message = "Debe seleccionar un Resultado Gestion.")
    private int id_resultados_gestion;
    @NotNull(message = "Debe seleccionar un programa")
    private int id_programa;
    @NotNull(message = "El Titulo no puede estar vacío.")
    @NotBlank(message = "El Titulo es obligatorio.")
    private String titulo;
    @NotNull(message = "La Descripcion no puede estar vacia.")
    @NotBlank(message = "La Descripcion es obligatoria.")
    private String descripcion;
    @NotNull(message = "El Elaborador no puede estar vacio.")
    @NotBlank(message = "El Elaborador es obligatorio.")
    private String elaborador;
    @NotNull(message = "El Resultado no puede estar vacio.")
    @NotBlank(message = "El Resultado es obligatorio.")
    private String resultado;
    @NotNull(message = "La vistas no puede estar vacia.")
    @NotBlank(message = "La vistas es obligatorio.")
    private String vistas;
    @NotNull(message = "La Fecha de publicacion no puede estar vacia.")
    @NotBlank(message = "La Fecha de publicacion es obligatoria.")
    private Date fec_publicacion;
    @NotNull(message = "Las Descargar no pueden estar vacia.")
    private int descargas;
}
