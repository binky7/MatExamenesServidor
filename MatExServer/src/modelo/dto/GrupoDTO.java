/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(
        name = "grupo",
        uniqueConstraints = 
        @UniqueConstraint(columnNames = {"nombre", "grado", "turno"})
)
public class GrupoDTO implements Serializable {
    
    private int id;
    private String nombre;
    private int grado;
    private Turno turno;
    private List<UsuarioDTO> alumnos = new ArrayList<UsuarioDTO>();
    private Map<CursoDTO, UsuarioDTO> maestros = 
            new HashMap<CursoDTO, UsuarioDTO>();

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

    @Column(name = "nombre", nullable = false, length = 5)
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

    @Column(name = "grado", nullable = false, length = 11)
    /**
     * @return the grado
     */
    public int getGrado() {
        return grado;
    }

    /**
     * @param grado the grado to set
     */
    public void setGrado(int grado) {
        this.grado = grado;
    }

    @Enumerated(value = STRING)
    @Column(name = "turno", nullable = false)
    /**
     * @return the turno
     */
    public Turno getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @OneToMany
    @JoinTable(
            name = "grupo_alumno",
            joinColumns = @JoinColumn(name = "idGrupo"),
            inverseJoinColumns = @JoinColumn(name = "idAlumno")
    )
    /**
     * @return the alumnos
     */
    public List<UsuarioDTO> getAlumnos() {
        return alumnos;
    }

    /**
     * @param alumnos the alumnos to set
     */
    public void setAlumnos(List<UsuarioDTO> alumnos) {
        this.alumnos = alumnos;
    }

    @OneToMany
    @JoinTable(
            name = "grupo_maestro",
            joinColumns = @JoinColumn(name = "idGrupo"),
            inverseJoinColumns = @JoinColumn(name = "idMaestro")
    )
    @MapKeyJoinColumn(name = "idCurso")
    /**
     * @return the maestros
     */
    public Map<CursoDTO, UsuarioDTO> getMaestros() {
        return maestros;
    }

    /**
     * @param maestros the maestros to set
     */
    public void setMaestros(Map<CursoDTO, UsuarioDTO> maestros) {
        this.maestros = maestros;
    }
    
    
    public static enum Turno {
        M, V
    }
}