package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelos.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.IEmpleadoService;
import com.ibm.academia.restapi.universidad.servicios.IPersonaService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Component
public class TestEmpleadoJPQL implements CommandLineRunner {
    
    @Autowired
    @Qualifier("empleadoServiceImpl")
    private IPersonaService personaService;

    @Override
    public void run(String... args) throws Exception {

//        List<Direccion> direcciones = new ArrayList<>();	
//        direcciones.add(new Direccion("45 poniente", "123", "786731", "Area 1", "1", "San Juanito"));
//        direcciones.add(new Direccion("23 oriente", "123", "786830", "Area 2", "2", "San Padrito"));
//        direcciones.add(new Direccion("76 norte", "56", "786480", "Area 3", "1", "San Pablo"));
//        direcciones.add(new Direccion("45 sur", "768", "386630", "Area 4", "4", "San Fernando"));
//        direcciones.add(new Direccion("3 ponitente", "3", "727430", "Area 5", "5", "San Agustin"));
//        
//        List<Empleado> empleados = new ArrayList<>();
//        empleados.add(new Empleado(null, BigDecimal.valueOf(5000), TipoEmpleado.MANTENIMIENTO, "Juan", "Alvarez", "213213", direcciones.get(0), "Lalo"));
//        empleados.add(new Empleado(null, BigDecimal.valueOf(2000), TipoEmpleado.ADMINISTRATRIVO, "Pedrito", "Sanchez", "2342", direcciones.get(1), "Lalo"));
//        empleados.add(new Empleado(null, BigDecimal.valueOf(3400), TipoEmpleado.MANTENIMIENTO, "Catalina", "hernandez", "324", direcciones.get(2), "Lalo"));
//        empleados.add(new Empleado(null, BigDecimal.valueOf(3200), TipoEmpleado.ADMINISTRATRIVO, "Pedrito", "Flores", "345345", direcciones.get(3), "Lalo"));
//        empleados.add(new Empleado(null, BigDecimal.valueOf(6400), TipoEmpleado.MANTENIMIENTO, "Mary", "Santa", "34575", direcciones.get(4), "Lalo"));
//        
//        Iterable<Empleado> empleadosGuardados =  ((IEmpleadoService)personaService).guardarTodos((Iterable)empleados);
//        System.out.println("Empleados guardados:");
//        empleadosGuardados.forEach(System.out::println);
        Iterable<Persona> empleadosAdmin = ((IEmpleadoService)personaService).buscarEmpleadosPorTipo(TipoEmpleado.ADMINISTRATRIVO);
        System.out.println("Empleados admin:");
        empleadosAdmin.forEach(System.out::println);
    }
    
}
