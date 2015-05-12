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
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "reactivoexamenasignado")
public class ReactivoAsignadoDTO implements Serializable {
    
    private ReactivoAsignadoPK id;
    private ExamenAsignadoDTO examen;
    private String redaccionReactivo;
    private String respuestaReactivo;
    private String respuestaAlumno;
    private List<String> opcionesReactivo = new ArrayList<String>();

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
     * @return the id
     */
    public ReactivoAsignadoPK getId() {
        return id;
    }

    /**
     * @param id the id to set
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
     * @return the examen
     */
    public ExamenAsignadoDTO getExamen() {
        return examen;
    }

    /**
     * @param examen the examen to set
     */
    public void setExamen(ExamenAsignadoDTO examen) {
        this.examen = examen;
    }

    @Column(name = "redaccionReactivo", nullable = false, length = 1000)
    /**
     * @return the redaccionReactivo
     */
    public String getRedaccionReactivo() {
        return redaccionReactivo;
    }

    /**
     * @param redaccionReactivo the redaccionReactivo to set
     */
    public void setRedaccionReactivo(String redaccionReactivo) {
        this.redaccionReactivo = redaccionReactivo;
    }

    @Column(name = "respuestaReactivo", nullable = false, length = 250)
    /**
     * @return the respuestaReactivo
     */
    public String getRespuestaReactivo() {
        return respuestaReactivo;
    }

    /**
     * @param respuestaReactivo the respuestaReactivo to set
     */
    public void setRespuestaReactivo(String respuestaReactivo) {
        this.respuestaReactivo = respuestaReactivo;
    }

    @Column(name = "respuestaAlumno", length = 250)
    /**
     * @return the respuestaAlumno
     */
    public String getRespuestaAlumno() {
        return respuestaAlumno;
    }

    /**
     * @param respuestaAlumno the respuestaAlumno to set
     */
    public void setRespuestaAlumno(String respuestaAlumno) {
        this.respuestaAlumno = respuestaAlumno;
    }

   @ElementCollection
   @CollectionTable(
           name="opciones_reactivoexamenasignado",
           joinColumns = {
                @JoinColumn(name = "idExamen", referencedColumnName = "idExamen"),
                @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno"),
                @JoinColumn(name = "idReactivo", referencedColumnName = "idReactivo")
            }
   )
   @Column(name="opcion", nullable = false, length = 250)
    /**
     * @return the opcionesReactivo
     */
    public List<String> getOpcionesReactivo() {
        return opcionesReactivo;
    }

    /**
     * @param opcionesReactivo the opcionesReactivo to set
     */
    public void setOpcionesReactivo(List<String> opcionesReactivo) {
        this.opcionesReactivo = opcionesReactivo;
    }
}