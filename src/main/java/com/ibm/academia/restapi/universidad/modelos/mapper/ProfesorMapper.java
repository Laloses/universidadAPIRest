package com.ibm.academia.restapi.universidad.modelos.mapper;

import com.ibm.academia.restapi.universidad.modelos.dto.ProfesorDTO;
import com.ibm.academia.restapi.universidad.modelos.entidades.Profesor;

public class ProfesorMapper {
    public static ProfesorDTO mapProfesor(Profesor profesor){
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setPersonaId(profesor.getId());
        profesorDTO.setNombre(profesor.getNombre());
        profesorDTO.setApellido(profesor.getApellido());
        profesorDTO.setDni(profesor.getDni());
        profesorDTO.setDireccion(profesor.getDireccion());
        profesorDTO.setFechaCreacion(profesor.getFechaCreacion());
        profesorDTO.setSueldo(profesor.getSueldo());
        return profesorDTO;
    }
}