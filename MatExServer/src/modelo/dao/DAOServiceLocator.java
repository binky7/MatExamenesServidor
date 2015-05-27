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

/**
 * Esta clase se encarga de implementar el patrón Singleton para asegurarse de
 * sólo tener una instancia de cada tipo en la aplicación, contiene todos los
 * tipos de daos de la aplicación.
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class DAOServiceLocator {
    
    /**
     * Dao de Curso
     */
    private static CursoDAO cursoDAO;
    /**
     * Dao de Examen
     */
    private static ExamenDAO examenDAO;
    /**
     * Dao de Examen Asignado
     */
    private static ExamenAsignadoDAO examenAsignadoDAO;
    /**
     * Dao de Grupo
     */
    private static GrupoDAO grupoDAO;
    /**
     * Dao de Reactivo
     */
    private static ReactivoDAO reactivoDAO;
    /**
     * Dao de Tema
     */
    private static TemaDAO temaDAO;
    /**
     * Dao de Usuario
     */
    private static UsuarioDAO usuarioDAO;
    /**
     * Dao Base
     */
    private static BaseDAO baseDAO;
    
    /**
     * Obtiene la instancia única del CursoDAO
     * @return la única instancia del CursoDAO
     */
    public static CursoDAO getCursoDAO() {
        if(cursoDAO == null) {
            cursoDAO = new CursoDAO();
        }
        
        return cursoDAO;
    }
    
    /**
     * Obtiene la instancia única del ExamenDAO
     * @return la única instancia del ExamenDAO
     */
    public static ExamenDAO getExamenDAO() {
        if(examenDAO == null) {
            examenDAO = new ExamenDAO();
        }
        
        return examenDAO;
    }
    
    /**
     * Obtiene la instancia única del GrupoDAO
     * @return la única instancia del GrupoDAO
     */
    public static GrupoDAO getGrupoDAO() {
        if(grupoDAO == null) {
            grupoDAO = new GrupoDAO();
        }
        
        return grupoDAO;
    }
    
    /**
     * Obtiene la instancia única del ReactivoDAO
     * @return la única instancia del ReactivoDAO
     */
    public static ReactivoDAO getReactivoDAO() {
        if(reactivoDAO == null) {
            reactivoDAO = new ReactivoDAO();
        }
        
        return reactivoDAO;
    }
    
    /**
     * Obtiene la instancia única del TemaDAO
     * @return la única instancia del TemaDAO
     */
    public static TemaDAO getTemaDAO() {
        if(temaDAO == null) {
            temaDAO = new TemaDAO();
        }
        
        return temaDAO;
    }
    
    /**
     * Obtiene la instancia única del UsuarioDAO
     * @return la única instancia del UsuarioDAO
     */
    public static UsuarioDAO getUsuarioDAO() {
        if(usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        
        return usuarioDAO;
    }
    
    /**
     * Obtiene la instancia única del ExamenAsignadoDAO
     * @return la única instancia del ExamenAsignadoDAO
     */
    public static ExamenAsignadoDAO getExamenAsignadoDAO() {
        if(examenAsignadoDAO == null) {
            examenAsignadoDAO = new ExamenAsignadoDAO();
        }
        
        return examenAsignadoDAO;
    }
    
    /**
     * Obtiene la instancia única del BaseDAO
     * @return la única instancia del BaseDAO
     */
    public static BaseDAO getBaseDAO() {
        if(baseDAO == null) {
            baseDAO = new BaseDAO();
        }
        
        return baseDAO;
    }
}