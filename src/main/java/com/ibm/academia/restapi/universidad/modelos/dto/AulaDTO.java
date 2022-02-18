package com.ibm.academia.restapi.universidad.modelos.dto;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AulaDTO implements Serializable {
    private Long aulaId;
    private Integer numeroAula;
    private String medidas;
    private Integer cantidadPupitres;
    private TipoPizarron tipoPizarron;
    private Date fechaCreacion;
}