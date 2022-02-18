package com.ibm.academia.restapi.universidad.modelos.mapper;

import com.ibm.academia.restapi.universidad.modelos.dto.EmpleadoDTO;
import com.ibm.academia.restapi.universidad.modelos.entidades.Empleado;

public class EmpleadoMapper {
    public static EmpleadoDTO mapEmpleado(Empleado empleado){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setPersonaId(empleado.getId());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setApellido(empleado.getApellido());
        empleadoDTO.setDni(empleado.getDni());
        empleadoDTO.setDireccion(empleado.getDireccion());
        empleadoDTO.setFechaCreacion(empleado.getFechaCreacion());
        empleadoDTO.setSueldo(empleado.getSueldo());
        empleadoDTO.setTipoEmpleado(empleado.getTipoEmpleado());
        return empleadoDTO;
    }
}