/*
 * Copyright (C) 2015 E. Iván Mariscal Martínez
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
package control.facade;

import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.TemaDTO;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class TemaFACADE extends BaseFACADE<TemaDTO, Integer> {

    /**
     *
     * @return Los temas que no pertenecen a ningun curso
     */
    public List<TemaDTO> obtenerTemasSinAsignar() {
        List<TemaDTO> listaTemas;

        listaTemas = DAOServiceLocator.getTemaDAO()
                .obtenerTemasSinAsignar();
        return listaTemas;
    }

    /**
     * Verifica si el nombre del tema ingresado ya existe en la base de datos.
     *
     * @param tema El tema del cual se quiere verificar su existencia.
     * @return Regresa verdadero si el nombre del tema ya existe en la base de
     * datos. Regresa falso si el nombre del tema no existe en la base de datos.
     */
    public boolean verificarExistencia(TemaDTO tema) {
        boolean ok = DAOServiceLocator.getTemaDAO().existe(tema);
        return ok;
    }

}
