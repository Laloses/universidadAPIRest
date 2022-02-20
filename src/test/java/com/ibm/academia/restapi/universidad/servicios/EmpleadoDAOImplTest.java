package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;
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
public class EmpleadoDAOImplTest {
    
    private EmpleadoDAO empleadoDAO;
    private EmpleadoRepository empleadoRepository;

    @BeforeEach
    void setUp(){
            empleadoRepository = mock(EmpleadoRepository.class);
            empleadoDAO = new EmpleadoDAOImpl(empleadoRepository);
    }

    @Test
    @DisplayName("TEST: buscarEmpleadosPorTipo de EmpleadoDAOImpl")
    @Disabled
    void buscarEmpleadosPorTipo(){
        //Given
        TipoEmpleado tipoEmpleado  = TipoEmpleado.ADMINISTRATRIVO;
        when(empleadoRepository.findEmpleadoByTipoEmpleado(tipoEmpleado))
                .thenReturn(Arrays.asList(DatosDummy.empleado02(), DatosDummy.empleado04()));

        //When
        List<Persona> expected = (List<Persona>) empleadoDAO.buscarEmpleadosPorTipo(tipoEmpleado);

        //Then
        assertThat(expected.get(0)).isEqualTo(DatosDummy.empleado02());
        assertThat(expected.get(1)).isEqualTo(DatosDummy.empleado04());

        verify(empleadoRepository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }
    
}
