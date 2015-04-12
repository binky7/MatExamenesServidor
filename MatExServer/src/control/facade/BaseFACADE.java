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
public class BaseFACADE<T, ID extends Serializable> {
    
    public List<T> obtenerEntidades(Class<T> clazz) {
        
        return DAOServiceLocator.getBaseDAO().obtenerTodos(clazz);
    }
    
    public ID guardarEntidad(T entidad) {
        return (ID) DAOServiceLocator.getBaseDAO().insertar(entidad);
    }
    
    public boolean modificarEntidad(T entidad) {
        return DAOServiceLocator.getBaseDAO().modificar(entidad);
    }
    
    public boolean eliminarEntidad(T entidad) {
        return DAOServiceLocator.getBaseDAO().eliminar(entidad);
    }
    
}