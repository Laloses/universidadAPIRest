package com.ibm.academia.restapi.universidad.datos;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelos.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelos.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelos.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.modelos.entidades.Profesor;
import java.math.BigDecimal;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
public class DatosDummy {
    //Carreras
    public static Carrera carrera01(){ return new Carrera(null, "Ing. Sistemas", 250, 4, "Lalo");}
    public static Carrera carrera02(){ return new Carrera(null, "Ing. Ambiental", 250, 4, "Lalo");}
    public static Carrera carrera03(){ return new Carrera(null, "Ing. Desarrollo Industrial", 250, 4, "Lalo");}
    public static Carrera carrera04(){ return new Carrera(null, "Ing. Agricola", 250, 4, "Lalo");}
    public static Carrera carrera05(){ return new Carrera(null, "Administracion de proyectos", 250, 4, "Lalo");}
    public static Carrera carrera06(){ return new Carrera(null, "Gastronomia", 250, 4, "Lalo");}
    //Direcciones
    public static Direccion direccion01(){ return new Direccion("30 poniente", "101", "786431", "Area 1", "1", "San Garpas");}
    public static Direccion direccion02(){ return new Direccion("30 oriente", "201", "786230", "Area 2", "1", "San Pedrito");}
    public static Direccion direccion03(){ return new Direccion("30 norte", "302", "786440", "Area 3", "1", "San Luis");}
    public static Direccion direccion04(){ return new Direccion("30 sur", "20", "786630", "Area 4", "1", "San Agustin");}
    public static Direccion direccion05(){ return new Direccion("35 ponitente", "12", "787430", "Area 5", "1", "San juan");}
    //Profesores
    public static Profesor profesor01(){ return new Profesor(BigDecimal.valueOf(1500), null, "Pedro", "Gonzales", "12341", direccion01(), "Lalo");}
    public static Profesor profesor02(){ return new Profesor(BigDecimal.valueOf(1500), null, "Julian", "Fernandez", "122341", direccion02(), "Lalo");}
    public static Profesor profesor03(){ return new Profesor(BigDecimal.valueOf(1500), null, "Alfredo", "Smith", "132341", direccion03(), "Lalo");}
    public static Profesor profesor04(){ return new Profesor(BigDecimal.valueOf(1500), null, "Eduardo", "Flores", "13341", direccion04(), "Lalo");}
    public static Profesor profesor05(){ return new Profesor(BigDecimal.valueOf(1500), null, "Marian", "Chavez", "123341", direccion05(), "Lalo");}
    //Alumnos
    public static Alumno alumno01(){ return new Alumno(null, "Pedro", "Gonzales", "12332441", direccion01(), "Lalo");}
    public static Alumno alumno02(){ return new Alumno(null, "Julian", "Fernandez", "123242341", direccion02(), "Lalo");}
    public static Alumno alumno03(){ return new Alumno(null, "Alfredo", "Smith", "136752341", direccion03(), "Lalo");}
    public static Alumno alumno04(){ return new Alumno(null, "Eduardo", "Flores", "1367341", direccion04(), "Lalo");}
    public static Alumno alumno05(){ return new Alumno(null, "Marian", "Chavez", "12378341", direccion05(), "Lalo");}
    //Aulas
    public static Aula aula01(){ return new  Aula(null, 10, "23", 12, TipoPizarron.PIZARRA, "lalo");}
    public static Aula aula02(){ return new  Aula(null, 11, "24", 20, TipoPizarron.TIZA, "lalo");}
    public static Aula aula03(){ return new  Aula(null, 12, "25", 30, TipoPizarron.PIZARRA, "lalo");}
    public static Aula aula04(){ return new  Aula(null, 13, "26", 40, TipoPizarron.TIZA, "lalo");}
    public static Aula aula05(){ return new  Aula(null, 14, "27", 23, TipoPizarron.PIZARRA, "lalo");}
    //Pabellones
    public static Pabellon pabellon01(){ return new  Pabellon(null, "este", "45", direccion01(),"Lalo");}
    public static Pabellon pabellon02(){ return new  Pabellon(null, "norte", "45", direccion02(),"Lalo");}
    public static Pabellon pabellon03(){ return new  Pabellon(null, "oeste", "45", direccion03(),"Lalo");}
    public static Pabellon pabellon04(){ return new  Pabellon(null, "sur", "45", direccion04(),"Lalo");}
    public static Pabellon pabellon05(){ return new  Pabellon(null, "exterior", "45", direccion05(),"Lalo");}
    //Empleados
    public static Empleado empleado01(){ return new  Empleado(null, BigDecimal.valueOf(5000), TipoEmpleado.MANTENIMIENTO, "Juan", "Alvarez", "213213", direccion01(), "Lalo");}
    public static Empleado empleado02(){ return new  Empleado(null, BigDecimal.valueOf(2000), TipoEmpleado.ADMINISTRATRIVO, "Pedrito", "Sanchez", "2342", direccion02(), "Lalo");}
    public static Empleado empleado03(){ return new  Empleado(null, BigDecimal.valueOf(3400), TipoEmpleado.MANTENIMIENTO, "Catalina", "hernandez", "324", direccion03(), "Lalo");}
    public static Empleado empleado04(){ return new  Empleado(null, BigDecimal.valueOf(3200), TipoEmpleado.ADMINISTRATRIVO, "Pedrito", "Flores", "345345", direccion04(), "Lalo");}
    public static Empleado empleado05(){ return new  Empleado(null, BigDecimal.valueOf(6400), TipoEmpleado.MANTENIMIENTO, "Mary", "Santa", "34575", direccion05(), "Lalo");}
}
