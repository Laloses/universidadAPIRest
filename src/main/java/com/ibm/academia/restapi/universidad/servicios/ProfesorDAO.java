package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelos.entidades.Profesor;
import java.util.List;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public interface ProfesorDAO extends PersonaDAO {
    public Iterable<Persona> buscarProfesoresPorCarrera(String nombreCarrera);
    public Profesor actualizar(Long profesorId, Profesor profesor);
    public Persona asociarCarreraProfesor(List<Long> carrerasId, Long profesorId);
}
