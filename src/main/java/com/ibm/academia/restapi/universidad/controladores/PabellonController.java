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
import com.ibm.academia.restapi.universidad.modelos.dto.PabellonDTO;
import com.ibm.academia.restapi.universidad.modelos.mapper.PabellonMapper;
import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.servicios.PabellonDAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pabellon")
@Api(value = "Metodos relacionados con las pabellones", tags = "Metodos sobre pabellones")
public class PabellonController {
    private final static Logger logger = LoggerFactory.getLogger(PabellonController.class);

    @Autowired
    private PabellonDAO pabellonDAO;

    /**
     * Endpoint para consultar todas las pabellones
     * @return Retorna una lista de pabellones.
     * @NotFoundException Si no hay datos en la base de datos
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/lista-completa")
    @ApiOperation(value = "Consultar todas las pabellones, con sus relaciones")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    public ResponseEntity<?> listarTodas() {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarTodos();
        
        if(pabellones.isEmpty()) throw new NotFoundException("No existen pabellones en la BD.");
        
        return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar un pabellon por id
     * @param pabellonId Parámetro de búsqueda de la pabellon
     * @return Retorna un objeto de tipo pabellon
     * @NotFoundException En caso de que falle buscando la pabellon
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/pabellonId/{pabellonId}")
    @ApiOperation(value = "Consultar un pabellon especifica por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan en la base de datos")
    })
    public ResponseEntity<?> buscarPorId(@PathVariable Long pabellonId) {
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(pabellonId);

        if(!oPabellon.isPresent())
                throw new NotFoundException(String.format("El pabellon con id: %d no existe", pabellonId));

        return new ResponseEntity<Pabellon>(oPabellon.get(), HttpStatus.OK);	
    }

    /**
     * Endpoint para guardar un pabellon
     * @param pabellon Parámetros que se guardaran en el pabellon
     * @return Retorna el pabellon guardado
     * @author EMHH - 17-02-2022
     */
    @PostMapping("/guardar")
    @ApiOperation(value = "Guardar un pabellon")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 400, message = "Errores en el envio de informacion")
    })
    public ResponseEntity<?> guardar(@Valid @RequestBody Pabellon pabellon, BindingResult result) {
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

        Pabellon pabellonGuardado = pabellonDAO.guardar(pabellon);
        return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
    }

    /**
     * Endpoint para eliminar un pabellon
     * @param pabellonId Parámetro de búsqueda del pabellon a eliminar
     * @return No retorna contenido, solo el status
     * @author EMHH - 17-02-2022
     */
    @DeleteMapping("/eliminar/pabellonId/{pabellonId}")
    @ApiOperation(value = "Eliminar un pabellon")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long pabellonId) {
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(pabellonId);

        if(!oPabellon.isPresent())
                throw new NotFoundException(String.format("EL pabellon con id: %d no existe", pabellonId));

        pabellonDAO.eliminarPorId(pabellonId);
        return new ResponseEntity<>("El pabellon con id: " + pabellonId + " fue eliminado", HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para actualizar un pabellon
     * @param pabellonId Parámetro de búsqueda de la pabellon a actualizar
     * @param pabellon Datos del pabellon que se cambiaran
     * @return Retorna el pabellon actualizada
     * @NotFoundException En caso de no existir el pabellon
     * @author EMHH - 17-02-2022
     */
    @PutMapping("/actualizar/pabellonId/{pabellonId}")
    @ApiOperation(value = "Actualizar un pabellon")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> actualizar(@PathVariable Long pabellonId, @Valid @RequestBody Pabellon pabellon, BindingResult result) {
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

        PabellonDTO pabellonActualizado = null;

        try{
            pabellonActualizado = PabellonMapper.mapPabellon( pabellonDAO.actualizar(pabellonId, pabellon));
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }

        return new ResponseEntity<PabellonDTO>(pabellonActualizado, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todas las pabellones mediante un DTO
     * @return Retorna una lista de tipo pabellonesDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/lista/dto")
    @ApiOperation(value = "Consultar todas las pabellones mediante un DTO")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerTodosSinRelacion() {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarTodos();

        if(pabellones.isEmpty())
                throw new NotFoundException("No existen pabellones en la BD.");

        List<PabellonDTO> listaPabellons = pabellones
                .stream()
                .map(PabellonMapper::mapPabellon)
                .collect(Collectors.toList());
        return new ResponseEntity<List<PabellonDTO>>(listaPabellons, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todos los pabellones por localidad
     * @param localidad parametro de busqueda
     * @return Retorna una lista de tipo pabellonesDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/localidad/{localidad}")
    @ApiOperation(value = "Consultar todos los pabellones por localidad")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerPorLocalidad(@PathVariable String localidad) {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarPorLocalidad(localidad);

        if(pabellones.isEmpty())
                throw new NotFoundException("No existen pabellones en esa localidad.");

        List<PabellonDTO> listaPabellons = pabellones
                .stream()
                .map(PabellonMapper::mapPabellon)
                .collect(Collectors.toList());
        return new ResponseEntity<List<PabellonDTO>>(listaPabellons, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todos los pabellones por nombre de pabellon
     * @param nombre parametro de busqueda
     * @return Retorna una lista de tipo pabellonesDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/pabellon/nombre/{nombre}")
    @ApiOperation(value = "Consultar todos los pabellones por nombre de pabellon")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerPorPabellonNombre(@PathVariable String nombre) {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarPorNombrePabellon(nombre);

        if(pabellones.isEmpty())
                throw new NotFoundException("No existen pabellones en esa localidad.");

        List<PabellonDTO> listaPabellons = pabellones
                .stream()
                .map(PabellonMapper::mapPabellon)
                .collect(Collectors.toList());
        return new ResponseEntity<List<PabellonDTO>>(listaPabellons, HttpStatus.OK);
    }
}