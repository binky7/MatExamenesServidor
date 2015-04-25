/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.util.ArrayList;
import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class ReactivoFACADE extends BaseFACADE<ReactivoDTO, Integer> {
    
    public ReactivoDTO obtenerReactivo(int idReactivo) {
        return DAOServiceLocator.getReactivoDAO().obtener(idReactivo);
    }
    
    public List<ReactivoDTO> obtenerReactivosPorTema(TemaDTO tema) {
        return DAOServiceLocator.getReactivoDAO().obtenerTodosPorTema(tema);
    }
    
    public List<ReactivoDTO> obtenerReactivosAleatorios(List<TemaDTO> temas,
            List<Integer> cantidades) {
        List<ReactivoDTO> reactivosTotales = new ArrayList<>();
        
        for(int i = 0; i < temas.size(); i++) {
            TemaDTO tema = temas.get(i);
            List<ReactivoDTO> reactivos = obtenerReactivosPorTema(tema);
            int cantidad = cantidades.get(i);
            
            if(reactivos != null && reactivos.size() >= cantidad) {
                reactivos = seleccionarReactivos(reactivos, cantidad);
                reactivosTotales.addAll(reactivos);
            }
            else {
                reactivosTotales = null;
                break;
            }
        }
        
        return reactivosTotales;
    }
    
    public boolean eliminarReactivos(List<ReactivoDTO> reactivos) {
        return DAOServiceLocator.getReactivoDAO().eliminar(reactivos);
    }
    
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