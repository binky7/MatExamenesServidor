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
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
                    .setFetchMode("opciones", FetchMode.JOIN);
            
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
     * Elimina la lista de reactivos ingresada de la persistencia
     * 
     * @param reactivos la lista de ReactivoDTO que se desea eliminar
     * @return true si la operación se realizó exitosamente, false si ocurrió
     * un error
     */
    public boolean eliminar(List<ReactivoDTO> reactivos) {
        
        Session s = getSession();
        Transaction tx = null;
        boolean ok = true;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return false;
        }
        
        try {
            tx = s.beginTransaction();
            //Eliminar uno por uno los reactivos de la lista
            for (int i = 0; i < reactivos.size(); i++) {
                ReactivoDTO reactivo = reactivos.get(i);
                
                s.delete(reactivo);
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
}