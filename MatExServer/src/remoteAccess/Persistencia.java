/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
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
package remoteAccess;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import modelo.dto.ClaveExamenDTO;
import modelo.dto.ClaveExamenPK;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TablaEstadisticas;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import modelo.dto.UsuarioDTO.Tipo;

/**
 * Esta interface se encarga de conectar la aplicación del cliente con la
 * aplicación del servidor de modo remoto y para acceder a sus métodos
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public interface Persistencia extends Remote {

    /**
     * Obtiene el total de entidades existentes de la clase especificada
     * 
     * @param <T> La entidad obtenida
     * @param clazz la clase de la entidad que se quiere obtener
     * @return la lista de entidades que se obtuvieron, null en caso de no
     * obtener nada
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    <T> List<T> obtenerEntidades(Class<T> clazz) throws RemoteException;

    /**
     * Almacena la entidad ingresada y la vuelve persistente
     * 
     * @param <T> La entidad a guardar
     * @param <ID> el id resultante de la entidad
     * @param entidad el objeto a ser persistido
     * @return el id resultante del registro de la nueva entidad
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    <T, ID extends Serializable> ID guardarEntidad(T entidad)
            throws RemoteException;

    /**
     * Actualiza la entidad ingresada en la persistencia
     * 
     * @param <T> La entidad a actualizar
     * @param entidad el objeto a actualizar
     * @return true si la operación se realizó exitosamente, false si ocurrió
     * un error
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    <T> boolean modificarEntidad(T entidad) throws RemoteException;

    /**
     * Elimina la entidad ingresada de la persistencia
     * 
     * @param <T> la entidad a eliminar
     * @param entidad el objeto a eliminar
     * @return true si la operación se realizó exitosamente, false si ocurrió
     * un error
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    <T> boolean eliminarEntidad(T entidad) throws RemoteException;

    List<TemaDTO> obtenerTemasSinAsignar() throws RemoteException;

    CursoDTO obtenerCurso(int idCurso) throws RemoteException;

    List<CursoDTO> obtenerCursosDeGrupo(UsuarioDTO maestro)
            throws RemoteException;

    List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso) throws RemoteException;

    boolean verificarExistencia(CursoDTO curso) throws RemoteException;

    UsuarioDTO obtenerUsuario(String usuario) throws RemoteException;

    List<UsuarioDTO> obtenerUsuariosPorApellido(String apellidoPaterno,
            Tipo tipo) throws RemoteException;

    List<UsuarioDTO> obtenerUsuariosPorNombreOApellidos(String nombre)
            throws RemoteException;
    
    List<UsuarioDTO> obtenerUsuariosPorApellidoM(String apellidoMaterno, Tipo tipo)
            throws RemoteException;
    
    List<UsuarioDTO> obtenerUsuariosPorNombre(String nombre, Tipo tipo)
            throws RemoteException;

    List<UsuarioDTO> obtenerAlumnosPorApellido(String apellidoPaterno)
            throws RemoteException;

    /**
     * Obtiene el reactivo completo al que pertenece el id ingresado
     * 
     * @param idReactivo el id del reactivo a obtener
     * @return el objeto ReactivoDTO completo, con todas sus relaciones, o null
     * en caso de que no exista
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    ReactivoDTO obtenerReactivo(int idReactivo) throws RemoteException;

    /**
     * Obtiene todos los reactivos del tema ingresado
     * 
     * @param tema el objeto TemaDTO del que se quieren obtener los reactivos
     * @return una lista de ReactivoDTO del tema ingresado, o null en caso de que
     * no exista ningún reactivo.
     * 
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    List<ReactivoDTO> obtenerReactivosPorTema(TemaDTO tema)
            throws RemoteException;

    /**
     * Obtiene la cantidad de reactivos por tema especificada de manera aleatoria
     * 
     * @param temas la lista de TemaDTO de donde se obtendrán los reactivos
     * aleatorios
     * @param cantidades la lista de cantidades de cada tema la cuál indica
     * cuantos reactivos se seleccionarán por cada tema
     * @return una lista de ReactivoDTO seleccionados aleatoriamente de cada tema
     * o regresa null en caso de que se especifiquen cantidades por tema mayores
     * de las que existen
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    List<ReactivoDTO> obtenerReactivosAleatorios(List<TemaDTO> temas,
            List<Integer> cantidades) throws RemoteException;

    /**
     * Elimina la lista de reactivos ingresada de la persistencia
     * 
     * @param reactivos la lista de ReactivoDTO que se desea eliminar
     * @return true si la operación se realizó exitosamente, false si ocurrió
     * un error
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    boolean eliminarReactivos(List<ReactivoDTO> reactivos)
            throws RemoteException;

    GrupoDTO obtenerGrupo(int idGrupo) throws RemoteException;

    List<UsuarioDTO> obtenerAlumnosDeGrupo(GrupoDTO grupo)
            throws RemoteException;

    List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso, UsuarioDTO maestro)
            throws RemoteException;

    boolean verificarExistencia(GrupoDTO grupo) throws RemoteException;

    ExamenDTO obtenerExamen(int idExamen) throws RemoteException;

    List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso, boolean todos,
            UsuarioDTO maestro) throws RemoteException;

    List<ExamenDTO> obtenerExamenesPorTitulo(String titulo, boolean todos,
            UsuarioDTO maestro) throws RemoteException;

    List<ExamenDTO> obtenerExamenesPorCursoYTitulo(CursoDTO curso, String titulo,
            boolean todos, UsuarioDTO maestro) throws RemoteException;

    TablaEstadisticas generarEstadisticasPorGrupos(List<ExamenDTO> examenes,
            List<GrupoDTO> grupos) throws RemoteException;

    TablaEstadisticas generarEstadisticasPorGrados(List<ExamenDTO> examenes)
            throws RemoteException;

    TablaEstadisticas generarEstadisticasPorTurnos(List<ExamenDTO> examenes)
            throws RemoteException;

    boolean asignarExamenes(List<ExamenAsignadoDTO> examenes)
            throws RemoteException;

    /**
     * Obtiene el examen con unicamente sus claves
     * @param idExamen el id del examen que se quiere obtener
     * @return el objeto examen con la lista de claves inicializada
     * @throws java.rmi.RemoteException
     */
    ExamenDTO obtenerExamenIncompleto(int idExamen) throws RemoteException;
    
    ClaveExamenDTO obtenerClaveExamen(ClaveExamenPK idClave)
            throws RemoteException;
    
    ExamenAsignadoDTO obtenerExamenContestado(ExamenAsignadoPK id)
            throws RemoteException;

    List<ExamenAsignadoDTO> obtenerExamenesContestados(UsuarioDTO alumno,
            CursoDTO curso) throws RemoteException;

    List<ExamenAsignadoDTO> obtenerExamenesAsignados(UsuarioDTO alumno)
            throws RemoteException;

    ExamenAsignadoDTO obtenerExamenAsignado(ExamenAsignadoPK id)
            throws RemoteException;
    
    CursoDTO obtenerCursoPorTema(TemaDTO tema)
            throws RemoteException;
    
    boolean  verificarExistencia(TemaDTO tema) throws RemoteException;
}