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

import control.facade.FACADEServiceLocator;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
 * Esta clase tiene la función de implementar todos los métodos de la interface
 * Persistencia para proporcionar un servicio de almacenamiento de datos por
 * medio de los distintos facades de la aplicación
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class AccesoDatos extends UnicastRemoteObject implements Persistencia {

    /**
     * Crea un nuevo objeto AccesoDatos
     *
     * @throws java.rmi.RemoteException en caso de que ocurra un error remoto
     */
    public AccesoDatos() throws RemoteException {

    }

    /**
     * Obtiene el total de entidades existentes de la clase especificada
     *
     * @param <T> La entidad obtenida
     * @param clazz la clase de la entidad que se quiere obtener
     * @return la lista de entidades que se obtuvieron, null en caso de no
     * obtener nada
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public <T> List<T> obtenerEntidades(Class<T> clazz) throws RemoteException {
        return FACADEServiceLocator.getBaseFACADE().obtenerEntidades(clazz);
    }

    /**
     * Almacena la entidad ingresada y la vuelve persistente
     *
     * @param <T> La entidad a guardar
     * @param <ID> el id resultante de la entidad
     * @param entidad el objeto a ser persistido
     * @return el id resultante del registro de la nueva entidad
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public <T, ID extends Serializable> ID guardarEntidad(T entidad)
            throws RemoteException {
        return (ID) FACADEServiceLocator.getBaseFACADE()
                .guardarEntidad(entidad);
    }

    /**
     * Actualiza la entidad ingresada en la persistencia
     *
     * @param <T> La entidad a actualizar
     * @param entidad el objeto a actualizar
     * @return true si la operación se realizó exitosamente, false si ocurrió un
     * error
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public <T> boolean modificarEntidad(T entidad) throws RemoteException {
        return FACADEServiceLocator.getBaseFACADE().modificarEntidad(entidad);
    }

    /**
     * Elimina la entidad ingresada de la persistencia
     *
     * @param <T> la entidad a eliminar
     * @param entidad el objeto a eliminar
     * @return true si la operación se realizó exitosamente, false si ocurrió un
     * error
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public <T> boolean eliminarEntidad(T entidad) throws RemoteException {
        return FACADEServiceLocator.getBaseFACADE().eliminarEntidad(entidad);
    }

    @Override
    public List<TemaDTO> obtenerTemasSinAsignar() throws RemoteException {
        return FACADEServiceLocator.getTemaFACADE().obtenerTemasSinAsignar();
    }

    @Override
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso)
            throws RemoteException {
        return FACADEServiceLocator.getCursoFACADE().obtenerTemasDeCurso(curso);
    }

    @Override
    public CursoDTO obtenerCurso(int idCurso) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Obtiene los cursos que imparte un maestro.
     *
     * @param maestro el maestro.
     * @return lista de cursos.
     * @throws RemoteException
     */
    @Override
    public List<CursoDTO> obtenerCursosDeGrupo(UsuarioDTO maestro)
            throws RemoteException {

        return FACADEServiceLocator.getGrupoFACADE().obtenerCursosDeGrupo(maestro);
    }

    @Override
    public boolean verificarExistencia(CursoDTO curso) throws RemoteException {
        return FACADEServiceLocator.getCursoFACADE().verificarExistencia(curso);
    }

    @Override
    public UsuarioDTO obtenerUsuario(String usuario) throws RemoteException {
        UsuarioDTO _usuario;
        _usuario = FACADEServiceLocator.getUsuarioFACADE().obtenerEntidad(usuario);
        return _usuario;
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellidoPaterno,
            Tipo tipo) throws RemoteException {
        List<UsuarioDTO> usuarios;

        usuarios = FACADEServiceLocator.getUsuarioFACADE()
                .obtenerUsuariosPorApellido(apellidoPaterno, tipo);

        return usuarios;
    }

    /**
     * Obtiene los alumnos que contienen el apellido paterno ingresado.
     *
     * @param apellidoPaterno el apellido paterno que se busca.
     * @return lista de alumnos.
     * @throws RemoteException
     */
    @Override
    public List<UsuarioDTO> obtenerAlumnosPorApellido(String apellidoPaterno) throws RemoteException {
        List<UsuarioDTO> usuarios;

        usuarios = FACADEServiceLocator.getUsuarioFACADE()
                .obtenerAlumnosPorApellido(apellidoPaterno);

        return usuarios;
    }

    /**
     * Obtiene los alumnos que contienen el apellido materno ingresado.
     *
     * @param apellidoMaterno el apellido materno que se busca.
     * @return lista de alumnos.
     * @throws RemoteException
     */
    @Override
    public List<UsuarioDTO> obtenerAlumnosPorApellidoM(String apellidoMaterno) throws RemoteException {
        List<UsuarioDTO> usuarios;

        usuarios = FACADEServiceLocator.getUsuarioFACADE()
                .obtenerAlumnosPorApellidoM(apellidoMaterno);

        return usuarios;
    }

    /**
     * Obtiene los alumnos que contienen el nombre ingresado.
     *
     * @param nombre el nombre que se busca.
     * @return lista de alumnos.
     * @throws RemoteException
     */
    @Override
    public List<UsuarioDTO> obtenerAlumnosPorNombre(String nombre) throws RemoteException {
        List<UsuarioDTO> usuarios;

        usuarios = FACADEServiceLocator.getUsuarioFACADE()
                .obtenerAlumnosPorNombre(nombre);

        return usuarios;
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorNombreOApellidos(String nombre)
            throws RemoteException {
        List<UsuarioDTO> usuarios;

        usuarios = FACADEServiceLocator.getUsuarioFACADE()
                .obtenerUsuariosPorNombreOApellidos(nombre);

        return usuarios;
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorApellidoM(String apellidoMaterno, Tipo tipo) throws RemoteException {
        List<UsuarioDTO> usuarios;

        usuarios = FACADEServiceLocator.getUsuarioFACADE()
                .obtenerUsuariosPorApellidoM(apellidoMaterno, tipo);

        return usuarios;
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorNombre(String nombre, Tipo tipo) throws RemoteException {
        List<UsuarioDTO> usuarios;

        usuarios = FACADEServiceLocator.getUsuarioFACADE()
                .obtenerUsuariosPorNombre(nombre, tipo);

        return usuarios;
    }

    /**
     * Obtiene el reactivo completo al que pertenece el id ingresado
     *
     * @param idReactivo el id del reactivo a obtener
     * @return el objeto ReactivoDTO completo, con todas sus relaciones, o null
     * en caso de que no exista
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public ReactivoDTO obtenerReactivo(int idReactivo) throws RemoteException {
        return FACADEServiceLocator.getReactivoFACADE()
                .obtenerReactivo(idReactivo);
    }

    /**
     * Obtiene todos los reactivos del tema ingresado
     *
     * @param tema el objeto TemaDTO del que se quieren obtener los reactivos
     * @return una lista de ReactivoDTO del tema ingresado, o null en caso de
     * que no exista ningún reactivo.
     *
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public List<ReactivoDTO> obtenerReactivosPorTema(TemaDTO tema)
            throws RemoteException {
        return FACADEServiceLocator.getReactivoFACADE()
                .obtenerReactivosPorTema(tema);
    }

    /**
     * Obtiene la cantidad de reactivos por tema especificada de manera
     * aleatoria
     *
     * @param temas la lista de TemaDTO de donde se obtendrán los reactivos
     * aleatorios
     * @param cantidades la lista de cantidades de cada tema la cuál indica
     * cuantos reactivos se seleccionarán por cada tema
     * @return una lista de ReactivoDTO seleccionados aleatoriamente de cada
     * tema o regresa null en caso de que se especifiquen cantidades por tema
     * mayores de las que existen
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public List<ReactivoDTO> obtenerReactivosAleatorios(List<TemaDTO> temas,
            List<Integer> cantidades) throws RemoteException {
        return FACADEServiceLocator.getReactivoFACADE()
                .obtenerReactivosAleatorios(temas, cantidades);
    }

    /**
     * Elimina la lista de reactivos ingresada de la persistencia
     *
     * @param reactivos la lista de ReactivoDTO que se desea eliminar
     * @return true si la operación se realizó exitosamente, false si ocurrió un
     * error
     * @throws RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public boolean eliminarReactivos(List<ReactivoDTO> reactivos)
            throws RemoteException {
        return FACADEServiceLocator.getReactivoFACADE()
                .eliminarReactivos(reactivos);
    }

    /**
     * Obtiene el grupo que conincide con el id ingresado.
     *
     * @param idGrupo el id del grupo.
     * @return el objeto GrupoDTO.
     * @throws RemoteException
     */
    @Override
    public GrupoDTO obtenerGrupo(int idGrupo) throws RemoteException {
        return FACADEServiceLocator.getGrupoFACADE().obtenerGrupo(idGrupo);
    }

    /**
     * Obtiene los alumnos pertenecientes al grupo seleccionado.
     *
     * @param grupo el grupo.
     * @return lista de alumnos.
     * @throws RemoteException
     */
    @Override
    public List<UsuarioDTO> obtenerAlumnosDeGrupo(GrupoDTO grupo)
            throws RemoteException {
        return FACADEServiceLocator.getGrupoFACADE().obtenerGrupo(grupo.getId()).getAlumnos();
    }

    /**
     * Obtiene los grupos en los que el maestro imparte el curso ingresado.
     *
     * @param curso el curso.
     * @param maestro el maestro.
     * @return lista de grupos.
     * @throws RemoteException
     */
    @Override
    public List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso, UsuarioDTO maestro)
            throws RemoteException {

        return FACADEServiceLocator.getGrupoFACADE()
                .obtenerGruposPorCurso(curso, maestro);
    }

    /**
     * Verifica la existencia del grupo ingresado.
     *
     * @param grupo el grupo a buscar.
     * @return verdadero si existe o falso si no existe.
     * @throws RemoteException
     */
    @Override
    public boolean verificarExistencia(GrupoDTO grupo)
            throws RemoteException {
        return FACADEServiceLocator.getGrupoFACADE()
                .verificarExistencia(grupo);
    }

    @Override
    public ExamenDTO obtenerExamen(int idExamen) throws RemoteException {
        return FACADEServiceLocator.getExamenFACADE().obtenerExamen(idExamen);
    }

    @Override
    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso, boolean todos,
            UsuarioDTO maestro) throws RemoteException {
        return FACADEServiceLocator.getExamenFACADE()
                .obtenerExamenesPorCurso(curso, todos, maestro);
    }

    @Override
    public List<ExamenDTO> obtenerExamenesPorTitulo(String titulo, boolean todos,
            UsuarioDTO maestro) throws RemoteException {
        return FACADEServiceLocator.getExamenFACADE()
                .obtenerExamenesPorTitulo(titulo, todos, maestro);
    }

    @Override
    public List<ExamenDTO> obtenerExamenesPorCursoYTitulo(CursoDTO curso,
            String titulo, boolean todos, UsuarioDTO maestro)
            throws RemoteException {
        return FACADEServiceLocator.getExamenFACADE()
                .obtenerExamenesPorCursoYTitulo(curso, titulo, todos, maestro);
    }

    @Override
    public TablaEstadisticas generarEstadisticasPorGrupos(
            List<ExamenDTO> examenes, List<GrupoDTO> grupos)
            throws RemoteException {

        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .generarEstadisticasPorGrupos(examenes, grupos);
    }

    @Override
    public TablaEstadisticas generarEstadisticasPorGrados(
            List<ExamenDTO> examenes) throws RemoteException {

        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .generarEstadisticasPorGrados(examenes);
    }

    @Override
    public TablaEstadisticas generarEstadisticasPorTurnos(
            List<ExamenDTO> examenes) throws RemoteException {

        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .generarEstadisticasPorTurnos(examenes);
    }

    @Override
    public boolean asignarExamenes(List<ExamenAsignadoDTO> examenes)
            throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .asignarExamenes(examenes);
    }

    @Override
    public ExamenAsignadoDTO obtenerExamenContestado(ExamenAsignadoPK id)
            throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerExamenContestado(id);
    }

    @Override
    public List<ExamenAsignadoDTO> obtenerExamenesContestados(UsuarioDTO alumno,
            CursoDTO curso) throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerExamenesContestados(alumno, curso);
    }

    @Override
    public List<ExamenAsignadoDTO> obtenerExamenesAsignados(UsuarioDTO alumno)
            throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerExamenesAsignados(alumno);
    }

    @Override
    public CursoDTO obtenerCursoPorTema(TemaDTO tema) throws RemoteException {
        return FACADEServiceLocator.getCursoFACADE().obtenerCursoPorTema(tema);
    }

    @Override
    public ExamenDTO obtenerExamenIncompleto(int idExamen) throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerExamen(idExamen);
    }

    @Override
    public ClaveExamenDTO obtenerClaveExamen(ClaveExamenPK idClave) throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerClaveExamen(idClave);
    }

    @Override
    public ExamenAsignadoDTO obtenerExamenAsignado(ExamenAsignadoPK id) throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerExamenAsignado(id);
    }

    @Override
    public boolean verificarExistencia(TemaDTO tema) throws RemoteException {
        return FACADEServiceLocator.getTemaFACADE().verificarExistencia(tema);
    }
}
