/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(
        name = "reactivoexamenasignado",
        uniqueConstraints = 
        @UniqueConstraint(columnNames = {"idExamenAsignado", "idReactivo"})
)
public class ReactivoAsignadoDTO implements Serializable {
    
    private int id;
    private ExamenAsignadoDTO examen;
    private int idReactivo;
    private String redaccionReactivo;
    private String respuestaReactivo;
    private String respuestaAlumno;
    private List<String> opcionesReactivo = new ArrayList<String>();

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
    @JoinColumn(name = "idExamenAsignado", nullable = false)
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

    @Column(name = "idReactivo", nullable = false, length = 11)
    /**
     * @return the idReactivo
     */
    public int getIdReactivo() {
        return idReactivo;
    }

    /**
     * @param idReactivo the idReactivo to set
     */
    public void setIdReactivo(int idReactivo) {
        this.idReactivo = idReactivo;
    }

    @Column(name = "redaccionReactivo", nullable = false, length = 500)
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

    @Column(name = "respuestaReactivo", nullable = false, length = 200)
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

    @Column(name = "respuestaAlumno", length = 200)
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
           joinColumns=@JoinColumn(name="idReactivoExamenAsignado")
   )
   @Column(name="opcion", nullable = false, length = 200)
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