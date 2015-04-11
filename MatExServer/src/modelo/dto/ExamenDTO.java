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
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "examen")
public class ExamenDTO implements Serializable {
    
    private int id;
    private String titulo;
    private CursoDTO curso;
    private String instrucciones;
    private List<ClaveExamenDTO> claves = new ArrayList<ClaveExamenDTO>();
    private Date fechaCreacion;
    private Date fechaModificacion;
    private UsuarioDTO autor;
    private Permiso permiso;

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

    @Column(name = "titulo", nullable = false, length = 100)
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCurso", nullable = false)
    /**
     * @return the curso
     */
    public CursoDTO getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }

    @Column(name = "instrucciones", nullable = false, length = 200)
    /**
     * @return the instrucciones
     */
    public String getInstrucciones() {
        return instrucciones;
    }

    /**
     * @param instrucciones the instrucciones to set
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
     * @return the claves
     */
    public List<ClaveExamenDTO> getClaves() {
        return claves;
    }

    /**
     * @param claves the claves to set
     */
    public void setClaves(List<ClaveExamenDTO> claves) {
        this.claves = claves;
    }

    
    /**
     * @return the fechaCreacion
     */
    @Temporal(value = TIMESTAMP)
    @Column(name = "fechaCreacion", nullable = false)
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

    @Enumerated(value = STRING)
    @Column(name = "permiso", nullable = false)
    /**
     * @return the permiso
     */
    public Permiso getPermiso() {
        return permiso;
    }

    /**
     * @param permiso the permiso to set
     */
    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
    
    //Para lograr la correcta relacion.
    public void addClave(ClaveExamenDTO clave) {
        clave.setExamen(this);
        claves.add(clave);
    }
    
    public static enum Permiso {
        Publico, Privado
    }
}