package com.ibm.academia.restapi.universidad.modelos.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "alumnos",schema = "universidad")
@PrimaryKeyJoinColumn(name = "persona_id")
@EqualsAndHashCode
public class Alumno extends Persona{
    
    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "carrera_id", foreignKey = @ForeignKey(name = "FK_CARRERA_ID"))
    private Carrera carrera;

    public Alumno(Long id, String nombre, String apellido, String dni, Direccion direccion, String usuarioCreacion) {
        super(id, nombre, apellido, dni, direccion, usuarioCreacion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alumno{");
        sb.append("carrera=").append(carrera);
        sb.append(", ").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
