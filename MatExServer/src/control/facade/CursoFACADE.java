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
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class CursoFACADE extends BaseFACADE<CursoDTO, Integer> {

    /**
     * Obtiene los temas de un curso.
     *
     * @param curso El curso seleccionado.
     * @return Regresa los temas del curso seleccionado. Regresa null si el
     * curso no tiene temas.
     */
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso) {
        List<TemaDTO> listaTemas;

        listaTemas = DAOServiceLocator.getCursoDAO().obtenerTemas(curso);
        return listaTemas;
    }

    /**
     * Obtiene los temas de un curso dependiendo del bloque seleccionado.
     *
     * @param curso El curso seleccionado.
     * @param bloque El bloque seleccionado.
     * @return Regresa los temas del curso seleccionado dependiendo del
     * bloque seleccionado. Regresa null si el curso no tiene temas del bloque
     * seleccionado.
     */
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso, int bloque) {
        List<TemaDTO> listaTemas;

        listaTemas = DAOServiceLocator.getCursoDAO().obtenerTemas(curso, bloque);
        return listaTemas;
    }

    /**
     * Verifica si el nombre del curso ingresado ya existe en la base de datos.
     *
     * @param curso El curso del cual se quiere verificar su existencia.
     * @return Regresa verdadero si el nombre del curso ya existe en la base de
     * datos. Regresa falso si el nombre del curso no existe en la base de
     * datos.
     */
    public boolean verificarExistencia(CursoDTO curso) {
        boolean ok = DAOServiceLocator.getCursoDAO().existe(curso);
        return ok;
    }

    /**
     * Obtiene el curso al que pertenece el tema seleccionado.
     *
     * @param tema El tema seleccionado.
     * @return Regresa el curso al que pertenece el tema seleccionado.
     */
    public CursoDTO obtenerCursoPorTema(TemaDTO tema) {
        return DAOServiceLocator.getCursoDAO().obtenerPorTema(tema);
    }

    public CursoDTO obtenerCurso(Integer id) {
        return DAOServiceLocator.getCursoDAO().obtener(id);
    }
}
