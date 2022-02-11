package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Alumno;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public interface IAlumnoService extends IPersonaService {
    public Iterable<Alumno> buscarPorNombreCarrera(String nombreCarrera);
}
