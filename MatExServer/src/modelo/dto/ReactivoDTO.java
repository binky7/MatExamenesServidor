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
 * Esta clase entidad almacena los datos del Reactivo
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
@Entity
@Table(name = "reactivo")
public class ReactivoDTO implements Serializable {
    
    /**
     * El id del reactivo
     */
    private int id;
    /**
     * El nombre del reactivo
     */
    private String nombre;
    /**
     * El tema del reactivo
     */
    private TemaDTO tema;
    /**
     * La redacción del reactivo
     */
    private String redaccion;
    /**
     * La respuesta del reactivo
     */
    private String respuesta;
    /**
     * Las opciones incorrectas del reactivo
     */
    private List<String> opcionesIncorrectas = new ArrayList<String>();
    /**
     * La fecha de creación del reactivo
     */
    private Date fechaCreacion;
    /**
     * La fecha de última modificación del reactivo
     */
    private Date fechaModificacion;
    /**
     * El autor del reactivo
     */
    private UsuarioDTO autor;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    /**
     * @return el id del reactivo
     */
    public int getId() {
        return id;
    }

    /**
     * @param id el id a guardar
     */
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false, length = 50)
    /**
     * @return el nombre del reactivo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre el nombre a guardar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "idTema", nullable = false)
    /**
     * @return el tema del reactivo
     */
    public TemaDTO getTema() {
        return tema;
    }

    /**
     * @param tema el tema a guardar
     */
    public void setTema(TemaDTO tema) {
        this.tema = tema;
    }

    @Column(name = "redaccion", nullable = false, length = 1000)
    /**
     * @return la redacción del reactivo
     */
    public String getRedaccion() {
        return redaccion;
    }

    /**
     * @param redaccion la redacción a guardar
     */
    public void setRedaccion(String redaccion) {
        this.redaccion = redaccion;
    }

    @Column(name = "respuesta", nullable = false, length = 250)
    /**
     * @return la respuesta del reactivo
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta la respuesta a guardar
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

   @ElementCollection
   @CollectionTable(
           name="opciones_incorrectas_reactivo",
           joinColumns=@JoinColumn(name="idReactivo")
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

    @Temporal(value = TIMESTAMP)
    @Column(name = "fechaCreacion", nullable = false)
    /**
     * @return la fecha de creación del reactivo
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion la fecha de creación a guardar
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(value = TIMESTAMP)
    @Column(name = "fechaModificacion", nullable = false)
    /**
     * @return la fecha de última modificación del reactivo
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion la fecha de modificación a guardar
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @ManyToOne
    @JoinColumn(name = "idAutor")
    /**
     * @return el autor del reactivo
     */
    public UsuarioDTO getAutor() {
        return autor;
    }

    /**
     * @param autor el autor a guardar
     */
    public void setAutor(UsuarioDTO autor) {
        this.autor = autor;
    }
    
    /**
     * Sobreescribir el método equals para poder ser utilizado al ordenar con
     * Collections
     * 
     * @param o el objeto a comparar con este
     * @return true si el objeto es igual a éste, false si no
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReactivoDTO))
            return false;
        ReactivoDTO r = (ReactivoDTO) o;
        
        return r.id == id;
    }

    /**
     * Sobreescribir el método hashCode para poder ser utilizado al ordenar con
     * Collections
     * 
     * @return el hashCode de este objeto
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }
}