package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Repository("repositorioEmpleado")
public interface EmpleadoRepository extends PersonaRepository{
    @Query("select e from Empleado e where e.tipoEmpleado = ?1")
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
    
    @Query("select e from Empleado e")
    public Iterable<Persona> buscarTodos();
    
}
