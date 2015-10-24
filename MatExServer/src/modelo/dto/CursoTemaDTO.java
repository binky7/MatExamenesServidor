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
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Esta clase entidad representa la relación entre un curso y un tema, donde se
 * almacena el número de bloque al cual pertenece cada tema asociado a un curso.
 * 
 * @author E. Iván Mariscal Martínez
 * @version
 */

@Entity
@Table(name = "curso_tema")
public class CursoTemaDTO implements Serializable {
    
    /**
     * El id de la entidad CursoTema. Contiene el id del curso y el id del tema.
     */
    private CursoTemaPK id = new CursoTemaPK();
    
    /**
     * El curso al que se asocian el tema.
     */
    private CursoDTO curso;
    
    /**
     * El tema que se asocia al curso.
     */
    private TemaDTO tema;
    
    /**
     * Número de bloque al cual pertenece el tema (del 1 al 5).
     */
    private int bloque;

    @AttributeOverrides({
        @AttributeOverride(name = "idCurso",
                column = @Column(name = "idCurso")),
        @AttributeOverride(name = "idTema",
                column = @Column(name = "idTema"))
    })
    @EmbeddedId
    /**
     * @return el id de la entidad CursoTema.
     */
    public CursoTemaPK getId() {
        return id;
    }

    /**
     * @param id el id a guardar de la entidad CursoTema.
     */
    public void setId(CursoTemaPK id) {
        this.id = id;
    }

    @MapsId("idCurso")
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "idCurso",
            referencedColumnName = "id",
            insertable = false,
            updatable = false,
            nullable = false
    )
    /**
     * @return el curso al que se asocia el tema.
     */
    public CursoDTO getCurso() {
        return curso;
    }

    /**
     * @param curso el curso a guardar.
     */
    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }

    @MapsId("idTema")
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "idTema",
            referencedColumnName = "id",
            insertable = false,
            updatable = false,
            nullable = false
    )
    /**
     * @return el tema asociado al curso.
     */
    public TemaDTO getTema() {
        return tema;
    }

    /**
     * @param tema el tema a guardar.
     */
    public void setTema(TemaDTO tema) {
        this.tema = tema;
    }

    @Column(name = "bloque", nullable = false, length = 11)
    /**
     * @return el número de bloque al que pertenece el tema.
     */
    public int getBloque() {
        return bloque;
    }

    /**
     * @param bloque el bloque a guardar.
     */
    public void setBloque(int bloque) {
        this.bloque = bloque;
    }
}
