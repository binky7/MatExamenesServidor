/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import modelo.dto.GrupoDTO;
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
public class GrupoDAO extends GenericDAO<GrupoDTO, Integer> {

    public GrupoDTO obtener(Integer id) {
        //Regresa todas las relaciones
        Session s = getSession();
        Transaction tx = null;
        GrupoDTO grupo;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, con sus relaciones
            Criteria c = s.createCriteria(GrupoDTO.class)
                    .add(Restrictions.idEq(id))
                    .setFetchMode("alumnos", FetchMode.JOIN);
            
            grupo = (GrupoDTO) c.uniqueResult();
            
            Hibernate.initialize(grupo.getMaestros());
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return grupo;
    }
    
    //obtenerSoloAlumnos(id)
}