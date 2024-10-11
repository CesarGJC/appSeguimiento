package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Hilos extends Adjuntos {
    /* Private Fields */

    private int id_hilo;
    private String hilo;
    private int id_tipo_hilo;
    private String tipo_hilo;
    private int id_destinatario;
    private String destinatario;
    private int id_duenio;
    private boolean privado;
    private String asunto;
    private String imagen;
    private Date fec_registro;
    private Date fecha;
    private String fecha1;
    private int id_tipo_segmento;
    private String tipo_segmento;
    private String id_estado;
    private int id_remitente;
    private String remitente;
    private int nro_mensajes;
    private int nro_mensajes_nuevos;
    private String segmento;
    private int id_segmento;
    private String adjunto;
    // private List lista;
    private String nombre_archivo;

}