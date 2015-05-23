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
package control.facade;

import java.util.ArrayList;
import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;

/**
 * Esta clase es un facade de ReactivoDTO para los métodos específicos a este
 * objeto dto, proporciona la funcionalidad necesaria accediendo a los datos
 * mediante capas inferiores
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class ReactivoFACADE extends BaseFACADE<ReactivoDTO, Integer> {
    
    /**
     * Obtiene el reactivo completo al que pertenece el id ingresado
     * 
     * @param idReactivo el id del reactivo a obtener
     * @return el objeto ReactivoDTO completo, con todas sus relaciones, o null
     * en caso de que no exista
     */
    public ReactivoDTO obtenerReactivo(int idReactivo) {
        return DAOServiceLocator.getReactivoDAO().obtener(idReactivo);
    }
    
    /**
     * Obtiene todos los reactivos del tema ingresado
     * 
     * @param tema el objeto TemaDTO del que se quieren obtener los reactivos
     * @return una lista de ReactivoDTO del tema ingresado, o null en caso de que
     * no exista ningún reactivo.
     * 
     */
    public List<ReactivoDTO> obtenerReactivosPorTema(TemaDTO tema) {
        return DAOServiceLocator.getReactivoDAO().obtenerTodosPorTema(tema);
    }
   
    /**
     * Obtiene la cantidad de reactivos por tema especificada de manera aleatoria
     * 
     * @param temas la lista de TemaDTO de donde se obtendrán los reactivos
     * aleatorios
     * @param cantidades la lista de cantidades de cada tema la cuál indica
     * cuantos reactivos se seleccionarán por cada tema
     * @return una lista de ReactivoDTO seleccionados aleatoriamente de cada tema
     * o regresa null en caso de que se especifiquen cantidades por tema mayores
     * de las que existen
     */
    public List<ReactivoDTO> obtenerReactivosAleatorios(List<TemaDTO> temas,
            List<Integer> cantidades) {
        //Total de reactivos seleccionados
        List<ReactivoDTO> reactivosTotales = new ArrayList<>();
        
        //Iterar por cada tema de la lista para seleccionar los reactivos
        for(int i = 0; i < temas.size(); i++) {
            TemaDTO tema = temas.get(i);
            //Obtener todos los reactivos del tema
            List<ReactivoDTO> reactivos = obtenerReactivosPorTema(tema);
            int cantidad = cantidades.get(i);
            
            //Obtener reactivos aleatoriamente de los reactivos resultantes
            //hasta que se cumpla la cantidad por el tema
            if(reactivos != null && reactivos.size() >= cantidad) {
                reactivos = seleccionarReactivos(reactivos, cantidad);
                reactivosTotales.addAll(reactivos);
            }
            else {
                //Si los reactivos no son suficientes para la cantidad que se
                //pide retornar null
                reactivosTotales = null;
                break;
            }
        }
        
        return reactivosTotales;
    }
 
    /**
     * Elimina la lista de reactivos ingresada de la persistencia
     * 
     * @param reactivos la lista de ReactivoDTO que se desea eliminar
     * @return true si la operación se realizó exitosamente, false si ocurrió
     * un error
     */
    public boolean eliminarReactivos(List<ReactivoDTO> reactivos) {
        return DAOServiceLocator.getReactivoDAO().eliminar(reactivos);
    }
    
    /**
     * Este método selecciona de manera aleatoria la cantidad de reactivos
     * especificada de la lista proporcionada
     * 
     * @param reactivos la lista total de ReactivoDTO de donde se seleccionarán
     * los reactivos
     * @param cantidad la cantidad que se quiere seleccionar
     * @return una nueva lista de ReactivoDTO con los reactivos seleccionados
     * aleatoriamente
     */
    private List<ReactivoDTO> seleccionarReactivos(List<ReactivoDTO> reactivos,
            int cantidad) {
        List<ReactivoDTO> seleccion = new ArrayList<>();
        
        for(int i = 0; i < cantidad; i++) {
            int pos = (int)(Math.random() * reactivos.size());
            
            seleccion.add(reactivos.remove(pos));
        }
        
        return seleccion;
    }
}