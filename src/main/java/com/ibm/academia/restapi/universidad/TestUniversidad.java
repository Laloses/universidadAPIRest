package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.servicios.IAlumnoService;
import com.ibm.academia.restapi.universidad.servicios.ICarreraService;
import com.ibm.academia.restapi.universidad.servicios.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
//@Component
public class TestUniversidad implements CommandLineRunner{
    @Autowired
    private ICarreraService carreraService;
    
    @Autowired
    private IAlumnoService alumnoService;
    
    @Autowired
    private IPersonaService personaService;
    

    @Override
    public void run(String... args) throws Exception {
//        Carrera ingenieriaSistemas = new Carrera(null, "Ing. Ssistemas", 130, 4,"Lalo");
//        Carrera responseCarrera = carreraService.guardar(ingenieriaSistemas);
//        System.out.println(responseCarrera.toString());

//        Direccion direccion = new Direccion("2 norte", "12", "74447", "San Andres", "1", "Santiago");
//        Persona alumno = new Alumno(null, "Eduardo", "Hernandez", "123123as", direccion, "Lalo");
//        Alumno insertedAlumno = (Alumno)alumnoService.guardar(alumno);
//        System.out.println(insertedAlumno);
        
//        System.out.println("Carrera id=1");
//        Carrera carrera = carreraService.buscarPorId(1L).orElse(new Carrera());
//        System.out.println(carrera);
//        
//        System.out.println("Persona dni=1234");
//        Persona persona = personaService.buscarPorDni("1234").orElse(null);
//        System.out.println(persona);
//        
//        System.out.println("Alumnos nomCarrera = sist");
//        Iterable<Alumno> alumnosSistemas = alumnoService.buscarPorNombreCarrera("sist");
//        alumnosSistemas.forEach(System.out::println);
    }
    
}
