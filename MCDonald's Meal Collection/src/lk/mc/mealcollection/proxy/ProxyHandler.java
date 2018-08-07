/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.mealcollection.proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.SuperService;
import lk.mc.common.service.custom.ItemService;

/**
 *
 * @author lakitha
 */
public class ProxyHandler implements ServiceFactory{
    
    private static ProxyHandler proxyHandler;
    private ServiceFactory serviceFactory;
    private ItemService itemService;

    private ProxyHandler() {
        try {
            serviceFactory=(ServiceFactory) Naming.lookup("rmi://localhost:5050/mc");
            System.out.println("ProxyHandler");
            itemService=(ItemService) serviceFactory.getService(ServiceTypes.ITEM);
            
        } catch (NotBoundException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static ProxyHandler getInstance(){
        if(proxyHandler==null){
            proxyHandler=new ProxyHandler();
            System.out.println("p.getInstance()");
        }
        return proxyHandler;
    }

    @Override
    public SuperService getService(ServiceTypes type) throws Exception {
        switch(type){
            case CUSTOMER:
                return null;
            case ITEM:
                return itemService;
            default:
                return null;
        }
    }
    
}
