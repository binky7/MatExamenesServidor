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
package modelo.dto;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Esta clase almacena los datos de la llave primaria del examen asignado.
 * Esto es el id del examen y el id del alumno que forman la relación del examen
 * asignado
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
@Embeddable
public class ExamenAsignadoPK implements Serializable {

    /**
     * El id del examen que es asignado al alumno
     */
    private int idExamen;
    /**
     * El id del alumno al que se le asigna el examen
     */
    private int idAlumno;

    /**
     * Constructor por defecto. Crea una nueva instancia de ExamenAsignadoPK
     * sin inicializar los atributos
     */
    public ExamenAsignadoPK() {
    }

    /**
     * Crea una nueva instancia de ExamenAsignadoPK con el idExamen y el idAlumno
     * inicializados.
     * 
     * @param idExamen el id del objeto ExamenDTO
     * @param idAlumno el id del objeto UsuarioDTO
     */
    public ExamenAsignadoPK(int idExamen, int idAlumno) {
        this.idExamen = idExamen;
        this.idAlumno = idAlumno;
    }
    /**
     * @return el id del examen
     */
    public int getIdExamen() {
        return idExamen;
    }

    /**
     * @param idExamen el id del examen a guardar
     */
    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    /**
     * @return el id del alumno
     */
    public int getIdAlumno() {
        return idAlumno;
    }

    /**
     * @param idAlumno el id del alumno a guardar
     */
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    /**
     * Este método sobreescribe el método hashCode para que el objeto pueda ser
     * comparable como una llave primaria única. Al ser una llave primaria
     * compuesta su identidad se determina con la combinación de todos
     * los atributos que compongan esta clave.
     * 
     * @return el código hash que identifica únicamente a esta llave compuesta.
     */
    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 0;
        result += prime * idExamen;
        result += prime * idAlumno;
        
        return result;
    }
 
    /**
     * Este método sobreescribe el método equals para que el objeto pueda ser
     * comparable como una llave primaria única. Al ser una llave primaria
     * compuesta su identidad se determina con la combinación de todos
     * los atributos que compongan esta clave.
     * 
     * @param obj el objeto que debe ser ExamenAsignadoPK a comparar con esta
     * llave compuesta
     * 
     * @return true si la llave compuesta ingresada es igual a esta llave compuesta
     * false de lo contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ExamenAsignadoPK)) {
            return false;
        }
        ExamenAsignadoPK other = (ExamenAsignadoPK) obj;
        
        return (idExamen == other.getIdExamen() 
                && idAlumno == other.getIdAlumno());
    }
}