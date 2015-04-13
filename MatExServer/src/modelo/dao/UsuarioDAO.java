/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dto.UsuarioDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class UsuarioDAO extends BaseDAO<UsuarioDTO, Integer> {
    
    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellido){
        Session s = getSession();
        Transaction tx = null;
        List<UsuarioDTO> usuarios;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos que concuenrden con el apellido
            
            usuarios = s.createCriteria(UsuarioDTO.class)
            .add( Restrictions.like("apellidoPaterno", apellido+"%") )
            .list();
      
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            usuarios = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        
        return usuarios;
    }
    
}