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
import com.ibm.academia.restapi.universidad.modelos.dto.CarreraDTO;
import com.ibm.academia.restapi.universidad.modelos.mapper.CarreraMapper;
import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.servicios.CarreraDAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/carrera")
@Api(value = "Metodos relacionados con las carreras", tags = "Metodos sobre carreras")
public class CarreraController {
    private final static Logger logger = LoggerFactory.getLogger(CarreraController.class);

    @Autowired
    private CarreraDAO carreraDao;

    /**
     * Endpoint para consultar todas las carreras
     * @return Retorna una lista de carreras.
     * @author NDSC - 14-02-2022
     */
    @ApiOperation(value = "Consultar todas las carreras")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    @GetMapping("/lista-completa")
    public ResponseEntity<?> listarTodas() {
        List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();
        return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar una carrera por id
     * @param carreraId Parámetro de búsqueda de la carrera
     * @return Retorna un objeto de tipo carrera
     * @NotFoundException En caso de que falle buscando la carrera
     * @author NDSC - 14-02-2022
     */
    @GetMapping("/carreraId/{carreraId}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long carreraId) {
        Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);

        if(!oCarrera.isPresent())
                throw new NotFoundException(String.format("La carrera con id: %d no existe", carreraId));

        return new ResponseEntity<Carrera>(oCarrera.get(), HttpStatus.OK);	
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Carrera carrera, BindingResult result) {
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

        Carrera carreraGuardada = carreraDao.guardar(carrera);
        return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/carreraId/{carreraId}")
    public ResponseEntity<?> eliminar(@PathVariable Long carreraId) {
        Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);

        if(!oCarrera.isPresent())
                throw new NotFoundException(String.format("La carrera con id: %d no existe", carreraId));

        carreraDao.eliminarPorId(carreraId);
        return new ResponseEntity<>("La carrera con id: " + carreraId + " fue eliminada", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/actualizar/carreraId/{carreraId}")
    public ResponseEntity<?> actualizar(@PathVariable Long carreraId, @Valid @RequestBody Carrera carrera, BindingResult result) {
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

        Carrera carreraActualizada = null;

        try{
            carreraActualizada = carreraDao.actualizar(carreraId, carrera);
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }

        return new ResponseEntity<Carrera>(carreraActualizada, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todas las carreras mediante un DTO
     * @return Retorna un objeto de tipo carreraDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author 16-02-2022
     */
    @GetMapping("/lista/dto")
    public ResponseEntity<?> consultarTodasCarreras() {
        List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();

        if(carreras.isEmpty())
                throw new NotFoundException("No existen carreras en la BD.");

        List<CarreraDTO> listaCarreras = carreras
                        .stream()
                        .map(CarreraMapper::mapCarrera)
                        .collect(Collectors.toList());
        return new ResponseEntity<List<CarreraDTO>>(listaCarreras, HttpStatus.OK);
    }
    
    /**
     * Endpoint para consultar todas las carreras coincian con el nombre
     * @param nombreCarrera Parametro de busqueda
     * @return Retorna la lista de carreras coincidentes
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/nombre/contains/{nombreCarrera}")
    @ApiOperation(value = "Buscar carreras que coincidan con el nombre proporcionado")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerPorNombreContains(@PathVariable String nombreCarrera) {
        List<Carrera> carreras = (List<Carrera>) carreraDao.findCarrerasByNombreContains(nombreCarrera);

        if(carreras.isEmpty())
                throw new NotFoundException("No existen carreras en la BD.");

        List<CarreraDTO> listaCarreras = carreras
                        .stream()
                        .map(CarreraMapper::mapCarrera)
                        .collect(Collectors.toList());
        return new ResponseEntity<List<CarreraDTO>>(listaCarreras, HttpStatus.OK);
    }
    
    /**
     * Endpoint para consultar todas las carreras coincian la cantidad de años en adelante
     * @param cantidadAnios Parametro de busqueda
     * @return Retorna la lista de carreras coincidentes
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @GetMapping("/cantidadAnios/{cantidadAnios}")
    @ApiOperation(value = "Buscar carreras por cantidad de años")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerPorCantidadAniosAfter(@PathVariable Integer cantidadAnios) {
        List<Carrera> carreras = (List<Carrera>) carreraDao.findCarrerasByCantidadAniosAfter(cantidadAnios);

        if(carreras.isEmpty())
                throw new NotFoundException("No existen carreras en la BD.");

        List<CarreraDTO> listaCarreras = carreras
                        .stream()
                        .map(CarreraMapper::mapCarrera)
                        .collect(Collectors.toList());
        return new ResponseEntity<List<CarreraDTO>>(listaCarreras, HttpStatus.OK);
    }
    
    /**
     * Endpoint para consultar todas las carreras coincian el nombre y apellido de algun profesor
     * @param nombre Parametro de busqueda
     * @param apellido Parametro de busqueda
     * @return Retorna la lista de carreras coincidentes
     * @NotFoundException En caso de que no existan valores en la BD
     * @author EMHH - 17-02-2022
     */
    @PutMapping("/profesor")
    @ApiOperation(value = "Buscar carreras por nombre y apellido de profesor")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos que coincidan")
    })
    public ResponseEntity<?> obtenerPorProfesor(@RequestParam String nombre, @RequestParam String apellido) {
        List<Carrera> carreras = (List<Carrera>) carreraDao.buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);

        if(carreras.isEmpty())
                throw new NotFoundException("No existen carreras en la BD.");

        List<CarreraDTO> listaCarreras = carreras
                        .stream()
                        .map(CarreraMapper::mapCarrera)
                        .collect(Collectors.toList());
        return new ResponseEntity<List<CarreraDTO>>(listaCarreras, HttpStatus.OK);
    }
}