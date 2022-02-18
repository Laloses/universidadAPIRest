package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Service
public class AulaDAOImpl extends GenericDAOImpl<Aula, AulaRepository> implements AulaDAO {

    @Autowired
    public AulaDAOImpl(AulaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> buscarPorTipoPizarron(TipoPizarron tipoPizarron) {
        return repository.findAulasByTipoPizarron(tipoPizarron);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> buscarPorNombrePabellon(String nombre) {
        return repository.findAulasByPabellonNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Aula> buscarPorNumeroAula(int numeroAula) {
        return repository.findByNumeroAula(numeroAula);
    }
    
    @Override
    @Transactional
    public Aula actualizar(Long aulaId, Aula aula) {
        Optional<Aula> oAula = repository.findById(aulaId);

        if(!oAula.isPresent())
                throw new NotFoundException(String.format("El aula con ID %d no existe", aulaId));

        Aula aulaActualizada = null;
        oAula.get().setNumeroAula(aula.getNumeroAula());
        oAula.get().setCantidadPupitres(aula.getCantidadPupitres());
        oAula.get().setTipoPizarron(aula.getTipoPizarron());
        aulaActualizada = repository.save(oAula.get());

        return aulaActualizada;
    }
    
}
