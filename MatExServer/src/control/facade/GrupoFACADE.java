/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
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
import modelo.dto.CursoDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.UsuarioDTO;

/**
 * Esta clase es un facade de GrupoDTO para los métodos específicos a este
 * objeto dto, proporciona la funcionalidad necesaria accediendo a los datos
 * mediante capas inferiores.
 *
 * @author Fernando Enrique Avendaño Hernández
 * @version 1 18 Mayo 2015
 */
public class GrupoFACADE extends BaseFACADE<GrupoDTO, Integer> {

    /**
     * Obtiene el grupo al que pertenece el id ingresado.
     *
     * @param idGrupo el id que se busca.
     * @return el grupo.
     */
    public GrupoDTO obtenerGrupo(int idGrupo) {
        return DAOServiceLocator.getGrupoDAO().obtener(idGrupo);
    }

    /**
     * Obtiene los cursos que imparte el maestro ingresado.
     *
     * @param maestro el maestro.
     * @return lista de cursos.
     */
    public List<CursoDTO> obtenerCursosDeGrupo(UsuarioDTO maestro) {
        return DAOServiceLocator.getGrupoDAO().obtenerCursos(maestro);
    }

    /**
     * Obtiene los cursos en los que el maestro imparte el curso seleccionado.
     *
     * @param curso el curso.
     * @param maestro el maestro.
     * @return lista de cursos.
     */
    public List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso, UsuarioDTO maestro) {
        return DAOServiceLocator.getGrupoDAO().obtenerTodosPorCurso(curso, maestro);
    }

    /**
     * Obtiene los alumnos que pertenecen al gurpo ingresado.
     *
     * @param grupo el grupo.
     * @return lista de alumnos.
     */
    public List<UsuarioDTO> obtenerAlumnosDeGrupo(GrupoDTO grupo) {
        return DAOServiceLocator.getGrupoDAO().obtenerSoloAlumnos(grupo);
    }

    /**
     * Verifica la existencia del grupo ingresado.
     *
     * @param grupo el grupo.
     * @return verdadero si el grupo existe y falso si no existe.
     */
    public boolean verificarExistencia(GrupoDTO grupo) {
        return DAOServiceLocator.getGrupoDAO().existe(grupo);
    }
}
