/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteAccess;

import control.facade.FACADEServiceLocator;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TablaEstadisticas;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class AccesoDatos extends UnicastRemoteObject implements Persistencia {

    public AccesoDatos() throws RemoteException {
        
    }
    
    @Override
    public <T> List<T> obtenerEntidades(Class<T> clazz) throws RemoteException {
        return FACADEServiceLocator.getBaseFACADE().obtenerEntidades(clazz);
    }

    @Override
    public <T, ID extends Serializable> ID guardarEntidad(T entidad)
            throws RemoteException {
        return (ID) FACADEServiceLocator.getBaseFACADE()
                .guardarEntidad(entidad);
    }

    @Override
    public <T> boolean modificarEntidad(T entidad) throws RemoteException {
        return FACADEServiceLocator.getBaseFACADE().modificarEntidad(entidad);
    }

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

    @Override
    public List<CursoDTO> obtenerCursos(UsuarioDTO maestro) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificarExistencia(CursoDTO curso) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDTO obtenerUsuario(String usuario) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellidoPaterno, UsuarioDTO.Tipo tipo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellidoPaterno) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReactivoDTO obtenerReactivo(int idReactivo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReactivoDTO> obtenerReactivosPorTema(TemaDTO tema) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReactivoDTO> obtenerReactivosAleatorios(List<TemaDTO> temas, List<Integer> cantidades) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarReactivos(List<ReactivoDTO> reactivos) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GrupoDTO obtenerGrupo(int idGrupo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GrupoDTO obtenerGrupoSoloAlumnos(int idGrupo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso, UsuarioDTO maestro) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificarExistencia(GrupoDTO grupo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExamenDTO obtenerExamen(int idExamen) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso, boolean todos, UsuarioDTO maestro) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExamenDTO> obtenerExamenesPorTitulo(String titulo, boolean todos, UsuarioDTO maestro) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExamenDTO> obtenerExamenesPorCursoYTitulo(CursoDTO curso, String titulo, boolean todos, UsuarioDTO maestro) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TablaEstadisticas generarEstadisticasPorGrupos(List<ExamenDTO> examenes, List<GrupoDTO> grupos) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TablaEstadisticas generarEstadisticasPorGrados(List<ExamenDTO> examenes) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TablaEstadisticas generarEstadisticasPorTurnos(List<ExamenDTO> examenes) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean asignarExamenes(List<ExamenAsignadoDTO> examenes) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExamenAsignadoDTO obtenerExamenContestado(ExamenAsignadoPK id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExamenAsignadoDTO> obtenerExamenesContestados(UsuarioDTO alumno, CursoDTO curso) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExamenAsignadoDTO obtenerExamenAsignado(UsuarioDTO alumno) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}