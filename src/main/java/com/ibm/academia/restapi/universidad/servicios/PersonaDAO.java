package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import java.util.Optional;

/**
 *
 * @author laloses
 */
public interface PersonaDAO extends GenericDAO<Persona>{
    public Optional<Persona> buscarPorNombreyApellido(String nombre, String apellido);
    public Optional<Persona> buscarPorDni(String dni);
    
    public Persona actualizar(Long personaId, Persona persona);
}
