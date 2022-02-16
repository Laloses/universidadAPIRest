package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public interface CarreraDAO extends GenericDAO<Carrera>{
    Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
}
