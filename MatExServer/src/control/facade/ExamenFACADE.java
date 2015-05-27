/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
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
import modelo.dto.ExamenDTO;
import modelo.dto.UsuarioDTO;

/**
 * Esta clase es un facade de ExamenDTO para los métodos específicos a este
 * objeto dto, proporciona la funcionalidad necesaria accediendo a los datos
 * mediante capas inferiores
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class ExamenFACADE extends BaseFACADE<ExamenDTO, Integer> {
    
    /**
     * Este método es utilizado para obtener el objeto examen perteneciente
     * al id ingresado
     * 
     * @param idExamen el id del examen que se quiere obtener
     * @return El objeto ExamenDTO con sus relaciones completamente inicializadas
     * hasta reactivos. (Las incorrectas de los reactivos no están inicializadas,
     * tampoco los temas del curso)
     */
    public ExamenDTO obtenerExamen(int idExamen) {
        return DAOServiceLocator.getExamenDAO().obtener(idExamen);
    }
    
    /**
     * Este método sirve para obtener exámenes por el curso seleccionado,
     * permitiendo seleccionar entre dos modalidades para recuperar un examen,
     * obteniendo todos los exámenes disponibles en ese curso u obteniendo sólo
     * los exámenes que fueran públicos o aquellos que fueran hechos por el usuario
     * actual del sistema, y que por supuesto coincidieran con el curso ingresado
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * 
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que
     * sean del curso ingresado. En caso de que la bandera sea false el sistema
     * recupera los exámenes de permiso público y todos aquellos que sean hechos
     * por el usuario actual, además de que coincidieran con el curso ingresado
     * 
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se decida
     * enviar false en el parámetro todos. Sirve para filtrar la búsqueda por el
     * autor del examen. Esta consulta regresaría los exámenes que pertenezcan
     * al curso y que además fueran públicos o hechos por el usuario actual.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso, boolean todos,
            UsuarioDTO maestro) {
        List<ExamenDTO> examenes;
        
        //Si la bandera es true obtener todos los exámenes por el curso
        if(todos) {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerTodosPorCurso(curso);
        }
        //Si es false obtener sólo los exámenes públicos y los privados propiedad
        //del maestro
        else {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerPublicosPorCurso(curso, maestro);
        }
        
        return examenes;
    }
    
    /**
     * Este método sirve para obtener exámenes por el nombre ingresado,
     * permitiendo seleccionar entre dos modalidades para recuperar un examen,
     * obteniendo todos los exámenes que coincidan con el nombre u obteniendo sólo
     * los exámenes que fueran públicos o aquellos que fueran hechos por el usuario
     * actual del sistema, y que por supuesto coincidieran con el nombre ingresado
     * 
     * @param nombre el nombre o parte del nombre del examen utilizado como filtro
     * 
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que
     * coincidan con el nombre ingresado. En caso de que la bandera sea false el
     * sistema recupera los exámenes de permiso público y todos aquellos que sean
     * hechos por el usuario actual, además de que coincidieran con el nombre
     * ingresado
     * 
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se decida
     * enviar false en el parámetro todos. Sirve para filtrar la búsqueda por el
     * autor del examen. Esta consulta regresaría los exámenes que coincidieran
     * con el nombre ingresado y que además fueran públicos o hechos por el
     * usuario actual.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorNombre(String nombre, boolean todos,
            UsuarioDTO maestro) {
        List<ExamenDTO> examenes;
        
        //Si la bandera es true obtiene todas las coincidencias con el nombre
        //ingresado
        if(todos) {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerTodosPorNombre(nombre);
        }
        //Si es false obtener las coincidencias del nombre públicos y propiedad
        //del maestro
        else {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerPublicosPorNombre(nombre, maestro);
        }
        
        return examenes;
    }
    
    /**
     * Este método sirve para obtener exámenes por el nombre ingresado y el curso
     * seleccionado permitiendo seleccionar entre dos modalidades para recuperar
     * un examen, obteniendo todos los exámenes que coincidan con el nombre y el
     * curso u obteniendo sólo los exámenes que fueran públicos o aquellos que
     * fueran hechos por el usuario actual del sistema, y que por supuesto
     * coincidieran con el nombre ingresado y el curso seleccionado
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * @param nombre el nombre o parte del nombre del examen utilizado como filtro
     * 
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que
     * coincidan con el nombre ingresado y el curso seleccionado. En caso de que
     * la bandera sea false el sistema recupera los exámenes de permiso público
     * y todos aquellos que sean hechos por el usuario actual, además de que
     * coincidieran con el nombre ingresado y el curso seleccionado
     * 
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se
     * decida enviar false en el parámetro todos. Sirve para filtrar la búsqueda
     * por el autor del examen. Esta consulta regresaría los exámenes que
     * coincidieran con el nombre ingresado y el curso seleccionado y que además
     * fueran públicos o hechos por el usuario actual.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorCursoYNombre(CursoDTO curso,
            String nombre, boolean todos, UsuarioDTO maestro) {
        List<ExamenDTO> examenes;
        
        //Si la bandera es true obtiene todas las coincidencias de nombre y curso
        if(todos) {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerTodosPorCursoYNombre(curso, nombre);
        }
        //Si es false obtiene todas las coincidencias públicas y de propiedad del
        //maestro de curso y nombre
        else {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerPublicosPorCursoYNombre(curso, nombre, maestro);
        }
        
        return examenes;
    }
}