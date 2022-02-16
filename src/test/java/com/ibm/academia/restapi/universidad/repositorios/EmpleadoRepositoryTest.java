package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@DataJpaTest
public class EmpleadoRepositoryTest {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @BeforeEach
    void setUp(){
        Set<Empleado> empelados = Stream.of(
                DatosDummy.empleado01(), 
                DatosDummy.empleado02(), 
                DatosDummy.empleado03()
        ).collect(Collectors.toCollection(HashSet::new));
        empleadoRepository.saveAll(empelados);
    }
    
    @AfterEach
    void tearDown(){
        empleadoRepository.deleteAll();
    }
    
    @Test
    @DisplayName("TEST: findEmpleadoByTipoEmpleado de EmpleadoRepository")
    void findEmpleadoByTipoEmpleado(){
        //GWT
        //Given
        TipoEmpleado givenTipoEmpleado = TipoEmpleado.ADMINISTRATRIVO;
        //When
        List<Persona> expected = (List<Persona>)empleadoRepository.findEmpleadoByTipoEmpleado(givenTipoEmpleado);
        //Then
        Assertions.assertThat(expected.size() == 1).isTrue();
    }
    
}
