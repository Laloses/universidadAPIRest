package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public class PersonaDAOImpl extends GenericDAOImpl<Persona, PersonaRepository> implements PersonaDAO{

    public PersonaDAOImpl( PersonaRepository repository) {
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
    
    @Override
    @Transactional
    public Persona actualizar(Long profesorId, Persona profesor) {
        Optional<Persona> opPersona = repository.findById(profesorId);

        if(!opPersona.isPresent())
                throw new NotFoundException(String.format("El aula con ID %d no existe", profesorId));
        
        Persona empleadoActualizado = null;
        Persona oPersona = (Persona) opPersona.get();

        oPersona.setNombre(profesor.getNombre());
        oPersona.setApellido(profesor.getApellido());
        oPersona.setDireccion(profesor.getDireccion());
        empleadoActualizado = (Persona)repository.save(oPersona);

        return empleadoActualizado;
    }
}
