package com.ibm.academia.restapi.universidad.modelos.entidades;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
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
@Table(name = "profesores", schema = "universidad")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Profesor extends Persona {
    
   @Column(name = "sueldo", nullable = false, length = 11)
   private BigDecimal sueldo;
   
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "profesor_carrera", schema = "univesidad",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "carrera_id")
            )
   private Set<Carrera> carreras;

    public Profesor(BigDecimal sueldo, Long id, String nombre, String apellido, String dni, Direccion direccion, String nombreUsuario) {
        super(id, nombre, apellido, dni, direccion, nombreUsuario);
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Profesor{");
        sb.append("carreras=").append(carreras);
        sb.append(", sueldo=").append(sueldo);
        sb.append(", ").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
   
}
