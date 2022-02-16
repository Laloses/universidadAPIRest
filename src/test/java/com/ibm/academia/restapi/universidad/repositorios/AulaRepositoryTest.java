package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
import java.util.List;
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
public class AulaRepositoryTest {
    @Autowired
    private AulaRepository aulaRepository;
    @Autowired
    private PabellonRepository pabellonRepository;
    
    @BeforeEach
    void setUp(){
        Aula aula = DatosDummy.aula01();
        Pabellon pabellon = DatosDummy.pabellon01();
        aula.setPabellon(pabellon);
        
        aulaRepository.save(aula);
        pabellonRepository.save(pabellon);
    }
    
    @AfterEach
    void tearDown(){
        aulaRepository.deleteAll();
        pabellonRepository.deleteAll();
    }
    
    @Test
    @DisplayName("TEST: findAulasByPabellonNombre de AulasRepository")
    void findAulasByPabellonNombre(){
        //GWT
        //Given (Setup)
        String givenNombre = "este";
        //When
        List<Aula> expectedAulas = (List<Aula>) aulaRepository.findAulasByPabellonNombre(givenNombre);
        //Then
        Assertions.assertThat(expectedAulas.size() == 1).isTrue();
    }
}
