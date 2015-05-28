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
package remoteAccess;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Esta clase se encarga de crear un registro nuevo para el servicio de
 * persistencia de esta aplicación y escuchar en un ciclo infinito a
 * invocaciones remotas de distintos clientes
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class InicioServidor {

    //Crea el servidor sin alguna interfaz gráfica.
//    /**
//     * Método principal de la aplicación, crea el registro, agrega el objeto que
//     * implementará la interface remota y proveerá los servicios
//     *
//     * @param args arreglo de String para enviarse desde la línea de comandos
//     */
//    public static void main(String[] args) {
//
//        try {
//            //Crea el nuevo registro en el puerto 9000
//            Registry registro = LocateRegistry.createRegistry(9000);
//            //Agrega la etiqueta para identificar el servicio y crea un nuevo
//            //Objeto para proveer los métodos remotos
//            registro.rebind("MatExPersist", new AccesoDatos());
//        } catch (RemoteException ex) {
//            System.out.println(ex);
//        }
//    }
    /**
     * Crea el registro, agrega el objeto que implementará la interface remota y
     * proveerá los servicios
     *
     * @param puerto El puerto por el cual es servidor escuchara.
     */
    public void iniciarServidor(int puerto) {
        try {
            //Crea el nuevo registro en el puerto 9000
            Registry registro = LocateRegistry.createRegistry(puerto);
            //Agrega la etiqueta para identificar el servicio y crea un nuevo
            //Objeto para proveer los métodos remotos
            registro.rebind("MatExPersist", new AccesoDatos());
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }
}
