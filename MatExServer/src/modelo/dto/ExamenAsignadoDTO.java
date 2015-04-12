/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "examenasignado")
public class ExamenAsignadoDTO implements Serializable {
    
    private ExamenAsignadoPK id = new ExamenAsignadoPK();
    private ExamenDTO examen;
    private UsuarioDTO alumno;
    private int tiempo;
    private Date fechaAsignacion;
    private double calificacion = -1;
    private List<ReactivoAsignadoDTO> reactivos = 
            new ArrayList<ReactivoAsignadoDTO>();

    @AttributeOverrides({
        @AttributeOverride(name = "idExamen",
                column = @Column(name = "idExamen")),
        @AttributeOverride(name = "idAlumno",
                column = @Column(name = "idAlumno"))
    })
    @EmbeddedId
    /**
     * @return the id
     */
    public ExamenAsignadoPK getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ExamenAsignadoPK id) {
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

    @MapsId("idAlumno")
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "idAlumno",
            referencedColumnName = "id",
            insertable = false,
            updatable = false,
            nullable = false
    )
    /**
     * @return the alumno
     */
    public UsuarioDTO getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(UsuarioDTO alumno) {
        this.alumno = alumno;
    }

    @Column(name = "tiempo", nullable = false, length = 11)
    /**
     * @return the tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Temporal(value = TIMESTAMP)
    @Column(name = "fechaAsignacion", nullable = false)
    /**
     * @return the fechaAsignacion
     */
    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * @param fechaAsignacion the fechaAsignacion to set
     */
    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    @Column(name = "calificacion")
    /**
     * @return the calificacion
     */
    public double getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    @OneToMany(
            mappedBy = "examen",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    /**
     * @return the reactivos
     */
    public List<ReactivoAsignadoDTO> getReactivos() {
        return reactivos;
    }

    /**
     * @param reactivos the reactivos to set
     */
    public void setReactivos(List<ReactivoAsignadoDTO> reactivos) {
        this.reactivos = reactivos;
    }
    
    public void addReactivo(ReactivoAsignadoDTO reactivo) {
        reactivo.setExamen(this);
        reactivos.add(reactivo);
    }
}