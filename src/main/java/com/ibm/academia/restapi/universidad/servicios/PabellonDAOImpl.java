package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Service
public class PabellonDAOImpl extends GenericDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO {

    @Autowired
    public PabellonDAOImpl(PabellonRepository repository) {
        super(repository);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> buscarPorLocalidad(String localidad) {
        return repository.findByDireccionLocalidad(localidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> buscarPorNombrePabellon(String nombre) {
        return repository.findByNombreIgnoreCase(nombre);
    }
    
    @Override
    @Transactional
    public Pabellon actualizar(Long pabellonId, Pabellon pabellon) {
        Optional<Pabellon> oPabellon = repository.findById(pabellonId);

        if(!oPabellon.isPresent())
                throw new NotFoundException(String.format("El aula con ID %d no existe", pabellonId));
        
        Pabellon pabellonActualizado = null;

        oPabellon.get().setNombre(pabellon.getNombre());
        oPabellon.get().setDireccion(pabellon.getDireccion());
        oPabellon.get().setMetrosCuadrados(pabellon.getMetrosCuadrados());
        pabellonActualizado = repository.save(oPabellon.get());

        return pabellonActualizado;
    }
}
