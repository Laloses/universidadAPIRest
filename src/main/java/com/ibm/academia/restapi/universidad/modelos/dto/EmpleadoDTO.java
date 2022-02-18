package com.ibm.academia.restapi.universidad.modelos.dto;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpleadoDTO extends ProfesorDTO {
    private TipoEmpleado tipoEmpleado;
}