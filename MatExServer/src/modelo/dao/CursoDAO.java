/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dto.CursoDTO;
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
public class CursoDAO extends BaseDAO<CursoDTO, Integer> {

    //Obtiene los temas del curso...
    private final String GET_TEMAS = "SELECT ELEMENTS(c.temas) "
            + " FROM CursoDTO AS c WHERE c = :curso";

    public CursoDTO obtener(Integer id) {
        //Obtener objeto con sus relaciones
        return null;
    }

    /**
     * Obtiene los temas de un curso.
     *
     * @param curso El curso seleccionado.
     * @return Regresa lista de temas si el curso seleccionado tiene temas.
     * Regresa null si el curso seleccionado no tiene temas.
     */
    public List<TemaDTO> obtenerTemas(CursoDTO curso) {
        Session s = getSession();
        Transaction tx = null;
        List<TemaDTO> temas;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los temas de este curso
            //Todo query, modificación, eliminación e inserción debe estar 
            //enmedio de este código

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

    /**
     * Verifica si el nombre del curso ingresado ya existe en la base de datos.
     *
     * @param curso El curso del cual se quiere verificar su existencia.
     * @return Regresa verdadero si el nombre del curso ya existe en la base de
     * datos. Regresa falso si el nombre del curso no existe en la base de
     * datos.
     */
    public boolean existe(CursoDTO curso) {
        Session s = getSession();
        Transaction tx = null;
        CursoDTO objCurso;
        boolean existe = false;

        if (s == null) {
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
            objCurso = (CursoDTO) c.uniqueResult();

            if (objCurso != null) {
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

    /**
     * Obtiene el curso al que pertenece el tema seleccionado.
     *
     * @param tema El tema seleccionado.
     * @return Regresa el curso al que pertenece el tema seleccionado.
     */
    public CursoDTO obtenerPorTema(TemaDTO tema) {
        Session s = getSession();
        Transaction tx = null;
        CursoDTO objCurso = null;

        if (s == null) {
            System.out.println("Session nula");
        }

        try {
            Criteria c = getSession().createCriteria(CursoDTO.class, "curso")
                    .createAlias("curso.temas", "temas")
                    .add(Restrictions.eq("temas.id", tema.getId()));

            objCurso = (CursoDTO) c.uniqueResult();
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

}
