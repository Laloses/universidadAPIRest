package com.ibm.academia.restapi.universidad.modelos.mapper;

import com.ibm.academia.restapi.universidad.modelos.dto.AlumnoDTO;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;

public class AlumnoMapper {
    public static AlumnoDTO mapAlumno(Persona alumno){
        AlumnoDTO alumnoDTO = new AlumnoDTO();
        alumnoDTO.setPersonaId(alumno.getId());
        alumnoDTO.setNombre(alumno.getNombre());
        alumnoDTO.setApellido(alumno.getApellido());
        alumnoDTO.setDni(alumno.getDni());
        alumnoDTO.setDireccion(alumno.getDireccion());
        alumnoDTO.setFechaCreacion(alumno.getFechaCreacion());
        return alumnoDTO;
    }
}