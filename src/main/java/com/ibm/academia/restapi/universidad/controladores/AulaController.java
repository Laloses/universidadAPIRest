package com.ibm.academia.restapi.universidad.controladores;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
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
import com.ibm.academia.restapi.universidad.modelos.dto.AulaDTO;
import com.ibm.academia.restapi.universidad.modelos.mapper.AulaMapper;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/aula")
@Api(value = "Metodos relacionados con las aulas", tags = "Metodos sobre aulas")
public class AulaController {
    private final static Logger logger = LoggerFactory.getLogger(AulaController.class);

    @Autowired
    private AulaDAO aulaDAO;

    /**
     * Endpoint para consultar todas las aulas
     * @return Retorna una lista de aulas.
     * @NotFoundException Si no hay datos en la base de datos
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/lista-completa")
    @ApiOperation(value = "Consultar todas las aulas, con sus relaciones")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    public ResponseEntity<?> listarTodas() {
        List<Aula> aulas = (List<Aula>) aulaDAO.buscarTodos();
        
        if(aulas.isEmpty()) throw new NotFoundException("No existen aulas en la BD.");
        
        return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar una aula por id
     * @param aulaId Parámetro de búsqueda de la aula
     * @return Retorna un objeto de tipo aula
     * @NotFoundException En caso de que falle buscando la aula
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/aulaId/{aulaId}")
    @ApiOperation(value = "Consultar un aula especifica por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan en la base de datos")
    })
    public ResponseEntity<?> buscarPorId(@PathVariable Long aulaId) {
        Optional<Aula> oAula = aulaDAO.buscarPorId(aulaId);

        if(!oAula.isPresent())
                throw new NotFoundException(String.format("La aula con id: %d no existe", aulaId));

        return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);	
    }

    /**
     * Endpoint para guardar un aula
     * @param aula Parámetros que se guardaran en el aula
     * @return Retorna el aula guardada
     * @author EMHH - 17-02-2022
     */
    @PostMapping("/guardar")
    @ApiOperation(value = "Guardar un aula")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 400, message = "Errores en el envio de informacion")
    })
    public ResponseEntity<?> guardar(@Valid @RequestBody Aula aula, BindingResult result) {
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

        Aula aulaGuardada = aulaDAO.guardar(aula);
        return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
    }

    /**
     * Endpoint para eliminar un aula
     * @param aulaId Parámetro de búsqueda de la aula a eliminar
     * @return No retorna contenido, solo el status
     * @author EMHH - 17-02-2022
     */
    @DeleteMapping("/eliminar/aulaId/{aulaId}")
    @ApiOperation(value = "Eliminar un aula")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long aulaId) {
        Optional<Aula> oAula = aulaDAO.buscarPorId(aulaId);

        if(!oAula.isPresent())
                throw new NotFoundException(String.format("La aula con id: %d no existe", aulaId));

        aulaDAO.eliminarPorId(aulaId);
        return new ResponseEntity<>("La aula con id: " + aulaId + " fue eliminada", HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para actualizar un aula
     * @param aulaId Parámetro de búsqueda de la aula a actualizar
     * @param aula Datos del aula que se cambiaran
     * @return Retorna el aula actualizada
     * @NotFoundException En caso de no existir el aula
     * @author EMHH - 17-02-2022
     */
    @PutMapping("/actualizar/aulaId/{aulaId}")
    @ApiOperation(value = "Actualizar un aula")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> actualizar(@PathVariable Long aulaId, @Valid @RequestBody Aula aula, BindingResult result) {
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

        AulaDTO aulaActualizada = null;

        try{
            aulaActualizada = AulaMapper.mapAula( aulaDAO.actualizar(aulaId, aula));
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }

        return new ResponseEntity<AulaDTO>(aulaActualizada, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todas las aulas mediante un DTO
     * @return Retorna una lista de tipo aulasDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/lista/dto")
    @ApiOperation(value = "Actualizar un aula")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerTodosSinPabellon() {
        List<Aula> aulas = (List<Aula>) aulaDAO.buscarTodos();

        if(aulas.isEmpty())
                throw new NotFoundException("No existen aulas en la BD.");

        List<AulaDTO> listaAulas = aulas
                        .stream()
                        .map(AulaMapper::mapAula)
                        .collect(Collectors.toList());
        return new ResponseEntity<List<AulaDTO>>(listaAulas, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todas las aulas por tipo de pizarron
     * @param tipoPizarron
     * @return Retorna una lista aulas
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/pizarron/{tipoPizarron}")
    @ApiOperation(value = "Buscar aulas por tipo de pizarron")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerPorPizarron(@PathVariable TipoPizarron tipoPizarron) {
        List<Aula> aulas = (List<Aula>) aulaDAO.buscarPorTipoPizarron(tipoPizarron);

        if(aulas.isEmpty())
                throw new NotFoundException("No existen aulas que coincidan.");

        List<AulaDTO> listaAulas = aulas
                        .stream()
                        .map(AulaMapper::mapAula)
                        .collect(Collectors.toList());
        return new ResponseEntity<List<AulaDTO>>(listaAulas, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todas las aulas por nombre del pabellon al que pertence
     * @param nombre
     * @return Retorna una lista aulas
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/pabellon/nombre/{nombre}")
    @ApiOperation(value = "Consultar todas las aulas por nombre del pabellon al que pertence")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerPorPabellon(@PathVariable String nombre) {
        List<Aula> aulas = (List<Aula>) aulaDAO.buscarPorNombrePabellon(nombre);

        if(aulas.isEmpty())
                throw new NotFoundException("No existen aulas que coincidan.");

        List<AulaDTO> listaAulas = aulas
                        .stream()
                        .map(AulaMapper::mapAula)
                        .collect(Collectors.toList());
        return new ResponseEntity<List<AulaDTO>>(listaAulas, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todas las aulas por nombre del pabellon al que pertence
     * @param numeroAula
     * @return Retorna el aula que coincida en forma de DTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/numeroAula/{numeroAula}")
    @ApiOperation(value = "Consultar el aula por nombre del pabellon al que pertence")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerPorNumeroAula(@PathVariable int numeroAula) {
        Optional<Aula> aula = aulaDAO.buscarPorNumeroAula(numeroAula);

        if(!aula.isPresent())
                throw new NotFoundException("No existen aulas que coincidan.");
        
        AulaDTO aulaDTO = AulaMapper.mapAula( aula.get() );
        return new ResponseEntity<AulaDTO>(aulaDTO, HttpStatus.OK);
    }
}