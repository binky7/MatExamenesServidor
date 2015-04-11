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
import modelo.dto.TemaDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class AccesoDatos extends UnicastRemoteObject implements Persistencia {

    public AccesoDatos() throws RemoteException {
        
    }
    
    @Override
    public <T> List<T> obtenerEntidades(Class<T> clazz) throws RemoteException {
        return FACADEServiceLocator.getGenericFACADE().obtenerEntidades(clazz);
    }

    @Override
    public <T, ID extends Serializable> ID guardarEntidad(T entidad)
            throws RemoteException {
        return (ID) FACADEServiceLocator.getGenericFACADE()
                .guardarEntidad(entidad);
    }

    @Override
    public <T> void modificarEntidad(T entidad) throws RemoteException {
        FACADEServiceLocator.getGenericFACADE().modificarEntidad(entidad);
    }

    @Override
    public <T> void eliminarEntidad(T entidad) throws RemoteException {
        FACADEServiceLocator.getGenericFACADE().eliminarEntidad(entidad);
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
}