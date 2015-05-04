/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.UsuarioDTO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class GrupoDAO extends BaseDAO<GrupoDTO, Integer> {

    private final String GET_CURSOS = "SELECT idCurso FROM grupo_maestro " +
        "WHERE idMaestro = :idMaestro";
    
    private final String GET_GRUPOS_POR_CURSO = "SELECT DISTINCT g FROM GrupoDTO AS g "
            + "WHERE :curso IN INDICES(g.maestros)";
    
    private final String GET_GRUPOS_POR_CURSO_MAESTRO = "SELECT idGrupo FROM "
            + "grupo_maestro WHERE idCurso = :idCurso AND idMaestro = :idMaestro";
    
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
            grupo = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return grupo;
    }
    
    public List<CursoDTO> obtenerCursos(UsuarioDTO maestro) {
        Session s = getSession();
        Transaction tx = null;
        List<CursoDTO> cursos = null;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene los ids de los cursos por maestro, ya que con hql no funciona
            Query q = s.createSQLQuery(GET_CURSOS).setParameter("idMaestro",
                    maestro.getId());
            
            List<Integer> ids = q.list();

            if (ids != null && !ids.isEmpty()) {
                //Obtiene los cursos que tienen el id en la coleccion de ids obtenida
                Criteria c = s.createCriteria(CursoDTO.class)
                        .add(Restrictions.in("id", ids));

                cursos = c.list();
            }
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            cursos = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return cursos;
    }
    
    public List<GrupoDTO> obtenerTodosPorCurso(CursoDTO curso, UsuarioDTO maestro) {
        Session s = getSession();
        Transaction tx = null;
        List<GrupoDTO> grupos = null;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            
            if(maestro == null) {
                //Traer los grupos que tienen el curso sin contar a los maestros
                Query q = s.createQuery(GET_GRUPOS_POR_CURSO)
                        .setEntity("curso", curso);
                
                grupos = q.list();
            }
            else {
                //Traer los grupos del maestro con el curso especifico
                
                //Obtener los ids de los grupos con sql....
                Query q = s.createSQLQuery(GET_GRUPOS_POR_CURSO_MAESTRO)
                        .setParameter("idCurso", curso.getId())
                        .setParameter("idMaestro", maestro.getId());
                
                List<Integer> ids = q.list();
                
                if(ids != null && !ids.isEmpty()) {
                    //Obtener los grupos segun los ids obtenidos
                    Criteria c = s.createCriteria(GrupoDTO.class)
                            .add(Restrictions.in("id", ids));
                
                    grupos = c.list();
                }
            }
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            grupos = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return grupos;
        
    }
}