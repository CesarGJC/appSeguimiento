package com.moxos.uab.domain.dto.request.adjuntos;

import com.moxos.uab.common.util.validation.NotSpaceBlankOrNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdjuntosRequest {
    private int id_adjunto_operaciones_actividad;
    private String nombre_documento = "";
    private String documento;
    private String tipo_documento;
    private String ruta = "";
    @NotSpaceBlankOrNull(message = "Debe ingresar una descripcion breve del documento.")
    private String descripcion_documento;
    @NotNull(message = "Debe subir un archivo válido.")
    private MultipartFile file;
    private Integer ult_usuario;
}
