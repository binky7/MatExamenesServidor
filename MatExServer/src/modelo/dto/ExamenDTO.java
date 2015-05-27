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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Esta clase entidad almacena los datos del Examen
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
@Entity
@Table(name = "examen")
public class ExamenDTO implements Serializable {
    
    /**
     * El id del examen
     */
    private int id;
    /**
     * El nombre del examen
     */
    private String nombre;
    /**
     * El curso del examen
     */
    private CursoDTO curso;
    /**
     * Las instrucciones del examen
     */
    private String instrucciones;
    /**
     * Las claves del examen
     */
    private List<ClaveExamenDTO> claves = new ArrayList<ClaveExamenDTO>();
    /**
     * La fecha de creación del examen
     */
    private Date fechaCreacion;
    /**
     * La fecha de última modificación del examen
     */
    private Date fechaModificacion;
    /**
     * El autor del examen
     */
    private UsuarioDTO autor;
    /**
     * El permiso del examen
     */
    private Permiso permiso;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    /**
     * @return el id del examen
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

    @Column(name = "nombre", nullable = false, length = 100)
    /**
     * @return el nombre del examen
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
    @JoinColumn(name = "idCurso", nullable = false)
    /**
     * @return el curso del examen
     */
    public CursoDTO getCurso() {
        return curso;
    }

    /**
     * @param curso el curso a guardar
     */
    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }

    @Column(name = "instrucciones", nullable = false, length = 200)
    /**
     * @return las instrucciones del examen
     */
    public String getInstrucciones() {
        return instrucciones;
    }

    /**
     * @param instrucciones las instrucciones a guardar
     */
    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    @OneToMany(
            mappedBy = "examen",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    /**
     * @return las claves del examen
     */
    public List<ClaveExamenDTO> getClaves() {
        return claves;
    }

    /**
     * @param claves las claves a guardar
     */
    public void setClaves(List<ClaveExamenDTO> claves) {
        this.claves = claves;
    }

    
    /**
     * @return la fecha de creación del examen
     */
    @Temporal(value = TIMESTAMP)
    @Column(name = "fechaCreacion", nullable = false)
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
     * @return la fecha de última modificación del examen
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion la fecha de última modificación a guardar
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @ManyToOne
    @JoinColumn(name = "idAutor")
    /**
     * @return el autor del examen
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

    @Enumerated(value = STRING)
    @Column(name = "permiso", nullable = false)
    /**
     * @return el permiso del examen
     */
    public Permiso getPermiso() {
        return permiso;
    }

    /**
     * @param permiso el permiso a guardar
     */
    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
    
    /**
     * Método para lograr la correcta relación bidireccional entre la clave del
     * examen a agregar y el examen al que será agregada. Si se desean agregar
     * una serie de claves al examen, cada una debe ser agregada con este método
     * 
     * @param clave el objeto ClaveExamenDTO que se desea agregar al examen.
     */
    public void addClave(ClaveExamenDTO clave) {
        clave.setExamen(this);
        claves.add(clave);
    }
    
    /**
     * Enumeración de tipo Permiso que contiene las dos opciones de permiso que
     * un examen puede tener. (Público o Privado)
     */
    public static enum Permiso {
        Publico, Privado
    }
}