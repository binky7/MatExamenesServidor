/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class ReactivoDAO extends BaseDAO<ReactivoDTO, Integer> {
    
    public ReactivoDTO obtener(int idReactivo) {
        
        Session s = getSession();
        Transaction tx = null;
        ReactivoDTO reactivo;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            Criteria c = s.createCriteria(ReactivoDTO.class)
                    .add(Restrictions.idEq(idReactivo))
                    .setFetchMode("opciones", FetchMode.JOIN);
            
            reactivo = (ReactivoDTO) c.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            reactivo = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return reactivo;
    }
    
    public List<ReactivoDTO> obtenerTodosPorTema(TemaDTO tema) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ReactivoDTO> reactivos;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            Criteria c = s.createCriteria(ReactivoDTO.class)
                    .add(Restrictions.eq("tema", tema));
            
            reactivos = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            reactivos = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return reactivos;
    }
    
    public boolean eliminar(List<ReactivoDTO> reactivos) {
        
        Session s = getSession();
        Transaction tx = null;
        boolean ok = true;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return false;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            for (int i = 0; i < reactivos.size(); i++) {
                ReactivoDTO reactivo = reactivos.get(i);
                
                s.delete(reactivo);
                if(i % 20 == 0) {
                    s.flush();
                    s.clear();
                }
            }
            
            tx.commit();
        } catch(StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            ok = false;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return ok;
    }
}