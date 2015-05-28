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

    /**
     * Obtiene el usuario completo que concuerde con su nombre de usuario.
     *
     * @param unUsuario El nombre de usuario a obtener.
     * @return el objeto UsuarioDTO completo, con todas sus relaciones, o null
     * en caso de que no exista
     *
     * @throws RemoteException En caso de que ocurra un error remoto
     */
    @Override
    public UsuarioDTO obtenerUsuario(String unUsuario) throws RemoteException {
        UsuarioDTO usuario;
        usuario = FACADEServiceLocator.getUsuarioFACADE().obtenerEntidad(unUsuario);
        return usuario;
    }

    /**
     * Obtiene todos los usuarios que concuerden con los parametros
     *
     * @param apellidoPaterno El patron por el cual se buscaran los usuarios
     * @param tipo el tipo de usuario a buscar
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     * 
     * @throws RemoteException En caso de que ocurra un error remoto
     */
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

    /**
     * Obtiene todos los usuarios que concuerden con el parametro
     *
     * @param nombre El patron por el cual se buscaran los usuarios
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     *
     * @throws RemoteException En caso de que ocurra un error remoto
     */
    @Override
    public List<UsuarioDTO> obtenerUsuariosPorNombreOApellidos(String nombre)
            throws RemoteException {
        List<UsuarioDTO> usuarios;

        usuarios = FACADEServiceLocator.getUsuarioFACADE()
                .obtenerUsuariosPorNombreOApellidos(nombre);

        return usuarios;
    }

    /**
     * Obtiene todos los usuarios que concuerden con los parametros
     *
     * @param apellidoMaterno El patron por el cual se buscaran los usuarios
     * @param tipo el tipo de usuario a buscar
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     * 
     * @throws RemoteException En caso de que ocurra un error remoto
     */
    @Override
    public List<UsuarioDTO> obtenerUsuariosPorApellidoM(String apellidoMaterno, Tipo tipo) throws RemoteException {
        List<UsuarioDTO> usuarios;

        usuarios = FACADEServiceLocator.getUsuarioFACADE()
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
     * 
     * @throws RemoteException En caso de que ocurra un error remoto
     */
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

    /**
     * Este método es utilizado para obtener el objeto examen perteneciente al
     * id ingresado
     *
     * @param idExamen el id del examen que se quiere obtener
     * @return El objeto ExamenDTO con sus relaciones completamente
     * inicializadas hasta reactivos. (Las incorrectas de los reactivos no están
     * inicializadas, tampoco los temas del curso)
     * @throws java.rmi.RemoteException en caso de que ocurra un problema con el
     * acceso remoto
     */
    @Override
    public ExamenDTO obtenerExamen(int idExamen) throws RemoteException {
        return FACADEServiceLocator.getExamenFACADE().obtenerExamen(idExamen);
    }

    /**
     * Este método sirve para obtener exámenes por el curso seleccionado,
     * permitiendo seleccionar entre dos modalidades para recuperar un examen,
     * obteniendo todos los exámenes disponibles en ese curso u obteniendo sólo
     * los exámenes que fueran públicos o aquellos que fueran hechos por el
     * usuario actual del sistema, y que por supuesto coincidieran con el curso
     * ingresado
     *
     * @param curso el objeto CursoDTO del que se quieren obtener los exámenes
     * que pertenecen a dicho curso
     *
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que sean
     * del curso ingresado. En caso de que la bandera sea false el sistema
     * recupera los exámenes de permiso público y todos aquellos que sean hechos
     * por el usuario actual, además de que coincidieran con el curso ingresado
     *
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se
     * decida enviar false en el parámetro todos. Sirve para filtrar la búsqueda
     * por el autor del examen. Esta consulta regresaría los exámenes que
     * pertenezcan al curso y que además fueran públicos o hechos por el usuario
     * actual.
     *
     * @return Una lista de ExamenDTO con los exámenes que cumplen las
     * coincidencias o null, en caso de que no haya coincidencias
     * @throws java.rmi.RemoteException en caso de que ocurra un problema con el
     * acceso remoto
     */
    @Override
    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso, boolean todos,
            UsuarioDTO maestro) throws RemoteException {
        return FACADEServiceLocator.getExamenFACADE()
                .obtenerExamenesPorCurso(curso, todos, maestro);
    }

    /**
     * Este método sirve para obtener exámenes por el nombre ingresado,
     * permitiendo seleccionar entre dos modalidades para recuperar un examen,
     * obteniendo todos los exámenes que coincidan con el nombre u obteniendo
     * sólo los exámenes que fueran públicos o aquellos que fueran hechos por el
     * usuario actual del sistema, y que por supuesto coincidieran con el nombre
     * ingresado
     *
     * @param nombre el nombre o parte del nombre del examen utilizado como
     * filtro
     *
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que
     * coincidan con el nombre ingresado. En caso de que la bandera sea false el
     * sistema recupera los exámenes de permiso público y todos aquellos que
     * sean hechos por el usuario actual, además de que coincidieran con el
     * nombre ingresado
     *
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se
     * decida enviar false en el parámetro todos. Sirve para filtrar la búsqueda
     * por el autor del examen. Esta consulta regresaría los exámenes que
     * coincidieran con el nombre ingresado y que además fueran públicos o
     * hechos por el usuario actual.
     *
     * @return Una lista de ExamenDTO con los exámenes que cumplen las
     * coincidencias o null, en caso de que no haya coincidencias
     * @throws java.rmi.RemoteException en caso de que ocurra un problema con el
     * acceso remoto
     */
    @Override
    public List<ExamenDTO> obtenerExamenesPorNombre(String nombre, boolean todos,
            UsuarioDTO maestro) throws RemoteException {
        return FACADEServiceLocator.getExamenFACADE()
                .obtenerExamenesPorNombre(nombre, todos, maestro);
    }

    /**
     * Este método sirve para obtener exámenes por el nombre ingresado y el
     * curso seleccionado permitiendo seleccionar entre dos modalidades para
     * recuperar un examen, obteniendo todos los exámenes que coincidan con el
     * nombre y el curso u obteniendo sólo los exámenes que fueran públicos o
     * aquellos que fueran hechos por el usuario actual del sistema, y que por
     * supuesto coincidieran con el nombre ingresado y el curso seleccionado
     *
     * @param curso el objeto CursoDTO del que se quieren obtener los exámenes
     * que pertenecen a dicho curso
     * @param nombre el nombre o parte del nombre del examen utilizado como
     * filtro
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
     * @return Una lista de ExamenDTO con los exámenes que cumplen las
     * coincidencias o null, en caso de que no haya coincidencias
     * @throws java.rmi.RemoteException en caso de que ocurra un problema con el
     * acceso remoto
     */
    @Override
    public List<ExamenDTO> obtenerExamenesPorCursoYNombre(CursoDTO curso,
            String nombre, boolean todos, UsuarioDTO maestro)
            throws RemoteException {
        return FACADEServiceLocator.getExamenFACADE()
                .obtenerExamenesPorCursoYNombre(curso, nombre, todos, maestro);
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
     * @return un objeto TablaEstadisticas con los promedios de los grupos por
     * los exámenes elegidos o null en caso de que la lista de grupos esté
     * vacía. También se puede regresar una tabla con celdas vacías (null), lo
     * que indica que no existe ningún grupo que haya contestado los exámenes
     * seleccionados.
     *
     * @throws java.rmi.RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public TablaEstadisticas generarEstadisticasPorGrupos(
            List<ExamenDTO> examenes, List<GrupoDTO> grupos)
            throws RemoteException {

        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .generarEstadisticasPorGrupos(examenes, grupos);
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
     * @throws java.rmi.RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public TablaEstadisticas generarEstadisticasPorGrados(
            List<ExamenDTO> examenes) throws RemoteException {

        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .generarEstadisticasPorGrados(examenes);
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
     *
     * @throws java.rmi.RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public TablaEstadisticas generarEstadisticasPorTurnos(
            List<ExamenDTO> examenes) throws RemoteException {

        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .generarEstadisticasPorTurnos(examenes);
    }

    /**
     * Almacena la lista de objetos ExamenAsignadoDTO ingresada y la vuelve
     * persistente. En caso de que ya existiera un mismo objeto de la lista en
     * la persistencia se actualiza con los nuevos datos. El objeto
     * ExamenAsignadoDTO representa la relación de asignación entre un examen y
     * un alumno
     *
     * @param examenes la lista de ExamenAsignadoDTO a ser persistida
     *
     * @return true si la operación se realizó con éxito o false en caso
     * contrario
     * @throws java.rmi.RemoteException en caso de que ocurra algún error remoto
     */
    @Override
    public boolean asignarExamenes(List<ExamenAsignadoDTO> examenes)
            throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .asignarExamenes(examenes);
    }

    /**
     * Este método obtiene el ExamenAsignadoDTO completo asociado a la llave
     * compuesta ingresada. Esto es: regresa todos los reactivos asignados a ese
     * objeto junto con las respuestas del alumno
     *
     * @param id el objeto ExamenAsignadoPK que representa el ExamenAsignadoDTO
     * que se quiere buscar
     *
     * @return el objeto ExamenAsignadoDTO completo que pertenece al id
     * ingresado o null, en caso de que no exista tal objeto.
     * @throws java.rmi.RemoteException en caso de que ocurra algún error remoto
     */
    @Override
    public ExamenAsignadoDTO obtenerExamenContestado(ExamenAsignadoPK id)
            throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerExamenContestado(id);
    }

    /**
     * Este método obtiene todos los exámenes contestados del alumno ingresado
     * que pertenezcan al cuso seleccionado.
     *
     * Esto es, devuelve todos los exámenes que ya han sido contestados por el
     * alumno y que pertenecen al cuso. (Ya tienen calificación)
     *
     * @param alumno el objeto UsuarioDTO que representa al alumno del cuál se
     * desea obtener los exámenes contestados
     * @param curso el objeto CursoDTO que representa al curso del cuál se desea
     * obtener los exámenes contestados
     *
     * @return una lista de objetos ExamenAsignadoDTO con los exámenes
     * contestados o null, en caso de que no existan exámenes contestados. La
     * lista sólo contiene las relaciones que pueden ser obtenidas en una
     * consulta general. (No incluye las colecciones)
     * @throws java.rmi.RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public List<ExamenAsignadoDTO> obtenerExamenesContestados(UsuarioDTO alumno,
            CursoDTO curso) throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerExamenesContestados(alumno, curso);
    }

    /**
     * Este método obtiene todos los exámenes asignados al alumno ingresado.
     * Esto es, devuelve todos los exámenes que aun no han sido contestados por
     * el alumno y están vigentes. (No tiene calificación aún y todavía no pasa
     * el tiempo límite para ser contestados) En caso de que existan exámenes
     * que fueron asignados al alumno pero nunca contestados, (después de pasar
     * el tiempo límite para contestarlos), dado por un error o porque
     * simplemente no lo hizo. Este método remueve de la base de datos estos
     * exámenes no contestados.
     *
     * @param alumno el objeto UsuarioDTO que representa al alumno del cuál se
     * desea obtener los exámenes asignados
     *
     * @return una lista de objetos ExamenAsignadoDTO con los exámenes que puede
     * contestar o null, en caso de que no existan exámenes asignados vigentes.
     * La lista sólo contiene las relaciones que pueden ser obtenidas en una
     * consulta general. (No incluye las colecciones)
     * @throws java.rmi.RemoteException en caso de que ocurra un error remoto
     */
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

    /**
     * Obtiene el examen con unicamente sus claves
     *
     * @param idExamen el id del examen que se quiere obtener
     * @return el objeto examen con la lista de claves inicializada
     * @throws java.rmi.RemoteException
     */
    @Override
    public ExamenDTO obtenerExamenIncompleto(int idExamen) throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerExamen(idExamen);
    }

    /**
     * Este método sirve para obtener una ClaveExamen completa en base al id de
     * la clave ingresado. Esto devuelve una ClaveExamen con todos sus reactivos
     * y opciones incorrectas inicializados.
     *
     * @param idClave el objeto ClaveExamenPK que representa la llave compuesta
     * del número de clave y el idExamen de la ClaveExamen que se quiere obtener
     *
     * @return el objeto ClaveExamenDTO correspondiente al idClave totalmente
     * inicializado o null en caso de que no exista
     * @throws java.rmi.RemoteException en caso de que ocurra un error remoto
     */
    @Override
    public ClaveExamenDTO obtenerClaveExamen(ClaveExamenPK idClave) throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerClaveExamen(idClave);
    }

    /**
     * Este método obtiene el ExamenAsignadoDTO completo asociado a la llave
     * compuesta ingresada. Esto es: regresa todos los reactivos asignados a ese
     * objeto junto con las opciones incorrectas de cada reactivo asignado.
     *
     * @param id el objeto ExamenAsignadoPK que representa el ExamenAsignadoDTO
     * que se quiere buscar
     *
     * @return el objeto ExamenAsignadoDTO completo que pertenece al id
     * ingresado o null, en caso de que no exista tal objeto.
     * @throws java.rmi.RemoteException
     */
    @Override
    public ExamenAsignadoDTO obtenerExamenAsignado(ExamenAsignadoPK id) throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE()
                .obtenerExamenAsignado(id);
    }

    @Override
    public boolean verificarExistencia(TemaDTO tema) throws RemoteException {
        return FACADEServiceLocator.getTemaFACADE().verificarExistencia(tema);
    }

    @Override
    public long obtenerTiempoServidor() throws RemoteException {
        return FACADEServiceLocator.getExamenAsignadoFACADE().obtenerTiempoSistema();
    }
}
