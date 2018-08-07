/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.SuperService;
import lk.mc.common.service.custom.AdminLoginService;
import lk.mc.common.service.custom.CashierLoginService;
import lk.mc.common.service.custom.CashierService;
import lk.mc.common.service.custom.ChefLoginService;
import lk.mc.server.business.custom.impl.TelephoneOperatorBOImpl;
import lk.mc.server.service.custom.impl.AdminLoginServiceImpl;
import lk.mc.server.service.custom.impl.BakeHouseServiceImpl;
import lk.mc.server.service.custom.impl.CashierLoginServiceImpl;
import lk.mc.server.service.custom.impl.CashierServiceImpl;
import lk.mc.server.service.custom.impl.ChefLoginServiceImpl;
import lk.mc.server.service.custom.impl.ChefServiceServiceImpl;
import lk.mc.server.service.custom.impl.CustomerServiceImpl;
import lk.mc.server.service.custom.impl.ItemServiceImpl;
import lk.mc.server.service.custom.impl.PlaceOrderServiceImpl;
import lk.mc.server.service.custom.impl.TelephoneOperatorServiceImpl;
import lk.mc.server.service.custom.impl.TempOrderDetailsServiceImpl;

/**
 *
 * @author lakitha
 */
public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory{
    
    public static ServiceFactory serviceFactory;

    public ServiceFactoryImpl() throws RemoteException{
        
    }
    
    
    public static ServiceFactory getInstance() throws RemoteException{
        if(serviceFactory==null){
            serviceFactory=new ServiceFactoryImpl();
        }
        return serviceFactory;
    }
    
    @Override
    public SuperService getService(ServiceTypes type) throws Exception {
        switch(type){
            case CUSTOMER:
                return new CustomerServiceImpl();
            case ITEM:
                return new ItemServiceImpl();
            case CASHIER: 
                return new CashierServiceImpl();
            case ADMINLOGIN:
                return new AdminLoginServiceImpl();
            case PLACEORDER:
                return new PlaceOrderServiceImpl();
            case CHEF:
                return new ChefServiceServiceImpl();
            case CHEFLOGIN:
                return new ChefLoginServiceImpl();
            case TEMPORDERDETAILS:
                return new TempOrderDetailsServiceImpl();
            case BAKEHOUSE:
                return new BakeHouseServiceImpl();
            case CASHIERLOGIN:
                return new CashierLoginServiceImpl();
            case TELEOPERATOR:
                return new TelephoneOperatorServiceImpl();
            default:
                return null;
        }
    }
    
}
