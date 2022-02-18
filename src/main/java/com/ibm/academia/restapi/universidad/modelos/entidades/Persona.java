package com.ibm.academia.restapi.universidad.modelos.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "personas", schema = "universidad")
public abstract class Persona implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @NotNull
    @NotEmpty
    @Max(value = 40, message = "Maximo 40 caracateres")
    @Min(value = 3, message = "Mininmo 3 caracateres")
    @Column(name = "nombre", nullable = false, length = 40)
    protected String nombre;
    
    @NotNull
    @NotEmpty
    @Max(value = 3, message = "Mininmo 3 caracateres")
    @Column(name = "apellido", nullable = false, length = 40)
    protected String apellido;
    
    @NotNull
    @NotEmpty
    @Size(min = 9, max = 10, message = "Deben ser 10 caracteres")
    @Column(name = "dni", nullable = false, length = 10, unique = true)
    protected String dni;
    
    @NotNull
    @NotEmpty
    @Column(name = "direccion")
    protected Direccion direccion;
    
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

    public Persona(Long id, String nombre, String apellido, String dni, Direccion direccion, String usuarioCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
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
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.dni);
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
}
