package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Alumno;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public interface AlumnoDAO extends PersonaDAO {
    public Iterable<Alumno> buscarPorNombreCarrera(String nombreCarrera);
}
