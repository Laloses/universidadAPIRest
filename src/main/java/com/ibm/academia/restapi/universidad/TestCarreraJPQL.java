package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.servicios.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Component
public class TestCarreraJPQL implements CommandLineRunner {

    @Autowired
    private ICarreraService carreraService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Carreras cuyos profesores se llamen Juan Hernandez");
        Iterable<Carrera> carreras = carreraService.buscarCarrerasPorProfesorNombreYApellido("Juan", "Hernandez");
        carreras.forEach(System.out::println);
        
        System.out.println("Carreras cuyos profesores se llamen Julian Fernandez");
        carreras = carreraService.buscarCarrerasPorProfesorNombreYApellido("Julian", "Fernandez");
        carreras.forEach(System.out::println);
    }
    
}
