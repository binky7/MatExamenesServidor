/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jesus Donaldo
 */
public class CursoDAO extends GenericDAO<CursoDTO, Integer> {

    //Obtiene los temas del curso...
    private final String GET_TEMAS = "SELECT ELEMENTS(c.temas) " +
            " FROM CursoDTO AS c WHERE c = :curso";
    
    public CursoDTO obtener(Integer id) {
        //Obtener objeto con sus relaciones
        return null;
    }
    
    public List<TemaDTO> obtenerTemas(CursoDTO curso) {
        Session s = getSession();
        Transaction tx = null;
        List<TemaDTO> temas;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los temas de este curso
            //Todo query, modificacion, eliminacion e insercion debe estar 
            //enmedio de este codigo
            
            Query q = s.createQuery(GET_TEMAS).setEntity("curso", curso);
            
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

}