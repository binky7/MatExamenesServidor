/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
 *
 * This file is part of MatExámenes.
 *
 * MatExámenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExámenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
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
 * Esta clase es un dao de GrupoDTO para los métodos específicos a este objeto
 * dto, proporciona la funcionalidad necesaria accediendo a la base de datos
 *
 * @author Fernando Enrique Avendaño Hernández
 * @version 1 18 Mayo 2015
 */
public class GrupoDAO extends BaseDAO<GrupoDTO, Integer> {

    /**
     * Variable final que contiene el query necesario para obtener los cursos
     * que imparte un maestro en el grupo.
     */
    private final String GET_CURSOS = "SELECT idCurso FROM grupo_maestro "
            + "WHERE idMaestro = :idMaestro";

    /**
     * Variable final que contiene el query necesario para obtener los grupos en
     * los que se imparte un curso.
     */
    private final String GET_GRUPOS_POR_CURSO = "SELECT DISTINCT g FROM GrupoDTO AS g "
            + "WHERE :curso IN INDICES(g.maestros)";

    /**
     * Variable final que contiene el query necesario para obtener los grupos en
     * los que un maestro imparte cierto curso.
     */
    private final String GET_GRUPOS_POR_CURSO_MAESTRO = "SELECT idGrupo FROM "
            + "grupo_maestro WHERE idCurso = :idCurso AND idMaestro = :idMaestro";

    /**
     * Variable final que contiene el query necesario para obtener los alumnos
     * que pertenecen a un grupo.
     */
    private final String GET_ALUMNOS = "SELECT ELEMENTS(g.alumnos) "
            + " FROM GrupoDTO AS g WHERE g = :grupo";

    /**
     * Recibe el id de un grupo y lo realiza la consulta a la base de datos para
     * obtener el grupo.
     *
     * @param id el id.
     * @return objeto GrupoDTO.
     */
    public GrupoDTO obtener(Integer id) {
        //Regresa todas las relaciones
        Session s = getSession();
        Transaction tx = null;
        GrupoDTO grupo;

        if (s == null) {
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

    /**
     * Recibe un maestro y bussca los cursos que imparte el maestro en algun
     * grupo.
     *
     * @param maestro el maestro.
     * @return lista de cursos.
     */
    public List<CursoDTO> obtenerCursos(UsuarioDTO maestro) {
        Session s = getSession();
        Transaction tx = null;
        List<CursoDTO> cursos = null;

        if (s == null) {
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

    /**
     * Recibe un curso y un maestro y obtiene los grupos en los que el maestro
     * imparte el curso.
     *
     * @param curso el curso.
     * @param maestro el maestro.
     * @return lista de grupos.
     */
    public List<GrupoDTO> obtenerTodosPorCurso(CursoDTO curso, UsuarioDTO maestro) {
        Session s = getSession();
        Transaction tx = null;
        List<GrupoDTO> grupos = null;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            if (maestro == null) {
                //Traer los grupos que tienen el curso sin contar a los maestros
                Query q = s.createQuery(GET_GRUPOS_POR_CURSO)
                        .setEntity("curso", curso);

                grupos = q.list();
            } else {
                //Traer los grupos del maestro con el curso especifico

                //Obtener los ids de los grupos con sql....
                Query q = s.createSQLQuery(GET_GRUPOS_POR_CURSO_MAESTRO)
                        .setParameter("idCurso", curso.getId())
                        .setParameter("idMaestro", maestro.getId());

                List<Integer> ids = q.list();

                if (ids != null && !ids.isEmpty()) {
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

    /**
     * Recibe el grupo y obtiene los alumnos de el grupo ingresado.
     *
     * @param grupo el grupo.
     * @return lista de alumnos.
     */
    public List<UsuarioDTO> obtenerSoloAlumnos(GrupoDTO grupo) {
        Session s = getSession();
        Transaction tx = null;
        List<UsuarioDTO> alumnos;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los alumnos de este grupo
            Query q = s.createQuery(GET_ALUMNOS).setEntity("grupo", grupo);

            alumnos = q.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            alumnos = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }

        return alumnos;
    }

    /**
     * Recibe un grupo y verifica si existe en la base de datos.
     *
     * @param grupo el grupo.
     * @return verdadero si existe, falso si no existe.
     */
    public boolean existe(GrupoDTO grupo) {
        Session s = getSession();
        Transaction tx = null;
        GrupoDTO objGrupo;
        boolean existe = false;

        if (s == null) {
            System.out.println("Session nula");
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los temas de este curso
            //Todo query, modificacion, eliminacion e insercion debe estar 
            //enmedio de este codigo
            Criteria c = s.createCriteria(GrupoDTO.class);
            c.setMaxResults(1);
            c.add(Restrictions.eq("grado", grupo.getGrado()));
            c.add(Restrictions.eq("nombre", grupo.getNombre()));
            c.add(Restrictions.eq("turno", grupo.getTurno()));
            objGrupo = (GrupoDTO) c.uniqueResult();

            if (objGrupo != null) {
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
}
