/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.UsuarioDTO;
import modelo.dto.UsuarioDTO.Tipo;

/**
 *
 * @author Alf
 */
public class UsuarioFACADE extends BaseFACADE<UsuarioDTO, Integer> {

    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellido) {
        List<UsuarioDTO> usuarios;

        usuarios = DAOServiceLocator.getUsuarioDAO()
                .obtenerUsuariosPorApellido(apellido);

        return usuarios;
    }

    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellido, Tipo tipo) {
        List<UsuarioDTO> usuarios;

        usuarios = DAOServiceLocator.getUsuarioDAO()
                .obtenerUsuariosPorApellido(apellido, tipo);

        return usuarios;
    }

    public List<UsuarioDTO> obtenerAlumnosPorApellido(String apellido) {
        List<UsuarioDTO> usuarios;

        usuarios = DAOServiceLocator.getUsuarioDAO()
                .obtenerAlumnosPorApellido(apellido);

        return usuarios;
    }

    public UsuarioDTO obtenerEntidad(String usuario) {
        UsuarioDTO _usuario;
        _usuario = DAOServiceLocator.getUsuarioDAO().obtener(usuario);
        return _usuario;
    }

}
