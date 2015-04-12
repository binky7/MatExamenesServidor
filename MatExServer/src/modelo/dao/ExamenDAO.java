/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;


import modelo.dto.ClaveExamenDTO;
import modelo.dto.ExamenDTO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class ExamenDAO extends BaseDAO<ExamenDTO, Integer> {

    public ExamenDTO obtener(Integer id) {
        //Regresa todas las relaciones
        Session s = getSession();
        Transaction tx = null;
        ExamenDTO examen;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, con sus relaciones
            Criteria c = s.createCriteria(ExamenDTO.class)
                    .add(Restrictions.idEq(id))
                    .setFetchMode("claves", FetchMode.JOIN);
            
            examen = (ExamenDTO) c.uniqueResult();
            
            for(ClaveExamenDTO clave : examen.getClaves()) {
                 Hibernate.initialize(clave.getReactivos());
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            examen = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return examen;
    }
    
    
    
}