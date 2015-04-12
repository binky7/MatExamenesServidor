/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteAccess;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jesus Donaldo
 * @param <T>
 * @param <ID>
 */
public interface DAOInterface<T, ID extends Serializable> {
    List<T> obtenerTodos(Class<T> clazz);
    ID insertar(T entidad);
    boolean modificar(T entidad);
    boolean eliminar(T entidad);
}
