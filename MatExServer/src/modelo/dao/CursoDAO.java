/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class CursoDAO extends BaseDAO<CursoDTO, Integer> {

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
    
    public boolean existe(CursoDTO curso) {
        Session s = getSession();
        Transaction tx = null;
        CursoDTO objCurso;
        boolean existe = false;
        
        if(s == null) {
            System.out.println("Session nula");
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los temas de este curso
            //Todo query, modificacion, eliminacion e insercion debe estar 
            //enmedio de este codigo
            Criteria c = s.createCriteria(CursoDTO.class);
            c.setMaxResults(1);
            c.add(Restrictions.eq("nombre", curso.getNombre()));
            objCurso = (CursoDTO)c.uniqueResult();
            
            if(objCurso != null) {
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
    
    public CursoDTO obtenerPorTema(TemaDTO tema) {
        Session s = getSession();
        Transaction tx = null;
        CursoDTO objCurso = null;
        
        if(s == null) {
            System.out.println("Session nula");
        }
        
        try {
            Criteria c = getSession().createCriteria(CursoDTO.class, "curso")
                    .createAlias("curso.temas", "temas")
                    .add(Restrictions.eq("temas.id", tema.getId()));
            
            objCurso = (CursoDTO)c.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return objCurso;
    }

    public List<CursoDTO> obtenerCursosPorMaestro(UsuarioDTO maestro) {
        return null;
    }

}