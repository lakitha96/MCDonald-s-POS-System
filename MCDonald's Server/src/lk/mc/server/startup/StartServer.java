/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.startup;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.mc.server.service.impl.ServiceFactoryImpl;

/**
 *
 * @author lakitha
 */
public class StartServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registry=LocateRegistry.createRegistry(5050);
            registry.rebind("mc", ServiceFactoryImpl.getInstance());
            System.out.println(ServiceFactoryImpl.getInstance());
            System.out.println("MCDonald's Server has been stared sucessfully...");
        } catch (RemoteException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
