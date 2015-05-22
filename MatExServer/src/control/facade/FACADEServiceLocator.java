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
package control.facade;

/**
 * Esta clase se encarga de implementar el patrón Singleton para asegurarse de
 * sólo tener una instancia de cada tipo en la aplicación, contiene todos los
 * tipos de facades de la aplicación.
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class FACADEServiceLocator {

    /**
     * Facade de Curso
     */
    private static CursoFACADE cursoFACADE;
    /**
     * Facade de Tema
     */
    private static TemaFACADE temaFACADE;
    /**
     * Facade Base
     */
    private static BaseFACADE baseFACADE;
    /**
     * Facade de Usuario
     */
    private static UsuarioFACADE usuarioFACADE;
    /**
     * Facade de Reactivo
     */
    private static ReactivoFACADE reactivoFACADE;
    /**
     * Facade de Examen
     */
    private static ExamenFACADE examenFACADE;
    /**
     * Facade de Examen Asignado
     */
    private static ExamenAsignadoFACADE examenAsignadoFACADE;
    /**
     * Facade de Grupo
     */
    private static GrupoFACADE grupoFACADE;

    /**
     * Obtiene la instancia única del CursoFACADE
     * @return la única instancia del CursoFACADE
     */
    public static CursoFACADE getCursoFACADE() {
        if (cursoFACADE == null) {
            cursoFACADE = new CursoFACADE();
        }

        return cursoFACADE;
    }

    /**
     * Obtiene la instancia única del TemaFACADE
     * @return la única instancia del TemaFACADE
     */
    public static TemaFACADE getTemaFACADE() {
        if (temaFACADE == null) {
            temaFACADE = new TemaFACADE();
        }

        return temaFACADE;
    }

    /**
     * Obtiene la instancia única del UsuarioFACADE
     * @return la única instancia del UsuarioFACADE
     */
    public static UsuarioFACADE getUsuarioFACADE() {
        if (usuarioFACADE == null) {
            usuarioFACADE = new UsuarioFACADE();
        }

        return usuarioFACADE;
    }

    /**
     * Obtiene la instancia única del ReactivoFACADE
     * @return la única instancia del ReactivoFACADE
     */
    public static ReactivoFACADE getReactivoFACADE() {
        if (reactivoFACADE == null) {
            reactivoFACADE = new ReactivoFACADE();
        }

        return reactivoFACADE;
    }

    /**
     * Obtiene la instancia única del ExamenFACADE
     * @return la única instancia del ExamenFACADE
     */
    public static ExamenFACADE getExamenFACADE() {
        if (examenFACADE == null) {
            examenFACADE = new ExamenFACADE();
        }

        return examenFACADE;
    }

    /**
     * Obtiene la instancia única del BaseFACADE
     * @return la única instancia del BaseFACADE
     */
    public static BaseFACADE getBaseFACADE() {
        if (baseFACADE == null) {
            baseFACADE = new BaseFACADE();
        }

        return baseFACADE;
    }

    /**
     * Obtiene la instancia única del GrupoFACADE
     * @return la única instancia del GrupoFACADE
     */
    public static GrupoFACADE getGrupoFACADE() {
        if (grupoFACADE == null) {
            grupoFACADE = new GrupoFACADE();
        }
        return grupoFACADE;
    }
    
    /**
     * Obtiene la instancia única del ExamenAsignadoFACADE
     * @return la única instancia del ExamenAsignadoFACADE
     */
    public static ExamenAsignadoFACADE getExamenAsignadoFACADE() {
        if (examenAsignadoFACADE == null) {
            examenAsignadoFACADE = new ExamenAsignadoFACADE();
        }
        return examenAsignadoFACADE;
    }

}