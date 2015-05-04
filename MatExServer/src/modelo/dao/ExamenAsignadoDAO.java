/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.ReactivoAsignadoDTO;
import modelo.dto.UsuarioDTO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class ExamenAsignadoDAO extends BaseDAO<ExamenAsignadoDTO, ExamenAsignadoPK> {
    
    private static final String GET_PROMEDIOS_GRUPO = "SELECT AVG(ea.calificacion) "
            + "FROM ExamenAsignadoDTO AS ea, GrupoDTO as g " +
    "WHERE (g = :grupo AND ea.alumno IN ELEMENTS(g.alumnos)) " +
    "AND ea.examen = :examen AND ea.calificacion <> -1 GROUP BY g";
    
    private static final String GET_PROMEDIOS_GRADO = "SELECT AVG(ea.calificacion) "
            + "FROM ExamenAsignadoDTO AS ea, GrupoDTO as g " +
    "WHERE (g.grado = :grado AND ea.alumno IN ELEMENTS(g.alumnos)) " +
    "AND ea.examen = :examen AND ea.calificacion <> -1 GROUP BY g.grado";
    
    private static final String GET_PROMEDIOS_TURNO = "SELECT AVG(ea.calificacion) "
            + "FROM ExamenAsignadoDTO AS ea, GrupoDTO as g " +
    "WHERE (g.turno = :turno AND ea.alumno IN ELEMENTS(g.alumnos)) " +
    "AND ea.examen = :examen AND ea.calificacion <> -1 GROUP BY g.turno";
    
    public boolean insertar(List<ExamenAsignadoDTO> examenes) {
        
        Session s = getSession();
        Transaction tx = null;
        boolean ok = true;
        
        if(s == null) {
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
                        .get(ExamenAsignadoDTO.class, examen.getId());
                
                if(examenBD == null) {
                    //No existe asignacion
                    examen.setFechaAsignacion(new Date());
                    s.save(examen);
                }
                else {
                    //Pasar los datos al objeto en la bd
                    examenBD.setTiempo(examen.getTiempo());
                    //Asignar fecha del servidor
                    examenBD.setFechaAsignacion(new Date());
                    examenBD.setCalificacion(examen.getCalificacion());
                    examenBD.setReactivos(new ArrayList<ReactivoAsignadoDTO>());
                    for(ReactivoAsignadoDTO reactivo : examen.getReactivos()) {
                        examenBD.addReactivo(reactivo);
                    }
                    //Actualizar la informacion
                    s.update(examenBD);
                }
                
                if(i % 20 == 0) {
                    s.flush();
                    s.clear();
                }
            }
            
            tx.commit();
        } catch(StaleStateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } catch (Exception e) {
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
    
    public List<ExamenAsignadoDTO> obtenerAsignados(UsuarioDTO alumno) {
        Session s = getSession();
        Transaction tx = null;
        List<ExamenAsignadoDTO> examenes = null;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            
            Criteria c = s.createCriteria(ExamenAsignadoDTO.class, "examen")
                    .add(Restrictions.and(
                            Restrictions.eq("examen.alumno", alumno),
                            Restrictions.eq("examen.calificacion", -1)
                    ));
            
            examenes = c.list();
            
            for(int i = 0; i < examenes.size(); i++) {
                ExamenAsignadoDTO examen = examenes.get(i);
                
                Date fechaAsignacion = examen.getFechaAsignacion();
                //Transformar los minutos a milisegundos
                long tiempoLimite = examen.getTiempo() * 60 * 1000;
                long hoy = System.currentTimeMillis();
                
                //Si ya paso el periodo para contestar el examen
                if(hoy >= (fechaAsignacion.getTime() + tiempoLimite)) {
                    //Ya no sirve tener un examen que nunca se contesto
                    s.delete(examen);
                    
                    examenes.remove(i);
                    i--;
                }
            }
            
            tx.commit();
            
        } catch(StaleStateException ex) {
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
    
    public List<ExamenAsignadoDTO> obtenerContestados(UsuarioDTO alumno,
            CursoDTO curso) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenAsignadoDTO> examenes = null;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            
            Criteria c = s.createCriteria(ExamenAsignadoDTO.class, "examen")
                    .createAlias("examen.examen.curso", "curso")
                    .add(Restrictions.and(
                            Restrictions.eq("examen.alumno", alumno),
                            Restrictions.eq("curso.nombre", curso.getNombre()),
                            Restrictions.ne("examen.calificacion", -1)
                    ));
            
            examenes = c.list();
            
            tx.commit();
            
        } catch(StaleStateException ex) {
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
    
    public ExamenAsignadoDTO obtenerContestado(ExamenAsignadoPK id) {
        Session s = getSession();
        Transaction tx = null;
        ExamenAsignadoDTO examen = null;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            
            Criteria c = s.createCriteria(ExamenAsignadoDTO.class, "examen")
                    .add(Restrictions.idEq(id))
                    .setFetchMode("examen.reactivos", FetchMode.JOIN);
            
            examen = (ExamenAsignadoDTO) c.uniqueResult();
            
            tx.commit();
            
        } catch(StaleStateException ex) {
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
    
    public Object[] obtenerPromediosPorGrupo(ExamenDTO examen,
            List<GrupoDTO> grupos) {
        
        Session s = getSession();
        Transaction tx = null;
        Object[] promedios = new Object[grupos.size()];
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            
            int i = 0;
            for (GrupoDTO grupo : grupos) {
                Query q = s.createQuery(GET_PROMEDIOS_GRUPO)
                        .setEntity("grupo", grupo)
                        .setEntity("examen", examen);

                promedios[i] = q.uniqueResult();
                i++;
            }

            tx.commit();
            
        } catch(StaleStateException ex) {
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
    
    public Object[] obtenerPromediosPorGrado(ExamenDTO examen) {
        Session s = getSession();
        Transaction tx = null;
        //Promedios de los 3 grados
        Object[] promedios = new Object[3];
        
        if(s == null) {
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
            
        } catch(StaleStateException ex) {
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
    
    public Object[] obtenerPromediosPorTurno(ExamenDTO examen) {
        Session s = getSession();
        Transaction tx = null;
        //Promedios por turno
        Object[] promedios = new Object[2];
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            
            //Obtener el promedio por cada turno
            for (int i = 0; i < 2; i++) {
                GrupoDTO.Turno turno;
                
                if(i == 0) {
                    turno = GrupoDTO.Turno.M;
                }
                else {
                    turno = GrupoDTO.Turno.V;
                }
                
                Query q = s.createQuery(GET_PROMEDIOS_TURNO)
                        .setString("turno", turno.toString())
                        .setEntity("examen", examen);

                promedios[i] = q.uniqueResult();
            }

            tx.commit();
            
        } catch(StaleStateException ex) {
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
}