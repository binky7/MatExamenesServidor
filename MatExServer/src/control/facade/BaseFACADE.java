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

import java.io.Serializable;
import java.util.List;
import modelo.dao.DAOServiceLocator;

/**
 * Esta clase es una generalización del facade para los métodos básicos del CRUD
 * para cualquier entidad dto de la aplicación. 
 * 
 * @param <T> la entidad
 * @param <ID> el id de la entidad
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class BaseFACADE<T, ID extends Serializable> {
    
    /**
     * Obtiene el total de entidades existentes de la clase especificada
     * 
     * @param clazz la clase de la entidad que se quiere obtener
     * @return la lista de entidades que se obtuvieron, null en caso de no
     * obtener nada
     */
    public List<T> obtenerEntidades(Class<T> clazz) {
        
        return DAOServiceLocator.getBaseDAO().obtenerTodos(clazz);
    }
   
    /**
     * Almacena la entidad ingresada y la vuelve persistente
     * 
     * @param entidad el objeto a ser persistido
     * @return el id resultante del registro de la nueva entidad
     */
    public ID guardarEntidad(T entidad) {
        return (ID) DAOServiceLocator.getBaseDAO().insertar(entidad);
    }
    
    /**
     * Actualiza la entidad ingresada en la persistencia
     * 
     * @param entidad el objeto a actualizar
     * @return true si la operación se realizó exitosamente, false si ocurrió
     * un error
     */
    public boolean modificarEntidad(T entidad) {
        return DAOServiceLocator.getBaseDAO().modificar(entidad);
    }
    
    /**
     * Elimina la entidad ingresada de la persistencia
     * 
     * @param entidad el objeto a eliminar
     * @return true si la operación se realizó exitosamente, false si ocurrió
     * un error
     */
    public boolean eliminarEntidad(T entidad) {
        return DAOServiceLocator.getBaseDAO().eliminar(entidad);
    }
    
}