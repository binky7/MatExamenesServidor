/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.CursoTemaDTO;
import modelo.dto.TemaDTO;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
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

    /**
     * Obtiene el curso completo al que pertenece el id ingresado.
     * 
     * @param id del curso a obtener.
     * @return el objeto CursoDTO completo con todas sus relacionas. Regresa
     * null en caso de que no exista.
     */
    public CursoDTO obtener(Integer id) {
        //Obtener objeto con sus relaciones
        Session s = getSession();
        Transaction tx = null;
        CursoDTO curso = null;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            Criteria c = s.createCriteria(CursoDTO.class, "curso")
                    .add(Restrictions.idEq(id));

            curso = (CursoDTO) c.uniqueResult();
            //Inicializa los temas del curso obtenido
            Hibernate.initialize(curso.getTemas());
            tx.commit();

        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            curso = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }

        return curso;
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
        List<TemaDTO> temas = new ArrayList();

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
            List<CursoTemaDTO> listaCursoTema = q.list();
            //Recorre la lista de objetos de tipo CursoTemaDTO para extraer
            //los objetos TemaDTO
            for (CursoTemaDTO j : listaCursoTema) {
                temas.add(j.getTema());
            }

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
     * Obtiene los temas de un curso dependiendo del bloque seleccionado.
     *
     * @param curso El curso seleccionado.
     * @param bloque El bloque seleccionado.
     * @return Regresa lista de temas si el curso seleccionado tiene temas
     * que coincidan con el bloque seleccionado.
     * Regresa null si el curso seleccionado no tiene temas del bloque seleccionado.
     */
    public List<TemaDTO> obtenerTemas(CursoDTO curso, int bloque) {
        Session s = getSession();
        Transaction tx = null;
        List<TemaDTO> temas = new ArrayList();

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
            List<CursoTemaDTO> listaCursoTema = q.list();
            //Recorre la lista de objetos de tipo CursoTemaDTO para extraer
            //los objetos TemaDTO
            for (CursoTemaDTO j : listaCursoTema) {
                //Extrae únicamente los temas que coincidan con el bloque
                //seleccionado.
                if (j.getBloque() == bloque) {
                    temas.add(j.getTema());
                }
            }

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
                    .add(Restrictions.eq("temas.id.idTema", tema.getId()));

            objCurso = (CursoDTO) c.uniqueResult();
            Hibernate.initialize(objCurso.getTemas());
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
