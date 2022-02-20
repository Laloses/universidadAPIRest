package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelos.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
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
public class PabellonRepositoryTest {
    @Autowired
    private PabellonRepository pabellonRepository;
    
    @BeforeEach
    void setUp(){
        List<Pabellon> pabellones = Arrays.asList(
                DatosDummy.pabellon01(),
                DatosDummy.pabellon02(),
                DatosDummy.pabellon03()
        );
        List<Direccion> direcciones = Arrays.asList(
                DatosDummy.direccion01(),
                DatosDummy.direccion02(),
                DatosDummy.direccion03()
        );
        pabellones.get(0).setDireccion(direcciones.get(0));
        pabellones.get(1).setDireccion(direcciones.get(1));
        pabellones.get(2).setDireccion(direcciones.get(2));
        
        pabellonRepository.saveAll(pabellones);
    }
    
    @AfterEach
    void tearDown(){
        pabellonRepository.deleteAll();
    }
    
    @Test
    @DisplayName("TEST: findByDireccionLocalidad de PabellonRepository")
    @Disabled
    void findByDireccionLocalidad(){
        //GWT
        //Given
        String givenLocalidad = "San Luis";
        //When
        List<Pabellon> expectedpPabellons = (List<Pabellon>) pabellonRepository.findByDireccionLocalidad(givenLocalidad);
        //Then
        Assertions.assertThat(expectedpPabellons.size() == 1).isTrue();
    }
    
}
