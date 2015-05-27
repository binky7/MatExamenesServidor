/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
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
 * Esta clase entidad almacena los datos del Grupo.
 *
 * @author Fernando Enrique Avendaño Hernández.
 * @version 1 18 Mayo 2015.
 */
@Entity
@Table(
        name = "grupo",
        uniqueConstraints
        = @UniqueConstraint(columnNames = {"nombre", "grado", "turno"})
)
public class GrupoDTO implements Serializable {

    /**
     * El id del grupo.
     */
    private int id;

    /**
     * El nombre del grupo.
     */
    private String nombre;

    /**
     * El grado del grupo.
     */
    private int grado;

    /**
     * El turno del grupo.
     */
    private Turno turno;

    /**
     * La lista de alumnos del grupo.
     */
    private List<UsuarioDTO> alumnos = new ArrayList<UsuarioDTO>();

    /**
     * El mapa de maestros y cursos del grupo.
     */
    private Map<CursoDTO, UsuarioDTO> maestros
            = new HashMap<CursoDTO, UsuarioDTO>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    /**
     * @return el id.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id el id a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false, length = 5)
    /**
     * @return el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre el nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "grado", nullable = false, length = 11)
    /**
     * @return el grado.
     */
    public int getGrado() {
        return grado;
    }

    /**
     * @param grado el grado a asignar.
     */
    public void setGrado(int grado) {
        this.grado = grado;
    }

    @Enumerated(value = STRING)
    @Column(name = "turno", nullable = false)
    /**
     * @return el turno.
     */
    public Turno getTurno() {
        return turno;
    }

    /**
     * @param turno el turno a asignar.
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
     * @return los alumnos.
     */
    public List<UsuarioDTO> getAlumnos() {
        return alumnos;
    }

    /**
     * @param alumnos los alumnos a asignar.
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
     * @return los maestros.
     */
    public Map<CursoDTO, UsuarioDTO> getMaestros() {
        return maestros;
    }

    /**
     * @param maestros los maestros a asignar.
     */
    public void setMaestros(Map<CursoDTO, UsuarioDTO> maestros) {
        this.maestros = maestros;
    }

    /**
     * Declaracion del enum turno.
     */
    public static enum Turno {

        M, V
    }
}
