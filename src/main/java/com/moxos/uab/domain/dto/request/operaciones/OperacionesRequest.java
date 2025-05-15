package com.moxos.uab.domain.dto.request.operaciones;

import com.moxos.uab.common.util.validation.NotSpaceBlankOrNull;
import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OperacionesRequest {
    private int id_operaciones_actividad = 0;
    private int id_descripcion_operaciones_poa;
    private int id_programa;
    private int id_departamento;
    private int id_formulario;
    private int ult_usuario;
    private boolean porcentaje;
    private double progreso = 0;
    private int id_trimestre;
    private List<ListView> trimestre;
    @NotSpaceBlankOrNull(message = "Debe ingresar el titulo")
    private String titulo;
    @NotSpaceBlankOrNull(message = "Debe ingresar la descripcion")
    private String descripcion;
    @NotSpaceBlankOrNull(message = "Debe ingresar quien lo elaboro")
    private String elaborador;
    @NotSpaceBlankOrNull(message = "Debe ingresar el resultado de la actividad")
    private String resultado;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Debe ingresar fecha de publicacion")
    private Date fec_publicacion = new Date();
}
