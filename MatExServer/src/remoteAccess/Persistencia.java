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
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;


public interface Persistencia extends Remote {
    
    <T> List<T> obtenerEntidades(Class<T> clazz) throws RemoteException;
    <T, ID extends Serializable> ID guardarEntidad(T entidad)
            throws RemoteException;
    <T> boolean modificarEntidad(T entidad) throws RemoteException;
    <T> boolean eliminarEntidad(T entidad) throws RemoteException;
    List<TemaDTO> obtenerTemasSinAsignar() throws RemoteException;
    List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso) throws RemoteException;
}