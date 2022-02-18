package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelos.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelos.entidades.Profesor;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {
    
    @Autowired
    private PabellonDAO pabellonDao;

    @Autowired
    public EmpleadoDAOImpl(@Qualifier("repositorioEmpleado")PersonaRepository repository) {
        super(repository);
    }
    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarEmpleadosPorTipo(TipoEmpleado tipoEmpleado) {
        return ((EmpleadoRepository)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarTodos() {
        return ((EmpleadoRepository)repository).buscarTodos();
    }
    
    @Override
    @Transactional
    public Empleado actualizar(Long empleadoId, Empleado empleado) {
        Optional<Persona> opEmpleado = repository.findById(empleadoId);

        if(!opEmpleado.isPresent())
                throw new NotFoundException(String.format("El aula con ID %d no existe", empleadoId));
        
        Empleado empleadoActualizado = null;
        Empleado oEmpleado = (Empleado) opEmpleado.get();

        oEmpleado.setNombre(empleado.getNombre());
        oEmpleado.setApellido(empleado.getApellido());
        oEmpleado.setDireccion(empleado.getDireccion());
        oEmpleado.setSueldo(empleado.getSueldo());
        oEmpleado.setTipoEmpleado(empleado.getTipoEmpleado());
        empleadoActualizado = (Empleado)repository.save(oEmpleado);

        return (Empleado)empleadoActualizado;
    }
    
    @Override
    @Transactional
    public Persona asociarPabellonEmpleado(Long pabellonId, Long empleadoId) {
            Optional<Persona> oEmpleado = repository.findById(empleadoId);
            if(!oEmpleado.isPresent())
                    throw new NotFoundException(String.format("El empleado con ID %d no existe", empleadoId));
            
            Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
            if(!oPabellon.isPresent())
                throw new NotFoundException(String.format("El pabellon con ID %d no existe", pabellonId));
            
            ((Empleado)oEmpleado.get()).setPabellon(oPabellon.get());
            return repository.save(oEmpleado.get());
    }
}
