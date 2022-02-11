package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public interface ICarreraService extends IGenericService<Carrera>{
    Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
}
