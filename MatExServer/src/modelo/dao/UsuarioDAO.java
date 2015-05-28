/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández, Alfredo Rouse Madrigal
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
import modelo.dto.UsuarioDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import modelo.dto.UsuarioDTO.Tipo;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

/**
 * Esta clase es un dao de UsuarioDTO para los métodos específicos a este objeto
 * dto, proporciona la funcionalidad necesaria accediendo a la base de datos
 *
 * @author Fernando Enrique Avendaño Hernández, Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class UsuarioDAO extends BaseDAO<UsuarioDTO, Integer> {

    /**
     * Esta cadena es un query en hql que regresa los alumnos que no pertenecen
     * a un grupo por su apellido paterno.
     */
    private final String GET_ALUMNOS_SIN_ASIGNAR = "SELECT DISTINCT a2 FROM "
            + "UsuarioDTO AS a2 WHERE a2 NOT IN"
            + "(SELECT ELEMENTS(g.alumnos) FROM GrupoDTO AS g) and a2.tipo = "
            + "'Alumno' and a2.apellidoPaterno like ?";

    /**
     * Esta cadena es un query en hql que regresa los alumnos que no pertenecen
     * a un grupo por su apellido materno.
     */
    private final String GET_ALUMNOS_SIN_ASIGNAR_APPM = "SELECT DISTINCT a2 FROM "
            + "UsuarioDTO AS a2 WHERE a2 NOT IN"
            + "(SELECT ELEMENTS(g.alumnos) FROM GrupoDTO AS g) and a2.tipo = "
            + "'Alumno' and a2.apellidoMaterno like ?";

    /**
     * Esta cadena es un query en hql que regresa los alumnos que no pertenecen
     * a un grupo por su nombre.
     */
    private final String GET_ALUMNOS_SIN_ASIGNAR_NOM = "SELECT DISTINCT a2 FROM "
            + "UsuarioDTO AS a2 WHERE a2 NOT IN"
            + "(SELECT ELEMENTS(g.alumnos) FROM GrupoDTO AS g) and a2.tipo = "
            + "'Alumno' and a2.nombre like ?";

    /**
     * Obtiene todos los usuarios que concuerden con el parametro
     *
     * @param nombre El patron por el cual se buscaran los usuarios
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerUsuariosPorNombreOApellidos(String nombre) {
        Session s = getSession();
        Transaction tx = null;
        List<UsuarioDTO> usuarios;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            usuarios = s.createCriteria(UsuarioDTO.class)
                    .add(Restrictions.or(
                                    Restrictions.like("apellidoPaterno", "%" + nombre + "%"),
                                    Restrictions.like("apellidoMaterno", "%" + nombre + "%"),
                                    Restrictions.like("nombre", "%" + nombre + "%")))
                    .addOrder(Order.asc("apellidoPaterno"))
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            usuarios = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        return usuarios;
    }

    /**
     * Obtiene todos los usuarios que concuerden con los parametros
     *
     * @param apellido El patron por el cual se buscaran los usuarios
     * @param tipo el tipo de usuario a buscar
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellido, Tipo tipo) {
        Session s = getSession();
        Transaction tx = null;
        List<UsuarioDTO> usuarios;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos que concuenrden con el apellido

            usuarios = s.createCriteria(UsuarioDTO.class)
                    .add(Restrictions.and(
                                    Restrictions.like("apellidoPaterno", "%" + apellido + "%"),
                                    Restrictions.eq("tipo", tipo)))
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            usuarios = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        return usuarios;
    }

    /**
     * Obtiene todos los usuarios que concuerden con los parametros
     *
     * @param apellidoMaterno El patron por el cual se buscaran los usuarios
     * @param tipo el tipo de usuario a buscar
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerUsuariosPorApellidoM(String apellidoMaterno, Tipo tipo) {
        Session s = getSession();
        Transaction tx = null;
        List<UsuarioDTO> usuarios;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos que concuenrden con el apellido

            usuarios = s.createCriteria(UsuarioDTO.class)
                    .add(Restrictions.and(
                                    Restrictions.like("apellidoMaterno", "%" + apellidoMaterno + "%"),
                                    Restrictions.eq("tipo", tipo)))
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            usuarios = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        return usuarios;
    }

    /**
     * Obtiene todos los usuarios que concuerden con los parametros
     *
     * @param nombre El patron por el cual se buscaran los usuarios
     * @param tipo el tipo de usuario a buscar
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerUsuariosPorNombre(String nombre, Tipo tipo) {
        Session s = getSession();
        Transaction tx = null;
        List<UsuarioDTO> usuarios;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos que concuenrden con el apellido

            usuarios = s.createCriteria(UsuarioDTO.class)
                    .add(Restrictions.and(
                                    Restrictions.like("nombre", "%" + nombre + "%"),
                                    Restrictions.eq("tipo", tipo)))
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            usuarios = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        return usuarios;
    }

    /**
     * Obtiene todos los usuarios tipo alumno que concuerden con el parametro
     *
     * @param apellido El patron por el cual se buscaran los usuarios
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerAlumnosPorApellido(String apellido) {
        Session s = getSession();
        Transaction tx = null;
        List<UsuarioDTO> usuarios;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos que concuenrden con el apellido
            Query q = s.createQuery(GET_ALUMNOS_SIN_ASIGNAR);
            q.setString(0, "%" + apellido + "%");
            usuarios = q.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            usuarios = null;
        } finally {
            //s.close();
            System.out.println("Session cerrada");
        }
        return usuarios;
    }

    /**
     * Obtiene todos los usuarios tipo alumno que concuerden con el parametro
     *
     * @param apellidoMaterno El patron por el cual se buscaran los usuarios
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerAlumnosPorApellidoM(String apellidoMaterno) {
        Session s = getSession();
        Transaction tx = null;
        List<UsuarioDTO> usuarios;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos que concuenrden con el apellido
            Query q = s.createQuery(GET_ALUMNOS_SIN_ASIGNAR_APPM);
            q.setString(0, "%" + apellidoMaterno + "%");
            usuarios = q.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            usuarios = null;
        } finally {
            //s.close();
            System.out.println("Session cerrada");
        }
        return usuarios;
    }

    /**
     * Obtiene todos los usuarios tipo alumno que concuerden con el parametro
     *
     * @param nombre El patron por el cual se buscaran los usuarios
     * @return Lista de UsuarioDTO que concuerden con el nombre ingresado, o
     * null en caso de que ningun usuario concuerde
     */
    public List<UsuarioDTO> obtenerAlumnosPorNombre(String nombre) {
        Session s = getSession();
        Transaction tx = null;
        List<UsuarioDTO> usuarios;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos que concuenrden con el apellido
            Query q = s.createQuery(GET_ALUMNOS_SIN_ASIGNAR_NOM);
            q.setString(0, "%" + nombre + "%");
            usuarios = q.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            usuarios = null;
        } finally {
            //s.close();
            System.out.println("Session cerrada");
        }
        return usuarios;
    }

    /**
     * Obtiene el usuario completo que concuerde con su nombre de usuario.
     *
     * @param unUsuario El nombre de usuario a obtener.
     * @return el objeto UsuarioDTO completo, con todas sus relaciones, o null
     * en caso de que no exista
     */
    public UsuarioDTO obtener(String unUsuario) {
        Session s = getSession();
        Transaction tx = null;
        UsuarioDTO usuario;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos que concuenrden con el apellido

            usuario = (UsuarioDTO) s.createCriteria(UsuarioDTO.class)
                    .add(Restrictions.eq("usuario", unUsuario)).uniqueResult();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            usuario = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        return usuario;
    }
}
