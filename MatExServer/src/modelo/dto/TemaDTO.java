/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "tema")
public class TemaDTO implements Serializable, Comparable<TemaDTO> {
    
    private int id;
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false, unique = true, length = 150)
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     @Override
    public boolean equals(Object o) {
        if (!(o instanceof TemaDTO))
            return false;
        TemaDTO t = (TemaDTO) o;
        
        return t.nombre.equals(nombre);
    }

    @Override
    public int hashCode() {
        return 37 * nombre.hashCode();
    }
    
    /**
     * Método necesario para poder utilizar la búsqueda binaria de Collections,
     * se debe regresar un valor entero para poder ser ordenado, Debe involucrar
     * los mismos atributos que aparezcan en equals y hashCode, y estos
     * atributos deben identificar a la entidad como única, ademas de que sea
     * un atributo fácilmente obtenible de la vista, por eso se escogió el
     * nombre del tema en vez del id, ya que en la vista se tendrán los nombres,
     * no los ids, de todas formas el nombre también identifica a la entidad
     * @param o
     * @return 
     */
    @Override
    public int compareTo(TemaDTO o) {
        return nombre.compareTo(o.nombre);
    }
}