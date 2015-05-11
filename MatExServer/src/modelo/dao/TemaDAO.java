/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dto.TemaDTO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class TemaDAO extends BaseDAO<TemaDTO, Integer> {

    //Obtener todos los temas que no pertenecen a un curso....
    private final String GET_TEMAS_SIN_ASIGNAR = "SELECT DISTINCT t2 FROM " +
            "TemaDTO AS t2 WHERE t2 NOT IN" +
            "(SELECT ELEMENTS(c.temas) FROM CursoDTO AS c)";
    
    public List<TemaDTO> obtenerTemasSinAsignar() {
        Session s = getSession();
        Transaction tx = null;
        List<TemaDTO> temas;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los temas que no tengan curso
            //Todo query, modificacion, eliminacion e insercion debe estar 
            //enmedio de este codigo
            Query q = s.createQuery(GET_TEMAS_SIN_ASIGNAR);
            
            temas = q.list();
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            temas = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return temas;
    }
    
    public boolean existe(TemaDTO tema) {
        Session s = getSession();
        Transaction tx = null;
        TemaDTO objTema;
        boolean existe = false;
        
        if(s == null) {
            System.out.println("Session nula");
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los temas de este curso
            //Todo query, modificacion, eliminacion e insercion debe estar 
            //enmedio de este codigo
            Criteria c = s.createCriteria(TemaDTO.class);
            c.setMaxResults(1);
            c.add(Restrictions.eq("nombre", tema.getNombre()));
            objTema = (TemaDTO)c.uniqueResult();
            
            if(objTema != null) {
                existe = true;
            } else {
                existe = false;
            }
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return existe;
    }
}