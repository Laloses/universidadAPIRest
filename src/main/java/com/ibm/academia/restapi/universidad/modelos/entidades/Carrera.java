package com.ibm.academia.restapi.universidad.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
@Table(name = "carreras", schema = "universidad")
public class Carrera implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @NotEmpty
    @Column(name = "nombre",nullable = false, length = 40)
    private String nombre;
    
    @Positive(message = "El valor debe ser mayor a cero")
    @Column(name = "cantidad_materias",nullable = false, length = 3)
    private Integer cantidadMaterias;
    
    @Positive(message = "El valor debe ser mayor a cero")
    @Column(name = "cantidad_anios", nullable = false, length = 2)
    private Integer cantidadAnios;
    
    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"carrera"})
    private Set<Alumno> alumnos;
    
    @ManyToMany(mappedBy = "carreras", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"carrera"})
    private Set<Profesor> profesores;
    
    @NotNull
    @NotEmpty
    @Column(name = "usuario_creacion", nullable = false)
    protected String usuarioCreacion;
    
    @Column(name = "fecha_creacion", nullable = false)
    protected Date fechaCreacion;
    
    @Column(name = "usuario_modificacion")
    protected String usuarioModificacion;
    
    @Column(name = "fecha_modificacion")
    protected Date fechaModificacion;

    public Carrera(Long id, String nombre, Integer cantidadMaterias, Integer cantidadAnios, String usuarioCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadMaterias = cantidadMaterias;
        this.cantidadAnios = cantidadAnios;
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
        sb.append("Carrera{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", cantidadMaterias=").append(cantidadMaterias);
        sb.append(", cantidadAnios=").append(cantidadAnios);
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
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.nombre);
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
        final Carrera other = (Carrera) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
}
