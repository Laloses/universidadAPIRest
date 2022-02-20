package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public class AulaDAOImplTest {
    
    private AulaDAO aulaDAO;
    private AulaRepository aulaRepository;
    
    @BeforeEach
    void setUp(){
        aulaRepository = mock(AulaRepository.class);
        aulaDAO = new AulaDAOImpl(aulaRepository);
    }
    
    @Test
    @DisplayName("TEST: buscarPorNombrePabellon de AulaDAOImpl")
    @Disabled
    void buscarPorNombrePabellon(){
        //GWT
        //Given
        String nombrePabellon = "este";
        when( aulaRepository.findAulasByPabellonNombre(nombrePabellon) )
            .thenReturn(
                    Arrays.asList(DatosDummy.aula01())
            );
        //When
        List<Aula> expectedAulas =  (List<Aula>) aulaDAO.buscarPorNombrePabellon(nombrePabellon);
        //Then
        assertThat(expectedAulas.get(0)).isEqualTo(DatosDummy.aula01());
        
        verify(aulaRepository).findAulasByPabellonNombre(nombrePabellon);
    }
    
}
