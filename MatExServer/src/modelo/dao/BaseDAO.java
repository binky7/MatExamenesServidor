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
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;

/**
 *
 * @author Jesus Donaldo
 * @param <T>
 * @param <ID>
 */
public class BaseDAO<T, ID extends Serializable> {
    
    protected Session getSession() {
        Session session = null;
        
        if(HibernateUtil.getSessionFactory() != null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        
        return session;
    }

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
            entidades = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return entidades;
    }
    
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
            id = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }

        return id;
    }
    
    public boolean modificar(T entidad) {  
        
        Session s = getSession();
        Transaction tx = null;
        boolean ok = true;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return false;
        }
        
        try {
            tx = s.beginTransaction();
            //Modifica la entidad en la base de datos
            s.update(entidad);
            tx.commit();
        } catch(StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ID id = insertar(entidad);
            if(id == null) {
                ok = false;
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
    
    public boolean eliminar(T entidad) {  
        
        Session s = getSession();
        Transaction tx = null;
        boolean ok = true;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return false;
        }
        
        try {
            tx = s.beginTransaction();
            //Elimina la entidad de la base de datos
            s.delete(entidad);
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