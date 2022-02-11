package com.ibm.academia.restapi.universidad.modelos.entidades;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Direccion implements Serializable{
    private String calle;
    private String numero;
    private String codigoPostal;
    private String departamento;
    private String piso;
    private String localidad;
}
