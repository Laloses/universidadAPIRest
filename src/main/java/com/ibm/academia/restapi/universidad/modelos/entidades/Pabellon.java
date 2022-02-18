package com.ibm.academia.restapi.universidad.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@Table(name = "pabellones", schema = "universidad")
public class Pabellon implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @NotEmpty
    @Max(value = 50, message = "Maximo 50 caracateres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @NotNull
    @NotEmpty
    @Column(name = "metros_cuadradtos")
    private String metrosCuadrados;
    
    @NotNull
    @NotEmpty
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
        @AttributeOverride(name = "departamento", column = @Column(name = "departamento")),
    })
    private Direccion direccion;
    
    @OneToMany(mappedBy = "pabellon", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"aula"})
    private Set<Aula> aula;
    
    @OneToOne(mappedBy = "pabellon")
    @JsonIgnoreProperties({"empleado"})
    private Empleado empleado;
    
    @Column(name = "usuario_creacion", nullable = false)
    protected String usuarioCreacion;
    
    @NotNull
    @NotEmpty
    @Column(name = "fecha_creacion", nullable = false)
    protected Date fechaCreacion;
    
    @Column(name = "usuario_modificacion")
    protected String usuarioModificacion;
    
    @Column(name = "fecha_modificacion")
    protected Date fechaModificacion;

    public Pabellon(Long id, String nombre, String metrosCuadrados, Direccion direccion, String usuarioCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.metrosCuadrados = metrosCuadrados;
        this.direccion = direccion;
        this.usuarioCreacion = usuarioCreacion;
    }
    
    @PrePersist
    private void antesPersistir(){
        this.fechaCreacion = new Date();
    }
    
    @PreUpdate
    private void antesActualizar(){
        this.fechaModificacion = new Date();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pabellon{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", metrosCuadrados=").append(metrosCuadrados);
        sb.append(", direccion=").append(direccion);
        sb.append(", usuarioCreacion=").append(usuarioCreacion);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", usuarioModificacion=").append(usuarioModificacion);
        sb.append(", fechaModificacion=").append(fechaModificacion);
        sb.append('}');
        return sb.toString();
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pabellon other = (Pabellon) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
}
