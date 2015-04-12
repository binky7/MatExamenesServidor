/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Jesus Donaldo
 */
@Embeddable
public class ReactivoAsignadoPK implements Serializable {
    
    private ExamenAsignadoPK idExamenAsignado;
    private int idReactivo;

    /**
     * @return the idExamenAsignado
     */
    public ExamenAsignadoPK getIdExamenAsignado() {
        return idExamenAsignado;
    }

    /**
     * @param idExamenAsignado the idExamenAsignado to set
     */
    public void setIdExamenAsignado(ExamenAsignadoPK idExamenAsignado) {
        this.idExamenAsignado = idExamenAsignado;
    }
    
    /**
     * @return the idReactivo
     */
    public int getIdReactivo() {
        return idReactivo;
    }

    /**
     * @param idReactivo the idReactivo to set
     */
    public void setIdReactivo(int idReactivo) {
        this.idReactivo = idReactivo;
    }
    
    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 0;
        result += prime * idExamenAsignado.hashCode();
        result += prime * idReactivo;
        
        return result;
    }
 
    /** (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
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