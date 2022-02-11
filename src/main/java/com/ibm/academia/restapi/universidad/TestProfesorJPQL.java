package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelos.entidades.Profesor;
import com.ibm.academia.restapi.universidad.servicios.ICarreraService;
import com.ibm.academia.restapi.universidad.servicios.IPersonaService;
import com.ibm.academia.restapi.universidad.servicios.IProfesorService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Component
public class TestProfesorJPQL implements CommandLineRunner {
    @Autowired
    @Qualifier(value = "profesorServiceImpl")
    private IPersonaService personaService;
    
    @Autowired
    private ICarreraService carreraService;

    @Override
    public void run(String... args) throws Exception {
//        List<Carrera> carrereas = new ArrayList<>();
//        carrereas.add(new Carrera(null, "Ing. Sistemas", 250, 4, "Lalo"));
//        carrereas.add(new Carrera(null, "Ing. Ambiental", 250, 4, "Lalo"));
//        carrereas.add(new Carrera(null, "Ing. Desarrollo Industrial", 250, 4, "Lalo"));
//        carrereas.add(new Carrera(null, "Ing. Agricola", 250, 4, "Lalo"));
//        carrereas.add(new Carrera(null, "Administracion de proyectos", 250, 4, "Lalo"));
//        carrereas.add(new Carrera(null, "Gastronomia", 250, 4, "Lalo"));
//        Iterable<Carrera> carrerasGuardadas = carreraService.guardarTodos(carrereas);
//        System.out.println("Carreras guardadas");
//        carrerasGuardadas.forEach(System.out::println);
        
//        List<Direccion> direcciones = new ArrayList<>();	
//        direcciones.add(new Direccion("30 poniente", "101", "786431", "Area 1", "1", "San juan"));
//        direcciones.add(new Direccion("30 oriente", "201", "786230", "Area 2", "1", "San juan"));
//        direcciones.add(new Direccion("30 norte", "302", "786440", "Area 3", "1", "San juan"));
//        direcciones.add(new Direccion("30 sur", "20", "786630", "Area 4", "1", "San juan"));
//        direcciones.add(new Direccion("35 ponitente", "12", "787430", "Area 5", "1", "San juan"));
//        
//        List<Profesor> profesores = new ArrayList<>();	
//        profesores.add(new Profesor(BigDecimal.valueOf(1500), null, "Pedro", "Gonzales", "12341", direcciones.get(0), "Lalo"));
//        profesores.add(new Profesor(BigDecimal.valueOf(1500), null, "Julian", "Fernandez", "122341", direcciones.get(1), "Lalo"));
//        profesores.add(new Profesor(BigDecimal.valueOf(1500), null, "Alfredo", "Smith", "132341", direcciones.get(2), "Lalo"));
//        profesores.add(new Profesor(BigDecimal.valueOf(1500), null, "Eduardo", "Flores", "13341", direcciones.get(3), "Lalo"));
//        profesores.add(new Profesor(BigDecimal.valueOf(1500), null, "Marian", "Chavez", "123341", direcciones.get(4), "Lalo"));
//        
//        Iterable<Profesor> savedProfes = ((IProfesorService)personaService).guardarTodos((Iterable)profesores);
//        System.out.println("Profesores insertados:");
//        savedProfes.forEach(System.out::println);

//        Iterable<Persona> profesores = personaService.buscarTodos();
//        System.out.println("Profesores:");
//        List<Carrera> carreras = (List<Carrera>) carreraService.buscarTodos();
//        profesores.forEach(profesor -> {
//            System.out.println(profesor);
//            Set<Carrera> carreraProfe = new HashSet<Carrera>();
//            carreraProfe.add(carreras.get(getRandomNumber(1, 7)));
//            carreraProfe.add(carreras.get(getRandomNumber(1, 7)));
//            
//            ((Profesor)profesor).setCarreras(carreraProfe);
//            profesor.setUsuarioModificacion("eduardo");
//            personaService.guardar(profesor);
//        });
//        System.out.println("Profesores actualizados?");

        System.out.println("Profesores por carrera = Gastronomia");
        System.out.println( ((IProfesorService)personaService).buscarProfesoresPorCarrera("Gastronomia") );
        
        System.out.println("Profesores por carrera = Sistemas");
        System.out.println( ((IProfesorService)personaService).buscarProfesoresPorCarrera("Sistemas") );
        
    }
    
    public Integer getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
}
