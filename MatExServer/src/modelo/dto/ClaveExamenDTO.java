/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "claveexamen")
public class ClaveExamenDTO implements Serializable {
    
    private ClaveExamenPK id;
    private ExamenDTO examen;
    private List<ReactivoDTO> reactivos = new ArrayList<ReactivoDTO>();

    @AttributeOverrides({
        @AttributeOverride(name = "clave",
                column = @Column(name = "clave")),
        @AttributeOverride(name = "idExamen",
                column = @Column(name = "idExamen"))
    })
    @EmbeddedId
    /**
     * @return the id
     */
    public ClaveExamenPK getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ClaveExamenPK id) {
        this.id = id;
    }
    
    @MapsId("idExamen")
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "idExamen",
            referencedColumnName = "id",
            insertable = false,
            updatable = false,
            nullable = false
    )
    /**
     * @return the examen
     */
    public ExamenDTO getExamen() {
        return examen;
    }

    /**
     * @param examen the examen to set
     */
    public void setExamen(ExamenDTO examen) {
        this.examen = examen;
    }

    @OneToMany
    @JoinTable(
            name = "claveexamen_reactivo",
            joinColumns = {
                @JoinColumn(name = "clave"),
                @JoinColumn(name = "idExamen")
            },
            inverseJoinColumns = @JoinColumn(name = "idReactivo")
    )
    /**
     * @return the reactivos
     */
    public List<ReactivoDTO> getReactivos() {
        return reactivos;
    }

    /**
     * @param reactivos the reactivos to set
     */
    public void setReactivos(List<ReactivoDTO> reactivos) {
        this.reactivos = reactivos;
    }
}