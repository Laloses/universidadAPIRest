package com.ibm.academia.restapi.universidad.modelos.dto;

import com.ibm.academia.restapi.universidad.modelos.entidades.Direccion;
import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlumnoDTO implements Serializable {
    private Long personaId;
    private String nombre;
    private String apellido;
    private String dni;
    private Direccion direccion;
    private Date fechaCreacion;
}