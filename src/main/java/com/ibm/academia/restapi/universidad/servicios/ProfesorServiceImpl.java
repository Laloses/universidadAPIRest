package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelos.entidades.Profesor;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Service
public class ProfesorServiceImpl extends PersonaServiceImpl implements IProfesorService {
    
    @Autowired
    public ProfesorServiceImpl(
            @Qualifier(value = "repositorioProfesor")
            PersonaRepository repository) {
        super(repository);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarProfesoresPorCarrera(String nombreCarrera) {
        return ((ProfesorRepository) repository).buscarProfesoresPorCarrera(nombreCarrera);
    }

    @Override
    public Iterable<Persona> buscarTodos() {
        return ((ProfesorRepository) repository).buscarTodos();
    }
    
    
    
}
