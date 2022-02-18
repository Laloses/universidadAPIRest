package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import org.springframework.stereotype.Repository;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Repository(value = "empleadoRepositorio")
public interface EmpleadoDAO extends PersonaDAO{
    public Iterable<Persona> buscarEmpleadosPorTipo(TipoEmpleado tipoEmpleado);
    public Empleado actualizar(Long empleadoId, Empleado empleado);
    public Persona asociarPabellonEmpleado(Long pabellonId, Long empleadoId);
}
