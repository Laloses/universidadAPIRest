package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelos.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;
import com.ibm.academia.restapi.universidad.servicios.IAulaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Component
public class TestAulaJPQL implements CommandLineRunner {

    @Autowired
    private IAulaService aulasService;
    
    @Override
    public void run(String... args) throws Exception {
        
//        List<Aula> aulas = new ArrayList<>();
//        aulas.add(new Aula(null, 10, "23", 12, TipoPizarron.PIZARRA, "lalo"));
//        aulas.add(new Aula(null, 11, "24", 20, TipoPizarron.TIZA, "lalo"));
//        aulas.add(new Aula(null, 12, "25", 30, TipoPizarron.PIZARRA, "lalo"));
//        aulas.add(new Aula(null, 13, "26", 40, TipoPizarron.TIZA, "lalo"));
//        aulas.add(new Aula(null, 14, "27", 23, TipoPizarron.PIZARRA, "lalo"));
//        aulas = (List<Aula>) aulasService.guardarTodos(aulas);
        
        List<Aula> aulas = (List<Aula>) aulasService.buscarPorTipoPizarron(TipoPizarron.PIZARRA) ;
        System.out.println("Aulas de tipo pizarron: ");
        aulas.forEach(System.out::println);
        
        Optional<Aula> aula = aulasService.buscarPorNumeroAula(12);
        System.out.println("Aulas con #12: ");
        System.out.println(aula.get().toString());
        
        aulas = (List<Aula>) aulasService.buscarPorNombrePabellon("sur");
        System.out.println("Aulas del pabellon 'sur': ");
        aulas.forEach(System.out::println);
    }
    
}
