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

import java.util.List;
import modelo.dto.ExamenDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Esta clase es un dao de ReactivoDTO para los métodos específicos a este
 * objeto dto, proporciona la funcionalidad necesaria accediendo a la base de
 * datos
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class ReactivoDAO extends BaseDAO<ReactivoDTO, Integer> {
    
    /**
     * Este atributo contiene el query necesario para obtener todos los exámenes
     * que utilicen el reactivo especificado
     */
    private final static String GET_EXAMENES_DE_REACTIVO = "SELECT ce.examen FROM "
            + "ClaveExamenDTO AS ce WHERE :reactivo IN ELEMENTS(ce.reactivos)";
    
    /**
     * Obtiene el reactivo completo al que pertenece el id ingresado
     * 
     * @param idReactivo el id del reactivo a obtener
     * @return el objeto ReactivoDTO completo, con todas sus relaciones, o null
     * en caso de que no exista
     */
    public ReactivoDTO obtener(int idReactivo) {
        
        Session s = getSession();
        Transaction tx = null;
        ReactivoDTO reactivo;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene el reactivo específico a ese id, incluyendo sus opciones
            Criteria c = s.createCriteria(ReactivoDTO.class)
                    .add(Restrictions.idEq(idReactivo))
                    .setFetchMode("opcionesIncorrectas", FetchMode.JOIN);
            
            reactivo = (ReactivoDTO) c.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            reactivo = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return reactivo;
    }
  
    /**
     * Obtiene todos los reactivos del tema ingresado
     * 
     * @param tema el objeto TemaDTO del que se quieren obtener los reactivos
     * @return una lista de ReactivoDTO del tema ingresado, o null en caso de que
     * no exista ningún reactivo.
     * 
     */
    public List<ReactivoDTO> obtenerTodosPorTema(TemaDTO tema) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ReactivoDTO> reactivos;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            //cumpliendo con la restricción de que contengan el tema especificado
            Criteria c = s.createCriteria(ReactivoDTO.class, "reactivo")
                    .createAlias("reactivo.tema", "tema")
                    .add(Restrictions.eq("tema.nombre", tema.getNombre()));
            
            reactivos = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            reactivos = null;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return reactivos;
    }
  
    /**
     * Elimina la lista de reactivos ingresada de la persistencia, siempre y
     * cuando estos reactivos no sean referenciados por ningún examen, de lo
     * contrario sólo se podrán eliminar aquellos reactivos que no sean
     * referenciados por ningún examen.
     * 
     * @param reactivos la lista de ReactivoDTO que se desea eliminar
     * @return true si la operación se realizó exitosamente y todos los
     * reactivos se eliminaron o false en caso de que exista por lo menos un
     * reactivo que no se pudo eliminar por ser referenciado por algún examen
     */
    public boolean eliminar(List<ReactivoDTO> reactivos) {
        
        Session s = getSession();
        Transaction tx = null;
        boolean ok = true;
        int reactivosEliminados = 0;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return false;
        }
        
        try {
            tx = s.beginTransaction();
            //Eliminar uno por uno los reactivos de la lista
            for (ReactivoDTO reactivo : reactivos) {
                //Verificar si existen referencias a este reactivo antes de
                //eliminarlo
                List<ExamenDTO> referencias = obtenerReferencias(reactivo);
                
                if (referencias == null || referencias.isEmpty()) {
                    s.delete(reactivo);
                    reactivosEliminados++;
                    
                    //Para limpiar la memoria y que los cambios sean inmediatos
                    if(reactivosEliminados % 20 == 0) {
                        s.flush();
                        s.clear();
                    }
                }
                else {
                    ok = false;
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
    
    /**
     * Este método regresa una lista de exámenes, los cuales contienen en alguna
     * de sus claves el reactivo enviado como parámetro
     * 
     * @param reactivo el objeto ReactivoDTO que se buscará en las claves de los
     * exámenes
     * @return una lista de ExamenDTO con las referencias de este reactivo, una
     * lista vacía en caso de no existir ningua referencia o null en caso de
     * ocurrir un error
     */
    private List<ExamenDTO> obtenerReferencias(ReactivoDTO reactivo) {
        
        Session s = getSession();
        Transaction tx = null;
        List<ExamenDTO> examenes;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }

        try {
            tx = s.beginTransaction();
            
            Query q = s.createQuery(GET_EXAMENES_DE_REACTIVO)
                    .setEntity("reactivo", reactivo);
            
            examenes = q.list();
            
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