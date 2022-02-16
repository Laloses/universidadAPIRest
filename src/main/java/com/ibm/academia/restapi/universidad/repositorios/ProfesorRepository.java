package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Repository(value = "repositorioProfesor")
public interface ProfesorRepository extends PersonaRepository{
    @Query(value = "select p from Profesor p join fetch p.carreras c where c.nombre = ?1")
    public Iterable<Persona> buscarProfesoresPorCarrera(String nombreCarrera);
    
    @Query(value = "select p from Profesor p left join fetch p.carreras c")
    public Iterable<Persona> buscarTodos();
}
