/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import modelo.dao.DAOServiceLocator;
import modelo.dto.GrupoDTO;

/**
 *
 * @author FernandoEnrique
 */
public class GrupoFACADE extends BaseFACADE<GrupoDTO, Integer>{
    
    public GrupoDTO obtenerGrupo(int idGrupo){
        return DAOServiceLocator.getGrupoDAO().obtener(idGrupo);
    }
    
}
