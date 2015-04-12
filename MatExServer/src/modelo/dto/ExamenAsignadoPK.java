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
public class ExamenAsignadoPK implements Serializable {

    private int idExamen;
    private int idAlumno;

    public ExamenAsignadoPK() {
    }

    public ExamenAsignadoPK(int idExamen, int idAlumno) {
        this.idExamen = idExamen;
        this.idAlumno = idAlumno;
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

    /**
     * @return the idAlumno
     */
    public int getIdAlumno() {
        return idAlumno;
    }

    /**
     * @param idAlumno the idAlumno to set
     */
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 0;
        result += prime * idExamen;
        result += prime * idAlumno;
        
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
        if (!(obj instanceof ExamenAsignadoPK)) {
            return false;
        }
        ExamenAsignadoPK other = (ExamenAsignadoPK) obj;
        
        return (idExamen == other.getIdExamen() 
                && idAlumno == other.getIdAlumno());
    }
}