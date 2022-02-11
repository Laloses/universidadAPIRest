package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Service
public class CarreraServiceImpl extends GenericServiceImpl<Carrera, CarreraRepository> implements ICarreraService {
    @Autowired
    public CarreraServiceImpl(CarreraRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido) {
        return repository.buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);
    }
}
