package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public interface CarreraDAO extends GenericDAO<Carrera>{
    public Iterable<Carrera> findCarrerasByNombreContains(String nombre);
    public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
    public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
    public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
    public Carrera actualizar(Long carreraId, Carrera carrera);
}
