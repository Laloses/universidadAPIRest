package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public interface AlumnoDAO extends PersonaDAO {
    public Iterable<Alumno> buscarPorNombreCarrera(String nombreCarrera);
    public Persona actualizar(Long alumnoId, Persona alumno);
    public Persona asociarCarreraAlumno(Long carreraId, Long alumnoId);
}
