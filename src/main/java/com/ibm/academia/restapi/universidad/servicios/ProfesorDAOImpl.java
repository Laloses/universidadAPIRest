package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelos.entidades.Profesor;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO {
    
    @Autowired
    private CarreraDAO carreraDao;
    
    @Autowired
    public ProfesorDAOImpl(
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
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarTodos() {
        return ((ProfesorRepository) repository).buscarTodos();
    }
    
    @Override
    @Transactional
    public Profesor actualizar(Long profesorId, Profesor profesor) {
        Optional<Persona> opProfesor = repository.findById(profesorId);

        if(!opProfesor.isPresent())
                throw new NotFoundException(String.format("El aula con ID %d no existe", profesorId));
        
        Profesor empleadoActualizado = null;
        Profesor oProfesor = (Profesor) opProfesor.get();

        oProfesor.setNombre(profesor.getNombre());
        oProfesor.setApellido(profesor.getApellido());
        oProfesor.setDireccion(profesor.getDireccion());
        oProfesor.setSueldo(profesor.getSueldo());
        empleadoActualizado = (Profesor)repository.save(oProfesor);

        return (Profesor)empleadoActualizado;
    }
    
    @Override
    @Transactional
    public Persona asociarCarreraProfesor(List<Long> carrerasId, Long profesorId) {
            Optional<Persona> oProfesor = repository.findById(profesorId);
            if(!oProfesor.isPresent())
                    throw new NotFoundException(String.format("El profesor con ID %d no existe", profesorId));

            Set<Carrera> carreras = new HashSet<>();
            carrerasId.forEach( carreraId -> {
                Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
                if(!oCarrera.isPresent())
                    throw new NotFoundException(String.format("La carrera con ID %d no existe", carreraId));
                else
                    carreras.add(oCarrera.get());
            });
            
            ((Profesor)oProfesor.get()).setCarreras(carreras);
            return repository.save(oProfesor.get());
    }
}
