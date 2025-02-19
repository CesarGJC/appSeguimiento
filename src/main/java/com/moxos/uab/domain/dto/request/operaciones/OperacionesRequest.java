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
    private int id_operaciones = 0;
    private int id_resultados_gestion=-1;
    private int id_detalle_periodos_programacion;
    @NotNull(message = "Debe seleccionar programa")
    private int id_programa;
    private int id_departamento;
    private int id_formulario;
    private int ult_usuario;
    private List<ListView>institutoInvestigacion;
    private List<ListView> programas = new ArrayList<>();
    @NotNull(message = "Debe seleccionar el resultado esperado")
    private Integer id_resultados;
    private List<ListView> resultadosEsperados = new ArrayList<>();
    @NotSpaceBlankOrNull(message = "Debe ingresar la operaciones")
    private String operaciones;
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
