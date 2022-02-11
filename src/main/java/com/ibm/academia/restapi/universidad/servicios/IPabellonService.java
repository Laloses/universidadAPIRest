package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public interface IPabellonService extends IGenericService<Pabellon>{
    
    public Iterable<Pabellon> buscarPorLocalidad(String localidad);
    
    public Iterable<Pabellon> buscarPorNombrePabellon(String nombre);
}
