/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.TemaDTO;

/**
 *
 * @author ivan
 */
public class TemaFACADE extends GenericFACADE<TemaDTO, Integer> {
    /**
     * 
     * @return Los temas que no pertenecen a ningun curso
     */
    public List<TemaDTO> obtenerTemasSinAsignar() {
        List<TemaDTO> listaTemas;

        listaTemas = DAOServiceLocator.getTemaDAO()
                .obtenerTemasSinAsignar();        
        return listaTemas;
    }
    
}
