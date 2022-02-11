package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.servicios.IPabellonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Component
public class TestPabellonJPQL implements CommandLineRunner {
    
    @Autowired
    private IPabellonService pabellonService;

    @Override
    public void run(String... args) throws Exception {
        
//        List<Direccion> direcciones = new ArrayList<>();	
//        direcciones.add(new Direccion("67 poniente", "34", "686731", "Area 1", "1", "San Juanito"));
//        direcciones.add(new Direccion("2 oriente", "435", "736830", "Area 2", "2", "San Padrito"));
//        direcciones.add(new Direccion("5 norte", "6", "486480", "Area 3", "1", "San Pablo"));
//        direcciones.add(new Direccion("56 sur", "3", "586630", "Area 4", "4", "San Fernando"));
//        direcciones.add(new Direccion("65 ponitente", "5", "827430", "Area 5", "5", "San Agustin"));
//        
//        List<Pabellon> pabellones =  new ArrayList<>();
//        pabellones.add(new Pabellon(null, "este", "45", direcciones.get(0),"Lalo"));
//        pabellones.add(new Pabellon(null, "norte", "45", direcciones.get(1),"Lalo"));
//        pabellones.add(new Pabellon(null, "oeste", "45", direcciones.get(2),"Lalo"));
//        pabellones.add(new Pabellon(null, "sur", "45", direcciones.get(3),"Lalo"));
//        pabellones.add(new Pabellon(null, "exterior", "45", direcciones.get(4),"Lalo"));
        
        System.out.println("Pabellones de localidad 'San Pablo'");
        Iterable<Pabellon> pabellones = (List<Pabellon>) pabellonService.buscarPorLocalidad("San Pablo");
        pabellones.forEach(System.out::println);
        
        System.out.println("Pabellones de nombre 'exterior'");
        pabellones = (List<Pabellon>) pabellonService.buscarPorNombrePabellon("exterior");
        pabellones.forEach(System.out::println);
    }
    
}
