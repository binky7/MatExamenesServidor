/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.UsuarioDTO;

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

}
