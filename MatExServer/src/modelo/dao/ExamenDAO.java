/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
 *
 * This file is part of MatExamenes.
 *
 * MatExamenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExamenes is distributed in the hope that it will be useful, but WITHOUT
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
 * Esta clase es un dao de ExamenDTO para los métodos específicos a este
 * objeto dto, proporciona la funcionalidad necesaria accediendo a la base de
 * datos
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class ExamenDAO extends BaseDAO<ExamenDTO, Integer> {

    /**
     * Este método es utilizado para obtener el objeto examen perteneciente
     * al id ingresado
     * 
     * @param id el id del examen que se quiere obtener
     * @return El objeto ExamenDTO con sus relaciones completamente inicializadas
     * hasta reactivos. (Las incorrectas de los reactivos no están inicializadas,
     * tampoco los temas del curso)
     */
    public ExamenDTO obtener(Integer id) {

        Session s = getSession();
        Transaction tx = null;
        ExamenDTO examen;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene el examen específico a ese id, incluyendo sus claves
            Criteria c = s.createCriteria(ExamenDTO.class)
                    .add(Restrictions.idEq(id))
                    .setFetchMode("claves", FetchMode.JOIN);
            
            examen = (ExamenDTO) c.uniqueResult();
            
            //Inicializa por cada clave todos los reactivos que contenga
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
    
    /**
     * Este método sirve para obtener exámenes por el curso seleccionado,
     * obteniendo todos los exámenes disponibles en ese curso
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
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
            //Obtiene todos los exámenes que coincidan con el curso,
            //sin sus relaciones (claves)
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
    
    /**
     * Este método sirve para obtener exámenes por el curso seleccionado,
     * obteniendo sólo los exámenes públicos o aquellos hechos por el maestro
     * ingresado y que coincidan con el curso ingresado
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * 
     * @param maestro Sirve para filtrar la búsqueda por el
     * autor del examen. Esta consulta regresa los exámenes que pertenezcan
     * al curso y que además son públicos o hechos por el maestro.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
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
            //Obtiene todos los exámenes que coincidan con el curso,
            //sin sus relaciones (claves)
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
    
    /**
     * Este método sirve para obtener exámenes por el nombre ingresado,
     * obteniendo todos los exámenes que coincidan con el nombre
     * 
     * @param nombre el nombre o parte del nombre del examen utilizado como filtro
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerTodosPorNombre(String nombre) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenDTO> examenes;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los exámenes que coincidan con el nombre,
            //sin sus relaciones (claves)
            Criteria c = s.createCriteria(ExamenDTO.class, "examen")
                    .add(Restrictions.like("examen.nombre", "%" + nombre + "%"));
            
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
    
    /**
     * Este método sirve para obtener exámenes por el nombre ingresado,
     * obteniendo sólo los exámenes públicos o aquellos hechos por el maestro
     * ingresado, y que coincidan con el nombre ingresado
     * 
     * @param nombre el nombre o parte del nombre del examen utilizado como filtro
     * 
     * @param maestro Sirve para filtrar la búsqueda por el
     * autor del examen. Esta consulta regresa los exámenes que coincidan con el
     * nombre y que además son públicos o hechos por el maestro.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerPublicosPorNombre(String nombre,
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
            //Obtiene todos los exámenes que coincidan con el nombre,
            //sin sus relaciones (claves)
            //Aquellos examenes que sean (publicos o del maestro) y que
            //coincidan con el nombre
            Criteria c = s.createCriteria(ExamenDTO.class, "examen")
                    .add(Restrictions.and(Restrictions.like("examen.nombre", "%" + nombre + "%"),
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
  
    /**
     * Este método sirve para obtener exámenes por el nombre ingresado y el curso
     * seleccionado obteniendo todos los exámenes que coincidan con el nombre y el
     * curso
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * @param nombre el nombre o parte del nombre del examen utilizado como filtro
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerTodosPorCursoYNombre(CursoDTO curso,
            String nombre) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenDTO> examenes;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los exámenes que coincidan con el nombre y el curso,
            //sin sus relaciones (claves)
            Criteria c = s.createCriteria(ExamenDTO.class, "examen")
                    .createAlias("examen.curso", "curso")
                    .add(Restrictions.and(Restrictions.eq("curso.nombre", curso.getNombre()),
                            Restrictions.like("examen.nombre", "%" + nombre + "%")));
            
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
    
    /**
     * Este método sirve para obtener exámenes por el nombre ingresado y el curso
     * seleccionado obteniendo sólo los exámenes públicos o hechos por el 
     * maestro ingresado, y que coincidan con el nombre ingresado y el curso
     * seleccionado
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * @param nombre el nombre o parte del nombre del examen utilizado como filtro
     * 
     * @param maestro Sirve para filtrar la búsqueda por el
     * autor del examen. Esta consulta regresa los exámenes que coincidan con el
     * nombre y el curso y que además son públicos o hechos por el maestro.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerPublicosPorCursoYNombre(CursoDTO curso,
            String nombre, UsuarioDTO maestro) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenDTO> examenes;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los exámenes que coincidan con el curso y el nombre,
            //sin sus relaciones (claves)
            //Aquellos examenes que sean (publicos o del maestro) y que
            //pertenezcan al curso y coincidan con el nombre
            Criteria c = s.createCriteria(ExamenDTO.class, "examen")
                    .createAlias("examen.curso", "curso")
                    .add(Restrictions.and(Restrictions.and(Restrictions.eq("curso.nombre", curso.getNombre()),
                            Restrictions.like("examen.nombre", "%" + nombre + "%")),
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