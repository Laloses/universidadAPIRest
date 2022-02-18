package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;
import java.util.Optional;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public interface AulaDAO extends GenericDAO<Aula>{
    
    public Iterable<Aula> buscarPorTipoPizarron(TipoPizarron tipoPizarron);
    
    public Iterable<Aula> buscarPorNombrePabellon(String nombre);
    
    public Optional<Aula> buscarPorNumeroAula(int numeroAula);
    
    public Aula actualizar(Long aulaId, Aula aula);
}
