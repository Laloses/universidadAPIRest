package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelos.entidades.Alumno;
import com.ibm.academia.restapi.universidad.repositorios.AlumnoRepository;
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
public class AlumnoDAOImplTest {
    
    private AlumnoDAO alumnoDAO;
    private AlumnoRepository alumnoRepository;
    
    @BeforeEach
    void setUp(){
        alumnoRepository = mock(AlumnoRepository.class);
        alumnoDAO = new AlumnoDAOImpl(alumnoRepository);
    }
    
    @Test
    @DisplayName("TEST: buscarPorNombreCarrera de AlumnoDAOImpl")
    @Disabled
    void buscarPorNombreCarrera(){
        //GWT
        //Given
        String nombreCarrera = "Gastronomia";
        when( alumnoRepository.buscarPorNombreCarrera(nombreCarrera) )
            .thenReturn(
                    Arrays.asList(DatosDummy.alumno01())
            );
        //When
        List<Alumno> expectedAlumnos =  (List<Alumno>) alumnoDAO.buscarPorNombreCarrera(nombreCarrera);
        //Then
        assertThat(expectedAlumnos.get(0).equals(DatosDummy.alumno01()));
        
        verify(alumnoRepository).buscarPorNombreCarrera(nombreCarrera);
    }
}
