package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Repository
public interface CarreraRepository extends CrudRepository<Carrera, Long>{
//    @Query("select c from Profesor p join p.carreras c where p.nombre = ?1 and p.apellido = ?2") //Los dos funcionan
    @Query("select c from Carrera c join fetch c.profesores p where p.nombre = ?1 and p.apellido = ?2")
    Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
}
