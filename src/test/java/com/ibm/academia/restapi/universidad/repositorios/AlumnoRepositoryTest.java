package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelos.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import java.util.Arrays;
import java.util.List;
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
public class AlumnoRepositoryTest {
    @Autowired
    private CarreraRepository carreraRepository;
    
    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @BeforeEach
    void setUp(){
        Carrera carrera = DatosDummy.carrera06();
        carreraRepository.save(carrera);
        
        List<Alumno> alumnos = Arrays.asList(
                DatosDummy.alumno01(),
                DatosDummy.alumno02(),
                DatosDummy.alumno03()
        );
        alumnos.get(0).setCarrera(carrera);
        alumnoRepository.saveAll(alumnos);
    }
    
    @AfterEach
    void tearDown(){
        carreraRepository.deleteAll();
        alumnoRepository.deleteAll();
    }
    
    @Test
    @DisplayName("TEST: buscarPorNombreCarrera de AlumnoRepository")
    @Disabled
    void buscarPorNombreCarrera(){
        //GWT
        //Given
        String given = "Gastronomia";
        //When
        List<Alumno> expectedAlumnos = (List<Alumno>) alumnoRepository.buscarPorNombreCarrera(given);
        //Then
        Assertions.assertThat(expectedAlumnos.size() == 1).isTrue();
    }
}
