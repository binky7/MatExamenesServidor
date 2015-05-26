/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández, Alfredo Rouse Madrigal
 *
 * This file is part of MatExamenes.
 *
 * MatExamenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExamenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package control.facade;

import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.UsuarioDTO;
import modelo.dto.UsuarioDTO.Tipo;

/**
 * Esta clase es un facade de UsuarioDTO para los métodos específicos a este
 * objeto dto, proporciona la funcionalidad necesaria accediendo a los datos
 * mediante capas inferiores
 *
 * @author Fernando Enrique Avendaño Hernández, Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class UsuarioFACADE extends BaseFACADE<UsuarioDTO, Integer> {

    /**
     * Obtiene todos los usuarios que concuerden con el parametro
     *
     * @param nombre El patron por el cual se buscaran los usuarios
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerUsuariosPorNombreOApellidos(String nombre) {
        List<UsuarioDTO> usuarios;

        usuarios = DAOServiceLocator.getUsuarioDAO()
                .obtenerUsuariosPorNombreOApellidos(nombre);

        return usuarios;
    }

    /**
     * Obtiene todos los usuarios que concuerden con los parametros
     *
     * @param apellido El patron por el cual se buscaran los usuarios
     * @param tipo el tipo de usuario a buscar
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellido, Tipo tipo) {
        List<UsuarioDTO> usuarios;

        usuarios = DAOServiceLocator.getUsuarioDAO()
                .obtenerUsuariosPorApellido(apellido, tipo);

        return usuarios;
    }

    /**
     * Obtiene todos los usuarios que concuerden con los parametros
     *
     * @param apellidoMaterno El patron por el cual se buscaran los usuarios
     * @param tipo el tipo de usuario a buscar
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerUsuariosPorApellidoM(String apellidoMaterno, Tipo tipo) {
        List<UsuarioDTO> usuarios;

        usuarios = DAOServiceLocator.getUsuarioDAO()
                .obtenerUsuariosPorApellidoM(apellidoMaterno, tipo);

        return usuarios;
    }

    /**
     * Obtiene todos los usuarios que concuerden con los parametros
     *
     * @param nombre El patron por el cual se buscaran los usuarios
     * @param tipo el tipo de usuario a buscar
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerUsuariosPorNombre(String nombre, Tipo tipo) {
        List<UsuarioDTO> usuarios;

        usuarios = DAOServiceLocator.getUsuarioDAO()
                .obtenerUsuariosPorNombre(nombre, tipo);

        return usuarios;
    }

    /**
     * Obtiene todos los usuarios tipo alumno que concuerden con el parametro
     *
     * @param apellido El patron por el cual se buscaran los usuarios
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerAlumnosPorApellido(String apellido) {
        List<UsuarioDTO> usuarios;

        usuarios = DAOServiceLocator.getUsuarioDAO()
                .obtenerAlumnosPorApellido(apellido);

        return usuarios;
    }

    /**
     * Obtiene todos los usuarios tipo alumno que concuerden con el parametro
     *
     * @param apellidoMaterno El patron por el cual se buscaran los usuarios
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerAlumnosPorApellidoM(String apellidoMaterno) {
        List<UsuarioDTO> usuarios;

        usuarios = DAOServiceLocator.getUsuarioDAO()
                .obtenerAlumnosPorApellidoM(apellidoMaterno);

        return usuarios;
    }

    /**
     * Obtiene todos los usuarios tipo alumno que concuerden con el parametro
     *
     * @param nombre El patron por el cual se buscaran los usuarios
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerAlumnosPorNombre(String nombre) {
        List<UsuarioDTO> usuarios;

        usuarios = DAOServiceLocator.getUsuarioDAO()
                .obtenerAlumnosPorNombre(nombre);

        return usuarios;
    }

    /**
     * Obtiene el usuario completo que concuerde con su nombre de usuario.
     *
     * @param unUsuario El nombre de usuario a obtener.
     * @return el objeto UsuarioDTO completo, con todas sus relaciones, o null
     * en caso de que no exista
     */
    public UsuarioDTO obtenerEntidad(String unUsuario) {
        UsuarioDTO usuario;
        usuario = DAOServiceLocator.getUsuarioDAO().obtener(unUsuario);
        return usuario;
    }

}
