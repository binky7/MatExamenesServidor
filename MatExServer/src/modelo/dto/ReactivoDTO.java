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
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "reactivo")
public class ReactivoDTO implements Serializable {
    
    private int id;
    private String nombre;
    private TemaDTO tema;
    private String redaccion;
    private String respuesta;
    private List<String> opciones = new ArrayList<String>();
    private Date fechaCreacion;
    private Date fechaModificacion;
    private UsuarioDTO autor;

    
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

    @Column(name = "nombre", nullable = false, length = 50)
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "idTema", nullable = false)
    /**
     * @return the tema
     */
    public TemaDTO getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(TemaDTO tema) {
        this.tema = tema;
    }

    @Column(name = "redaccion", nullable = false, length = 500)
    /**
     * @return the redaccion
     */
    public String getRedaccion() {
        return redaccion;
    }

    /**
     * @param redaccion the redaccion to set
     */
    public void setRedaccion(String redaccion) {
        this.redaccion = redaccion;
    }

    @Column(name = "respuesta", nullable = false, length = 200)
    /**
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

   @ElementCollection
   @CollectionTable(
           name="opciones_reactivo",
           joinColumns=@JoinColumn(name="idReactivo")
   )
   @Column(name="opcion", nullable = false, length = 200)
    /**
     * @return the opciones
     */
    public List<String> getOpciones() {
        return opciones;
    }

    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    @Temporal(value = TIMESTAMP)
    @Column(name = "fechaCreacion", nullable = false)
    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(value = TIMESTAMP)
    @Column(name = "fechaModificacion", nullable = false)
    /**
     * @return the fechaModificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @ManyToOne
    @JoinColumn(name = "idAutor")
    /**
     * @return the autor
     */
    public UsuarioDTO getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(UsuarioDTO autor) {
        this.autor = autor;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReactivoDTO))
            return false;
        ReactivoDTO r = (ReactivoDTO) o;
        
        return r.id == id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }
}