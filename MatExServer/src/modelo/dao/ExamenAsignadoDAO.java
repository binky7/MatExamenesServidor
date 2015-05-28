/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
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

import java.util.Date;
import java.util.List;
import modelo.dto.ClaveExamenDTO;
import modelo.dto.ClaveExamenPK;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.ReactivoAsignadoDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.UsuarioDTO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Esta clase es un dao de ExamenAsignadoDTO para los métodos específicos a este
 * objeto dto, proporciona la funcionalidad necesaria accediendo a la base de
 * datos
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class ExamenAsignadoDAO extends BaseDAO<ExamenAsignadoDTO, ExamenAsignadoPK> {

    /**
     * Esta cadena es un query en hql que regresa el promedio de todo el grupo
     * ingresado en el examen ingresado.
     */
    private static final String GET_PROMEDIOS_GRUPO = "SELECT AVG(ea.calificacion) "
            + "FROM ExamenAsignadoDTO AS ea, GrupoDTO as g "
            + "WHERE (g = :grupo AND ea.alumno IN ELEMENTS(g.alumnos)) "
            + "AND ea.examen = :examen AND ea.calificacion <> -1 GROUP BY g";

    /**
     * Esta cadena es un query en hql que regresa el promedio de todo el grado
     * ingresado en el examen ingresado.
     */
    private static final String GET_PROMEDIOS_GRADO = "SELECT AVG(ea.calificacion) "
            + "FROM ExamenAsignadoDTO AS ea, GrupoDTO as g "
            + "WHERE (g.grado = :grado AND ea.alumno IN ELEMENTS(g.alumnos)) "
            + "AND ea.examen = :examen AND ea.calificacion <> -1 GROUP BY g.grado";

    /**
     * Esta cadena es un query en hql que regresa el promedio de todo el turno
     * ingresado en el examen ingresado.
     */
    private static final String GET_PROMEDIOS_TURNO = "SELECT AVG(ea.calificacion) "
            + "FROM ExamenAsignadoDTO AS ea, GrupoDTO as g "
            + "WHERE (g.turno = :turno AND ea.alumno IN ELEMENTS(g.alumnos)) "
            + "AND ea.examen = :examen AND ea.calificacion <> -1 GROUP BY g.turno";

    /**
     * Inserta la lista de exámenes asignados y los vuelve persitentes
     *
     * @param examenes la lista de ExamenAsignadoDTO a ser persistida
     *
     * @return true en caso de que la operación se realice correctamente o false
     * en caso contrario
     */
    public boolean insertar(List<ExamenAsignadoDTO> examenes) {

        Session s = getSession();
        Transaction tx = null;
        boolean ok = true;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return false;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            for (int i = 0; i < examenes.size(); i++) {
                ExamenAsignadoDTO examen = examenes.get(i);
                //Buscar si ya hay un examen asignado en la base de datos
                ExamenAsignadoDTO examenBD = (ExamenAsignadoDTO) s
                        .get(ExamenAsignadoDTO.class,
                                new ExamenAsignadoPK(examen.getExamen().getId(),
                                        examen.getAlumno().getId()));

                if (examenBD == null) {
                    //No existe asignación
                    examen.setFechaAsignacion(new Date());
                    s.save(examen);
                } else {
                    //Existe una asignación
                    //Remover objeto antigüo
                    s.delete(examenBD);

                    examen.setFechaAsignacion(new Date());
                    //Actualizar la información
                    s.save(examen);
                }

                //Guardar los cambios en la base de datos cada 20 inserciones
                if (i % 20 == 0) {
                    s.flush();
                    s.clear();
                }
            }

            tx.commit();
        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } catch (Exception e) {
            System.out.println("Exepciòn" + e);
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

    /**
     * Para obtener el examen que se quiere utilizar en Asignar examenes,
     * devuelve el examen únicamente con la lista de claves
     *
     * @param idExamen el id del examen seleccionado
     * @return el objeto examen con su lista de claves
     */
    public ExamenDTO obtenerExamen(int idExamen) {
        Session s = getSession();
        Transaction tx = null;
        ExamenDTO examen = null;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            //Obtener examen con la lista de claves inicializada
            Criteria c = s.createCriteria(ExamenDTO.class)
                    .add(Restrictions.idEq(idExamen))
                    .setFetchMode("claves", FetchMode.JOIN);

            examen = (ExamenDTO) c.uniqueResult();

            tx.commit();

        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
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
     * Regresa la clave seleccionada con sus reactivos y cada reactivo con sus
     * opciones, para que se puedan asignar los reactivos al ExamenAsignado
     *
     * @param idClave el objeto ClaveExamenPK que representa el id de la clave
     * seleccionada
     * @return el objeto ClaveExamenDTO que corresponde al idClave ingresado
     */
    public ClaveExamenDTO obtenerClaveExamen(ClaveExamenPK idClave) {
        Session s = getSession();
        Transaction tx = null;
        ClaveExamenDTO claveExamen = null;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            Criteria c = s.createCriteria(ClaveExamenDTO.class)
                    .add(Restrictions.idEq(idClave))
                    .setFetchMode("reactivos", FetchMode.JOIN);

            claveExamen = (ClaveExamenDTO) c.uniqueResult();

            //Inicializar las opciones del reactivo
            for (ReactivoDTO reactivo : claveExamen.getReactivos()) {
                Hibernate.initialize(reactivo.getOpcionesIncorrectas());
            }

            tx.commit();

        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            claveExamen = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }

        return claveExamen;
    }

    /**
     * Obtiene todos los examenes que han sido asignados (pero no contestados) a
     * un alumno, tambien este método valida que no haya examenes almacenados
     * que el alumno nunca contestó y los cuales ya no son válidos porque el
     * lapso para ser contestados ha pasado
     *
     * @param alumno el objeto usuario que representa al alumno, del que se
     * quiere obtener sus exámenes asignados sin contestar
     *
     * @return una lista de exámenes asignados vigentes para ser contestados por
     * el alumno
     */
    public List<ExamenAsignadoDTO> obtenerAsignados(UsuarioDTO alumno) {
        Session s = getSession();
        Transaction tx = null;
        List<ExamenAsignadoDTO> examenes = null;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            //Obtener los exámenes del alumno que aún no tengan calificación
            Criteria c = s.createCriteria(ExamenAsignadoDTO.class, "examen")
                    .add(Restrictions.and(
                                    Restrictions.eq("examen.alumno", alumno),
                                    Restrictions.eq("examen.calificacion", -1.0)
                            ));

            examenes = c.list();

            for (int i = 0; i < examenes.size(); i++) {
                ExamenAsignadoDTO examen = examenes.get(i);

                Date fechaAsignacion = examen.getFechaAsignacion();
                //Transformar los minutos a milisegundos
                long tiempoLimite = examen.getTiempo() * 60 * 1000;
                long hoy = System.currentTimeMillis();

                //Si ya pasó el periodo para contestar el examen
                if (hoy >= (fechaAsignacion.getTime() + tiempoLimite)) {
                    //Ya no sirve tener un examen que nunca se contestó
                    s.delete(examen);

                    examenes.remove(i);
                    i--;
                }
            }

            tx.commit();

        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
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
     * Obtiene un objeto examen asignado a partir de su id, el objeto contiene
     * todos los reactivos y las opciones del reactivo de ese examen, para poder
     * ser manipulados en Contestar Examen
     *
     * @param id el objeto ExamenAsignadoPK que representa el id del
     * examenAsignado
     * @return el objeto ExamenAsignado que esta asociado al id ingresado
     */
    public ExamenAsignadoDTO obtenerAsignado(ExamenAsignadoPK id) {
        Session s = getSession();
        Transaction tx = null;
        ExamenAsignadoDTO examen = null;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            Criteria c = s.createCriteria(ExamenAsignadoDTO.class, "examen")
                    .add(Restrictions.idEq(id))
                    .setFetchMode("examen.reactivos", FetchMode.JOIN);

            examen = (ExamenAsignadoDTO) c.uniqueResult();

            //Inicializar opciones
            for (ReactivoAsignadoDTO reactivo : examen.getReactivos()) {
                Hibernate.initialize(reactivo.getOpcionesIncorrectas());
            }

            tx.commit();

        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
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
     * Obtiene una lista de examenes contestados de un alumno filtrandose por el
     * curso ingresado
     *
     * @param alumno el objeto alumno que representa al alumno del cual se
     * quiere obtener los examenes contestados
     * @param curso el objeto curso el cual sera el filtro para obtener los
     * examenes contestados
     * @return lista de examenes contestados
     */
    public List<ExamenAsignadoDTO> obtenerContestados(UsuarioDTO alumno,
            CursoDTO curso) {

        Session s = getSession();
        Transaction tx = null;
        List<ExamenAsignadoDTO> examenes = null;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            //Obtener los exámenes contestados de un alumno en el curso ingresado
            Criteria c = s.createCriteria(ExamenAsignadoDTO.class, "examen")
                    .createAlias("examen.examen.curso", "curso")
                    .add(Restrictions.and(
                                    Restrictions.eq("examen.alumno", alumno),
                                    Restrictions.eq("curso.nombre", curso.getNombre()),
                                    Restrictions.ne("examen.calificacion", -1.0)
                            ));

            examenes = c.list();

            tx.commit();

        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
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
     * Obtiene un examen contestado asociado al id ingresado, el cual contiene
     * los reactivos del examen contestado.
     *
     * @param id el objeto ExamenAsignadoPK que representa el id del examen
     * asignado
     * @return un objeto ExamenAsignado que representa el examen contestado
     * asignado al id ingresado.
     */
    public ExamenAsignadoDTO obtenerContestado(ExamenAsignadoPK id) {
        Session s = getSession();
        Transaction tx = null;
        ExamenAsignadoDTO examen = null;

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            Criteria c = s.createCriteria(ExamenAsignadoDTO.class, "examen")
                    .add(Restrictions.idEq(id));

            examen = (ExamenAsignadoDTO) c.uniqueResult();
            //Inicializa los reactivos del examen asignado obtenido
            Hibernate.initialize(examen.getReactivos());
            tx.commit();

        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
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
     * Obtiene una lista de promedios de todos los grupos por el examen
     * ingresado
     *
     * @param examen el examen del que se quiere obtener los promedios
     * @param grupos la lista de grupos para obtener sus promedios
     *
     * @return una lista de objetos que contiene valores reales que representan
     * los promedios de los grupos en el examen ingresado
     */
    public Object[] obtenerPromediosPorGrupo(ExamenDTO examen,
            List<GrupoDTO> grupos) {

        Session s = getSession();
        Transaction tx = null;
        Object[] promedios = new Object[grupos.size()];

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            int i = 0;
            //Por cada grupo en la lista, obtener el promedio de todos los alumnos
            //en el examen ingresado.
            for (GrupoDTO grupo : grupos) {
                Query q = s.createQuery(GET_PROMEDIOS_GRUPO)
                        .setEntity("grupo", grupo)
                        .setEntity("examen", examen);

                promedios[i] = q.uniqueResult();
                i++;
            }

            tx.commit();

        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            promedios = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }

        return promedios;
    }

    /**
     * Obtiene los promedios de los 3 grados por el examen ingresado
     *
     * @param examen el examen del que se quiere obtener los promedios
     * @return una lista de objetos que contiene numeros reales que representan
     * los promedios por los 3 grados en el examen seleccionado.
     */
    public Object[] obtenerPromediosPorGrado(ExamenDTO examen) {
        Session s = getSession();
        Transaction tx = null;
        //Promedios de los 3 grados
        Object[] promedios = new Object[3];

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            //Obtener el promedio por cada grado
            for (int i = 0; i < 3; i++) {
                Query q = s.createQuery(GET_PROMEDIOS_GRADO)
                        .setParameter("grado", i + 1)
                        .setEntity("examen", examen);

                promedios[i] = q.uniqueResult();
            }

            tx.commit();

        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            promedios = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }

        return promedios;
    }

    /**
     * Obtiene los promedios por los dos turnos en el examen ingresado
     *
     * @param examen el examen del cual se quiere obtener los promedios
     * @return una lista de objetos que contiene valores reales que representan
     * los promedios de los turnos en el examen seleccionado
     */
    public Object[] obtenerPromediosPorTurno(ExamenDTO examen) {
        Session s = getSession();
        Transaction tx = null;
        //Promedios por turno
        Object[] promedios = new Object[2];

        if (s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();

            //Obtener el promedio por cada turno
            for (int i = 0; i < 2; i++) {
                GrupoDTO.Turno turno;

                if (i == 0) {
                    turno = GrupoDTO.Turno.M;
                } else {
                    turno = GrupoDTO.Turno.V;
                }

                Query q = s.createQuery(GET_PROMEDIOS_TURNO)
                        .setString("turno", turno.toString())
                        .setEntity("examen", examen);

                promedios[i] = q.uniqueResult();
            }

            tx.commit();

        } catch (StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            promedios = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }

        return promedios;
    }

    /**
     * Obtiene el tiempo actual en el servidor.
     *
     * @return El tiempo transcurrido en milisegundos.
     */
    public long getTiempoActual() {
        return System.currentTimeMillis();
    }
}
