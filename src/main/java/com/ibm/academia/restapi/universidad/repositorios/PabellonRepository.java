package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Long>{
    public Iterable<Pabellon> findByDireccionLocalidad(String localidad);
    
    public Iterable<Pabellon> findByNombre(String nombre);
}
