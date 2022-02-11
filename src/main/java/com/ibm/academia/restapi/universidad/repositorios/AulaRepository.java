package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Repository
public interface AulaRepository extends CrudRepository<Aula, Long>{
    public Iterable<Aula> findAulasByTipoPizarron(TipoPizarron tipoPizarron);
    
    public Iterable<Aula> findAulasByPabellonNombre(String nombre);
    
    public Optional<Aula> findByNumeroAula(Integer numeroAula);
}
