package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;
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
public class PabellonDAOImplTest {
    
    private PabellonDAO pabellonDAO;
    private PabellonRepository pabellonRepository;

    @BeforeEach
    void setUp(){
            pabellonRepository = mock(PabellonRepository.class);
            pabellonDAO = new PabellonDAOImpl(pabellonRepository);
    }

    @Test
    @DisplayName("TEST: buscarPorLocalidad de PabellonDAOImpl")
    @Disabled
    void buscarPorLocalidad(){
        //Given
        String localidad = "San Garpas";
        when(pabellonRepository.findByDireccionLocalidad(localidad))
            .thenReturn(Arrays.asList(DatosDummy.pabellon01(), DatosDummy.pabellon02()));

        //When
        List<Pabellon> expected = (List<Pabellon>) pabellonDAO.buscarPorLocalidad(localidad);

        //Then
        assertThat(expected.get(0)).isEqualTo(DatosDummy.pabellon01());
        assertThat(expected.get(1)).isEqualTo(DatosDummy.pabellon02());

        verify(pabellonRepository).findByDireccionLocalidad(localidad);
    }
}
