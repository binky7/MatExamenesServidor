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
 * Esta clase almacena los datos de la llave primaria de la clave de examen.
 * Esto es el número de clave y el id del examen al que pertenece
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
@Embeddable
public class ClaveExamenPK implements Serializable {
    /**
     * El número de clave de la clave de examen
     */
    private int clave;
    /**
     * El id del examen al que pertenece la clave de examen
     */
    private int idExamen;

    /**
     * @return el número de clave de la clave de examen
     */
    public int getClave() {
        return clave;
    }

    /**
     * @param clave el número de clave a guardar
     */
    public void setClave(int clave) {
        this.clave = clave;
    }

    /**
     * @return el id del examen al que pertenece esta clave de examen
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
        result += prime * clave;
        result += prime * idExamen;
        
        return result;
    }
 
    /**
     * Este método sobreescribe el método equals para que el objeto pueda ser
     * comparable como una llave primaria única. Al ser una llave primaria
     * compuesta su identidad se determina con la combinación de todos
     * los atributos que compongan esta clave.
     * 
     * @param obj el objeto que debe ser ClaveExamenPK a comparar con esta
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
        if (!(obj instanceof ClaveExamenPK)) {
            return false;
        }
        ClaveExamenPK other = (ClaveExamenPK) obj;
        
        return (clave == other.getClave() && idExamen == other.getIdExamen());
    }
    
}