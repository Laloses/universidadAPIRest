package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;
import java.util.Optional;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public interface IAulaService extends IGenericService<Aula>{
    
    public Iterable<Aula> buscarPorTipoPizarron(TipoPizarron tipoPizarron);
    
    public Iterable<Aula> buscarPorNombrePabellon(String nombre);
    
    public Optional<Aula> buscarPorNumeroAula(int numeroAula);
}
