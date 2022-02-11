package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Service
public class PabellonServiceImpl extends GenericServiceImpl<Pabellon, PabellonRepository> implements IPabellonService {

    @Autowired
    public PabellonServiceImpl(PabellonRepository repository) {
        super(repository);
    }
    
    @Override
    public Iterable<Pabellon> buscarPorLocalidad(String localidad) {
        return repository.findByDireccionLocalidad(localidad);
    }

    @Override
    public Iterable<Pabellon> buscarPorNombrePabellon(String nombre) {
        return repository.findByNombre(nombre);
    }
    
}
