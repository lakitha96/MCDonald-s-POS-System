/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.repository;

import lk.mc.server.entity.Chef;
import lk.mc.server.repository.custom.CashierRepository;
import lk.mc.server.repository.custom.OrderDetailsRepository;
import lk.mc.server.repository.custom.impl.AdminLoginRepositoryImpl;
import lk.mc.server.repository.custom.impl.BakeHouseRepositoryImpl;
import lk.mc.server.repository.custom.impl.CashierLoginRepositoryImpl;
import lk.mc.server.repository.custom.impl.CashierRepositoryImpl;
import lk.mc.server.repository.custom.impl.ChefLoginRepositoryImpl;
import lk.mc.server.repository.custom.impl.ChefRepositoryImpl;
import lk.mc.server.repository.custom.impl.CustomerRepositoryImpl;
import lk.mc.server.repository.custom.impl.ItemRepositoryImpl;
import lk.mc.server.repository.custom.impl.OrderDetailsRepositoryImpl;
import lk.mc.server.repository.custom.impl.OrderRepositoryImpl;
import lk.mc.server.repository.custom.impl.TelephoneOperatorRepositoryImpl;
import lk.mc.server.repository.custom.impl.TempOrderDetailsRepositoryImpl;

/**
 *
 * @author lakitha
 */
public class RepositoryFactory {
    
    public enum RepositoryTypes{
        CUSTOMER, ITEM, CASHIER, ADMINLOGIN, ORDER, ORDERDETAILS, TEMPORDERDETAILS, CHEF, TELEPHONEOPERATOR, CHEFLOGIN, BAKEHOUSE, CASHIERLOGIN
    }
    
    public static RepositoryFactory repositoryFactory;

    public RepositoryFactory() {
    }
    
    public static RepositoryFactory getInstance(){
        if(repositoryFactory==null){
            repositoryFactory=new RepositoryFactory();
        }
        return repositoryFactory;
    }
    
    public SuperRepository getRepository(RepositoryTypes repositoryTypes){
        switch(repositoryTypes){
            case CUSTOMER:
                return new CustomerRepositoryImpl();
            case ITEM:
                return new ItemRepositoryImpl();
            case CASHIER:
                return new CashierRepositoryImpl();
            case ADMINLOGIN:
                return new AdminLoginRepositoryImpl();
            case ORDER: 
                return new OrderRepositoryImpl();
            case ORDERDETAILS: 
                return new OrderDetailsRepositoryImpl();
            case TEMPORDERDETAILS:
                return new TempOrderDetailsRepositoryImpl();
            case CHEF:
                return new ChefRepositoryImpl();
            case TELEPHONEOPERATOR:
                return new TelephoneOperatorRepositoryImpl();
            case CHEFLOGIN:
                return new ChefLoginRepositoryImpl();
            case BAKEHOUSE:
                return new BakeHouseRepositoryImpl();
            case CASHIERLOGIN:
                return new CashierLoginRepositoryImpl();
            default:
                return null;
        }
    }
    
}
