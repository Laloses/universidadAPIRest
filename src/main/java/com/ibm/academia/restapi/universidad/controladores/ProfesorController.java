package com.ibm.academia.restapi.universidad.controladores;

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
import com.ibm.academia.restapi.universidad.modelos.dto.ProfesorDTO;
import com.ibm.academia.restapi.universidad.modelos.mapper.ProfesorMapper;
import com.ibm.academia.restapi.universidad.modelos.entidades.Profesor;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;
import com.ibm.academia.restapi.universidad.servicios.ProfesorDAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/profesor")
@Api(value = "Metodos relacionados con las profesores", tags = "Metodos sobre profesores")
public class ProfesorController {
    private final static Logger logger = LoggerFactory.getLogger(ProfesorController.class);

    @Autowired
    @Qualifier("profesorDAOImpl")
    private PersonaDAO profesorDAO;

    /**
     * Endpoint para consultar todas las profesores
     * @return Retorna una lista de profesores.
     * @NotFoundException Si no hay datos en la base de datos
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/lista-completa")
    @ApiOperation(value = "Consultar todas los profesores, con sus relaciones")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    public ResponseEntity<?> listarTodas() {
        List<Persona> profesores = (List<Persona>) profesorDAO.buscarTodos();
        
        if(profesores.isEmpty()) throw new NotFoundException("No existen profesores en la BD.");
        
        return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar un profesor por id
     * @param profesorId Parámetro de búsqueda de la profesor
     * @return Retorna un objeto de tipo profesor
     * @NotFoundException En caso de que falle buscando la profesor
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/profesorId/{profesorId}")
    @ApiOperation(value = "Consultar un profesor especifica por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan en la base de datos")
    })
    public ResponseEntity<?> buscarPorId(@PathVariable Long profesorId) {
        Optional<Persona> oProfesor = profesorDAO.buscarPorId(profesorId);

        if(!oProfesor.isPresent())
                throw new NotFoundException(String.format("El profesor con id: %d no existe", profesorId));

        return new ResponseEntity<Persona>(oProfesor.get(), HttpStatus.OK);	
    }

    /**
     * Endpoint para guardar un profesor
     * @param profesor Parámetros que se guardaran en el profesor
     * @return Retorna el profesor guardado
     * @author EMHH - 17-02-2022
     */
    @PostMapping("/guardar")
    @ApiOperation(value = "Guardar un profesor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 400, message = "Errores en el envio de informacion")
    })
    public ResponseEntity<?> guardar(@Valid @RequestBody Profesor profesor, BindingResult result) {
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

        Persona profesorGuardado = profesorDAO.guardar(profesor);
        return new ResponseEntity<Persona>(profesorGuardado, HttpStatus.CREATED);
    }

    /**
     * Endpoint para eliminar un profesor
     * @param profesorId Parámetro de búsqueda del profesor a eliminar
     * @return No retorna contenido, solo el status
     * @author EMHH - 17-02-2022
     */
    @DeleteMapping("/eliminar/profesorId/{profesorId}")
    @ApiOperation(value = "Eliminar un profesor")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long profesorId) {
        Optional<Persona> oProfesor = profesorDAO.buscarPorId(profesorId);

        if(!oProfesor.isPresent())
                throw new NotFoundException(String.format("EL profesor con id: %d no existe", profesorId));

        profesorDAO.eliminarPorId(profesorId);
        return new ResponseEntity<>("El profesor con id: " + profesorId + " fue eliminado", HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para actualizar un profesor
     * @param profesorId Parámetro de búsqueda de la profesor a actualizar
     * @param profesor Datos del profesor que se cambiaran
     * @return Retorna el profesor actualizada
     * @NotFoundException En caso de no existir el profesor
     * @author EMHH - 17-02-2022
     */
    @PutMapping("/actualizar/profesorId/{profesorId}")
    @ApiOperation(value = "Actualizar un profesor")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> actualizar(@PathVariable Long profesorId, @Valid @RequestBody Profesor profesor, BindingResult result) {
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

        ProfesorDTO profesorActualizado = null;

        try{
            profesorActualizado = ProfesorMapper.mapProfesor( ((ProfesorDAO)profesorDAO).actualizar(profesorId, profesor));
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }

        return new ResponseEntity<ProfesorDTO>(profesorActualizado, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todas las profesores mediante un DTO
     * @return Retorna una lista de tipo profesoresDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/lista/dto")
    @ApiOperation(value = "Actualizar un profesor")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerTodosSinRelacion() {
        List<Persona> profesores = (List<Persona>) profesorDAO.buscarTodos();

        if(profesores.isEmpty())
                throw new NotFoundException("No existen profesores en la BD.");

        List<ProfesorDTO> listaProfesors = profesores
                .stream()
                .map(profesor -> (Profesor)profesor)
                .map(ProfesorMapper::mapProfesor)
                .collect(Collectors.toList());
        return new ResponseEntity<List<ProfesorDTO>>(listaProfesors, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todos las profesores por el nombre de carrera
     * @param nombreCarrera Parámetro de búsqueda de la profesor
     * @return Retorna una lista de tipo profesoresDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/carrera/{nombreCarrera}")
    @ApiOperation(value = "Actualizar un profesor")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerPorNombreCarrera(@PathVariable String nombreCarrera) {
        List<Persona> profesores = (List<Persona>) ((ProfesorDAO)profesorDAO).buscarProfesoresPorCarrera(nombreCarrera);

        if(profesores.isEmpty())
                throw new NotFoundException("No existen profesores en la BD.");

        List<ProfesorDTO> listaProfesors = profesores
                .stream()
                .map(profesor -> (Profesor)profesor)
                .map(ProfesorMapper::mapProfesor)
                .collect(Collectors.toList());
        return new ResponseEntity<List<ProfesorDTO>>(listaProfesors, HttpStatus.OK);
    }
    
    /**
     * Endpoint para asociar una lista de carreras a un profesor
     * @param carrerasId un array de id's de carreras
     * @param profesorId
     * @return La informacion del profesor con las carrera asociadas
     * @NotFoundException en caso de no existir el profesor
     * @NotFoundException en caso de no existir alguna de las carrera
     */
    @PutMapping("/asociar-carreras")
    @ApiOperation(value = "Asociar una o varias carreras a un profesor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos, alumno o carrera")
    })
    public ResponseEntity<?> asignarCarreras(@RequestParam List<Long> carrerasId, @RequestParam Long profesorId) {
        Persona profesor = null;
        try{
            profesor = ((ProfesorDAO)profesorDAO).asociarCarreraProfesor(carrerasId, profesorId); 
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
        
        return new ResponseEntity<Persona>(profesor, HttpStatus.OK);
    }
}