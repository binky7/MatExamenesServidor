/*
 * Copyright (C) 2015 E. Iván Mariscal Martínez
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
 * Esta clase almacena los datos de la llave primaria de la entidad CursoTema.
 * Esto es el id del curso y el id del tema que forman la relación entre el
 * curso y el tema.
 *
 * @author E. Iván Mariscal Martínez
 * @version
 */
@Embeddable
public class CursoTemaPK implements Serializable {

    /**
     * El id del curso al que se asocia el tema.
     */
    private int idCurso;

    /**
     * El id del tema que se asocia al curso.
     */
    private int idTema;

    /**
     * Constructor por defecto. Crea una nueva instancia de CursoTemaPK sin
     * inicializar los atributos.
     */
    public CursoTemaPK() {

    }

    /**
     * Crea una nueva instancia de CursoTemaPK con el idCurso y el idTema
     * inicializados.
     *
     * @param idCurso el id del objeto CursoDTO.
     * @param idTema el id del objeto TemaDTO.
     */
    public CursoTemaPK(int idCurso, int idTema) {
        this.idCurso = idCurso;
        this.idTema = idTema;
    }

    /**
     * @return el id del curso al que se asocia el tema.
     */
    public int getIdCurso() {
        return idCurso;
    }

    /**
     * @param idCurso el id del curso a guardar.
     */
    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    /**
     * @return el id del tema que se asocia al curso.
     */
    public int getIdTema() {
        return idTema;
    }

    /**
     * @param idTema el id del tema a guardar.
     */
    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    /**
     * Este método sobreescribe el método hashCode para que el objeto pueda ser
     * comparable como una llave primaria única. Al ser una llave primaria
     * compuesta su identidad se determina con la combinación de todos los
     * atributos que compongan esta clave.
     *
     * @return el código hash que identifica únicamente a esta llave compuesta.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.idCurso;
        hash = 47 * hash + this.idTema;
        return hash;
    }

    /**
     * Este método sobreescribe el método equals para que el objeto pueda ser
     * comparable como una llave primaria única. Al ser una llave primaria
     * compuesta su identidad se determina con la combinación de todos los
     * atributos que compongan esta clave.
     *
     * @param obj el objeto que debe ser CursoTemaPK a comparar con esta
     * llave compuesta
     *
     * @return true si la llave compuesta ingresada es igual a esta llave
     * compuesta false de lo contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CursoTemaPK other = (CursoTemaPK) obj;
        if (this.idCurso != other.idCurso) {
            return false;
        }
        return this.idTema == other.idTema;
    }

}
