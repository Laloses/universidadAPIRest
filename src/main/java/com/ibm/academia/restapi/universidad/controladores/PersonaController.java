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
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping("/persona")
@Api(value = "Metodos relacionados con las personas", tags = "Metodos sobre personas")
public class PersonaController {
    private final static Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private PersonaDAO personaDAO;

    /**
     * Endpoint para consultar todas las personas
     * @return Retorna una lista de personas.
     * @NotFoundException Si no hay datos en la base de datos
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/lista-completa")
    @ApiOperation(value = "Consultar todas los personas, con sus relaciones")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    public ResponseEntity<?> listarTodas() {
        List<Persona> personas = (List<Persona>) personaDAO.buscarTodos();
        
        if(personas.isEmpty()) throw new NotFoundException("No existen personas en la BD.");
        
        return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar un persona por id
     * @param personaId Parámetro de búsqueda de la persona
     * @return Retorna un objeto de tipo persona
     * @NotFoundException En caso de que falle buscando la persona
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/personaId/{personaId}")
    @ApiOperation(value = "Consultar un persona especifica por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan en la base de datos")
    })
    public ResponseEntity<?> buscarPorId(@PathVariable Long personaId) {
        Optional<Persona> oPersona = personaDAO.buscarPorId(personaId);

        if(!oPersona.isPresent())
                throw new NotFoundException(String.format("El persona con id: %d no existe", personaId));

        return new ResponseEntity<Persona>(oPersona.get(), HttpStatus.OK);	
    }

    /**
     * Endpoint para guardar un persona
     * @param persona Parámetros que se guardaran en el persona
     * @return Retorna el persona guardado
     * @author EMHH - 17-02-2022
     */
    @PostMapping("/guardar")
    @ApiOperation(value = "Guardar un persona")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 400, message = "Errores en el envio de informacion")
    })
    public ResponseEntity<?> guardar(@Valid @RequestBody Persona persona, BindingResult result) {
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

        Persona personaGuardado = personaDAO.guardar(persona);
        return new ResponseEntity<Persona>(personaGuardado, HttpStatus.CREATED);
    }

    /**
     * Endpoint para eliminar un persona
     * @param personaId Parámetro de búsqueda del persona a eliminar
     * @return No retorna contenido, solo el status
     * @author EMHH - 17-02-2022
     */
    @DeleteMapping("/eliminar/personaId/{personaId}")
    @ApiOperation(value = "Eliminar un persona")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long personaId) {
        Optional<Persona> oPersona = personaDAO.buscarPorId(personaId);

        if(!oPersona.isPresent())
                throw new NotFoundException(String.format("EL persona con id: %d no existe", personaId));

        personaDAO.eliminarPorId(personaId);
        return new ResponseEntity<>("El persona con id: " + personaId + " fue eliminado", HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para actualizar un persona
     * @param personaId Parámetro de búsqueda de la persona a actualizar
     * @param persona Datos del persona que se cambiaran
     * @return Retorna el persona actualizada
     * @NotFoundException En caso de no existir el persona
     * @author EMHH - 17-02-2022
     */
    @PutMapping("/actualizar/personaId/{personaId}")
    @ApiOperation(value = "Actualizar un persona")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> actualizar(@PathVariable Long personaId, @Valid @RequestBody Persona persona, BindingResult result) {
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

        Persona personaActualizado = null;

        try{
            personaActualizado = personaDAO.actualizar(personaId, persona);
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }

        return new ResponseEntity<Persona>(personaActualizado, HttpStatus.OK);
    }
    
    /**
     * Endpoint para buscar una persona por DNI
     * @param dni Parámetro de búsqueda de la persona
     * @return Retorna la persona
     * @NotFoundException En caso de no existir la persona
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/dni/{dni}")
    @ApiOperation(value = "Buscar una persona por DNI")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> buscarPorDNI(@PathVariable String dni) {
        Optional<Persona> oPersona = personaDAO.buscarPorDni(dni);

        if(!oPersona.isPresent())
                throw new NotFoundException(String.format("La persona con DNI: %d no existe", dni));

        return new ResponseEntity<Persona>(oPersona.get(), HttpStatus.OK);
    }
    
    /**
     * Endpoint para buscar una persona por apellido
     * @param nombre Parámetro de búsqueda de la persona
     * @param apellido Parámetro de búsqueda de la persona
     * @return Retorna la persona
     * @NotFoundException En caso de no existir la persona
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/nombre/{nombre}/apellido/{apellido}")
    @ApiOperation(value = "Buscar una persona por nombre y apellido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> buscarPorNombreApellido(@PathVariable String nombre, @PathVariable String apellido) {
        Optional<Persona> oPersona = personaDAO.buscarPorNombreyApellido(nombre, apellido);

        if(!oPersona.isPresent())
                throw new NotFoundException(String.format("La persona %s %s no existe", nombre, apellido));

        return new ResponseEntity<Persona>(oPersona.get(), HttpStatus.OK);
    }
}