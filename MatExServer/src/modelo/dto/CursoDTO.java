/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "curso")
public class CursoDTO implements Serializable, Comparable<CursoDTO> {
    
    private int id;
    private String nombre;
    private List<TemaDTO> temas = new ArrayList<TemaDTO>();

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

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
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

    @OneToMany
    @JoinTable(
            name = "curso_tema",
            joinColumns = @JoinColumn(name = "idCurso"),
            inverseJoinColumns = @JoinColumn(name = "idTema")
    )
    /**
     * @return the temas
     */
    public List<TemaDTO> getTemas() {
        return temas;
    }

    /**
     * @param temas the temas to set
     */
    public void setTemas(List<TemaDTO> temas) {
        this.temas = temas;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CursoDTO))
            return false;
        CursoDTO c = (CursoDTO) o;
        
        return c.nombre.equals(nombre);
    }

    @Override
    public int hashCode() {
        return 37 * nombre.hashCode();
    }
    
    @Override
    public int compareTo(CursoDTO o) {
        return nombre.compareTo(o.nombre);
    }
    
}