/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

public interface Persistencia extends Remote {

    <T> List<T> obtenerEntidades(Class<T> clazz) throws RemoteException;

    <T, ID extends Serializable> ID guardarEntidad(T entidad)
            throws RemoteException;

    <T> boolean modificarEntidad(T entidad) throws RemoteException;

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

    ReactivoDTO obtenerReactivo(int idReactivo) throws RemoteException;

    List<ReactivoDTO> obtenerReactivosPorTema(TemaDTO tema)
            throws RemoteException;

    List<ReactivoDTO> obtenerReactivosAleatorios(List<TemaDTO> temas,
            List<Integer> cantidades) throws RemoteException;

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
    
    boolean verificarExistencia(TemaDTO tema) throws RemoteException;
}
