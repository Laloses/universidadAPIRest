package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
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

/** Todos los que sean pruebas de repositorios en memoria llevan la anotacion DataJpaTest
 * @author Eduardo Martell Hernandez Hernandez
 */
@DataJpaTest
public class CarreraRepositoryTest {
    @Autowired
    private CarreraRepository carreraRepository;
    
    @Autowired
    private ProfesorRepository profesorRepository;
    
    @BeforeEach
    void ponerArriba(){
        Set<Carrera> carreras = Stream.of(
                DatosDummy.carrera01(), 
                DatosDummy.carrera02(), 
                DatosDummy.carrera03()
        ).collect(Collectors.toCollection(HashSet::new));
        carreraRepository.saveAll(carreras);
        
        List<Profesor> profesores = Arrays.asList(
                DatosDummy.profesor01(),
                DatosDummy.profesor02(),
                DatosDummy.profesor03()
        );
        profesores.get(0).setCarreras(carreras);
        profesorRepository.saveAll(profesores);
    }
    
    @AfterEach
    void abrirAbajo(){
        carreraRepository.deleteAll();
        profesorRepository.deleteAll();
    }
    
    @Test
    @DisplayName("TEST: Buscar carrera por nombre y apellido de Profesor")
    @Disabled
    void buscarCarrerasPorProfesorNombreYApellido(){
        //GWT
        //Given
//        Optional<Persona> oProfesor1 =  profesorRepository.buscarPorDni("12341");
//        Optional<Persona> oProfesor2 =  profesorRepository.buscarPorDni("122341");
//        
//        ((Profesor)oProfesor1.get()).setCarreras(carreras);
        //When
        List<Carrera> expected = (List<Carrera>) carreraRepository.buscarCarrerasPorProfesorNombreYApellido("Pedro", "Gonzales");
        
        //then
        Assertions.assertThat(expected.size() == 3).isTrue();
    }
}
