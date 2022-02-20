package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelos.entidades.Profesor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@DataJpaTest
public class ProfesorRepositoryTest {
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
    
    @BeforeEach
    void setUp(){
        Set<Carrera> carreras = Stream.of(
                DatosDummy.carrera04(), 
                DatosDummy.carrera05()
        ).collect(Collectors.toCollection(HashSet::new));
        carreraRepository.saveAll(carreras);
        
        List<Profesor> profesores = Arrays.asList(
                DatosDummy.profesor03(),
                DatosDummy.profesor04(),
                DatosDummy.profesor05()
        );
        
        profesores.get(2).setCarreras(carreras);
        profesores.get(1).setCarreras(carreras);
        profesorRepository.saveAll(profesores);
    }
    
    @AfterEach
    void tearDown(){
        carreraRepository.deleteAll();
        profesorRepository.deleteAll();
    }
    
    @Test
    @DisplayName("TEST: buscarProfesoresPorCarrera de ProfesorRepository")
    @Disabled
    void buscarProfesoresPorCarrera(){
        //GWT
        //Given
        String givenNombre = "Administracion de proyectos";
        //When
        List<Persona> expectedprProfesor = (List<Persona>) profesorRepository.buscarProfesoresPorCarrera(givenNombre);
        //Then
        Assertions.assertThat(expectedprProfesor.size() == 2).isTrue();
    }
    
}
