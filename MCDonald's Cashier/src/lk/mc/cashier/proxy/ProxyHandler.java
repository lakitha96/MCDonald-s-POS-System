/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.cashier.proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.ServiceFactory.ServiceTypes;
import static lk.mc.common.service.ServiceFactory.ServiceTypes.CUSTOMER;
import static lk.mc.common.service.ServiceFactory.ServiceTypes.ITEM;
import lk.mc.common.service.SuperService;
import lk.mc.common.service.custom.CashierLoginService;
import lk.mc.common.service.custom.CashierService;
import lk.mc.common.service.custom.CustomerService;
import lk.mc.common.service.custom.ItemService;
import lk.mc.common.service.custom.PlaceOrderService;


/**
 *
 * @author lakitha
 */
public class ProxyHandler implements ServiceFactory{
    
    private static ProxyHandler proxyHandler;
    private ServiceFactory serviceFactory;
    private CustomerService customerService;
    private ItemService itemService;
    private CashierService cashierService;
    private PlaceOrderService placeOrderService;
    private CashierLoginService cashierLoginService;

    private ProxyHandler() {
        try {
            serviceFactory=(ServiceFactory) Naming.lookup("rmi://localhost:5050/mc");
            System.out.println("ProxyHandler");
            customerService=(CustomerService) serviceFactory.getService(ServiceTypes.CUSTOMER);
            itemService=(ItemService) serviceFactory.getService(ServiceTypes.ITEM);
            cashierService=(CashierService) serviceFactory.getService(ServiceTypes.CASHIER);
            placeOrderService=(PlaceOrderService) serviceFactory.getService(ServiceTypes.PLACEORDER);
            cashierLoginService=(CashierLoginService) serviceFactory.getService(ServiceTypes.CASHIERLOGIN);
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
                return customerService;
            case ITEM:
                return itemService;
            case CASHIER:
                return cashierService;
            case PLACEORDER:
                return placeOrderService;
            case CASHIERLOGIN:
                return cashierLoginService;
            default:
                return null;
        }
    }
    
}
