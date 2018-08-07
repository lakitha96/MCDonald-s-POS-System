/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service;

import java.rmi.Remote;

/**
 *
 * @author lakitha
 */
public interface ServiceFactory extends Remote{
    
    public enum ServiceTypes{
        CUSTOMER, ITEM, CASHIER, ADMINLOGIN, PLACEORDER, TEMPORDERDETAILS, CHEF, TELEOPERATOR, CHEFLOGIN, BAKEHOUSE, CASHIERLOGIN
    }
    
    public SuperService getService(ServiceTypes type) throws Exception;
}
