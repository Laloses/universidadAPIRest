package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public class PersonaServiceImpl extends GenericServiceImpl<Persona, PersonaRepository> implements IPersonaService{

    public PersonaServiceImpl( PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> buscarPorNombreyApellido(String nombre, String apellido) {
        return repository.buscarPorNombreyApellido(nombre, apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> buscarPorDni(String dni) {
        return repository.buscarPorDni(dni);
    }
    
}
