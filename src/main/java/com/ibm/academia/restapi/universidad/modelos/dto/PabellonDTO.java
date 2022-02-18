package com.ibm.academia.restapi.universidad.modelos.dto;

import com.ibm.academia.restapi.universidad.modelos.entidades.Direccion;
import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PabellonDTO implements Serializable {
    private Long pabellonId;
    private String nombre;
    private String metrosCuadrados;
    private Direccion direccion;
    private Date fechaCreacion;
}