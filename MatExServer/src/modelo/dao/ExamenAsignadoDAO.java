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
import modelo.dto.ReactivoAsignadoDTO;
import modelo.dto.UsuarioDTO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class ExamenAsignadoDAO extends BaseDAO<ExamenAsignadoDTO, ExamenAsignadoPK> {
    
    
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
}