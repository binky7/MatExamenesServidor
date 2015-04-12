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
public class ClaveExamenPK implements Serializable {
    private int clave;
    private int idExamen;

    /**
     * @return the clave
     */
    public int getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(int clave) {
        this.clave = clave;
    }

    /**
     * @return the idExamen
     */
    public int getIdExamen() {
        return idExamen;
    }

    /**
     * @param idExamen the idExamen to set
     */
    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }
    
    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 0;
        result += prime * clave;
        result += prime * idExamen;
        
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
        if (!(obj instanceof ClaveExamenPK)) {
            return false;
        }
        ClaveExamenPK other = (ClaveExamenPK) obj;
        
        return (clave == other.getClave() && idExamen == other.getIdExamen());
    }
    
}