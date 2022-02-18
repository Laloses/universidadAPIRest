package com.ibm.academia.restapi.universidad.modelos.dto;

import com.ibm.academia.restapi.universidad.modelos.entidades.Direccion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfesorDTO implements Serializable {
    protected Long personaId;
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected Direccion direccion;
    protected Date fechaCreacion;
    protected BigDecimal sueldo;
}