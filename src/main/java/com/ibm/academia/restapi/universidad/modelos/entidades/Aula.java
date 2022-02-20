package com.ibm.academia.restapi.universidad.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Eduardo Martell Hernandez Hernandez
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
//@Table(name = "aulas",schema = "universidad")
@Table(name = "aulas")
public class Aula implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @NotEmpty
    @Positive(message = "El valor debe ser mayor a cero")
    @Column(name = "numero_aula", nullable = false, unique = true, length = 5)
    private Integer numeroAula;
    
    @NotNull
    @NotEmpty
    @Column(name = "medidas", nullable = false)
    private String medidas;
    
    @NotNull
    @NotEmpty
    @Min(value = 5, message = "Minimo deben haber 5 pupitres")
    @Max(value = 40, message = "Maximo pueden haber 40 pupitres")
    @Column(name = "cantidad_pupitres", nullable = false, length = 3)
    private Integer cantidadPupitres;
    
    @NotNull
    @NotEmpty
    @Column(name = "tipo_pizarron", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPizarron tipoPizarron;
    
    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
    @JsonIgnoreProperties({"aula"})
    private Pabellon pabellon;
    
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

    public Aula(Long id, Integer numeroAula, String medidas, Integer cantidadPupitres, TipoPizarron tipoPizarron, String usurioCreacion) {
        this.id = id;
        this.numeroAula = numeroAula;
        this.medidas = medidas;
        this.cantidadPupitres = cantidadPupitres;
        this.tipoPizarron = tipoPizarron;
        this.usuarioCreacion = usurioCreacion;
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
        hash = 47 * hash + Objects.hashCode(this.numeroAula);
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
        final Aula other = (Aula) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.numeroAula, other.numeroAula);
    }
    
}
