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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Esta clase entidad almacena los datos de una Clave de Examen específica
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
@Entity
@Table(name = "clave_examen")
public class ClaveExamenDTO implements Serializable {
    
    /**
     * El id de la clave del examen. Contiene el id del examen y el número de
     * clave
     */
    private ClaveExamenPK id;
    /**
     * El examen al que pertenece esta clave de examen. (El padre)
     */
    private ExamenDTO examen;
    /**
     * Los reactivos que contiene esta clave de examen
     */
    private List<ReactivoDTO> reactivos = new ArrayList<ReactivoDTO>();

    @AttributeOverrides({
        @AttributeOverride(name = "clave",
                column = @Column(name = "clave")),
        @AttributeOverride(name = "idExamen",
                column = @Column(name = "idExamen"))
    })
    @EmbeddedId
    /**
     * @return el id de la clave de examen
     */
    public ClaveExamenPK getId() {
        return id;
    }

    /**
     * @param id el id a guardar, debe contener el número de clave de esta
     * clave de examen
     */
    public void setId(ClaveExamenPK id) {
        this.id = id;
    }
    
    @MapsId("idExamen")
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "idExamen",
            referencedColumnName = "id",
            insertable = false,
            updatable = false,
            nullable = false
    )
    /**
     * @return el examen al que pertenece esta clave de examen
     */
    public ExamenDTO getExamen() {
        return examen;
    }

    /**
     * @param examen el examen a guardar (el padre de esta clave de examen)
     */
    public void setExamen(ExamenDTO examen) {
        this.examen = examen;
    }

    @OneToMany
    @JoinTable(
            name = "clave_examen_reactivo",
            joinColumns = {
                @JoinColumn(name = "clave"),
                @JoinColumn(name = "idExamen")
            },
            inverseJoinColumns = @JoinColumn(name = "idReactivo")
    )
    /**
     * @return los reactivos de esta clave de examen
     */
    public List<ReactivoDTO> getReactivos() {
        return reactivos;
    }

    /**
     * @param reactivos los reactivos a relacionar con esta clave de examen
     */
    public void setReactivos(List<ReactivoDTO> reactivos) {
        this.reactivos = reactivos;
    }
}