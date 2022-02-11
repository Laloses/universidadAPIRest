package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Service
public class EmpleadoServiceImpl extends PersonaServiceImpl implements IEmpleadoService {

    @Autowired
    public EmpleadoServiceImpl(@Qualifier("repositorioEmpleado")PersonaRepository repository) {
        super(repository);
    }
    @Override
    public Iterable<Persona> buscarEmpleadosPorTipo(TipoEmpleado tipoEmpleado) {
        return ((EmpleadoRepository)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }

    @Override
    public Iterable<Persona> buscarTodos() {
        return ((EmpleadoRepository)repository).buscarTodos();
    }
    
    
}
