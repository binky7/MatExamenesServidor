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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(
        name = "examenasignado",
        uniqueConstraints = 
        @UniqueConstraint(columnNames = {"idExamen", "idAlumno"})
)
public class ExamenAsignadoDTO implements Serializable {
    
    private int id;
    private ExamenDTO examen;
    private UsuarioDTO alumno;
    private int tiempo;
    private Date fechaAsignacion;
    private double calificacion;
    private List<ReactivoAsignadoDTO> reactivos = 
            new ArrayList<ReactivoAsignadoDTO>();

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "idAlumno", nullable = false)
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
    
    
}