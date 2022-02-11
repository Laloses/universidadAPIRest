 package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/** Como es abstracta, no es repository (NoRepositoryBean)
 * @author Eduardo Martell Hernandez Hernandez
 */
@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona, Long> {
    
    @Query(value = "select p from Persona p where p.nombre = ?1 and p.apellido = ?2")
    public Optional<Persona> buscarPorNombreyApellido(String nombre, String apellido);
    
    @Query(value = "select p from Persona p where p.dni = ?1")
    public Optional<Persona> buscarPorDni(String dni);
}
