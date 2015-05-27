/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
 *
 * This file is part of MatExámenes.
 *
 * MatExámenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExámenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
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
 * Esta clase entidad almacena los datos del Examen Asignado, un examen asignado
 * representa la relación entre un alumno y el examen que le asignan para ser
 * contestado.
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
@Entity
@Table(name = "examen_asignado")
public class ExamenAsignadoDTO implements Serializable {
    
    /**
     * El id del examen asignado. Contiene el id del examen y el id del alumno
     */
    private ExamenAsignadoPK id = new ExamenAsignadoPK();
    /**
     * El examen del examen asignado. El examen que es asignado
     */
    private ExamenDTO examen;
    /**
     * El alumno del examen asignado. El alumno al que se le asigna el examen
     */
    private UsuarioDTO alumno;
    /**
     * El tiempo límite para contestar este examen asignado, medido en minutos.
     */
    private int tiempo;
    /**
     * Fecha en la que se realizó la asignación de este examen asignado.
     */
    private Date fechaAsignacion;
    /**
     * Calificación, por default -1, lo que indica que este examen no ha sido
     * contestado aún. La calificación va del 0 al 10.
     */
    private double calificacion = -1;
    /**
     * Los reactivos asignados a este examen asignado.
     */
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
     * @return el id del examen asignado
     */
    public ExamenAsignadoPK getId() {
        return id;
    }

    /**
     * @param id el id a guardar
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
     * @return el examen que se asigna en este examen asignado.
     */
    public ExamenDTO getExamen() {
        return examen;
    }

    /**
     * @param examen el examen que se asigna a guardar
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
     * @return el alumno al que se le asigna el examen en el examen asignado
     */
    public UsuarioDTO getAlumno() {
        return alumno;
    }

    /**
     * @param alumno el alumno al que se le asigna el examen a guardar
     */
    public void setAlumno(UsuarioDTO alumno) {
        this.alumno = alumno;
    }

    @Column(name = "tiempo", nullable = false, length = 11)
    /**
     * @return el tiempo límite de este examen asignado
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo el tiempo a guardar
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Temporal(value = TIMESTAMP)
    @Column(name = "fechaAsignacion", nullable = false)
    /**
     * @return la fecha de asignación de este examen asignado
     */
    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * @param fechaAsignacion la fecha de asignación a guardar
     */
    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    @Column(name = "calificacion")
    /**
     * @return la calificación de este examen asignado
     */
    public double getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion la calificación a guardar
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
     * @return los reactivos asignados a este examen asignado
     */
    public List<ReactivoAsignadoDTO> getReactivos() {
        return reactivos;
    }

    /**
     * @param reactivos los reactivos asignados a guardar
     */
    public void setReactivos(List<ReactivoAsignadoDTO> reactivos) {
        this.reactivos = reactivos;
    }
    
    /**
     * Este método sirve para agregar correctamente un reactivo asignado al
     * examen asignado, mediante la correcta creación de sus relaciones.
     * Todos los reactivos asignados que se deseen agregar a este examen deben
     * ser agregados por medio de este método.
     * 
     * @param reactivo el objeto ReactivoAsignadoDTO que se desea agregar al
     * examen asignado
     */
    public void addReactivo(ReactivoAsignadoDTO reactivo) {
        reactivo.setExamen(this);
        reactivos.add(reactivo);
    }
}