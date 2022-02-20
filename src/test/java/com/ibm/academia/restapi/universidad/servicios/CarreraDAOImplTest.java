package com.ibm.academia.restapi.universidad.servicios;

import static com.ibm.academia.restapi.universidad.datos.DatosDummy.carrera01;
import static com.ibm.academia.restapi.universidad.datos.DatosDummy.carrera03;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;
import org.junit.jupiter.api.Disabled;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public class CarreraDAOImplTest {
    
    private CarreraDAO carreraDao;
    private CarreraRepository carreraRepository;

    @BeforeEach
    void setUp(){
            carreraRepository = mock(CarreraRepository.class);
            carreraDao = new CarreraDAOImpl(carreraRepository);
    }

    @Test
    @DisplayName("TEST: buscarCarrerasPorProfesorNombreYApellido de CarreraDAOImpl")
    @Disabled
    void buscarCarrerasPorProfesorNombreYApellido(){
        //Given
        String nombreProfesor = "Julian";
        String apellidoProfesor = "Fernandez";
        when(carreraRepository.buscarCarrerasPorProfesorNombreYApellido(nombreProfesor, apellidoProfesor))
                .thenReturn(Arrays.asList(carrera01(), carrera03()));

        //When
        List<Carrera> expected = (List<Carrera>) carreraDao.buscarCarrerasPorProfesorNombreYApellido(nombreProfesor, apellidoProfesor);

        //Then
        assertThat(expected.get(0)).isEqualTo(carrera01());
        assertThat(expected.get(1)).isEqualTo(carrera03());

        verify(carreraRepository).buscarCarrerasPorProfesorNombreYApellido(nombreProfesor, apellidoProfesor);
    }
}