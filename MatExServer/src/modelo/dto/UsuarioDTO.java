/*
 * Copyright (C) 2015 Alfredo Rouse Madrigal
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
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Esta clase entidad almacena los datos de los usuarios.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
@Entity
@Table(name = "usuario")
public class UsuarioDTO implements Serializable {

    /**
     * El id del usuario.
     */
    private int id;
    /**
     * El nombre de usuario para el usuario.
     */
    private String usuario;
    /**
     * El password del usuario.
     */
    private String password;
    /**
     * El tipo de usuario.
     */
    private Tipo tipo;
    /**
     * El nombre del usuario.
     */
    private String nombre;
    /**
     * El apellido paterno del usuario.
     */
    private String apellidoPaterno;
    /**
     * El apellido materno del usuario.
     */
    private String apellidoMaterno;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    /**
     * @return El id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id El id a guardar
     */
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "usuario", nullable = false, unique = true, length = 50)
    /**
     * @return El usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario El usuario a guardar
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Column(name = "password", nullable = false, length = 50)
    /**
     * @return El password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password El password a guardar
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(value = STRING)
    @Column(name = "tipo", nullable = false)
    /**
     * @return El tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo El tipo a guardar
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Column(name = "nombre", nullable = false, length = 50)
    /**
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nombre a guardar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "apellidoPaterno", nullable = false, length = 50)
    /**
     * @return El apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno El apellidoPaterno a guardar
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Column(name = "apellidoMaterno", nullable = false, length = 50)
    /**
     * @return El apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno El apellidoMaterno a guardar
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Enumeración usada para definir el tipo de usuario 
     */
    public static enum Tipo {

        Maestro, Alumno, Admin
    }
}