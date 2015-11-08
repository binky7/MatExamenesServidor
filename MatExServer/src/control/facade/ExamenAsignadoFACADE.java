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
import modelo.dto.ClaveExamenDTO;
import modelo.dto.ClaveExamenPK;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.TablaEstadisticas;
import modelo.dto.UsuarioDTO;

/**
 * Esta clase es un facade de ExamenAsignadoDTO para los métodos específicos a
 * este objeto dto, proporciona la funcionalidad necesaria accediendo a los
 * datos mediante capas inferiores
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class ExamenAsignadoFACADE extends BaseFACADE<ExamenAsignadoDTO, ExamenAsignadoPK> {

    /**
     * Almacena la lista de objetos ExamenAsignadoDTO ingresada 
     * y la vuelve persistente. En caso de que ya existiera un mismo objeto de la
     * lista en la persistencia se actualiza con los nuevos datos.
     * El objeto ExamenAsignadoDTO representa la relación de asignación entre un
     * examen y un alumno
     * 
     * @param examenes la lista de ExamenAsignadoDTO a ser persistida
     * 
     * @return true si la operación se realizó con éxito o false en caso contrario
     */
    public boolean asignarExamenes(List<ExamenAsignadoDTO> examenes) {
        return DAOServiceLocator.getExamenAsignadoDAO().insertar(examenes);
    }

    /**
     * Obtiene el examen con únicamente sus claves
     *
     * @param idExamen el id del examen que se quiere obtener
     * 
     * @return el objeto examen con la lista de claves inicializada o null en
     * caso de que no exista ese objeto examen
     */
    public ExamenDTO obtenerExamen(int idExamen) {
        return DAOServiceLocator.getExamenAsignadoDAO().obtenerExamen(idExamen);
    }
    
    /**
     * Este método sirve para obtener una ClaveExamen completa en base al id
     * de la clave ingresado. Esto devuelve una ClaveExamen con todos sus
     * reactivos y opciones incorrectas inicializados.
     * 
     * @param idClave el objeto ClaveExamenPK que representa la llave compuesta
     * del número de clave y el idExamen de la ClaveExamen que se quiere obtener
     * 
     * @return el objeto ClaveExamenDTO correspondiente al idClave totalmente
     * inicializado o null en caso de que no exista
     */
    public ClaveExamenDTO obtenerClaveExamen(ClaveExamenPK idClave) {
        return DAOServiceLocator.getExamenAsignadoDAO()
                .obtenerClaveExamen(idClave);
    }
   
    /**
     * Este método obtiene todos los exámenes asignados al alumno ingresado. Esto
     * es, devuelve todos los exámenes que aun no han sido contestados por el
     * alumno y están vigentes. (No tiene calificación aún y todavía no pasa el
     * tiempo límite para ser contestados)
     * En caso de que existan exámenes que fueron asignados al alumno pero nunca
     * contestados, (después de pasar el tiempo límite para contestarlos),
     * dado por un error o porque simplemente no lo hizo. Este
     * método remueve de la base de datos estos exámenes no contestados.
     * 
     * @param alumno el objeto UsuarioDTO que representa al alumno del cuál se
     * desea obtener los exámenes asignados
     * 
     * @return una lista de objetos ExamenAsignadoDTO con los exámenes que puede
     * contestar o null, en caso de que no existan exámenes asignados vigentes.
     * La lista sólo contiene las relaciones que pueden ser obtenidas en una
     * consulta general. (No incluye las colecciones)
     */
    public List<ExamenAsignadoDTO> obtenerExamenesAsignados(UsuarioDTO alumno) {
        return DAOServiceLocator.getExamenAsignadoDAO().obtenerAsignados(alumno);
    }

    /**
     * Este método obtiene el ExamenAsignadoDTO completo asociado a la llave
     * compuesta ingresada. Esto es: regresa todos los reactivos asignados a ese
     * objeto junto con las opciones incorrectas de cada reactivo asignado.
     * 
     * @param id el objeto ExamenAsignadoPK que representa el ExamenAsignadoDTO
     * que se quiere buscar
     * 
     * @return el objeto ExamenAsignadoDTO completo que pertenece al id ingresado
     * o null, en caso de que no exista tal objeto.
     */
    public ExamenAsignadoDTO obtenerExamenAsignado(ExamenAsignadoPK id) {
        return DAOServiceLocator.getExamenAsignadoDAO().obtenerAsignado(id);
    }
    
    /**
     * Este método obtiene todos los exámenes contestados del alumno ingresado que
     * pertenezcan al cuso seleccionado.
     * 
     * Esto es, devuelve todos los exámenes que ya han sido contestados por el
     * alumno y que pertenecen al cuso. (Ya tienen calificación)
     * 
     * @param alumno el objeto UsuarioDTO que representa al alumno del cuál se
     * desea obtener los exámenes contestados
     * @param curso el objeto CursoDTO que representa al curso del cuál se desea
     * obtener los exámenes contestados
     * 
     * @return una lista de objetos ExamenAsignadoDTO con los exámenes contestados
     * o null, en caso de que no existan exámenes contestados.
     * La lista sólo contiene las relaciones que pueden ser obtenidas en una
     * consulta general. (No incluye las colecciones)
     */
    public List<ExamenAsignadoDTO> obtenerExamenesContestados(UsuarioDTO alumno,
            CursoDTO curso) {

        return DAOServiceLocator.getExamenAsignadoDAO()
                .obtenerContestados(alumno, curso);
    }

    /**
     * Este método obtiene el ExamenAsignadoDTO completo asociado a la llave
     * compuesta ingresada. Esto es: regresa todos los reactivos asignados a ese
     * objeto junto con las respuestas del alumno
     * 
     * @param id el objeto ExamenAsignadoPK que representa el ExamenAsignadoDTO
     * que se quiere buscar
     * 
     * @return el objeto ExamenAsignadoDTO completo que pertenece al id ingresado
     * o null, en caso de que no exista tal objeto.
     */
    public ExamenAsignadoDTO obtenerExamenContestado(ExamenAsignadoPK id) {
        return DAOServiceLocator.getExamenAsignadoDAO().obtenerContestado(id);
    }

    /**
     * Este método sirve para generar un objeto TablaEstadisticas con los
     * promedios de los grupos ingresados por los exámenes ingresados
     * 
     * @param examenes la lista de ExamenDTO de los que se desea obtener los
     * promedios por grupo
     * @param grupos la lista de GrupoDTO de los grupos que se desea obtener el
     * promedio por cada examen ingresado.
     * 
     * @return un objeto TablaEstadisticas con los promedios de los grupos
     * por los exámenes elegidos o null en caso de que la lista de grupos esté
     * vacía.
     * También se puede regresar una tabla con celdas vacías (null), lo que
     * indica que no existe ningún grupo que haya contestado los exámenes
     * seleccionados.
     */
    public TablaEstadisticas generarEstadisticasPorGrupos(
            List<ExamenDTO> examenes, List<GrupoDTO> grupos) {
        //El objeto que contendrá los datos de las estadísticas para graficar
        TablaEstadisticas tabla = null;

        if (grupos != null && !grupos.isEmpty()) {
            String[] columnas = new String[grupos.size()];
            int i = 0;

            //Crear nombres de columnas con los grupos
            for (GrupoDTO grupo : grupos) {
                columnas[i] = grupo.getGrado() + grupo.getNombre()
                        + grupo.getTurno();

                i++;
            }

            //Crea una matriz de exámenes filas por grupos columnas
            Object[][] datos = new Object[examenes.size()][];

            i = 0;
            //Llena la matriz con los promedios de los grupos por cada examen
            for (ExamenDTO examen : examenes) {
                Object[] promedios = DAOServiceLocator.getExamenAsignadoDAO()
                        .obtenerPromediosPorGrupo(examen, grupos);

                datos[i] = promedios;
                i++;
            }

            tabla = new TablaEstadisticas(columnas, datos);
        }

        return tabla;
    }

    /**
     * Este método sirve para generar un objeto TablaEstadisticas con los
     * promedios de todos los grados por los exámenes ingresados
     * 
     * @param examenes la lista de ExamenDTO de los que se desea obtener los
     * promedios por grado
     * 
     * @return un objeto TablaEstadisticas con los promedios de todos los grados
     * por los exámenes elegidos o null en caso de que no existan grupos.
     * También se puede regresar una tabla con celdas vacías (null), lo que
     * indica que no existe ningún grupo que haya contestado los exámenes
     * seleccionados.
     * 
     */
    public TablaEstadisticas generarEstadisticasPorGrados(
            List<ExamenDTO> examenes) {
        //El objeto que contendrá los datos de las estadísticas para graficar
        TablaEstadisticas tabla;

        //Crear columnas para los 3 grados
        String[] columnas = new String[3];

        for (int i = 0; i < 3; i++) {
            columnas[i] = (i + 1) + "°";
        }

        //Crea una matriz de exámenes filas por grados columnas
        Object[][] datos = new Object[examenes.size()][];

        int i = 0;
        //Llena la matriz con los promedios de los grados por cada examen
        for (ExamenDTO examen : examenes) {
            Object[] promedios = DAOServiceLocator.getExamenAsignadoDAO()
                    .obtenerPromediosPorGrado(examen);

            datos[i] = promedios;
            i++;
        }

        tabla = new TablaEstadisticas(columnas, datos);

        return tabla;
    }

    /**
     * Este método sirve para generar un objeto TablaEstadisticas con los
     * promedios de todos los turnos por los exámenes ingresados en forma de
     * índices en la lista ingresada.
     * 
     * @param examenes la lista de ExamenDTO de los que se desea obtener los
     * promedios por turno
     * 
     * @return un objeto TablaEstadisticas con los promedios de todos los turnos
     * por los exámenes elegidos o null en caso de que no existan grupos.
     * También se puede regresar una tabla con celdas vacías (null), lo que
     * indica que no existe ningún grupo que haya contestado los exámenes
     * seleccionados.
     */
    public TablaEstadisticas generarEstadisticasPorTurnos(
            List<ExamenDTO> examenes) {
        //El objeto que contendrá los datos de las estadísticas para graficar
        TablaEstadisticas tabla;
        //Crear columnas para los 2 turnos
        String[] columnas = new String[2];

        //Crear nombres de columnas con los turnos
        for(int i = 0; i < 2; i++) {
            if(i == 0) {
                columnas[i] = "Matutino";
            }
            else {
                columnas[i] = "Vespertino";
            }
        }

        //Crea una matriz de examenes filas por grupos columnas
        Object[][] datos = new Object[examenes.size()][];

        int i = 0;
        //Llena la matriz con los promedios de los grupos por cada examen
        for (ExamenDTO examen : examenes) {
            Object[] promedios = DAOServiceLocator.getExamenAsignadoDAO()
                    .obtenerPromediosPorTurno(examen);

            datos[i] = promedios;
            i++;
        }

        tabla = new TablaEstadisticas(columnas, datos);

        return tabla;
    }
    
    /**
     * Este método devuelve un objeto TablaEstadisticas que contiene los datos de
     * todos los alumnos que hayan contestado el examen ingresado.
     * 
     * @param examen el objeto ExamenDTO del cuál se quieren obtener los alumnos
     * con sus calificaciones
     * 
     * @return un objeto de tipo TablaEstadisticas o null en caso de no
     * encontrarse datos
     */
    public TablaEstadisticas obtenerCalificaciones(ExamenDTO examen) {
        TablaEstadisticas tablaCalificaciones = null;
        
        //Obtener matriz de datos
        Object[][] datos = DAOServiceLocator.getExamenAsignadoDAO()
                .obtenerCalificaciones(examen);
        
        if(datos != null) {
            String[] columnas = {"Apellido Paterno", "Apellido Materno",
                "Nombre(s)", "Grado", "Grupo", "Turno", "Calificación"};
            
            tablaCalificaciones = new TablaEstadisticas(columnas, datos);
        }
                
        return tablaCalificaciones;
    }
    
    public long obtenerTiempoSistema(){
        return DAOServiceLocator.getExamenAsignadoDAO().getTiempoActual();
    }
}