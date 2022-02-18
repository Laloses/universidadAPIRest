package com.ibm.academia.restapi.universidad.controladores;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelos.dto.EmpleadoDTO;
import com.ibm.academia.restapi.universidad.modelos.mapper.EmpleadoMapper;
import com.ibm.academia.restapi.universidad.modelos.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.EmpleadoDAO;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;
import com.ibm.academia.restapi.universidad.servicios.ProfesorDAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/empleado")
@Api(value = "Metodos relacionados con las emppleados", tags = "Metodos sobre empleados")
public class EmpleadoController {
    private final static Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    @Qualifier("empleadoDAOImpl")
    private PersonaDAO empleadoDAO;

    /**
     * Endpoint para consultar todas las empleados
     * @return Retorna una lista de empleados.
     * @NotFoundException Si no hay datos en la base de datos
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/lista-completa")
    @ApiOperation(value = "Consultar todas las empleados, con sus relaciones")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    public ResponseEntity<?> listarTodas() {
        List<Persona> empleados = (List<Persona>) empleadoDAO.buscarTodos();
        
        if(empleados.isEmpty()) throw new NotFoundException("No existen empleados en la BD.");
        
        return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar un empleado por id
     * @param empleadoId Parámetro de búsqueda de la empleado
     * @return Retorna un objeto de tipo empleado
     * @NotFoundException En caso de que falle buscando la empleado
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/empleadoId/{empleadoId}")
    @ApiOperation(value = "Consultar un empleado especifica por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan en la base de datos")
    })
    public ResponseEntity<?> buscarPorId(@PathVariable Long empleadoId) {
        Optional<Persona> oEmpleado = empleadoDAO.buscarPorId(empleadoId);

        if(!oEmpleado.isPresent())
                throw new NotFoundException(String.format("El empleado con id: %d no existe", empleadoId));

        return new ResponseEntity<Persona>(oEmpleado.get(), HttpStatus.OK);	
    }

    /**
     * Endpoint para guardar un empleado
     * @param empleado Parámetros que se guardaran en el empleado
     * @return Retorna el empleado guardado
     * @author EMHH - 17-02-2022
     */
    @PostMapping("/guardar")
    @ApiOperation(value = "Guardar un empleado")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 400, message = "Errores en el envio de informacion")
    })
    public ResponseEntity<?> guardar(@Valid @RequestBody Empleado empleado, BindingResult result) {
        Map<String, Object> validaciones = new HashMap<String, Object>();
        if(result.hasErrors()) {
            List<String> listaErrores = result.getFieldErrors()
                            .stream()
                            .map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
                            .collect(Collectors.toList());
            validaciones.put("Lista Errores", listaErrores);
            logger.info(validaciones.toString());
            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
        }

        Persona empleadoGuardado = empleadoDAO.guardar(empleado);
        return new ResponseEntity<Persona>(empleadoGuardado, HttpStatus.CREATED);
    }

    /**
     * Endpoint para eliminar un empleado
     * @param empleadoId Parámetro de búsqueda del empleado a eliminar
     * @return No retorna contenido, solo el status
     * @author EMHH - 17-02-2022
     */
    @DeleteMapping("/eliminar/empleadoId/{empleadoId}")
    @ApiOperation(value = "Eliminar un empleado")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long empleadoId) {
        Optional<Persona> oEmpleado = empleadoDAO.buscarPorId(empleadoId);

        if(!oEmpleado.isPresent())
                throw new NotFoundException(String.format("EL empleado con id: %d no existe", empleadoId));

        empleadoDAO.eliminarPorId(empleadoId);
        return new ResponseEntity<>("El empleado con id: " + empleadoId + " fue eliminado", HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para actualizar un empleado
     * @param empleadoId Parámetro de búsqueda de la empleado a actualizar
     * @param empleado Datos del empleado que se cambiaran
     * @return Retorna el empleado actualizada
     * @NotFoundException En caso de no existir el empleado
     * @author EMHH - 17-02-2022
     */
    @PutMapping("/actualizar/empleadoId/{empleadoId}")
    @ApiOperation(value = "Actualizar un empleado")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> actualizar(@PathVariable Long empleadoId, @Valid @RequestBody Empleado empleado, BindingResult result) {
        Map<String, Object> validaciones = new HashMap<String, Object>();
        if(result.hasErrors()) {
            List<String> listaErrores = result.getFieldErrors()
                            .stream()
                            .map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
                            .collect(Collectors.toList());
            validaciones.put("Lista Errores", listaErrores);
            logger.info(validaciones.toString());
            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
        }

        EmpleadoDTO empleadoActualizado = null;

        try{
            empleadoActualizado = EmpleadoMapper.mapEmpleado( ((EmpleadoDAO)empleadoDAO).actualizar(empleadoId, empleado));
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }

        return new ResponseEntity<EmpleadoDTO>(empleadoActualizado, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todas las empleados mediante un DTO
     * @return Retorna una lista de tipo empleadosDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/lista/dto")
    @ApiOperation(value = "Actualizar un empleado")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerTodosSinRelacion() {
        List<Persona> empleados = (List<Persona>) empleadoDAO.buscarTodos();

        if(empleados.isEmpty())
                throw new NotFoundException("No existen empleados en la BD.");

        List<EmpleadoDTO> listaEmpleados = empleados
                .stream()
                .map(empleado -> (Empleado)empleado)
                .map(EmpleadoMapper::mapEmpleado)
                .collect(Collectors.toList());
        return new ResponseEntity<List<EmpleadoDTO>>(listaEmpleados, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todos los empleados por su tipo
     * @param tipoEmpleado Parametro para la busqueda
     * @return Retorna una lista de empleados
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/tipo/{tipoEmpleado}")
    @ApiOperation(value = "Buscar empleados por su tipo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerPorTipo(@PathVariable TipoEmpleado tipoEmpleado) {
        List<Persona> empleados = (List<Persona>) ((EmpleadoDAO)empleadoDAO).buscarEmpleadosPorTipo(tipoEmpleado);

        if(empleados.isEmpty())
                throw new NotFoundException("No existen empleados con ese tipo");

        List<EmpleadoDTO> listaEmpleados = empleados
                .stream()
                .map(empleado -> (Empleado)empleado)
                .map(EmpleadoMapper::mapEmpleado)
                .collect(Collectors.toList());
        return new ResponseEntity<List<EmpleadoDTO>>(listaEmpleados, HttpStatus.OK);
    }
    
    /**
     * Endpoint para asociar un pabellon a un empleado
     * @param pabellonId 
     * @param empleadoId
     * @return La informacion del empleado actualizado
     * @NotFoundException en caso de no existir el empleado
     * @NotFoundException en caso de no existir el pabellon
     */
    @PutMapping("/asociar-pabellon")
    @ApiOperation(value = "Asociar una o varias carreras a un profesor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos, alumno o carrera")
    })
    public ResponseEntity<?> asignarCarreras(@RequestParam Long pabellonId, @RequestParam Long empleadoId) {
        Persona pabellon = null;
        try{
            pabellon = ((EmpleadoDAO)empleadoDAO).asociarPabellonEmpleado(pabellonId, empleadoId); 
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
        
        return new ResponseEntity<Persona>(pabellon, HttpStatus.OK);
    }
}