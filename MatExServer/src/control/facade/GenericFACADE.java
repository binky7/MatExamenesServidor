/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.io.Serializable;
import java.util.List;
import modelo.dao.DAOServiceLocator;

/**
 *
 * @author Jesus Donaldo
 * @param <T>
 * @param <ID>
 */
public class GenericFACADE<T, ID extends Serializable> {
    
    public List<T> obtenerEntidades(Class<T> clazz) {
        
        return DAOServiceLocator.getGenericDAO().obtenerTodos(clazz);
    }
    
    public ID guardarEntidad(T entidad) {
        return (ID) DAOServiceLocator.getGenericDAO().insertar(entidad);
    }
    
    public void modificarEntidad(T entidad) {
        DAOServiceLocator.getGenericDAO().modificar(entidad);
    }
    
    public void eliminarEntidad(T entidad) {
        DAOServiceLocator.getGenericDAO().eliminar(entidad);
    }
    
}