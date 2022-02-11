package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Alumno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Repository(value = "repositorioAlumno")
public interface AlumnoRepository extends PersonaRepository{
    @Query(value = "select a from Alumno a where a.carrera.nombre like %?1%")
    public Iterable<Alumno> buscarPorNombreCarrera(String nombreCarrera);
}
