/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import remoteAccess.DAOInterface;

/**
 *
 * @author Jesus Donaldo
 * @param <T>
 * @param <ID>
 */
public class GenericDAO<T, ID extends Serializable> 
implements DAOInterface<T, ID> {
    
    protected Session getSession() {
        Session session = null;
        
        if(HibernateUtil.getSessionFactory() != null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        
        return session;
    }

    @Override
    public List<T> obtenerTodos(Class<T> clazz) {
        
        Session s = getSession();
        Transaction tx = null;
        List<T> entidades;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            Criteria c = s.createCriteria(clazz);
            entidades = c.list();
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
        
        return entidades;
    }
    
    @Override
    public ID insertar(T entidad) {  
        
        Session s = getSession();
        Transaction tx = null;
        
        ID id;
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Persiste la entidad en la base de datos
            id = (ID) s.save(entidad);
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

        return id;
    }
    
    @Override
    public void modificar(T entidad) {  
        
        Session s = getSession();
        Transaction tx = null;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return;
        }
        
        try {
            tx = s.beginTransaction();
            //Modifica la entidad en la base de datos
            s.update(entidad);
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
    }
    
    @Override
    public void eliminar(T entidad) {  
        
        Session s = getSession();
        Transaction tx = null;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return;
        }
        
        try {
            tx = s.beginTransaction();
            //Elimina la entidad de la base de datos
            s.delete(entidad);
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
    }
}