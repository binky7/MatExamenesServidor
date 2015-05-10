/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dto.UsuarioDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import modelo.dto.UsuarioDTO.Tipo;
import org.hibernate.Query;

/**
 *
 * @author Jesus Donaldo
 */
public class UsuarioDAO extends BaseDAO<UsuarioDTO, Integer> {

    //Obtener todos los alumnos que no pertenecen a un grupo....
    private final String GET_ALUMNOS_SIN_ASIGNAR = "SELECT DISTINCT a2 FROM "
            + "UsuarioDTO AS a2 WHERE a2 NOT IN"
            + "(SELECT ELEMENTS(g.alumnos) FROM GrupoDTO AS g) and a2.tipo = "
            + "'Alumno' and a2.apellidoPaterno like ?";

    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellido) {
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
                    .add(Restrictions.like("apellidoPaterno", "%" + apellido + "%"))
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

    public UsuarioDTO obtener(String usuario) {
        Session s = getSession();
        Transaction tx = null;
        UsuarioDTO _usuario;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos que concuenrden con el apellido

            _usuario = (UsuarioDTO) s.createCriteria(UsuarioDTO.class)
                    .add(Restrictions.eq("usuario", usuario)).uniqueResult();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            _usuario = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        return _usuario;
    }
}
