package com.ibm.academia.restapi.universidad.modelos.entidades;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
@Table(name="empleados",schema = "universidad")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona{
   @Column(name = "sueldo", nullable = false, length = 11)
   private BigDecimal sueldo;
   
   @Column(name = "tipo_empleado", nullable = false)
   @Enumerated(EnumType.STRING)
   private TipoEmpleado tipoEmpleado;
   
   @OneToOne(optional = true, cascade = CascadeType.ALL)
   @JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_EMPLEADO_PABELLON_ID"))
   private Pabellon pabellon;

    public Empleado( Long id, BigDecimal sueldo, TipoEmpleado tipoEmpleado, String nombre, String apellido, String dni, Direccion direccion, String usuarioCreacion) {
        super(id, nombre, apellido, dni, direccion,usuarioCreacion);
        this.sueldo = sueldo;
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado{");
        sb.append("sueldo=").append(sueldo);
        sb.append(", tipoEmpleado=").append(tipoEmpleado);
        sb.append(", pabellon=").append(pabellon);
        sb.append(", ").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
   
}
