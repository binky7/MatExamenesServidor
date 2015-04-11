/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteAccess;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Jesus Donaldo
 */
public class InicioServidor {
    
    public static void main(String[] args) {

        try {
            Registry registro = LocateRegistry.createRegistry(9000);
            registro.rebind("MatExPersist", new AccesoDatos());
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }
}