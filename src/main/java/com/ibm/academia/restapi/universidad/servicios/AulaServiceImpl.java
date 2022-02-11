package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Service
public class AulaServiceImpl extends GenericServiceImpl<Aula, AulaRepository> implements IAulaService {

    @Autowired
    public AulaServiceImpl(AulaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Aula> buscarPorTipoPizarron(TipoPizarron tipoPizarron) {
        return repository.findAulasByTipoPizarron(tipoPizarron);
    }

    @Override
    public Iterable<Aula> buscarPorNombrePabellon(String nombre) {
        return repository.findAulasByPabellonNombre(nombre);
    }

    @Override
    public Optional<Aula> buscarPorNumeroAula(int numeroAula) {
        return repository.findByNumeroAula(numeroAula);
    }
    
}
