package com.ibm.academia.restapi.universidad.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelos.dto.AlumnoDTO;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelos.mapper.AlumnoMapper;
import com.ibm.academia.restapi.universidad.servicios.AlumnoDAO;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    private final static Logger logger = LoggerFactory.getLogger(AlumnoController.class);

    @Autowired
    @Qualifier("alumnoDAOImpl")
    private PersonaDAO alumnoDao;

    /**
     * Endpoint para guardar un nuevo alumno
     * @param alumno
     * @return Retorna el alumno guardado
     */
    @PostMapping("/guardar")
    @ApiOperation(value = "Guardar un nuevo alumno")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    public ResponseEntity<?> crearAlumno(@Valid @RequestBody Persona alumno, BindingResult result) {
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
        Persona alumnoGuardado = alumnoDao.guardar(alumno);
        return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
    }

    /**
     * Endpoint para consultar todos los alumnos
     * @return Una lista de alumnos
     * @NotFoundException En caso de no haber alumnos
     */
    @GetMapping("/lista-completa")
    @ApiOperation(value = "Consultar todos los alumnos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    public ResponseEntity<?> obtenerTodos() {
        List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();

        if(alumnos.isEmpty())
                throw new NotFoundException("No existen alumnos");

        return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar a un alumno por su id
     * @param alumnoId
     * @return La informacion del alumno
     * @NotFoundException en caso de no existir el alumno
     */
    @GetMapping("/alumnoId/{alumnoId}")
    @ApiOperation(value = "Consultar un alumno por su id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Long alumnoId) {
        Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);

        if(!oAlumno.isPresent()) 
            throw new NotFoundException(String.format("Alumno con id %d no existe", alumnoId));

        return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
    }

    /**
     * Endpoint para eliminar a un alumno por su id
     * @param alumnoId
     * @return No retorna contenido, solo el status
     * @NotFoundException en caso de no existir el alumno a eliminar
     */
    @DeleteMapping("/eliminar/alumnoId/{alumnoId}")
    @ApiOperation(value = "Eliminar un alumno por sy id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No existe el alumno en la base de datos")
    })
    public ResponseEntity<?> eliminarAlumno(@PathVariable Long alumnoId) {
        Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);

        if(!oAlumno.isPresent())
                throw new NotFoundException(String.format("El alumno con ID %d no existe", alumnoId));

        alumnoDao.eliminarPorId(oAlumno.get().getId()); 
        return new ResponseEntity<String>("Alumno ID: " + alumnoId + " se elimino satisfactoriamente",  HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para actualizar a un alumno por su id
     * @param alumnoId
     * @param alumno Datos del alumno a actualizar
     * @return La informacion del alumno actualizado
     * @NotFoundException en caso de no existir el alumno
     */
    @PutMapping("/actualizar/alumnoId/{alumnoId}")
    @ApiOperation(value = "Actualizar datos de un alumno")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No existe el alumno en la base de datos")
    })
    public ResponseEntity<?> actualizarAlumno(@PathVariable Long alumnoId, @RequestBody Persona alumno) {
        Persona alumnoActualizado = null;
        try{
            alumnoActualizado = ((AlumnoDAO)alumnoDao).actualizar(alumnoId, alumno);
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
        return new ResponseEntity<Persona>(alumnoActualizado, HttpStatus.OK);
    }

    /**
     * Endpoint para asociar una carrera a un alumno
     * @param carreraId
     * @param alumnoId
     * @return La informacion del alumno con la carrera asociada
     * @NotFoundException en caso de no existir el alumno
     * @NotFoundException en caso de no existir la carrera
     */
    @PutMapping("/asociar-carrera")
    @ApiOperation(value = "Asociar una carrera a un alumno")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos, alumno o carrera")
    })
    public ResponseEntity<?> asignarCarreraAlumno(@RequestParam Long carreraId, @RequestParam(name = "alumno_id") Long alumnoId) {
        Persona alumno = null;
        try{
            alumno = ((AlumnoDAO)alumnoDao).asociarCarreraAlumno(carreraId, alumnoId); 
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
        
        return new ResponseEntity<Persona>(alumno, HttpStatus.OK);
    }
    
    /**
     * Endpoint para consultar todos los alumnos mediante un DTO
     * @return Retorna una lista de alumnos de tipo AlumnoDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/lista/dto")
    public ResponseEntity<?> obtenerTodosSinCarrera() {
        List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();

        if(alumnos.isEmpty())
                throw new NotFoundException("No existen alumnos en la BD.");

        List<AlumnoDTO> listaAlumnos = alumnos
                .stream()
                .map(AlumnoMapper::mapAlumno)
                .collect(Collectors.toList());
        return new ResponseEntity<List<AlumnoDTO>>(listaAlumnos, HttpStatus.OK);
    }
}