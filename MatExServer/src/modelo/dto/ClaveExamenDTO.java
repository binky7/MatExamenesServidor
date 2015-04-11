/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(
        name = "claveexamen",
        uniqueConstraints = 
        @UniqueConstraint(columnNames = {"clave", "idExamen"})
)
public class ClaveExamenDTO implements Serializable {
    
    private int id;
    private int clave;
    private ExamenDTO examen;
    private List<ReactivoDTO> reactivos = new ArrayList<ReactivoDTO>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "clave", nullable = false, length = 11)
    /**
     * @return the clave
     */
    public int getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(int clave) {
        this.clave = clave;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "idExamen", nullable = false)
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
            joinColumns = @JoinColumn(name = "idClaveExamen"),
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