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
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Esta clase entidad almacena los datos de un Reactivo Asignado específico de
 * un Examen Asignado. Un Reactivo Asignado contiene las respuestas del alumno
 * y la respuesta correcta del reactivo, junto con sus opciones. Es una copia
 * de un ReactivoDTO para asegurarse de que alguna modificación a este no afecte
 * las respuestas del alumno
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
@Entity
@Table(name = "reactivo_asignado")
public class ReactivoAsignadoDTO implements Serializable {
    
    /**
     * El id del Reactivo Asignado
     */
    private ReactivoAsignadoPK id;
    /**
     * El examen asignado al que pertence este Reactivo Asignado
     */
    private ExamenAsignadoDTO examen;
    /**
     * La redacción del reactivo
     */
    private String redaccionReactivo;
    /**
     * La respuesta correcta del reactivo
     */
    private String respuestaReactivo;
    /**
     * La respuesta del alumno
     */
    private String respuestaAlumno;
    /**
     * Las opciones incorrectas del reactivo
     */
    private List<String> opcionesIncorrectas = new ArrayList<String>();

    @AttributeOverrides({
        @AttributeOverride(name = "idExamenAsignado.idExamen",
                column = @Column(name = "idExamen")),
        @AttributeOverride(name = "idExamenAsignado.idAlumno",
                column = @Column(name = "idAlumno")),
        @AttributeOverride(name = "idReactivo",
                column = @Column(name = "idReactivo"))
    })
    @EmbeddedId
    /**
     * @return el id del reactivo asignado
     */
    public ReactivoAsignadoPK getId() {
        return id;
    }

    /**
     * @param id el id a guardar. Debe contener el idReactivo en ella
     */
    public void setId(ReactivoAsignadoPK id) {
        this.id = id;
    }
    
    @MapsId("idExamenAsignado")
    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(
            name = "idExamen",
            referencedColumnName = "idExamen",
            insertable = false,
            updatable = false,
            nullable = false
        ),
        @JoinColumn(
            name = "idAlumno",
            referencedColumnName = "idAlumno",
            insertable = false,
            updatable = false,
            nullable = false
        )
    })
    /**
     * @return el examen asignado al que pertenece el reactivo asignado
     */
    public ExamenAsignadoDTO getExamen() {
        return examen;
    }

    /**
     * @param examen el examen asignado a guardar
     */
    public void setExamen(ExamenAsignadoDTO examen) {
        this.examen = examen;
    }

    @Column(name = "redaccionReactivo", nullable = false, length = 1000)
    /**
     * @return la redacción del reactivo
     */
    public String getRedaccionReactivo() {
        return redaccionReactivo;
    }

    /**
     * @param redaccionReactivo la redacción a guardar
     */
    public void setRedaccionReactivo(String redaccionReactivo) {
        this.redaccionReactivo = redaccionReactivo;
    }

    @Column(name = "respuestaReactivo", nullable = false, length = 250)
    /**
     * @return la respuesta del reactivo
     */
    public String getRespuestaReactivo() {
        return respuestaReactivo;
    }

    /**
     * @param respuestaReactivo la respuesta del reactivo a guardar
     */
    public void setRespuestaReactivo(String respuestaReactivo) {
        this.respuestaReactivo = respuestaReactivo;
    }

    @Column(name = "respuestaAlumno", length = 250)
    /**
     * @return la respuesta del alumno
     */
    public String getRespuestaAlumno() {
        return respuestaAlumno;
    }

    /**
     * @param respuestaAlumno la respuesta del alumno a guardar
     */
    public void setRespuestaAlumno(String respuestaAlumno) {
        this.respuestaAlumno = respuestaAlumno;
    }

   @ElementCollection
   @CollectionTable(
           name="opciones_incorrectas_reactivo_asignado",
           joinColumns = {
                @JoinColumn(name = "idExamen", referencedColumnName = "idExamen"),
                @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno"),
                @JoinColumn(name = "idReactivo", referencedColumnName = "idReactivo")
            }
   )
   @Column(name="opcion", nullable = false, length = 250)
    /**
     * @return las opciones incorrectas del reactivo
     */
    public List<String> getOpcionesIncorrectas() {
        return opcionesIncorrectas;
    }

    /**
     * @param opcionesIncorrectas las opciones incorrectas a guardar
     */
    public void setOpcionesIncorrectas(List<String> opcionesIncorrectas) {
        this.opcionesIncorrectas = opcionesIncorrectas;
    }
}