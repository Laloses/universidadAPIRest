package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import org.springframework.stereotype.Repository;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Repository(value = "empleadoRepositorio")
public interface IEmpleadoService extends IPersonaService{
    public Iterable<Persona> buscarEmpleadosPorTipo(TipoEmpleado tipoEmpleado);
}
