/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;


import java.util.List;
import modelo.dto.ClaveExamenDTO;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.ExamenDTO.Permiso;
import modelo.dto.UsuarioDTO;
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
    
    public List<ExamenDTO> obtenerTodosPorCurso(CursoDTO curso) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenDTO> examenes;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            Criteria c = s.createCriteria(ExamenDTO.class, "examen")
                    .createAlias("examen.curso", "curso")
                    .add(Restrictions.eq("curso.nombre", curso.getNombre()));
            
            examenes = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            examenes = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return examenes;
    }
    
    public List<ExamenDTO> obtenerPublicosPorCurso(CursoDTO curso, 
            UsuarioDTO maestro) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenDTO> examenes;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            //Aquellos examenes que sean (publicos o del maestro) y que
            //pertenezcan al curso
            Criteria c = s.createCriteria(ExamenDTO.class, "examen")
                    .createAlias("examen.curso", "curso")
                    .add(Restrictions.and(
                            Restrictions.eq("curso.nombre", curso.getNombre()),
                            Restrictions.or(Restrictions.eq("examen.permiso",
                                    Permiso.Publico),
                            Restrictions.eq("examen.autor", maestro))));
            
            examenes = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            examenes = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return examenes;
    }
    
    public List<ExamenDTO> obtenerTodosPorTitulo(String titulo) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenDTO> examenes;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            Criteria c = s.createCriteria(ExamenDTO.class, "examen")
                    .add(Restrictions.like("examen.titulo", "%" + titulo + "%"));
            
            examenes = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            examenes = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return examenes;
    }
    
    public List<ExamenDTO> obtenerPublicosPorTitulo(String titulo,
            UsuarioDTO maestro) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenDTO> examenes;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            //Aquellos examenes que sean (publicos o del maestro) y que
            //pertenezcan al curso
            Criteria c = s.createCriteria(ExamenDTO.class, "examen")
                    .add(Restrictions.and(
                            Restrictions.like("examen.titulo", "%" + titulo + "%"),
                            Restrictions.or(Restrictions.eq("examen.permiso",
                                    Permiso.Publico),
                            Restrictions.eq("examen.autor", maestro))));
            
            examenes = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            examenes = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return examenes;
    }
    
    public List<ExamenDTO> obtenerTodosPorCursoYTitulo(CursoDTO curso,
            String titulo) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenDTO> examenes;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            Criteria c = s.createCriteria(ExamenDTO.class, "examen")
                    .createAlias("examen.curso", "curso")
                    .add(Restrictions.and(
                            Restrictions.eq("curso.nombre", curso.getNombre()),
                            Restrictions.like("examen.titulo", "%" + titulo + "%")));
            
            examenes = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            examenes = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return examenes;
    }
    
    public List<ExamenDTO> obtenerPublicosPorCursoYTitulo(CursoDTO curso,
            String titulo, UsuarioDTO maestro) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenDTO> examenes;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            //Aquellos examenes que sean (publicos o del maestro) y que
            //pertenezcan al curso
            Criteria c = s.createCriteria(ExamenDTO.class, "examen")
                    .createAlias("examen.curso", "curso")
                    .add(Restrictions.and(
                            Restrictions.and(
                            Restrictions.eq("curso.nombre", curso.getNombre()),
                            Restrictions.like("examen.titulo", "%" + titulo + "%")),
                            Restrictions.or(Restrictions.eq("examen.permiso",
                                    Permiso.Publico),
                            Restrictions.eq("examen.autor", maestro))));
            
            examenes = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            examenes = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return examenes;
    }
}