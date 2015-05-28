/*
 * Copyright (C) 2015 Jesus Donaldo Osornio Hernández
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
package vista.controlador;

import org.apache.commons.lang3.StringUtils;

/**
 * Clase Validador que es utilizada para validar las cadenas de texto y
 * caracteres aceptados.
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class Validador {

    /**
     * Valida si el campo es un número entero positivo
     *
     * @param campo la cadena de texto a validar
     * @return false si el campo es negativo, decimal o demasiado grande, o si
     * en efecto no es un numero, true si ocurre lo contrario
     */
    public static boolean esNumero(String campo) {
        boolean ok = true;

        //Si es numero entero sin signo
        if (estaVacio(campo) || !StringUtils.isNumeric(campo)) {
            ok = false;
        }

        return ok;
    }

    /**
     * Valida que el campo no esté vacío, incluyendo espacios.
     *
     * @param campo la cadena a validar
     * @return false si el campo no está vacío, true si el campo está vacío
     */
    public static boolean estaVacio(String campo) {
        boolean ok = false;

        if (campo == null || campo.trim().isEmpty()) {
            ok = true;
        }

        return ok;
    }
}
