package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public class ProfesorDAOImplTest {
    
    private ProfesorDAO profesorDAO;
    private ProfesorRepository profesorRepository;

    @BeforeEach
    void setUp(){
            profesorRepository = mock(ProfesorRepository.class);
            profesorDAO = new ProfesorDAOImpl(profesorRepository);
    }

    @Test
    @DisplayName("TEST: buscarProfesoresPorCarrera de PabellonDAOImpl")
    void buscarProfesoresPorCarrera(){
        //Given
        String carrera = "Ing. Sistemas";
        when(profesorRepository.buscarProfesoresPorCarrera(carrera))
            .thenReturn(Arrays.asList(DatosDummy.profesor01(), DatosDummy.profesor02()));

        //When
        List<Persona> expected = (List<Persona>) profesorDAO.buscarProfesoresPorCarrera(carrera);

        //Then
        assertThat(expected.get(0)).isEqualTo(DatosDummy.profesor01());
        assertThat(expected.get(1)).isEqualTo(DatosDummy.profesor02());

        verify(profesorRepository).buscarProfesoresPorCarrera(carrera);
    }
}
