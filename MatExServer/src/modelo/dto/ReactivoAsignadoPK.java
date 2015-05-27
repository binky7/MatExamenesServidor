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
package modelo.dto;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Esta clase almacena los datos de la llave primaria del reactivo asignado.
 * Esto es el id del examen asignado y el id del reactivo que forman la relación
 * del reactivo asignado
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
@Embeddable
public class ReactivoAsignadoPK implements Serializable {
    
    /**
     * La llave compuesta del Examen Asignado al que pertenece este reactivo
     * asignado
     */
    private ExamenAsignadoPK idExamenAsignado;
    /**
     * Identificador del reactivo como único dentro del examen asignado
     */
    private int idReactivo;

    /**
     * @return la llave primaria del examen asignado al que pertenece el reactivo
     */
    public ExamenAsignadoPK getIdExamenAsignado() {
        return idExamenAsignado;
    }

    /**
     * @param idExamenAsignado la llave primaria del examen asignado a almacenar
     */
    public void setIdExamenAsignado(ExamenAsignadoPK idExamenAsignado) {
        this.idExamenAsignado = idExamenAsignado;
    }
    
    /**
     * @return el identificador del reactivo
     */
    public int getIdReactivo() {
        return idReactivo;
    }

    /**
     * @param idReactivo el identificador a almacenar
     */
    public void setIdReactivo(int idReactivo) {
        this.idReactivo = idReactivo;
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
        result += prime * idExamenAsignado.hashCode();
        result += prime * idReactivo;
        
        return result;
    }
 
    /**
     * Este método sobreescribe el método equals para que el objeto pueda ser
     * comparable como una llave primaria única. Al ser una llave primaria
     * compuesta su identidad se determina con la combinación de todos
     * los atributos que compongan esta clave.
     * 
     * @param obj el objeto que debe ser ReactivoAsignadoPK a comparar con esta
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
        if (!(obj instanceof ReactivoAsignadoPK)) {
            return false;
        }
        ReactivoAsignadoPK other = (ReactivoAsignadoPK) obj;
        
        return (idExamenAsignado.equals(other.getIdExamenAsignado())
                && idReactivo == other.getIdReactivo());
    }

}